package views;

import models.Inventory;
import models.items.takeable.TakeableItem;
import utilities.IOUtilities;
import views.sprites.Sprite;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by sergiopuleri on 3/2/16.
 */
public class InventoryView extends View {

    private final String ITEM_IMAGE_LOCATION = "./src/res/items/takeable/";
    private final int ITEMS_PER_ROW = 10;
    //INVENTORY TITLE
    private int titleStartX;
    private int titleStartY;
    private int titleWidth;
    private int titleHeight;


    //INVETORY DIMENSION
    private int inventoryViewXStart;
    private int inventoryViewYStart;
    private int inventoryViewWidth;
    private int inventoryViewHeight;

    //ITEM VIEW DIMENSION
    private int itemViewXStart;
    private int itemViewYStart;
    private int itemViewWidth;
    private int itemViewHeight;

    private int itemMargin = 15;
    private int itemWidth;
    private int itemHeight;

    //INFO VIEW DIMENSION
    private int infoViewXStart;
    private int infoViewYStart;
    private int infoViewWidth;
    private int infoViewHeight;

    private int infoXMargin;
    private int infoYMargin;
    private int infoElementHeight;
    private int infoDescriptionWidth;

    private Font font;
    private Font smallFont;
    private Font largeFont;
    private Font titleFont;

    private int selectedItem;
    private ArrayList<Inventory.ItemNode> itemNodeList;
    private Inventory inventory;
    private String takeableItemRootFilepath;

    public InventoryView(int width, int height, Display display) {
        super(width, height, display);
        takeableItemRootFilepath = IOUtilities.getFileSystemDependentPath("./src/res/items/takeable/");
        selectedItem = 0;
    }

