package views;


import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 2/29/16.
 */
public class ToastView extends View {

    private int toastWidth;
    private int toastHeight;
    private int toastY;
    private int fontSize;
    private Font toastFont;
    private String toastText;

    public ToastView(int width, int height, Display display, String toastText) {
        super(width, height, display);
        this.toastText = toastText;
        scaleView();
    }

    @Override
    public void render(Graphics g) {


        // Text sizes
        FontMetrics fm = g.getFontMetrics(toastFont);
        Rectangle2D r1 = fm.getStringBounds(toastText, g);
        int msgWidth = (int) r1.getWidth();
        int msgHeight = (int) r1.getHeight();

        toastHeight = msgHeight * 2;
        toastWidth = (int) (msgWidth * 1.5);


        // Toast blurb
        // Color == Dark Gray with an alpha value.
        g.setColor(new Color(64, 64, 64, 200));
        int x_pos = (getScreenWidth() - toastWidth) / 2;
        g.fillRoundRect(x_pos, toastY, toastWidth, toastHeight, 50, 50);


        // draw toast
        g.setColor(Color.WHITE);
        g.setFont(toastFont);
        int msg_x = x_pos + (toastWidth - msgWidth) / 2;
        int msg_y = toastY + (toastHeight - msgHeight) / 2 + fm.getAscent();
        g.drawString(toastText, msg_x, msg_y);
    }

    @Override
    public void scaleView() {
        toastHeight = getScreenHeight() * 1 / 28;
        toastWidth = getScreenWidth() * 1 / 4;
        fontSize = getScreenHeight() * 1 / 50;
        toastY = (int) (getScreenHeight() * .80);

        toastFont = new Font("Courier New", Font.PLAIN, fontSize);

    }

    public String getToastText() {
        return toastText;
    }

    public void setToastText(String toastText) {
        this.toastText = toastText;
    }
}