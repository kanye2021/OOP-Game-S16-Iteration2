package views;


import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 2/29/16.
 */
public class ToastView extends View {

    private int toastWidth;
    private int toastHeight;
    private int fontSize;
    private Font toastFont;
    private String toastText;

    public ToastView(int width, int height, Display display, String toastText) {
        super(width, height ,display);
        this.toastText = toastText;
        scaleView();
    }

    @Override
    public void render(Graphics g) {

        // Toast blurb
        // Color == Dark Gray with an alpha value.
        g.setColor(new Color(64, 64, 64, 200));
        int x_pos = (getScreenWidth() - toastWidth)/2;
        int y_pos = (getScreenHeight() - toastHeight)/2;
        g.fillRoundRect(x_pos, y_pos , toastWidth, toastHeight, 50, 50);

        // Text
        g.setColor(Color.WHITE);
        g.setFont(toastFont);
        FontMetrics fm = g.getFontMetrics(toastFont);
        Rectangle2D r1 = fm.getStringBounds(toastText, g);
        int msg_x = x_pos + (toastWidth - (int)r1.getWidth())/2;
        int msg_y = y_pos + (toastHeight - (int) r1.getHeight())/2 + fm.getAscent();
        g.drawString(toastText, msg_x, msg_y);
    }

    @Override
    public void scaleView() {
        toastHeight = getScreenHeight() * 1/28;
        toastWidth = getScreenWidth() * 1/4;
        fontSize = getScreenHeight() * 1/68;
        toastFont = new Font("Helvetica", Font.PLAIN, fontSize);

    }

    public String getToastText() {
        return toastText;
    }

    public void setToastText(String toastText) {
        this.toastText = toastText;
    }
}