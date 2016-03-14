package views;

import models.stats.Stats;
import utilities.IOUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Bradley on 2/27/16.
 */
public class StatusViewport extends View {

    private final String RESOURCE_BASE_PATH = IOUtilities.getFileSystemDependentPath("./src/res/etc/");
    private final Font FIXED_FONT_MED = new Font("Courier New", 1, 18);
    private final Font FIXED_FONT_SMALL = new Font("Courier New", 1, 14);
    private Stats stats;
    // Scalable Viewport Attributes
    private int viewportWidth;
    private int viewportHeight;

    // Font attributes
    private FontMetrics fm;

    // Other positioning attribues
    private int borderRadius;
    private int marginBtwnHearts;
    private int heartSize;

    // boolean whether or not to show details.
    private boolean showDetails;

    public StatusViewport(int width, int height, Display display, Stats stats) {
        super(width, height, display);
        this.stats = stats;
        showDetails = false;
        borderRadius = 40;
        heartSize = 20;
        marginBtwnHearts = 5;
        scaleView();
    }

    public void toggleDetails() {
        showDetails = !showDetails;
        getDisplay().repaint();
    }

    @Override
    public void render(Graphics g) {
        // Display the avatar's level
        drawLevelAndLives(g);

        // Draw the health bar
        drawStatusBars(g);

        // Draw the other stats if the user wants it
        if (showDetails) {
            Graphics2D g2 = (Graphics2D) g.create();
            drawStatAttributes(g2);
        }

    }

    private void drawLevelAndLives(Graphics g) {
        String level = "Level: " + stats.getStat(Stats.Type.LEVEL);

        // Set the font
        g.setFont(FIXED_FONT_MED);
        fm = g.getFontMetrics(FIXED_FONT_MED);

        // Determine the size the string takes up
        Rectangle2D levelRect = fm.getStringBounds(level, g);

        // Display the Lives text
        int levelX = 20;
        int levelY = 20;

        g.setColor(Color.WHITE);
        g.drawString(level, levelX, levelY);

        // Render the lives
        int lives = stats.getStat(Stats.Type.LIVES);

        int livesX = levelX + (int) levelRect.getWidth() + 20;
        int livesY = levelY;

        g.drawString("Lives: ", livesX, livesY);

        // Get the bounding rect of the lives string.
        Rectangle2D livesRect = fm.getStringBounds("Lives: ", g);

        // Load the heart image
        ImageIcon lifeIcon = IOUtilities.getImageIcon(RESOURCE_BASE_PATH + "life-heart.png");
        Image lifeImg = lifeIcon.getImage();

        // Set the spacing between hearts
        int heartsX = livesX + (int) livesRect.getWidth();
        int heartsY = livesY - (int) livesRect.getHeight() * 3 / 4;
        for (int i = 0; i < lives; i++) {
            g.drawImage(lifeImg, heartsX, heartsY, heartSize, heartSize, getDisplay());
            heartsX += heartSize + marginBtwnHearts;
        }
    }

    private void drawStatusBars(Graphics g) {

        // Start with the healthbar
        // Get the necessary stats
        int health = stats.getStat(Stats.Type.HEALTH);
        int maxHealth = stats.getStat(Stats.Type.MAX_HEALTH);

        // Set the font
        g.setFont(FIXED_FONT_MED);

        // Determine how large text is and where to place the Health string
        Rectangle2D healthRect = fm.getStringBounds("HP: ", g);
        int healthX = 20;
        int healthY = 50;

        // Draw the health string.
        g.setColor(Color.WHITE);
        g.drawString("HP: ", healthX, healthY);

        // Set the location and size of the health bar.
        int healthBarX = healthX + (int) healthRect.getWidth();
        int healthBarY = healthY - (int) healthRect.getHeight() + 8;
        int healthBarWidth = 210;
        int healthBarHeight = 15;

        // Draw the outline of the health bar.
        g.setColor(Color.WHITE);
        g.drawRoundRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight, borderRadius, borderRadius);

        // Determine what fraction of the healthbar should be shown.
        double healthFraction = (double) health / (double) maxHealth;
        int healthFillWidth = (int) (healthFraction * healthBarWidth);

        // Fill the healthbar
        g.setColor(Color.RED);
        g.fillRoundRect(healthBarX, healthBarY, healthFillWidth, healthBarHeight, borderRadius, borderRadius);

        // Display the fraction of health
        g.setFont(FIXED_FONT_SMALL);
        g.setColor(Color.WHITE);
        String healthFractionString = "(" + health + "/" + maxHealth + ")";

        // Place the font at the right of the bar
        Rectangle2D healthFractionRect = fm.getStringBounds(healthFractionString, g);

        int healthFractionX = healthBarX + healthBarWidth - (int) healthFractionRect.getWidth() + 15;
        int healthFractionY = healthBarY + healthBarHeight - 4;
        g.drawString(healthFractionString, healthFractionX, healthFractionY);

