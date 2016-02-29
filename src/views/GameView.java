package views;

import models.entities.Avatar;
import models.map.Map;
import models.stats.Stats;

import java.awt.*;

/**
 * Created by Bradley on 2/26/2016.
 */
public class GameView extends View {

    private AreaViewport areaViewport;
    private StatusViewport statusViewport;

    public GameView(int width, int height, Display display){
        super(width, height, display);

    }

    public void initAreaViewport(Map map, Avatar avatar){
        this.areaViewport = new AreaViewport(getScreenWidth(), getScreenHeight(), getDisplay(), map, avatar);
    }

    public void initStatusViewport(Stats stats){
        this.statusViewport = new StatusViewport(getScreenWidth(), getScreenHeight(), getDisplay(), stats);
    }

    @Override
    public void render(Graphics g) {
        if(areaViewport!=null && statusViewport!= null){
            areaViewport.render(g);
            statusViewport.render(g);
        }
    }

    @Override
    public void scaleView() {
        if(areaViewport!=null && statusViewport!= null){
            areaViewport.scaleView(getScreenWidth(), getScreenHeight());
            statusViewport.scaleView(getScreenWidth(), getScreenHeight());
        }
    }
}
