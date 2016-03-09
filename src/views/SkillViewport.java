package views;

import models.skills.Skill;
import models.skills.SkillList;
import org.w3c.dom.css.Rect;
import utilities.IOUtilities;
import utilities.MathUtilities;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by sergiopuleri on 3/8/16.
 */
public class SkillViewport extends View {

    private final String SKILL_RESOURCE_BASE_PATH = IOUtilities.getFileSystemDependentPath("./src/res/skills/");

    // Model attributes
    private SkillList skills;
    private int skillCount;

    // Font
    private int skillLabelFontSize;
    private int keyBindFontSize;
    private int CDFontSize;
    private Font CDFont;
    private Font skillLabelFont;
    private Font keyBindFont;


    // Scalable Viewport Attributes
    private int skillRectWidth;
    private int skillRectHeight;
    private int skillBoxSize;
    private int imgSize;

    // Font attributes
    private FontMetrics fm;



    public SkillViewport(int width, int height, Display display, SkillList skills) {
        super(width, height, display);
        this.skills = skills;
        this.skillCount = skills.size();
        scaleView();
    }

    @Override
    public void render(Graphics g) {
        // Draw erre-thang
        drawSkillRectAndBoxes(g);
    }

    private void drawSkillRectAndBoxes(Graphics g) {

        // Draw outline
        Color goldTrim = new Color(223, 196, 99);
        g.setColor(goldTrim);
        int rect_x = (getScreenWidth() - skillRectWidth)/2;
        int rect_y = getScreenHeight() - skillRectHeight - 25;

        // Fill
        g.drawRect(rect_x, rect_y, skillRectWidth, skillRectHeight);
        g.setColor(Color.darkGray);
        g.fillRect(rect_x, rect_y, skillRectWidth, skillRectHeight);


        // Values needed to draw
        int skillBoxX = rect_x;
        int skillBoxY = rect_y;
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
            g.setFont(keyBindFont);
            skillText = currentSkill.getName();
            fm = g.getFontMetrics(keyBindFont);
            textRect = fm.getStringBounds(skillText, g);
            drawSkillName(g, skillText, textRect, skillBoxY, skillBoxX);

            // Draw Keybind in Upper Left Corner
            keyBind = "3"; //skill.getKeyBind() <-- possibily???
            textRect = fm.getStringBounds(keyBind, g);
            g.drawString(keyBind, skillBoxX + (int)textRect.getWidth()/2 + topAndSideMargin, skillBoxY + (int)textRect.getHeight() - 1  + topAndSideMargin);

            // Draw Skill level in Upper Right Corner
            String skillLvl = "Lvl " + Integer.toString(currentSkill.getLevel());
            textRect = fm.getStringBounds(skillLvl, g);
            g.drawString(skillLvl, skillBoxX + skillBoxSize - (int)textRect.getWidth() - topAndSideMargin, skillBoxY + (int)textRect.getHeight() + topAndSideMargin);

            // Draw cool down in center, if skill is coolin down
            if (currentSkill.isCooldown()) {
                double currentTime = currentSkill.getCooldownTimeRemaining();
                drawAndUpdateCoolDown(g, skillBoxX, skillBoxY, currentTime, currentSkill);
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
        }, 0, 500);
    }

    @Override
    public void scaleView() {
        // Slot sizes
        skillBoxSize = (int) (((double) getScreenWidth() / 15));
        imgSize = (int) ((double) skillBoxSize * .80);

        // Rect size
        skillRectWidth = skillCount * skillBoxSize;
        skillRectHeight = skillBoxSize;

        // Font
        skillLabelFontSize = getScreenWidth()/130;
        keyBindFontSize = getScreenWidth()/100;
        CDFontSize = getScreenWidth()/50;

        skillLabelFont = new Font("Helvetica", Font.PLAIN, skillLabelFontSize);
        keyBindFont = new Font("Helvetica", Font.PLAIN, keyBindFontSize);
        CDFont = new Font("Helvetica", Font.PLAIN, CDFontSize);
    }



}
