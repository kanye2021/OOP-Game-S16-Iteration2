package views;

import controllers.ViewController;
import models.Inventory;
import models.entities.Avatar;
import models.entities.npc.NPC;
import models.items.Item;
import models.items.takeable.TakeableItem;
import sun.jvm.hotspot.jdi.IntegerTypeImpl;
import utilities.IOUtilities;
import views.sprites.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by dyeung on 3/2/16.
 */
public class NPCShopView extends View {
    private final String ITEM_IMAGE_LOCATION = "./src/res/items/takeable/";
    private final int ITEMS_PER_ROW = 5;
    //INVENTORY TITLE
    private int titleStartX;
    private int titleStartY;
    private int titleWidth;
    private int titleHeight;

    //Background for Inventories
    private int backgroundX;
    private int backgroundY;
    private int backgroundWidth;
    private int backgroundHeight;
    private int bgMargin;
    //INVENTORY DIMENSION
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
    private int selectedView;
    private ArrayList<Inventory.ItemNode> shopNodeList;
    private ArrayList<Inventory.ItemNode> invNodeList;
    private String takeableItemRootFilepath;
    private NPC npc;
    private Avatar avatar;
    public NPCShopView(int width, int height, Display display, NPC npc, Avatar avatar){
        super(width, height, display);
        takeableItemRootFilepath = IOUtilities.getFileSystemDependentPath("./src/res/items/takeable/");
        selectedItem = 0;
        selectedView = 0;
        this.npc = npc;
        shopNodeList = npc.getInventory().getItemNodeArrayList(); //Sorry dave
        this.avatar = avatar;
        invNodeList = avatar.getInventory().getItemNodeArrayList();
    }

    @Override
    public void render(Graphics g) {
        renderTitle(g);
        renderInv(g);
        renderShop(g);

    }

