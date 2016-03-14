package AI.Memory;

import models.entities.Entity;
import models.items.Item;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Bradley on 3/5/16.
 */
public class VisualInformation {

    private HashMap<Entity, Point> entities = new HashMap<>();
    private HashMap<Item, Point> items = new HashMap<>();
    private HashMap<Class<?>, setter> hashes = new HashMap<>();

    public VisualInformation() {

        hashes.put(Entity.class, (o, p) -> entities.put((Entity) o, p));
        hashes.put(Item.class, (o, p) -> items.put((Item) o, p));

    }

    public void addVisualInput(Object object, Point point) {

        for (java.util.AbstractMap.Entry<Class<?>, setter> pair : hashes.entrySet()) {

            if (object != null) {

                if (pair.getKey().isAssignableFrom(object.getClass())) {

                    pair.getValue().set(object, point);
                    return;

                }

            }

        }

    }

    public HashMap<Entity, Point> getEntities() {
        return entities;
    }

    public HashMap<Item, Point> getItems() {
        return items;
    }

    public boolean foundEntities() {
        return !entities.isEmpty();
    }

    public boolean foundItems() {
        return !items.isEmpty();
    }

    public void forget() {

        entities.clear();
        items.clear();

    }

    private interface setter {

        void set(Object o, Point p);

    }

}
