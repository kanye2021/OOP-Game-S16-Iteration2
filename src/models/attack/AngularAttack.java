package models.attack;

import models.Attackion;
import models.entities.Entity;
import models.map.Map;
import models.map.Tile;

import java.awt.*;
import java.util.*;
import java.util.Queue;

/**
 * Created by ben on 3/8/16.
 */
public class AngularAttack extends Attackion {
    //private Map map;
    private Projectile projectile;

    public AngularAttack(Entity entity, Projectile projectile) {
        this.entity = entity;
        //If you cannot attack
        if (!canAttack(entity)) {
            return;
        }
        this.origin = entity.getLocation();
        this.damage = projectile.damage;
        this.range = projectile.range;
        this.orientation = entity.getOrientation();
        this.map = entity.getMap();
        findBreadthFirstTile();
        this.projectile = projectile;
        launchAttack();
    }

    public void launchAttack() {
        new Thread(new Runnable() {
            public void run() {
                findBreadthFirstTile(); // Launches attack
            }
        }).start();
    }


    public void findBreadthFirstTile() {
        Queue<PointNode> pointQueue = new LinkedList<>();
        HashMap<Point, PointNode> hitTiles = new HashMap<>();
        Point firstPoint = orientation.neighbor(origin);
        Tile originTile = map.getTileAt(firstPoint);
        PointNode root = new PointNode(originTile, firstPoint, 1);
        pointQueue.add(root);

        int currentDistance = 1;
        HashMap<Point, PointNode> projectilesOnMap = new HashMap<>();

        while (!pointQueue.isEmpty()) {
            PointNode current = pointQueue.poll();
            Point attackPoint = new Point(current.target);
            Tile desiredTile = map.getTileAt(attackPoint);

            if (desiredTile != null && !hitTiles.containsKey(attackPoint)) {

                // Mark this tile as hit.
                hitTiles.put(new Point(attackPoint), current);

                if (desiredTile.hasEntity() && entity != desiredTile.getEntity()) {
                    Entity target = desiredTile.getEntity();
                    target.takeDamage(-damage);
                    //System.out.println("Has Entity yo");
                }

                for (PointNode pointNode : getAdjacentTiles(current, orientation)) {
                    if (!hitTiles.containsKey(pointNode.target)) {

                        pointNode.range += 1;
                        pointQueue.offer(pointNode);
                    }
                }


                if (current.range == currentDistance) {
                    map.insertProjectileAtPoint(projectile, attackPoint);
                    projectilesOnMap.put(new Point(attackPoint), current);
                } else {
                    currentDistance = current.range;

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (Iterator iterator = projectilesOnMap.keySet().iterator(); iterator.hasNext(); ) {
                        Point location = (Point) iterator.next();

                        map.removeProjectileAtPoint(location);
                        iterator.remove();
                    }

                    map.insertProjectileAtPoint(projectile, attackPoint);
                    projectilesOnMap.put(new Point(attackPoint), current);
                }

                if (current.range > range) {

                    // Clear out any projectiles that are left on themap.
                    for (Iterator iterator = projectilesOnMap.keySet().iterator(); iterator.hasNext(); ) {
                        Point location = (Point) iterator.next();

                        map.removeProjectileAtPoint(location);
                        iterator.remove();
                    }
                    return;
                }
            }
        }
    }

