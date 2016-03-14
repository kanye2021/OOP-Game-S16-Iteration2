package views;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class Display extends JPanel {

    private View activeView;

    public Display(int width, int height) {
        initDisplay(width, height);
    }

    private void initDisplay(int width, int height) {
        // Init JPanel stuff
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));
    }

    public void setActiveView(View view) {
        activeView = view;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        activeView.render(g);
    }
}