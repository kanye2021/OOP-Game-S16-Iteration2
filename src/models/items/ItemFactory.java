package models.items;

/**
 * Created by aseber on 2/21/16.
 */
public class ItemFactory {

    public static Item createItem(int id) {

        switch (id) {
            case 1:
                return new WoodHelmet();
            default:
                return null;
        }

    }

}