        // Draw the mana bar
        // Get the necessary stats
        int mana = stats.getStat(Stats.Type.MANA);
        int maxMana = stats.getStat(Stats.Type.MAX_MANA);

        // Set the font
        g.setFont(FIXED_FONT_MED);

        // Determine how large text is and where to place the mana string
        Rectangle2D manaRect = fm.getStringBounds("MP: ", g);
        int manaX = 20;
        int manaY = healthY + healthBarHeight + 10;

        // Draw the MP string.
        g.setColor(Color.WHITE);
        g.drawString("MP: ", manaX, manaY);

        // Set the location and size of the mana bar.
        int manaBarX = manaX + (int) manaRect.getWidth();
        int manaBarY = manaY - (int) manaRect.getHeight() + 8;
        int manaBarWidth = healthBarWidth;
        int manaBarHeight = healthBarHeight;

        // Draw the outline of the health bar.
        g.setColor(Color.WHITE);
        g.drawRoundRect(manaBarX, manaBarY, manaBarWidth, manaBarHeight, borderRadius, borderRadius);

        // Determine what fraction of the mana bar should be shown.
        double manaFraction = (double) mana / (double) maxMana;
        int manaFillWidth = (int) (manaFraction * manaBarWidth);

        // Fill the mana
        g.setColor(Color.BLUE);
        g.fillRoundRect(manaBarX, manaBarY, manaFillWidth, manaBarHeight, borderRadius, borderRadius);

        // Display the fraction of mana
        g.setFont(FIXED_FONT_SMALL);
        g.setColor(Color.WHITE);
        String manaFractionString = "(" + mana + "/" + maxMana + ")";

        // Place the font at the right of the bar
        Rectangle2D manaFractionRect = fm.getStringBounds(manaFractionString, g);

        int manaFractionX = manaBarX + manaBarWidth - (int) manaFractionRect.getWidth() + 15;
        int manaFractionY = manaBarY + manaBarHeight - 4;
        g.drawString(manaFractionString, manaFractionX, manaFractionY);


        // Draw the exp bar
        // Get the necessary stats
        int exp = stats.getStat(Stats.Type.EXPERIENCE);
        int expReqLvlUp = stats.getStat(Stats.Type.EXP_TO_LEVEL);
        int lastLvlExpReq = stats.getStat(Stats.Type.LAST_EXP_TO_LEVEL);

        // Set the font
        g.setFont(FIXED_FONT_MED);

        // Determine how large text is and where to place the exp string
        Rectangle2D expRect = fm.getStringBounds("XP: ", g);
        int expX = 20;
        int expY = manaY + manaBarHeight + 10;

        // Draw the MP string.
        g.setColor(Color.WHITE);
        g.drawString("XP: ", expX, expY);

        // Set the location and size of the exp bar.
        int expBarX = expX + (int) expRect.getWidth();
        int expBarY = expY - (int) expRect.getHeight() + 8;
        int expBarWidth = manaBarWidth;
        int expBarHeight = manaBarHeight;

        // Draw the outline of the exp bar.
        g.setColor(Color.WHITE);
        g.drawRoundRect(expBarX, expBarY, expBarWidth, expBarHeight, borderRadius, borderRadius);

        // Determine what fraction of the exp bar should be shown.
        double expFraction = (double) (exp - lastLvlExpReq) / (double) (expReqLvlUp - lastLvlExpReq);
        int expFillWidth = (int) (expFraction * expBarWidth);

        // Fill the exp bar
        g.setColor(Color.YELLOW);
        g.fillRoundRect(expBarX, expBarY, expFillWidth, expBarHeight, borderRadius, borderRadius);

        // Display the fraction of exp
        g.setFont(FIXED_FONT_SMALL);
        g.setColor(Color.WHITE);
        String expFractionString = "(" + exp + "/" + expReqLvlUp + ")";

        // Place the font at the right of the bar
        Rectangle2D expFractionRect = fm.getStringBounds(expFractionString, g);

