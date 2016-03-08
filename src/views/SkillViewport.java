package views;

import models.skills.Skill;
import models.skills.SkillList;
import models.stats.Stats;
import utilities.IOUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by sergiopuleri on 3/8/16.
 */
public class SkillViewport extends View {

    private final String SKILL_RESOURCE_BASE_PATH = IOUtilities.getFileSystemDependentPath("./src/res/skills/");

    // Model attributes
    private SkillList skills;
    private int skillCount;
    private Timer coolDownTimer;

    // Font
    private int regFontSize;
    private int keyBindFontSize;
    private Font regFont;
    private Font keyBindFont;


    // Scalable Viewport Attributes
    private int skillRectWidth;
    private int skillRectHeight;
    private int skillBoxSize;
    private int imgSize;

    // Font attributes
    private FontMetrics fm;

    // boolean whether or not to show details.
//    private boolean showDetails;

    public SkillViewport(int width, int height, Display display, SkillList skills) {
        super(width, height, display);
        this.skills = skills;
        this.skillCount = skills.size();
        scaleView();
    }

    @Override
    public void render(Graphics g) {
        // Display the avatar's level
        drawSkillRectAndBoxes(g);


    }

    private void drawSkillRectAndBoxes(Graphics g) {

        // Draw outline
        Color goldTrim = new Color(223, 196, 99);
        g.setColor(goldTrim);
        int rect_x = (getScreenWidth() - skillRectWidth)/2;
        int rect_y = getScreenHeight() - skillRectHeight*3;

        // Fill
        g.drawRect(rect_x, rect_y, skillRectWidth, skillRectHeight);
        g.setColor(Color.darkGray);
        g.fillRect(rect_x, rect_y, skillRectWidth, skillRectHeight);

        // Draw each box
        int skillBoxX = rect_x;
        int skillBoxY = rect_y;
        Rectangle2D skillRect;
        Rectangle2D textRect;
        String skillText;
        String keyBind;
        Skill currentSkill;
        Color coolDownFontColor = Color.RED;
        for(int i = 0; i < skillCount; i++) {
            currentSkill = skills.get(i);

            // Draw skill box
            g.setColor(goldTrim);
            g.drawRect(skillBoxX, skillBoxY, skillBoxSize, skillBoxSize);

            // Draw skill name
            g.setColor(Color.white);
            g.setFont(keyBindFont);
            skillText = currentSkill.getName();
            fm = g.getFontMetrics(keyBindFont);
            textRect = fm.getStringBounds(skillText, g);
            g.drawString(skillText, skillBoxX + (skillBoxSize - (int)textRect.getWidth())/2, skillBoxY + (skillRectHeight -  (int) textRect.getHeight()) + fm.getAscent() );
            skillBoxX += skillBoxSize;

            // Draw Keybind in Upper Left Corner
            keyBind = "Q"; //skill.getKeyBind() <-- possibily???
            textRect = fm.getStringBounds(keyBind, g);
            g.drawString(keyBind, skillBoxX, skillBoxY + (int)textRect.getHeight() - 1 );

            // Draw cooldown in center
            if (currentSkill.isCooldown()) {
                g.setColor(coolDownFontColor);
                int currentTime = currentSkill.getCooldownTime();
            }

        }
    }


    // TODO: make boxes larger, update fonts based on them.
    @Override
    public void scaleView() {
        // Slot sizes
        skillBoxSize = (int) (((double) getScreenWidth() / 24));
        imgSize = (int) ((double) skillBoxSize * .80);

        // Rect size
        skillRectWidth = skillCount * skillBoxSize;
        skillRectHeight = skillBoxSize;

        // Font
        regFontSize = getScreenWidth()/100;
        keyBindFontSize = getScreenWidth()/150;

        regFont = new Font("Helvetica", Font.PLAIN, regFontSize);
        keyBindFont = new Font("Helvetica", Font.PLAIN, keyBindFontSize);
    }

}