    private ArrayList<PointNode> getAdjacentTiles(PointNode pointNode, Map.Direction orientation) {
        ArrayList<PointNode> adjacentTiles = new ArrayList<>();
        if (orientation == Map.Direction.NORTH) {

            // Get the tile adjacent to the north.
            Point northLogicalPoint = new Point(pointNode.target); // Get the tiles logical point
            northLogicalPoint.translate(0, -1);

            Tile northTile = map.getTileAt(northLogicalPoint);
            if (northTile != null) {
                adjacentTiles.add(new PointNode(northTile, northLogicalPoint, pointNode.range));
            }

            if (pointNode.range % 2 != 0) {//if range is odd
                // Get the tile to the north west of the current position.
                Point northWestLogicalPoint = new Point(pointNode.target);
                northWestLogicalPoint.translate(-1, 0);

                Tile northWestTile = map.getTileAt(northWestLogicalPoint);
                if (northWestTile != null) {
                    adjacentTiles.add(new PointNode(northWestTile, northWestLogicalPoint, pointNode.range));
                }


                // Get the tile to the north east of the current position.
                Point northEastLogicaPoint = new Point(pointNode.target);
                northEastLogicaPoint.translate(1, -1);

                Tile northEastTile = map.getTileAt(northEastLogicaPoint);
                if (northEastTile != null) {
                    adjacentTiles.add(new PointNode(northEastTile, northEastLogicaPoint, pointNode.range));
                }
            }
        } else if (orientation == Map.Direction.NORTH_EAST) {

            // Get the tile to the north east of the current position.
            Point northEastLogicaPoint = new Point(pointNode.target);
            northEastLogicaPoint.translate(1, -1);

            Tile northEastTile = map.getTileAt(northEastLogicaPoint);
            if (northEastTile != null) {
                adjacentTiles.add(new PointNode(northEastTile, northEastLogicaPoint, pointNode.range));
            }


            if (pointNode.range % 2 != 0) {
                // Get the tile adjacent to the north.
                Point northLogicalPoint = new Point(pointNode.target); // Get the tiles logical point
                northLogicalPoint.translate(0, -1);


                Tile northTile = map.getTileAt(northLogicalPoint);
                if (northTile != null) {
                    adjacentTiles.add(new PointNode(northTile, northLogicalPoint, pointNode.range));
                }
                // Get the tile to the south east of the current position.
                Point southEastLogicalPoint = new Point(pointNode.target);
                southEastLogicalPoint.translate(1, 0);
                Tile southEastTile = map.getTileAt(southEastLogicalPoint);
                if (southEastTile != null) {
                    adjacentTiles.add(new PointNode(southEastTile, southEastLogicalPoint, pointNode.range));
                }
            }
        } else if (orientation == Map.Direction.SOUTH_EAST) {
            // Get the tile to the south east of the current position.
            Point southEastLogicalPoint = new Point(pointNode.target);
            southEastLogicalPoint.translate(1, 0);

            Tile southEastTile = map.getTileAt(southEastLogicalPoint);
            if (southEastTile != null) {
                adjacentTiles.add(new PointNode(southEastTile, southEastLogicalPoint, pointNode.range));
            }
            if (pointNode.range % 2 != 0) {
                // Get the tile to the north east of the current position.
                Point northEastLogicaPoint = new Point(pointNode.target);
                northEastLogicaPoint.translate(1, -1);

                Tile northEastTile = map.getTileAt(northEastLogicaPoint);
                if (northEastTile != null) {
                    adjacentTiles.add(new PointNode(northEastTile, northEastLogicaPoint, pointNode.range));
                }

                // Get the tile to the south of the current position.
                Point southLogicalPoint = new Point(pointNode.target);
                southLogicalPoint.translate(0, 1);


                Tile southTile = map.getTileAt(southLogicalPoint);
                if (southTile != null) {
                    adjacentTiles.add(new PointNode(southTile, southLogicalPoint, pointNode.range));
                }
            }
        } else if (orientation == Map.Direction.SOUTH) {
            // Get the tile to the south of the current position.
            Point southLogicalPoint = new Point(pointNode.target);
            southLogicalPoint.translate(0, 1);

            Tile southTile = map.getTileAt(southLogicalPoint);
            if (southTile != null) {
                adjacentTiles.add(new PointNode(southTile, southLogicalPoint, pointNode.range));
            }


            if (pointNode.range % 2 != 0) {
                // Get the tile to the south east of the current position.
                Point southEastLogicalPoint = new Point(pointNode.target);
                southEastLogicalPoint.translate(1, 0);

                Tile southEastTile = map.getTileAt(southEastLogicalPoint);
                if (southEastTile != null) {
                    adjacentTiles.add(new PointNode(southEastTile, southEastLogicalPoint, pointNode.range));
                }


                // Get the tile to the south west of the current position.
                Point southWestLogicalPoint = new Point(pointNode.target);
                southWestLogicalPoint.translate(-1, 1);

                Tile southWestTile = map.getTileAt(southWestLogicalPoint);
                if (southWestTile != null) {
                    adjacentTiles.add(new PointNode(southWestTile, southWestLogicalPoint, pointNode.range));
                }
            }
        } else if (orientation == Map.Direction.SOUTH_WEST) {
            // Get the tile to the south west of the current position.
            Point southWestLogicalPoint = new Point(pointNode.target);
            southWestLogicalPoint.translate(-1, 1);

            Tile southWestTile = map.getTileAt(southWestLogicalPoint);
            if (southWestTile != null) {
                adjacentTiles.add(new PointNode(southWestTile, southWestLogicalPoint, pointNode.range));
            }
            if (pointNode.range % 2 != 0) {
                // Get the tile to the south of the current position.
                Point southLogicalPoint = new Point(pointNode.target);
                southLogicalPoint.translate(0, 1);


                Tile southTile = map.getTileAt(southLogicalPoint);
                if (southTile != null) {
                    adjacentTiles.add(new PointNode(southTile, southLogicalPoint, pointNode.range));
                }

                // Get the tile to the north west of the current position.
                Point northWestLogicalPoint = new Point(pointNode.target);
                northWestLogicalPoint.translate(-1, 0);

                Tile northWestTile = map.getTileAt(northWestLogicalPoint);
                if (northWestTile != null) {
                    adjacentTiles.add(new PointNode(northWestTile, northWestLogicalPoint, pointNode.range));
                }
            }
        } else if (orientation == Map.Direction.NORTH_WEST) {
            // Get the tile to the north west of the current position.
            Point northWestLogicalPoint = new Point(pointNode.target);
            northWestLogicalPoint.translate(-1, 0);

            Tile northWestTile = map.getTileAt(northWestLogicalPoint);
            if (northWestTile != null) {
                adjacentTiles.add(new PointNode(northWestTile, northWestLogicalPoint, pointNode.range));
            }
            if (pointNode.range % 2 != 0) {
                //SW Tile
                Point southWestLogicalPoint = new Point(pointNode.target);
                southWestLogicalPoint.translate(-1, 1);

                Tile southWestTile = map.getTileAt(southWestLogicalPoint);
                if (southWestTile != null) {
                    adjacentTiles.add(new PointNode(southWestTile, southWestLogicalPoint, pointNode.range));
                }
                // Get the tile adjacent to the north.
                Point northLogicalPoint = new Point(pointNode.target); // Get the tiles logical point
                northLogicalPoint.translate(0, -1);


                Tile northTile = map.getTileAt(northLogicalPoint);
                if (northTile != null) {
                    adjacentTiles.add(new PointNode(northTile, northLogicalPoint, pointNode.range));
                }
            }
        } else {
            System.err.println("AngularAttack: HOW DID YOU GET HERE");
        }


        return adjacentTiles;
    }

    @Override
    public void calculateDamage() {

    }

    class PointNode {
        public Point target;
        public Tile tile;
        public int range;

        public PointNode(Tile tile, Point point, int range) {
            target = new Point();
            this.target.x = point.x;
            this.target.y = point.y;
            this.tile = tile;
            this.range = range;
            //this.range = -1;
        }
    }
}
