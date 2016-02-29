package views;

import models.stats.Stats;

import java.awt.*;

/**
 * Created by Bradley on 2/27/16.
 */
public class StatusViewport extends View {

    private Stats stats;
    private int viewportWidth;
    private int viewportHeight;

    public StatusViewport(int width, int height, Display display, Stats stats) {
        super(width, height ,display);
        this.stats = stats;
        scaleView();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, getScreenHeight() * 4 / 5, viewportWidth, viewportHeight);
    }

    @Override
    public void scaleView() {
        viewportHeight = getScreenHeight() * 1/5;
        viewportWidth = getScreenWidth();
    }
}