        int expFractionX = expBarX + expBarWidth - (int) expFractionRect.getWidth() + 15;
        int expFractionY = expBarY + expBarHeight - 4;
        g.drawString(expFractionString, expFractionX, expFractionY);
    }

    private void drawStatAttributes(Graphics2D g) {
        // Get the ncessary stats
        String strength = "Strength: " + stats.getStat(Stats.Type.STRENGTH);
        String agility = "Agility: " + stats.getStat(Stats.Type.AGILITY);
        String intellect = "Intellect: " + stats.getStat(Stats.Type.INTELLECT);
        String hardiness = "Hardiness: " + stats.getStat(Stats.Type.HARDINESS);
        String offensiveRating = "Offense: " + stats.getStat(Stats.Type.OFFSENSIVE_RATING);
        String defensiveRating = "Defense: " + stats.getStat(Stats.Type.DEFENSIVE_RATING);
        String armorRating = "Armor: " + stats.getStat(Stats.Type.ARMOR_RATING);
        String movement = "Speed: " + stats.getStat(Stats.Type.MOVEMENT);


        // Set the font
        g.setFont(FIXED_FONT_MED);
        fm = g.getFontMetrics(FIXED_FONT_MED);

        // Setup the rects for positioning
        Rectangle2D strengthRect = fm.getStringBounds(strength, g);
        Rectangle2D agilityRect = fm.getStringBounds(agility, g);
        Rectangle2D intellectRect = fm.getStringBounds(intellect, g);
        Rectangle2D hardinessRect = fm.getStringBounds(hardiness, g);
        Rectangle2D offensiveRect = fm.getStringBounds(offensiveRating, g);
        Rectangle2D defensiveRect = fm.getStringBounds(defensiveRating, g);
        Rectangle2D armorRect = fm.getStringBounds(armorRating, g);
        Rectangle2D movementRect = fm.getStringBounds(movement, g);

        // Determine the width that all the stats will take up using the rectangles

        // Get the dimensions of the first column and determine which is biggest;
        int strengthWidth = (int) strengthRect.getWidth();
        int agilityWidth = (int) agilityRect.getWidth();
        int firstColumnWidth = strengthWidth > agilityWidth ? strengthWidth : agilityWidth;

        // Get the width of the second column
        int intellectWidth = (int) intellectRect.getWidth();
        int hardinessWidth = (int) hardinessRect.getWidth();
        int secondColumnWidth = intellectWidth > hardinessWidth ? intellectWidth : hardinessWidth;

        // Get the dimensions of the third column
        int offensiveWidth = (int) offensiveRect.getWidth();
        int defensiveWidth = (int) defensiveRect.getWidth();
        int thirdColumnWidth = offensiveWidth > defensiveWidth ? offensiveWidth : defensiveWidth;

        // Ge the width of the fourth column
        int armorWidth = (int) armorRect.getWidth();
        int movementWidth = (int) movementRect.getWidth();
        int fourthColumnWidth = armorWidth > movementWidth ? armorWidth : movementWidth;

        // Set the margin between columns
        int columnMargin = 10;

        // Determine the total width and use it to postiion the first column
        int totalWidth = firstColumnWidth + secondColumnWidth + thirdColumnWidth + fourthColumnWidth + columnMargin * 3;

        // Draw an opaque box around all the stats
        AlphaComposite acomp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
        g.setComposite(acomp);

        int startX = viewportWidth / 2 - totalWidth / 2 - 40;
        int startY = viewportHeight - 4 * (int) strengthRect.getHeight() - 20;
        int width = totalWidth + 100;
        int height = viewportHeight - startY;

        Image statsParchment = IOUtilities.getImageIcon(RESOURCE_BASE_PATH + "parchment.png").getImage();
        Shape statsCont = new RoundRectangle2D.Double(startX, startY, width, height, 20, 20);
        Shape oldClip = g.getClip();
        g.setClip(statsCont);
        g.drawImage(statsParchment, startX, startY, width, height, getDisplay());
        g.setClip(oldClip);

        // Set the alpha comp back to normal.
        acomp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        g.setComposite(acomp);
        g.setColor(Color.black);

        // Find the positioning of and draw strength
        int strengthX = viewportWidth / 2 - totalWidth / 2;
        int strengthY = viewportHeight - 4 * (int) strengthRect.getHeight() + 15;
        g.drawString(strength, strengthX, strengthY);

        // Find the positioning of and draw agility
        int agilityX = strengthX;
        int agilityY = strengthY + (int) agilityRect.getHeight();
        g.drawString(agility, agilityX, agilityY);

        // Draw the intellect
        int intellectX = strengthX + (strengthWidth > agilityWidth ? strengthWidth : agilityWidth) + columnMargin;
        int intellectY = strengthY;
        g.drawString(intellect, intellectX, intellectY);

        // Draw the hardiness
        int hardinessX = intellectX;
        int hardinessY = agilityY;
        g.drawString(hardiness, hardinessX, hardinessY);

        // Draw the offensive Rating
        int offensiveX = intellectX + (intellectWidth > hardinessWidth ? intellectWidth : hardinessWidth) + columnMargin;
        int offensiveY = strengthY;
        g.drawString(offensiveRating, offensiveX, offensiveY);

        // Draw the defensive Rating
        int defensiveX = offensiveX;
        int defensiveY = hardinessY;
        g.drawString(defensiveRating, defensiveX, defensiveY);

        // Draw the Armor
        int armorX = offensiveX + (offensiveWidth > defensiveWidth ? offensiveWidth : defensiveWidth) + columnMargin;
        int armorY = offensiveY;
        g.drawString(armorRating, armorX, armorY);

        // Draw the Speed
        int speedX = armorX;
        int speedY = defensiveY;
        g.drawString(movement, speedX, speedY);
    }

    @Override
    public void scaleView() {
        viewportHeight = getScreenHeight();
        viewportWidth = getScreenWidth();
    }
}