    public void setItemNodeList(ArrayList<Inventory.ItemNode> itemNodeList) {
        this.itemNodeList = itemNodeList;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void render(Graphics g) {
        renderTitle(g);
        renderItemsView(g);
        renderInfoView(g);
        int moneyX = inventoryViewXStart + itemMargin / 2;
        int moneyY = titleStartY + titleHeight - 30;
        renderMoney(g, Integer.toString(inventory.getMoney()), moneyX, moneyY);
    }

    public void renderMoney(Graphics g, String value, int imgStart_X, int imgStart_Y) {
        //Renders the money amount
        g.setFont(smallFont);
        FontMetrics fm = g.getFontMetrics();
        int valueWidth = fm.stringWidth(value);

        ///Renders the icon
        String imageBasePath = IOUtilities.getFileSystemDependentPath("./src/res/etc/money.png");
        Sprite s = new Sprite(imageBasePath);
        Image money = s.getImage();
//        int imgStart_X = titleStartX ;
//        int imgStart_Y = titleStartY * 3;
        int imgWidth = money.getWidth(null) / 2;
        int imgHeight = money.getHeight(null) / 2;

        g.drawImage(money, imgStart_X + valueWidth, imgStart_Y, imgWidth, imgHeight, null);

        g.drawString(value, imgStart_X, imgStart_Y + (int) (imgHeight * .66));

    }

    private void renderTitle(Graphics g) {
        int titleMargin = 5;
        int instMargin = 15;

        // Draw the background
        g.setColor(new Color(25, 25, 25));
        g.fillRect(titleStartX, titleStartY, titleWidth, titleHeight);

        // Get ready to draw the title
        g.setFont(titleFont);
        String title = "Inventory";
        FontMetrics fm = g.getFontMetrics(titleFont);
        Rectangle2D titleRect = fm.getStringBounds(title, g);

        // Get the location of the title
        int titleX = titleStartX + titleWidth / 2 - (int) titleRect.getWidth() / 2;
        int titleY = titleStartY + (int) titleRect.getHeight() + titleMargin;

        // Draw the title
        g.setColor(Color.lightGray);
        g.drawString(title, titleX, titleY);

        // Get ready to draw the instructions
        g.setFont(smallFont);
        String instructions = "Press [Enter] to equip an item or [d] to drop it.";
        FontMetrics fm2 = g.getFontMetrics(smallFont);
        Rectangle2D instRect = fm2.getStringBounds(instructions, g);

        // Get the location of the instr
        int instX = titleStartX + titleWidth / 2 - (int) instRect.getWidth() / 2;
        int instY = titleStartY + titleHeight - instMargin;

        // Draw the instr
        g.drawString(instructions, instX, instY);


    }

    private void renderItemsView(Graphics g) {
        BufferedImage overImage = new BufferedImage(itemViewWidth, itemViewHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = overImage.getGraphics();

        //paint background
        g2.setColor(new Color(32, 32, 32));
        g2.fillRect(0, 0, itemViewWidth, itemViewHeight);

        int size = itemNodeList.size();

        int xPosStart = itemMargin;
        int xPosInc = itemMargin + itemWidth;
        int yPosInc = itemMargin + itemHeight;
        int xpos = xPosStart;
        int ypos = itemMargin;

        for (int i = 0; i < size; i++) {

            //selected
            if (selectedItem == i) {
                g2.setColor(Color.RED);
                //change
                g2.drawRect(xpos - 3, ypos - 3, this.itemWidth + 6, this.itemHeight + 6);
            }

            paintIcon(g2, xpos, ypos, itemNodeList.get(i));

            //increment for next paint
            if ((i + 1) % ITEMS_PER_ROW == 0) {
                xpos = xPosStart;
                ypos += yPosInc;
            } else {
                xpos += xPosInc;
            }
        }
        g.drawImage(overImage, this.itemViewXStart, this.itemViewYStart, this.itemViewWidth, this.itemViewHeight, null);
    }

    private void paintIcon(Graphics g, int xpos, int ypos, Inventory.ItemNode itemNode) {

        if (itemNode == null) {
            //draw empty slot
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(xpos, ypos, itemWidth, itemHeight);

            int xMid = (2 * xpos + itemWidth) / 2;

//			g.setFont(new Font(this.FONT_USE, Font.PLAIN, (int)(this.itemHeight*0.6)));
            g.setFont(largeFont);
            g.setColor(Color.BLACK);
            FontMetrics fm = g.getFontMetrics();
            //g.drawString("?", xMid - fm.stringWidth("?")/2, ypos + fm.getHeight());
        } else {

            //draw pic
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(xpos, ypos, itemWidth, itemHeight);
            g.drawImage(itemNode.getImage(), xpos, ypos, this.itemWidth, this.itemHeight, null);


            //draw amount
            g.setFont(smallFont);
            FontMetrics fm = g.getFontMetrics();
            int width = fm.stringWidth(itemNode.getAmount() + "");
            int height = fm.getHeight();
            g.setColor(Color.BLACK);
            g.drawString("x" + itemNode.getAmount() + "", xpos, ypos + (int) (fm.getHeight() * 0.8));
        }

    }


    private void renderInfoView(Graphics g) {
        BufferedImage overImage = new BufferedImage(infoViewWidth, infoViewHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = overImage.getGraphics();

        //paint background
        g2.setColor(new Color(25, 25, 25));
        g2.fillRect(0, 0, itemViewWidth, itemViewHeight);

        if (!itemNodeList.isEmpty()) {
            TakeableItem item = itemNodeList.get(selectedItem).getItem();

            paintSelectedIcon(g2, item);

            paintInfo(g2, item);

            paintOptions(g2, item);
        }

        g.drawImage(overImage, this.infoViewXStart, this.infoViewYStart, this.infoViewWidth, this.infoViewHeight, null);
    }


    private void paintSelectedIcon(Graphics g2, TakeableItem item) {
        if (item == null) {
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(infoXMargin, infoYMargin, infoElementHeight, infoElementHeight);
        } else {
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(infoXMargin, infoYMargin, infoElementHeight, infoElementHeight);
            g2.drawImage(item.getImage(), infoXMargin, infoYMargin, infoElementHeight, infoElementHeight, null);
        }
    }

    private void paintInfo(Graphics g2, TakeableItem item) {

        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();

        int xpos = infoViewWidth / 2 - infoDescriptionWidth / 2;
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(xpos, infoYMargin, infoDescriptionWidth, infoElementHeight);

        if (item != null) {
            String description = item.getDescription();
            String[] subString = description.split(" ");
            String[] output = new String[subString.length];
            int lines = 0;
            output[0] = subString[0];

            int descriptionWidth = (int) (infoDescriptionWidth * 0.8);

            for (int i = 1; i < subString.length; i++) {
                if (fm.stringWidth(output[lines] + " " + subString[i]) < descriptionWidth) {
                    output[lines] += " " + subString[i];
                } else {
                    output[++lines] = subString[i];
                }
            }
            lines++;
            int ypos = infoYMargin + 25;

            g2.setColor(Color.BLACK);
            for (int i = 0; i < lines; i++) {
                g2.drawString(output[i], infoViewWidth / 2 - fm.stringWidth(output[i]) / 2, ypos);
                ypos += fm.getHeight();
            }
        }
    }


    private void paintOptions(Graphics g2, TakeableItem item) {

        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(infoViewWidth - infoXMargin - infoElementHeight, infoYMargin, infoElementHeight, infoElementHeight);


        if (item == null) {

        } else {

        }

    }

    public void updateSelected(int index) {
        selectedItem = index;
    }

    @Override
    public void scaleView() {
        font = new Font("Courier New", 1, getScreenWidth() / 50);
        smallFont = new Font("Courier New", 1, getScreenWidth() / 67);
        largeFont = new Font("Courier New", 1, getScreenWidth() / 30);
        titleFont = new Font("Courier New", 1, getScreenWidth() / 22);

        //INVENTORY TITLE
        titleStartX = (int) (getScreenWidth() * 0.1);
        titleStartY = (int) (getScreenHeight() * 0.05);
        titleWidth = (int) (getScreenWidth() * 0.8);
        titleHeight = (int) (getScreenHeight() * 0.15);


        //INVETORY DIMENSION
        inventoryViewXStart = (int) (getScreenWidth() * 0.1);
        inventoryViewYStart = titleStartY + titleHeight;
        inventoryViewWidth = (int) (getScreenWidth() * 0.8);
        inventoryViewHeight = (int) (getScreenHeight() * 0.52);

        //ITEM VIEW DIMENSION
        itemViewXStart = inventoryViewXStart;
        itemViewYStart = inventoryViewYStart;
        itemViewWidth = inventoryViewWidth;
        itemViewHeight = (int) (inventoryViewHeight * 0.7);

        itemMargin = getScreenWidth() / 80;
        itemWidth = (itemViewWidth - (ITEMS_PER_ROW + 1) * itemMargin) / ITEMS_PER_ROW;
        itemHeight = itemWidth;

        //INFO VIEW DIMENSION
        infoViewXStart = inventoryViewXStart;
        infoViewYStart = itemViewYStart + itemViewHeight;
        infoViewWidth = inventoryViewWidth;
        infoViewHeight = inventoryViewHeight - itemViewHeight;

        infoXMargin = getScreenWidth() / 30;
        infoYMargin = (int) (infoViewHeight * 0.2);
        infoElementHeight = infoViewHeight - infoYMargin * 2;
        infoDescriptionWidth = infoViewWidth - 2 * infoElementHeight - 4 * infoXMargin;
    }
}
