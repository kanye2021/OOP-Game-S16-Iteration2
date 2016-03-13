package views;

import models.skills.ActiveSkill;
import models.skills.Skill;
import models.skills.SkillList;
import models.stats.Stats;
import org.w3c.dom.css.Rect;
import utilities.IOUtilities;
import utilities.MathUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by sergiopuleri on 3/8/16.
 */
public class SkillViewport extends View{

    private final String RESOURCE_BASE_PATH = IOUtilities.getFileSystemDependentPath("./src/res/etc/");

    // Model attributes
    private SkillList skills;
    private Stats stats;
    private int skillCount;

    // Font
    private int skillLabelFontSize;
    private int keyBindFontSize;
    private int CDFontSize;
    private Font CDFont;
    private Font skillLabelFont;
    private Font keyBindFont;

    private Color goldTrim;

    // Scalable Viewport Attributes
    private int skillRectWidth;
    private int skillRectHeight;
    private int skillBoxSize;
    private int skillBoxStartX;
    private int skillBoxStartY;
    private int levelUpBoxSize;
    private int levelUpBoxLeftX;
    private int levelUpBoxTopY;
    private int levelUpBoxBottomY;
    private int levelUpBoxRightX;
    private int levelUpBoxLeftXDistanceGap;
    private int plusIconSize;
    private int imgSize;

    // Font attributes
    private FontMetrics fm;



    public SkillViewport(int width, int height, Display display, SkillList skills, Stats stats) {
        super(width, height, display);
        this.skills = skills;
        this.stats = stats;
        this.skillCount = skills.size();
//        this.skillPointsAvailable = 0;
        this.goldTrim = new Color(223, 196, 99);
        scaleView();
    }

    @Override
    public void render(Graphics g) {
        // Draw erre-thang
        drawSkillRectAndBoxes(g);


    }

    private void drawSkillRectAndBoxes(Graphics g) {

        // Draw outline
        g.setColor(goldTrim);


        // Fill
        g.drawRect(skillBoxStartX, skillBoxStartY, skillRectWidth, skillRectHeight);
        g.setColor(Color.darkGray);
        g.fillRect(skillBoxStartX, skillBoxStartY, skillRectWidth, skillRectHeight);


        // Values needed to draw
        int skillBoxX = skillBoxStartX;
        int skillBoxY = skillBoxStartY;
        int topAndSideMargin = skillBoxSize/28;
        Color coolDownFontColor = Color.RED;
        Rectangle2D skillRect;
        Rectangle2D textRect;
        String skillText;
        String keyBind;
        Skill currentSkill;

        // Draw each box, loop thru each skill
        for(int i = 0; i < skillCount; i++) {
            currentSkill = skills.get(i);

            // Draw skill box
            g.setColor(goldTrim);
            g.drawRect(skillBoxX, skillBoxY, skillBoxSize, skillBoxSize);

            // TODO: Do draw image when have images. Not rly a large priority tbh
            // Draw skill image


            // Draw skill name
            g.setColor(Color.white);
            g.setFont(skillLabelFont);
            skillText = currentSkill.getName();
            fm = g.getFontMetrics(skillLabelFont);
            textRect = fm.getStringBounds(skillText, g);
            drawSkillName(g, skillText, textRect, skillBoxY, skillBoxX);

            // Draw Keybind in Upper Left Corner
            // Only active skils have keybinds
            if (currentSkill.isActive()) {
                // Set font
                g.setFont(keyBindFont);
                fm = g.getFontMetrics(keyBindFont);
                g.setColor(goldTrim);

                // Get keybind
                ActiveSkill currentActiveSkill = (ActiveSkill)currentSkill;
                int keyCode = currentActiveSkill.getKeyBind();
                keyBind = KeyEvent.getKeyText(keyCode);
                textRect = fm.getStringBounds(keyBind, g);
                g.drawString(keyBind, skillBoxX + (int)textRect.getWidth()/2 + topAndSideMargin, skillBoxY + (int)textRect.getHeight() - 1  );
            }

            // Set font
            g.setFont(skillLabelFont);
            fm = g.getFontMetrics(skillLabelFont);
            g.setColor(Color.WHITE);

            // Draw Skill level in Upper Right Corner
            String skillLvl = "Lvl " + Integer.toString(currentSkill.getLevel());
            textRect = fm.getStringBounds(skillLvl, g);
            g.drawString(skillLvl, skillBoxX + skillBoxSize - (int)textRect.getWidth() - topAndSideMargin, skillBoxY + (int)textRect.getHeight() + topAndSideMargin);

            // Draw cool down in center, if skill is coolin down
            if (currentSkill.isCooldown()) {
                double currentTime = currentSkill.getCooldownTimeRemaining();
                drawAndUpdateCoolDown(g, skillBoxX, skillBoxY, currentTime, currentSkill);
            }

            // Draw option to level up this skill if have skill points
            if (stats.getStat(Stats.Type.SKILL_POINTS) > 0) {
                drawSkillLevelUpBox(g, skillBoxX, skillBoxY);
            }

            // Increment x position
            skillBoxX += skillBoxSize;

        }
    }

