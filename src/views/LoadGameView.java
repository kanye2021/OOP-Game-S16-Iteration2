package views;

import controllers.LoadGameViewController;
import controllers.StartMenuViewController;
import utilities.IOUtilities;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by dyeung on 2/18/16.
 */
/*
  //----------View Design stuff --------
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 50;
    private final int START_POSITION = 100;
    //--------File Path stuff -------
    private String saveFilePath ="src/res/save_files/";
    private File[] listOfSaveFiles;
    public LoadGameView(){
        saveFilePath = saveFilePath.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));
        this.viewController = new LoadGameController(this);
//        File folder = new File(saveFilePath);
        listOfSaveFiles = ((LoadGameController)viewController).getFileNames();
        //getNewFiles();
    }
    public void getNewFiles(){ //Function is used to update the list of save files in the folder
        listOfSaveFiles = ((LoadGameController)this.viewController).loadNewFolder();
        System.out.println("LGV: " + listOfSaveFiles.length);
    }
 */
public class LoadGameView extends View {
    //Constants
    private final String SAVE_FILE_LOCATION = IOUtilities.getFileSystemDependentPath("./src/res/save_files/");

    //Scalable variables for resizing
    private int buttonWidth;
    private int buttonHeight;
    private int titleButtonMargin;
    private Font titleFont;
    private Font buttonFont;

    //Properties
    ArrayList<File> listOfSaveFiles;
    int selectedOption;

    public LoadGameView (int width, int height){
        super(width, height);
        listOfSaveFiles = new ArrayList<>();
        selectedOption = 0;
    }
    public void render(Graphics g){
        retrieveSaveFiles();
        if (listOfSaveFiles.isEmpty()) {
            renderTitle(g,false); //Changes the message of the title to no save files present
        }else {
            renderTitle(g,true);
            renderSaveFiles(g);
        }
    }
    public void scaleView(){ //Remainder that this is called from class view
        titleFont = new Font("Courier New", Font.PLAIN, getScreenWidth() / 19 );
        buttonWidth = getScreenWidth() / 6;
        buttonHeight = getScreenHeight() / 25;
        buttonFont = new Font("Helvetica", Font.BOLD, getScreenWidth() / 86);
        titleButtonMargin = (int) (getScreenHeight() * 0.15);

    }

    public void renderTitle(Graphics g, boolean hasTitle){
        // Draw title
        FontMetrics fm = g.getFontMetrics(titleFont);
        g.setFont(titleFont);
        g.setColor(Color.white);
        String titleString = "Select file";
        if (!hasTitle) {
            titleString = "No games found!";
        }
        Rectangle2D rec = fm.getStringBounds(titleString, g);
        int xTitle = (getScreenWidth()/2 - (int) (rec.getWidth()/2));
        int yTitle = getScreenHeight()/6 + (int) (rec.getHeight() / 2);
        g.drawString(titleString, xTitle , yTitle );
    }
    public void renderSaveFiles(Graphics g){
        int START_FILES = g.getFontMetrics(titleFont).getHeight() + titleButtonMargin;
        FontMetrics fm = g.getFontMetrics(buttonFont);
        g.setFont(buttonFont);
        //display list of files
        for (int i = 0; i < listOfSaveFiles.size(); i++) {
            File file = listOfSaveFiles.get(i);
            // Exclude .DS_Store file lol.
            if (file.isFile() && !file.getName().equals(".DS_Store")) {
                String fileName = file.getName();
                Rectangle2D rectangle = fm.getStringBounds(fileName, g);

                int boxX = getScreenWidth() / 2 - buttonWidth / 2;
                int boxY = buttonHeight * i + START_FILES;
                int boxDX = buttonWidth;
                int boxDY = buttonHeight;

                int stringX = getScreenWidth() / 2 - (int) (rectangle.getWidth() / 2);
                int stringY = i * buttonHeight + (int) (rectangle.getHeight() / 2) + fm.getAscent() + START_FILES;

                Color primaryColor;
                Color secondaryColor;

                if (i == selectedOption) {
                    primaryColor = Color.WHITE;
                    secondaryColor = Color.BLACK;

                } else {
                    primaryColor = Color.BLACK;
                    secondaryColor = Color.WHITE;

                }
                g.setColor(primaryColor);
                g.fillRect(boxX, boxY, boxDX, boxDY);
                g.setColor(secondaryColor);
                g.drawString(fileName, stringX, stringY);
            }
        }
    }
    //Function is used to look into the save file folder and retrieve the list of proper save files
    public void retrieveSaveFiles(){
        File folder = new File(SAVE_FILE_LOCATION);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File[] f = folder.listFiles();
        //TODO: Should there be a limit to the amount of save files?
        //TODO: Might cause a problem with screen if the there are too many save files
        int limit = 5;
        listOfSaveFiles.clear(); // Needs to reset the array whenever it retrieves the new set of files
        for (int i = 0; i < f.length && i <= limit; i++) {
            File current = f[i];
            // Checks if its a valid name (IE not .DS_Store) and adds it to the array list
            if (checkValidFileName(current.getName()))  {
                listOfSaveFiles.add(current);
            }
        }
    }
    public void updateOption(int newOption){
        selectedOption = newOption;
    }
    //Helper functions
    public boolean checkValidFileName(String name) {
        //TODO: Simple file name but might need to be upgraded for future use (IE what about .bd.sf.xml or a.xml.df.sf)
        //file name has to contain the .xml extension but can not be just ".xml"
        if (name.contains(".xml") && !name.equals(".xml")){
            return true;
        }
        return false;
    }
    //Accessor
    public ArrayList<File> getListOfSaveFiles(){
        return listOfSaveFiles;
    }
}
