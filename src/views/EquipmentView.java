package views;

import models.Equipment;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import utilities.IOUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 3/4/16.
 */
public class EquipmentView extends View {


    // Model
    private Equipment equipment;

    // Colors
    private Color secondary;
    private Color primary;
    private Color rlySmallColor;

    // Fonts
    private Font rlySmall;
    private Font small;
    private Font title;
    private Font desc;
    private Font infoTitle;

    // Stuff
    private String takeableItemRootFilepath;
    private int selectedIndex;

    // Strings
    private final String TITLE = "Equipped Items";
    private final String DESCRIPTION = "Press [ENTER] on your selected item to unequip";

    // Panel sizes n shit
    private int rect_w;
    private int rect_h;
    private int top_pane_h;
    private int rect_xy_offset_top;
    private int rect_x_offset;
    private int rect_y_offset;
    private int info_pane_x;
    private int info_pane_y;
    private int info_pane_w;
    private int info_pane_h;

    // Slot sizes
    private int item_slot;
    private int img_size;



    public EquipmentView(int width, int height, Display display){
        super(width, height, display);
        takeableItemRootFilepath = IOUtilities.getFileSystemDependentPath("./src/res/items/takeable/");
        selectedIndex = 0;

        secondary = (Color.red);
        primary = (Color.lightGray);
        rlySmallColor = Color.white;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    @Override
    public void render(Graphics g) {

        // Draw Top Pane
        g.setColor(new Color(32, 32, 32));
        g.drawRoundRect(rect_xy_offset_top, rect_xy_offset_top, rect_w, top_pane_h, 5, 5);
        g.fillRoundRect(rect_xy_offset_top, rect_xy_offset_top, rect_w, top_pane_h, 5, 5);

        // Draw Main Pane
        g.drawRoundRect(rect_x_offset, rect_y_offset, rect_w, rect_h, 5, 5);
        g.fillRoundRect(rect_x_offset, rect_y_offset, rect_w, rect_h, 5, 5);

        // Draw title
        g.setColor(primary);
        FontMetrics fm = g.getFontMetrics(title);
        g.setFont(title);
        Rectangle2D rec = fm.getStringBounds(TITLE, g);
        int titleY = rect_xy_offset_top + ((int) (rec.getHeight()) + fm.getAscent());
        int titleX = rect_xy_offset_top + rect_w / 2 - (int) rec.getWidth() / 2;
        g.drawString(TITLE, titleX, titleY);

        // Draw Description text
        fm = g.getFontMetrics(desc);
        g.setFont(desc);
        rec = fm.getStringBounds(DESCRIPTION, g);
        int descY = (titleY + ((int) (rec.getHeight()) + fm.getAscent()));
        int descX = rect_xy_offset_top + rect_w / 2 - (int) rec.getWidth() / 2;
        g.drawString(DESCRIPTION, descX, descY);

        // Draw slots + equipped item images
        renderSlots(g);


    }

    private void renderSlots(Graphics g) {

        //
        int xFirstCol = rect_x_offset + 1 * rect_w / 4 - item_slot / 2;
        int xSecondCol = rect_x_offset + 2 * rect_w / 4 - item_slot / 2;
        int xThirdCol = rect_x_offset + 3 * rect_w / 4 - item_slot / 2;
        int yFirstRow = rect_y_offset + rect_h / 6 - item_slot / 2;
        int ySecondRow = rect_y_offset + 3 * rect_h / 6 - item_slot / 2;
        int yThirdRow = rect_y_offset + 5 * rect_h / 6 - item_slot / 2;

        Rectangle2D rec;
        int x;
        int y;
        int colCount = 1;
        Image i;
        ImageIcon ii;
        for (Equipment.Location location : Equipment.Location.values()) {
            if (location.ordinal() < 3) {
                y = yFirstRow;
            } else if (location.ordinal() >= 3 && location.ordinal() < 6) {
                y = ySecondRow;
            } else {
                y = yThirdRow;
            }
            if (colCount % 3 == 1) {
                x = xFirstCol;
            } else if (colCount % 3 == 2) {
                x = xSecondCol;
            } else {
                x = xThirdCol;
            }
            colCount++;
            // Draw slots
            g.fillRect(x, y, item_slot, item_slot);
            if (selectedIndex == location.ordinal()) {
                g.setColor(secondary);
                g.drawRect(x - 3, y - 3, item_slot + 6, item_slot + 6);
                g.setColor(primary);

            }
            // Get image path for appropiate slot if item is equipped there
            boolean hasItemAtSlot = equipment.isEquipmentAtLocation(location);
            Image itemImage;
            if (hasItemAtSlot) {
                EquippableItem item = equipment.getEquipmentLocation(location);
                itemImage = item.getImage();

                // Draw the name of the item underneath it rly smallly
                String itemName = item.getName();
                g.setColor(Color.BLACK);
                FontMetrics fm = g.getFontMetrics(rlySmall);
                g.setFont(rlySmall);
                rec = fm.getStringBounds(itemName, g);
                g.drawString(itemName, x + (item_slot - (int) rec.getWidth()) / 2, y + item_slot - ((int) (rec.getHeight()) / 2));

                if (selectedIndex == location.ordinal()) {
                    drawInfoPane(g, item);
                }
            }
            else {
                // No item equipped for this slot
                // Render a place holder image
                ii = new ImageIcon("./src/res/items/takeable/placeholder.png");
                itemImage = ii.getImage();
            }
            // Draw item image
            g.drawImage(itemImage, x + (item_slot - img_size) / 2, y + (item_slot - img_size) / 2, img_size, img_size, null);

            g.setColor(primary);
            FontMetrics fm = g.getFontMetrics(small);
            g.setFont(small);
            // Draw equipment slot text
            rec = fm.getStringBounds(location.getDescriptor(), g);
            // If the text contains more than 5 letters (e.g. "primary weapon") we want to render it differently
            if (location.getDescriptor().length() > 5) {
                g.drawString(location.getDescriptor(), x, y + item_slot + ((int) (rec.getHeight()) + fm.getAscent()));
            } else
                g.drawString(location.getDescriptor(), x + (int) rec.getWidth() / 2, y + item_slot + ((int) (rec.getHeight()) + fm.getAscent()));
        }

    }

    private void drawInfoPane(Graphics g, EquippableItem item) {
        Rectangle2D rec;
        FontMetrics fm;
        int h = 0;
        int w = 0;

        // Draw Top Pane
        g.setColor(new Color(32, 32, 32));
        g.drawRoundRect(info_pane_x, rect_xy_offset_top, info_pane_w, top_pane_h, 5, 5);
        g.fillRoundRect(info_pane_x, rect_xy_offset_top, info_pane_w, top_pane_h, 5, 5);

        // Draw box
        g.fillRect(info_pane_x, info_pane_y, info_pane_w, info_pane_h);

        // Draw Title
        String infoString = item.getName();
        g.setFont(infoTitle);
        g.setColor(primary);
        fm = g.getFontMetrics(infoTitle);
        if (infoString.length() > 10) {
            // Split into 2 strings
            String[] split = infoString.split("\\s+");
            String infoString1 = split[0];
            String infoString2 = split[1];

            // Draw both
            rec = fm.getStringBounds(infoString1, g);
            int w1 = (int) rec.getWidth();
            int h1 = (int) rec.getHeight();
            rec = fm.getStringBounds(infoString2, g);
            int w2 = (int) rec.getWidth();
            int h2 = (int) rec.getHeight();
            int y1 = info_pane_y - (h1 + h2 + fm.getAscent());
            int y2 = info_pane_y - (h2 + fm.getAscent());
            g.drawString(infoString1, info_pane_x + (info_pane_w - w1)/2, y1);
            g.drawString(infoString2, info_pane_x + (info_pane_w - w2)/2, y2);

        } else {
            // Else, only 1 line.
            rec = fm.getStringBounds(infoString, g);
            w = (int) rec.getWidth();
            h = (int) rec.getHeight();
            g.drawString(infoString, info_pane_x + (info_pane_w - w) / 2, info_pane_y - (h + fm.getAscent()));
        }

        // Draw gray line too
        g.setColor(Color.lightGray);
        int line_w = info_pane_w/15;
        g.fillRect(info_pane_x - line_w/2, rect_xy_offset_top, line_w , info_pane_h + top_pane_h);

        int statModsX = info_pane_x + line_w;
        int initialY,  statModsY;
        initialY = statModsY= info_pane_y;

        // Draw Stat Mods toStrings first
        g.setColor(Color.RED);
        g.setFont(small);
        fm = g.getFontMetrics(small);
        statModsY = initialY;
        g.setColor(Color.RED);
        for (StatModification s : item.getOnEquipModifications().getModifications()) {
            String statModString  = s.toString();
            rec = fm.getStringBounds(statModString, g);
            w = (int) rec.getWidth();
            h = (int) rec.getHeight();
            g.drawString(statModString, statModsX, statModsY - fm.getAscent());
            statModsY += h + fm.getAscent();
        }

    }

    @Override
    public void scaleView() {
        small = new Font("Courier New", 1, getScreenWidth()/100);
        rlySmall = new Font("Courier New", Font.BOLD, getScreenWidth()/120);
        desc = new Font("Courier New", 1, getScreenWidth()/86);
        infoTitle = new Font("Courier New", Font.BOLD, getScreenWidth()/50);
        title = new Font("Courier New", Font.BOLD, getScreenWidth()/38);



        // Panel sizes
        rect_w = getScreenWidth() / 3;
        rect_h = getScreenHeight() / 2;
        top_pane_h = getScreenHeight() / 8;
        rect_xy_offset_top = (int) (((double) getScreenWidth()/24) * 1.5);
        rect_x_offset = (int) (((double) getScreenWidth()/24) * 1.5);
        rect_y_offset = (int) (((double) getScreenWidth()/24) * 1.5) + top_pane_h;
        info_pane_x = rect_x_offset + rect_w;
        info_pane_y = rect_y_offset;
        info_pane_w = rect_w/2;
        info_pane_h = rect_h;

        // Slot sizes
        item_slot = (int) (((double) getScreenWidth()/24) * 1.5);
        img_size = (int) ((double) item_slot * .80);
    }
}
