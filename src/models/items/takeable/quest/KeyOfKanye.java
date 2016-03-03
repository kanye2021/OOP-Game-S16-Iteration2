package models.items.takeable.quest;

import views.sprites.Sprite;

/**
 * Created by aseber on 3/2/16.
 */
public class KeyOfKanye extends QuestItem {

    public KeyOfKanye() {

        ID = ItemDictionary.KEY_OF_KANYE;
        name = "Key of Kanye";
        description = "A key to the gate of Kanye";
        sprite = new Sprite("./src/res/items/takeable/quest_items/KanyeKey.png");

    }

}