    private void renderTitle(Graphics g) {
        int titleMargin = 5;
        int instMargin = 15;

        // Draw the background
        g.setColor(new Color(25, 25, 25));
        g.fillRect(titleStartX, titleStartY, titleWidth, titleHeight);

        // Get ready to draw the title
        g.setFont(titleFont);
        String title = "Shop Keeper";
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
        String instructions = "Press [Enter] to buy an item, [backspace] to exit, [tab] to switch buy/sell";
        FontMetrics fm2 = g.getFontMetrics(smallFont);
        Rectangle2D instRect = fm2.getStringBounds(instructions, g);

        // Get the location of the instr
        int instX = titleStartX + titleWidth / 2 - (int) instRect.getWidth() / 2;
        int instY = titleStartY + titleHeight - instMargin;

        // Draw the instr
        g.drawString(instructions, instX, instY);

        //Render the backdrop
        g.setColor(new Color(73, 73, 73));
        g.fillRect(backgroundX, backgroundY, backgroundWidth, backgroundHeight);

    }
    public void renderMoney(Graphics g, String value, int imgStart_X, int imgStart_Y){
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
        int imgWidth = money.getWidth(null)/2;
        int imgHeight = money.getHeight(null)/2;

        g.drawImage(money, imgStart_X + valueWidth, imgStart_Y, imgWidth, imgHeight, null);

        g.drawString(value, imgStart_X, imgStart_Y + (int)(imgHeight * .66));

    }
    private void renderInv(Graphics g){
        String miniTitle = "My Inventory";
        int titleX = backgroundX + (bgMargin * 2);
        int titleY = backgroundY + bgMargin;
        g.setColor(Color.lightGray);
        g.setFont(smallFont);
        g.drawString(miniTitle,titleX,titleY);


        int startX = backgroundX + inventoryViewWidth - titleX;
        int startY = titleY - bgMargin;
        renderMoney(g, "Wallet: " + avatar.getAmountofMoney(), startX, startY);

        //Render the actual inv view
        int invStartX = backgroundX + bgMargin;
        int invStartY = backgroundY + bgMargin * 2;
        if (selectedView == 0) {
            renderItemsView(g, invNodeList, invStartX, invStartY, true); //This is for the inventory view
        }else{
            renderItemsView(g, invNodeList, invStartX, invStartY, false); //This is for the inventory view
        }
    }
    private void renderShop(Graphics g){
        String miniTitle = "Shop";
        int titleX = backgroundX + inventoryViewWidth + (bgMargin * 4);
        int titleY = backgroundY + bgMargin;
        g.setColor(Color.lightGray);
        g.setFont(smallFont);
        g.drawString(miniTitle,titleX,titleY);

        int shopStartX = backgroundX + inventoryViewWidth + (bgMargin * 3);
        int shopStartY = backgroundY + (bgMargin*2);
        if (selectedView == 1) {
            renderItemsView(g, shopNodeList, shopStartX, shopStartY, true);
        }else {
            renderItemsView(g, shopNodeList, shopStartX, shopStartY, false);
        }
    }
    private void renderItemsView(Graphics g, ArrayList<Inventory.ItemNode> itemNodeList, int startX, int startY, boolean isSelected) {
        BufferedImage overImage = new BufferedImage(itemViewWidth, itemViewHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = overImage.getGraphics();

        if (isSelected){
//            g2.setColor(Color.RED);
//            g2.drawRect(startX - 5, startY - 5, inventoryViewWidth + 6, inventoryViewHeight + 6);
            g.setColor(new Color(169, 46, 44));
            g.fillRect(startX - 3, startY - 3, inventoryViewWidth + 6, inventoryViewHeight + 6);
        }
            int size = itemNodeList.size();
            int xPosStart = itemMargin ;
            int xPosInc = itemMargin + itemWidth;
            int yPosInc = itemMargin + itemHeight;
            int xpos = xPosStart;
            int ypos = itemMargin;

            for (int i = 0; i < size; i++) {

                //selected
                if (selectedItem == i && isSelected) {
                    g2.setColor(Color.RED);
                    //change
                    g2.drawRect(xpos - 3, ypos - 3, this.itemWidth + 6, this.itemHeight + 6);
                }
                paintIcon(g2, xpos, ypos, itemNodeList.get(i));
                //paintItemValue(g2, xpos, ypos, itemNodeList.get(i).getItem());

                //increment for next paint
                if ((i + 1) % ITEMS_PER_ROW == 0) {
                    xpos = xPosStart;
                    ypos += yPosInc;
                } else {
                    xpos += xPosInc;
                }
            }
           // g.drawImage(overImage, this.itemViewXStart, this.itemViewYStart, this.itemViewWidth, this.itemViewHeight, null);
            g.setColor(new Color(238, 238, 238));
            g.drawImage(overImage, startX, startY, this.itemViewWidth, this.itemViewHeight, null);

            TakeableItem selected;
            if (itemNodeList.size() > selectedItem && isSelected) {
                selected = itemNodeList.get(selectedItem).getItem();
            }else {
                selected = null;
            }
            int infoStartY = startY + itemViewHeight;

            renderInfoView(g, selected, startX, infoStartY);


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
//    private void paintItemValue(Graphics g, int xpos, int ypos, Inventory.ItemNode itemNode){
//        Item item = itemNode.getItem();
//        String itemValue = Integer.toString(item.getPrice());
//        g.drawString(itemValue,xpos,ypos);
//
//    }

    private void renderInfoView(Graphics g, TakeableItem item, int startX, int startY) {
        BufferedImage overImage = new BufferedImage(infoViewWidth, infoViewHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = overImage.getGraphics();

        //paint background
        g2.setColor(new Color(151, 151, 151));
        g2.fillRect(0, 0, itemViewWidth, itemViewHeight);

        paintSelectedIcon(g2, item, infoXMargin, infoYMargin);

        paintInfo(g2, item, startX + infoViewWidth, startY);

        paintOptions(g2, item);

        //g.drawImage(overImage, this.infoViewXStart, this.infoViewYStart, this.infoViewWidth, this.infoViewHeight, null);
        g.drawImage(overImage, startX, startY, this.infoViewWidth, this.infoViewHeight, null);

    }


    private void paintSelectedIcon(Graphics g2, TakeableItem item, int startX, int startY) {
        g2.setColor(new Color(32,32,32));
        if (item == null) {
            g2.fillRect(startX, startY, infoElementHeight, infoElementHeight);
        } else {
            //g2.fillRect(infoXMargin, infoYMargin, infoElementHeight, infoElementHeight);
            //g2.drawImage(item.getImage(), infoXMargin, infoYMargin, infoElementHeight, infoElementHeight, null);
            g2.fillRect(startX, startY, infoElementHeight, infoElementHeight);
            g2.drawImage(item.getImage(), startX, startY, infoElementHeight, infoElementHeight, null);
        }
    }

    private void paintInfo(Graphics g2, TakeableItem item, int startX, int startY) {

        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();

        //int xpos = infoViewWidth / 2 - infoDescriptionWidth / 2;
        g2.setColor(Color.LIGHT_GRAY);
        int descriptionBoxWidth = infoDescriptionWidth + infoElementHeight;
        g2.fillRect(startX, startY, descriptionBoxWidth, infoElementHeight);

        if (item != null) {
            String description = item.getDescription();
            String[] subString = description.split(" ");
            String[] output = new String[subString.length];
            int lines = 0;
            output[0] = subString[0];

            int descriptionWidth = (int) (descriptionBoxWidth * 0.8);

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

            // Prints out the monetary value of the item
            String price = Integer.toString(item.getMonetaryValue()) + " C";
            int stringX = infoDescriptionWidth + startX;
            int stringY = ypos + fm.getHeight()/2;
            g2.drawString(price, stringX, stringY);
        }
    }


    private void paintOptions(Graphics g2, TakeableItem item) {
        int startX = infoViewWidth - infoXMargin - infoElementHeight;
        int startY = infoYMargin;
        //g2.setColor(new Color(32,32,32));
        //g2.fillRect(infoViewWidth - infoXMargin - infoElementHeight, infoYMargin, infoElementHeight, infoElementHeight);

        if (item != null) {
            String amount = Integer.toString(item.getMonetaryValue());
            g2.drawString(amount, startX, startY);
        }

    }
    public void updateSelected(int index) {
        selectedItem = index;
    }
    public void updateSelectedView(int index) {
        selectedView = index;
    }
    @Override
    public void scaleView() {
        font = new Font("Courier New", 1, getScreenWidth()/50);
        smallFont = new Font("Courier New", 1, getScreenWidth()/67);
        largeFont = new Font("Courier New", 1, getScreenWidth()/30);
        titleFont = new Font("Courier New", 1, getScreenWidth()/22);

        //INVENTORY TITLE
        titleStartX = (int) (getScreenWidth() * 0.1);
        titleStartY = (int) (getScreenHeight() * 0.05);
        titleWidth = (int) (getScreenWidth() * 0.8);
        titleHeight = (int) (getScreenHeight() * 0.15);
        //Background Dimension
        backgroundX = titleStartX;
        backgroundY = titleStartY + titleHeight + 10;
        backgroundWidth = (int) (getScreenWidth() * 0.8) ;
        backgroundHeight = (int) (getScreenHeight() * 0.52);

        //INVENTORY DIMENSION
        bgMargin = getScreenWidth()/70;
        inventoryViewXStart = titleStartX;
        inventoryViewYStart = titleStartY + titleHeight;
        inventoryViewWidth = (int) (getScreenWidth() * 0.8)/2 - (bgMargin*2);
        inventoryViewHeight = (int) (getScreenHeight() * 0.52) - (bgMargin*4);

        //ITEM VIEW DIMENSION
        itemViewXStart = inventoryViewXStart;
        itemViewYStart = inventoryViewYStart;
        itemViewWidth = inventoryViewWidth;
        itemViewHeight = (int) (inventoryViewHeight * 0.7);

        itemMargin = getScreenWidth()/80;
        itemWidth = (itemViewWidth - (ITEMS_PER_ROW + 1) * itemMargin) / ITEMS_PER_ROW;
        itemHeight = itemWidth;

        //INFO VIEW DIMENSION
        infoViewXStart = inventoryViewXStart;
        infoViewYStart = itemViewYStart + itemViewHeight;
        infoViewWidth = inventoryViewWidth;
        infoViewHeight = inventoryViewHeight - itemViewHeight;

        infoXMargin = getScreenWidth()/30;
        infoYMargin = (int) (infoViewHeight * 0.2);
        infoElementHeight = (int) (infoViewHeight - infoYMargin * 2);
        infoDescriptionWidth = (int) (infoViewWidth - 2 * infoElementHeight - 4 * infoXMargin);
    }
}