    private void drawSkillName(Graphics g, String skillText, Rectangle2D textRect, int skillBoxY, int skillBoxX) {
        // Get width and Y pos.
        int skillNameWidth = (int)textRect.getWidth();
        int skillStringY = skillBoxY + (skillRectHeight -  (int) textRect.getHeight()) + fm.getAscent();

        // if text is 2 long for the box... Split that shit up boiii
        if (skillNameWidth > skillBoxSize) {
            // Split by whitespace
            String []arr = skillText.split(" ");
            String curr;
            Rectangle2D textRect2;

            // Loop thru each substring from the end and draw appropriately
            for(int j = arr.length-1; j >=0 ; j--) {
                curr = arr[j];
                textRect = fm.getStringBounds(curr, g);
                // If this substring is less than half the width of the box and theres anotha substring
                if ((int)textRect.getWidth() < skillBoxSize/2 && j != 0) {
                    // Get and measure the otha 1
                    String anotha1 = arr[j-1];
                    textRect2 = fm.getStringBounds(anotha1, g);

                    // Draw both on the same line if can fit
                    int combinedW = (int)textRect.getWidth() + (int)textRect2.getWidth() + 2;
                    String combined = anotha1 + " " + curr ;
                    if (combinedW < skillBoxSize) {
                        g.drawString(combined, skillBoxX + (skillBoxSize - combinedW)/2, skillStringY);
                        j--;
                    }
                    // Otherwise, draw one of those suckas
                    else {
                        g.drawString(curr, skillBoxX + (skillBoxSize - (int)textRect.getWidth())/2, skillStringY);
                    }
                }
                // Otherwise, draw one of those suckas
                else {
                    g.drawString(curr, skillBoxX + (skillBoxSize - (int)textRect.getWidth())/2, skillStringY);
                }

                //Decrease dat Y
                skillStringY -=  (int) textRect.getHeight();
            }

        } else {
            g.drawString(skillText, skillBoxX + (skillBoxSize - skillNameWidth)/2, skillBoxY + (skillRectHeight -  (int) textRect.getHeight()) + fm.getAscent() );
        }

    }

    private void drawSkillLevelUpBox(Graphics g, int x, int y) {
        // Draw rect
        g.setColor(goldTrim);
        int rect_x = x + (skillBoxSize - levelUpBoxSize)/2;
        int rect_y = y - levelUpBoxSize;
        g.drawRect(rect_x, rect_y , levelUpBoxSize, levelUpBoxSize);

        // Draw plus icon
        // Load the heart image
        ImageIcon plusIcon = IOUtilities.getImageIcon(RESOURCE_BASE_PATH + "plus_icon.png");
        Image plusIconImg = plusIcon.getImage();
        g.drawImage(plusIconImg, rect_x + (levelUpBoxSize - plusIconSize)/2, rect_y + (levelUpBoxSize - plusIconSize)/2, plusIconSize, plusIconSize, null);

    }

    private void drawAndUpdateCoolDown(Graphics g, int x, int y, double time, Skill skill) {
        // configure shit
        Graphics gg = g.create();
        gg.setColor(Color.RED);
        gg.setFont(CDFont);
        FontMetrics fm2 = gg.getFontMetrics(CDFont);
        Rectangle2D textRect = fm2.getStringBounds(Double.toString(time), g);

        // moar
        int strX = x + (skillBoxSize - (int)textRect.getWidth())/2;
        int strY = y + (skillBoxSize - (int)textRect.getHeight())/2 + fm2.getAscent();
        Timer CDTimer = new Timer();
        CDTimer.schedule(new TimerTask() {
            double currentTime;
            double rounded;
            String currentTimeStr;
            String displayedText;
            @Override
            public void run() {
                if (skill.isCooldown()) {
                    currentTime = skill.getCooldownTimeRemaining();
                    // Round dat shit
                    rounded = MathUtilities.round(currentTime, 1);
                    currentTimeStr = Double.toString(rounded);
                    // Concatante "s" for "seconds"
                    displayedText = currentTimeStr + "s";
                    gg.drawString(displayedText, strX, strY);
                }
                else {
                    CDTimer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
                    CDTimer.purge();   // Removes all cancelled tasks from this timer's task queue.
                }
            };
        }, 0, 30);
    }

    public void handleMouseClick(MouseEvent e) {
        int mouseX = e.getX();
        // For somee reason screen is 25 pixels shorter?
        int mouseY = e.getY() - 25;

        System.out.println("CLICKED X: " + mouseX + " Y: " + mouseY);

        int targetSkillNumber;

        // If within the Y range
        if (mouseY <= levelUpBoxBottomY && mouseY >= levelUpBoxTopY) {
            // Check which index of skill they selected
            if (mouseX >= levelUpBoxLeftX && mouseX <= levelUpBoxRightX) {
                int distanceFromStart = mouseX - levelUpBoxLeftX;

                int approximateBoxNumber = (distanceFromStart/skillBoxSize) + 1;
                System.out.println("Approximately clicked box #: " + approximateBoxNumber);
                int gapWidth = levelUpBoxLeftX + levelUpBoxLeftXDistanceGap - (levelUpBoxLeftX + levelUpBoxSize);

                int rightEdgeOfDesiredBox = levelUpBoxLeftX + (approximateBoxNumber*levelUpBoxSize) + ((approximateBoxNumber-1)*gapWidth);
                // Means we're in a gap! didnt hit a box.
                if ( levelUpBoxLeftX + distanceFromStart > rightEdgeOfDesiredBox ) {
                    System.out.println("hit a gap");
                    return;
                }

                // Get the skill (arrays are indexed by 0 (; )
                targetSkillNumber = approximateBoxNumber-1;
                Skill desiredSkill = skills.get(targetSkillNumber);

                // level up
                levelUpSkill(desiredSkill);
            }
        }
    }

    private void levelUpSkill(Skill skill) {
        skill.incrementLevel();
        stats.decrementSkillPoints();
    }

    @Override
    public void scaleView() {
        // Slot sizes
        skillBoxSize = (int) (((double) getScreenWidth() / 15));
        imgSize = (int) ((double) skillBoxSize * .80);
        levelUpBoxSize = (int) ((double) skillBoxSize * .60);
        plusIconSize = (int) ((double) skillBoxSize * .45);

        // Rect size
        skillRectWidth = skillCount * skillBoxSize;
        skillRectHeight = skillBoxSize;

        // Coordinates for skill box
        skillBoxStartX = (getScreenWidth() - skillRectWidth)/2;
        skillBoxStartY = getScreenHeight() - skillRectHeight - 25;


        // Coordinates for level up boxes
        levelUpBoxLeftX = skillBoxStartX + (skillBoxSize - levelUpBoxSize)/2;
        int skillBoxRight = skillBoxStartX + skillBoxSize;
        levelUpBoxLeftXDistanceGap = skillBoxRight + (skillBoxSize - levelUpBoxSize)/2 - levelUpBoxLeftX;
        levelUpBoxRightX = levelUpBoxLeftX + ((skillCount-1) * levelUpBoxLeftXDistanceGap) + levelUpBoxSize;
        levelUpBoxBottomY = skillBoxStartY;
        levelUpBoxTopY = levelUpBoxBottomY - levelUpBoxSize;



        // Font
        keyBindFontSize = getScreenWidth()/100;
        skillLabelFontSize = keyBindFontSize;
        CDFontSize = getScreenWidth()/50;

        skillLabelFont = new Font("Courier New", Font.PLAIN, skillLabelFontSize);

        // Setup keybind font
        keyBindFont = new Font("Courier New", Font.BOLD, keyBindFontSize);
        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.FAMILY, keyBindFont.getFamily());
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_EXTRABOLD);
        attributes.put(TextAttribute.SIZE, (int) (keyBindFont.getSize() * 1.5));
        keyBindFont = Font.getFont(attributes);

        CDFont = new Font("Courier New", Font.PLAIN, CDFontSize);
    }


}
