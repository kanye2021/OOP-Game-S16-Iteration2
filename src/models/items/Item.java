package models.items;

import models.entities.Entity;
import models.items.decorations.potionDecorations.smallDecoration;
import models.items.decorations.smasherDecorations.*;
import models.items.decorations.sneakDecorations.*;
import models.items.decorations.summonerDecorations.*;
import models.items.interactive.GateOfKanye;
import models.items.obstacle.Grave;
import models.items.obstacle.Octopus;
import models.items.obstacle.Statue;
import models.items.takeable.consumable.Potions.SmallHealthPotion;
import models.items.takeable.consumable.Potions.SmallManaPotion;
import models.items.takeable.equippable.boots.Special_Yeezy;
import models.items.takeable.equippable.boots.boots;
import models.items.takeable.equippable.chestplate.*;
import models.items.takeable.equippable.gloves.gloves;
import models.items.takeable.equippable.greaves.greaves;
import models.items.takeable.equippable.greaves.skirt;
import models.items.takeable.equippable.helmets.coif;
import models.items.takeable.equippable.helmets.hat;
import models.items.takeable.equippable.helmets.helmet;
import models.items.takeable.equippable.shield.shield;
import models.items.takeable.equippable.weapons.oneHanded.Special_EnergySword;
import models.items.takeable.equippable.weapons.oneHanded.sword;
import models.items.takeable.equippable.weapons.twoHanded.bow;
import models.items.takeable.equippable.weapons.twoHanded.staff;
import models.items.takeable.equippable.weapons.twoHanded.twoHandedSword;
import models.items.takeable.quest.KeyOfKanye;
import utilities.IOUtilities;
import views.sprites.Drawable;
import views.sprites.Sprite;

import java.awt.*;

/**
 * Created by aseber on 2/21/16.
 */
public abstract class Item {

    protected ItemDictionary ID;
    protected Drawable sprite;

    public static Image getBagImage() {
        return IOUtilities.getImageIcon(IOUtilities.getFileSystemDependentPath("./src/res/items/bag.png")).getImage();
    }

    public abstract boolean onTouch(Entity entity); // Method to describe what happens when an item is touched

    public ItemDictionary getID() {

        return ID;

    }

    public int getItemId() {
        return ID.getID();
    }

    public final void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public final boolean equals(Object o) {

        if (o instanceof Item) {

            Item otherItem = (Item) o;

            return this.getID() == otherItem.getID();

        }

        return false;

    }

    public Image getImage() {
        return sprite.getImage();
    }

    public abstract String getType();

    public enum ItemDictionary {

        // Helmets
        WOOD_HELMET(1000) {
            public Item createInstance() {
                return new helmet(new woodDecoration());
            }
        },
        IRON_HELMET(1001) {
            public Item createInstance() {
                return new helmet(new ironDecoration());
            }
        },
        STEEL_HELMET(1002) {
            public Item createInstance() {
                return new helmet(new steelDecoration());
            }
        },
        MITHRIL_HELMET(1003) {
            public Item createInstance() {
                return new helmet(new mithrilDecoration());
            }
        },
        GOLD_HELMET(1004) {
            public Item createInstance() {
                return new helmet(new goldDecoration());
            }
        },
        RUNITE_HELMET(1005) {
            public Item createInstance() {
                return new helmet(new runiteDecoration());
            }
        },

        SOFT_LEATHER_COIF(1006) {
            public Item createInstance() {
                return new coif(new softLeatherDecoration());
            }
        },
        HARD_LEATHER_COIF(1007) {
            public Item createInstance() {
                return new coif(new hardLeatherDecoration());
            }
        },
        STUDDED_LEATHER_COIF(1008) {
            public Item createInstance() {
                return new coif(new studdedLeatherDecoration());
            }
        },
        GREEN_D_COIF(1009) {
            public Item createInstance() {
                return new coif(new greenDragonhideDecoration());
            }
        },
        BLUE_D_COIF(1010) {
            public Item createInstance() {
                return new coif(new blueDragonhideDecoration());
            }
        },
        RED_D_COIF(1011) {
            public Item createInstance() {
                return new coif(new redDragonhideDecoration());
            }
        },
        BLACK_D_COIF(1012) {
            public Item createInstance() {
                return new coif(new blackDragonhideDecoration());
            }
        },

        BODY_HAT(1013) {
            public Item createInstance() {
                return new hat(new bodyDecoration());
            }
        },
        MIND_HAT(1014) {
            public Item createInstance() {
                return new hat(new mindDecoration());
            }
        },
        COSMIC_HAT(1015) {
            public Item createInstance() {
                return new hat(new cosmicDecoration());
            }
        },
        CHAOS_HAT(1016) {
            public Item createInstance() {
                return new hat(new chaosDecoration());
            }
        },
        NATURE_HAT(1017) {
            public Item createInstance() {
                return new hat(new natureDecoration());
            }
        },
        DEATH_HAT(1018) {
            public Item createInstance() {
                return new hat(new deathDecoration());
            }
        },
        LAW_HAT(1019) {
            public Item createInstance() {
                return new hat(new lawDecoration());
            }
        },

        WOOD_CHESTPLATE(1100) {
            public Item createInstance() {
                return new chestplate(new woodDecoration());
            }
        },
        IRON_CHESTPLATE(1101) {
            public Item createInstance() {
                return new chestplate(new ironDecoration());
            }
        },
        STEEL_CHESTPLATE(1102) {
            public Item createInstance() {
                return new chestplate(new steelDecoration());
            }
        },
        MITHRIL_CHESTPLATE(1103) {
            public Item createInstance() {
                return new chestplate(new mithrilDecoration());
            }
        },
        GOLD_CHESTPLATE(1104) {
            public Item createInstance() {
                return new chestplate(new goldDecoration());
            }
        },
        RUNITE_CHESTPLATE(1105) {
            public Item createInstance() {
                return new chestplate(new runiteDecoration());
            }
        },


        SOFT_LEATHER_HIDE(1106) {
            public Item createInstance() {
                return new hide(new softLeatherDecoration());
            }
        },
        HARD_LEATHER_HIDE(1107) {
            public Item createInstance() {
                return new hide(new hardLeatherDecoration());
            }
        },
        STUDDED_LEATHER_HIDE(1108) {
            public Item createInstance() {
                return new hide(new studdedLeatherDecoration());
            }
        },
        GREEN_D_HIDE(1109) {
            public Item createInstance() {
                return new hide(new greenDragonhideDecoration());
            }
        },
        BLUE_D_HIDE(1110) {
            public Item createInstance() {
                return new hide(new blueDragonhideDecoration());
            }
        },
        RED_D_HIDE(1111) {
            public Item createInstance() {
                return new hide(new redDragonhideDecoration());
            }
        },
        BLACK_D_HIDE(1112) {
            public Item createInstance() {
                return new hide(new blackDragonhideDecoration());
            }
        },

        BODY_ROBE1(1113) {
            public Item createInstance() {
                return new robe(new bodyDecoration());
            }
        },
        MIND_ROBE1(1114) {
            public Item createInstance() {
                return new robe(new mindDecoration());
            }
        },
        COSMIC_ROBE1(1115) {
            public Item createInstance() {
                return new robe(new cosmicDecoration());
            }
        },
        CHAOS_ROBE(1116) {
            public Item createInstance() {
                return new robe(new chaosDecoration());
            }
        },
        NATURE_ROBE(1117) {
            public Item createInstance() {
                return new robe(new natureDecoration());
            }
        },
        DEATH_ROBE(1118) {
            public Item createInstance() {
                return new robe(new deathDecoration());
            }
        },
        LAW_ROBE(1119) {
            public Item createInstance() {
                return new robe(new lawDecoration());
            }
        },


        DRAGON_HIDE(1120) {
            public Item createInstance() {
                return new hide(new dragonHideDecoration());
            }
        },
        LEATHER_HIDE(1121) {
            public Item createInstance() {
                return new hide(new leatherDecoration());
            }
        },
        WOOL_HIDE(1122) {
            public Item createInstance() {
                return new hide(new woolDecoration());
            }
        },

        BODY_ROBE(1123) {
            public Item createInstance() {
                return new robe(new bodyDecoration());
            }
        },
        COSMIC_ROBE(1124) {
            public Item createInstance() {
                return new robe(new cosmicDecoration());
            }
        },
        DRAGON_FUR_ROBE(1125) {
            public Item createInstance() {
                return new robe(new dragonFurDecoration());
            }
        },
        MIND_ROBE(1126) {
            public Item createInstance() {
                return new robe(new mindDecoration());
            }
        },


        WOOD_GREAVES(1200) {
            public Item createInstance() {
                return new greaves(new woodDecoration());
            }
        },
        IRON_GREAVES(1201) {
            public Item createInstance() {
                return new greaves(new ironDecoration());
            }
        },
        STEEL_GREAVES(1202) {
            public Item createInstance() {
                return new greaves(new steelDecoration());
            }
        },
        MITHRIL_GREAVES(1203) {
            public Item createInstance() {
                return new greaves(new mithrilDecoration());
            }
        },
        GOLD_GREAVES(1204) {
            public Item createInstance() {
                return new greaves(new goldDecoration());
            }
        },
        RUNITE_GREAVES(1205) {
            public Item createInstance() {
                return new greaves(new runiteDecoration());
            }
        },

        SOFT_LEATHER_CHAPS(1206) {
            public Item createInstance() {
                return new chaps(new softLeatherDecoration());
            }
        },
        HARD_LEATHER_CHAPS(1207) {
            public Item createInstance() {
                return new chaps(new hardLeatherDecoration());
            }
        },
        STUDDED_LEATHER_CHAPS(1208) {
            public Item createInstance() {
                return new chaps(new studdedLeatherDecoration());
            }
        },
        GREEN_D_CHAPS(1209) {
            public Item createInstance() {
                return new chaps(new greenDragonhideDecoration());
            }
        },
        BLUE_D_CHAPS(1210) {
            public Item createInstance() {
                return new chaps(new blueDragonhideDecoration());
            }
        },
        RED_D_CHAPS(1211) {
            public Item createInstance() {
                return new chaps(new redDragonhideDecoration());
            }
        },
        BLACK_D_CHAPS(1212) {
            public Item createInstance() {
                return new chaps(new blackDragonhideDecoration());
            }
        },

        WOOD_SKIRT(1212) {
            public Item createInstance() {
                return new skirt(new woodDecoration());
            }
        },
        IRON_SKIRT(1213) {
            public Item createInstance() {
                return new skirt(new ironDecoration());
            }
        },
        STEEL_SKIRT(1214) {
            public Item createInstance() {
                return new skirt(new steelDecoration());
            }
        },
        MITHRIL_SKIRT(1215) {
            public Item createInstance() {
                return new skirt(new mithrilDecoration());
            }
        },
        GOLD_SKIRT(1216) {
            public Item createInstance() {
                return new skirt(new goldDecoration());
            }
        },
        RUNITE_SKIRT(1217) {
            public Item createInstance() {
                return new skirt(new runiteDecoration());
            }
        },

        WOOD_BOOTS(1300) {
            public Item createInstance() {
                return new boots(new woodDecoration());
            }
        },
        IRON_BOOTS(1301) {
            public Item createInstance() {
                return new boots(new ironDecoration());
            }
        },
        STEEL_BOOTS(1302) {
            public Item createInstance() {
                return new boots(new steelDecoration());
            }
        },
        MITHRIL_BOOTS(1303) {
            public Item createInstance() {
                return new boots(new mithrilDecoration());
            }
        },
        GOLD_BOOTS(1304) {
            public Item createInstance() {
                return new boots(new goldDecoration());
            }
        },
        RUNITE_BOOTS(1305) {
            public Item createInstance() {
                return new boots(new runiteDecoration());
            }
        },

        // Gloves
        WOOD_GLOVES(1400) {
            public Item createInstance() {
                return new gloves(new woodDecoration());
            }
        },
        IRON_GLOVES(1401) {
            public Item createInstance() {
                return new gloves(new ironDecoration());
            }
        },
        STEEL_GLOVES(1402) {
            public Item createInstance() {
                return new gloves(new steelDecoration());
            }
        },
        MITHRIL_GLOVES(1403) {
            public Item createInstance() {
                return new gloves(new mithrilDecoration());
            }
        },
        GOLD_GLOVES(1404) {
            public Item createInstance() {
                return new gloves(new goldDecoration());
            }
        },
        RUNITE_GLOVES(1405) {
            public Item createInstance() {
                return new gloves(new runiteDecoration());
            }
        },

        // Shields
        WOOD_SHIELD(1500) {
            public Item createInstance() {
                return new shield(new woodDecoration());
            }
        },
        IRON_SHIELD(1501) {
            public Item createInstance() {
                return new shield(new ironDecoration());
            }
        },
        STEEL_SHIELD(1502) {
            public Item createInstance() {
                return new shield(new steelDecoration());
            }
        },
        MITHRIL_SHIELD(1503) {
            public Item createInstance() {
                return new shield(new mithrilDecoration());
            }
        },
        GOLD_SHIELD(1504) {
            public Item createInstance() {
                return new shield(new goldDecoration());
            }
        },
        RUNITE_SHIELD(1505) {
            public Item createInstance() {
                return new shield(new runiteDecoration());
            }
        },

        // One handed weapons
        WOOD_SWORD(1600) {
            public Item createInstance() {
                return new sword(new woodDecoration());
            }
        },
        IRON_SWORD(1601) {
            public Item createInstance() {
                return new sword(new ironDecoration());
            }
        },
        STEEL_SWORD(1602) {
            public Item createInstance() {
                return new sword(new steelDecoration());
            }
        },
        MITHRIL_SWORD(1603) {
            public Item createInstance() {
                return new sword(new mithrilDecoration());
            }
        },
        GOLD_SWORD(1604) {
            public Item createInstance() {
                return new sword(new goldDecoration());
            }
        },
        RUNITE_SWORD(1605) {
            public Item createInstance() {
                return new sword(new runiteDecoration());
            }
        },

        // Two handed weapons
        WOOD_TWO_HAND_SWORD(1700) {
            public Item createInstance() {
                return new twoHandedSword(new woodDecoration());
            }
        },
        IRON_TWO_HAND_SWORD(1701) {
            public Item createInstance() {
                return new twoHandedSword(new ironDecoration());
            }
        },
        STEEL_TWO_HAND_SWORD(1702) {
            public Item createInstance() {
                return new twoHandedSword(new steelDecoration());
            }
        },
        MITHRIL_TWO_HAND_SWORD(1703) {
            public Item createInstance() {
                return new twoHandedSword(new mithrilDecoration());
            }
        },
        GOLD_TWO_HAND_SWORD(1704) {
            public Item createInstance() {
                return new twoHandedSword(new goldDecoration());
            }
        },
        RUNITE_TWO_HAND_SWORD(1705) {
            public Item createInstance() {
                return new twoHandedSword(new runiteDecoration());
            }
        },

        // Bows

        WOOD_BOW(1900) {public Item createInstance() {return new bow(new woodDecoration());}},
        IRON_BOW(1901) {public Item createInstance() {return new bow(new ironDecoration());}},
        STEEL_BOW(1902) {public Item createInstance() {return new bow(new steelDecoration());}},
        MITHRIL_BOW(1903) {public Item createInstance() {return new bow(new mithrilDecoration());}},
        GOLD_BOW(1904) {public Item createInstance() {return new bow(new goldDecoration());}},
        RUNITE_BOW(1905) {public Item createInstance() {return new bow(new runiteDecoration());}},

        // Staff
        WOOD_STAFF(2100) {public Item createInstance() {return new staff(new woodDecoration());}},
        IRON_STAFF(2101) {public Item createInstance() {return new staff(new ironDecoration());}},
        STEEL_STAFF(2102) {public Item createInstance() {return new staff(new steelDecoration());}},
        MITHRIL_STAFF(2103) {public Item createInstance() {return new staff(new mithrilDecoration());}},
        GOLD_STAFF(2104) {public Item createInstance() {return new staff(new goldDecoration());}},
        RUNITE_STAFF(2105) {public Item createInstance() {return new staff(new runiteDecoration());}},


        SWAMPHACKS_SHIRT(1800) {
            public Item createInstance() {
                return new Special_SwampHacksShirt();
            }
        },
        YEEZY(1801) {
            public Item createInstance() {
                return new Special_Yeezy();
            }
        },
        ENERGY_SWORD(1802) {
            public Item createInstance() {
                return new Special_EnergySword();
            }
        },


        // Obstacles
        GRAVE(2000) {
            public Item createInstance() {
                return new Grave();
            }
        },
        OCTOPUS(2001) {
            public Item createInstance() {
                return new Octopus();
            }
        },
        STATUE(2002) {
            public Item createInstance() {
                return new Statue();
            }
        },

        // Interactive items
        GATE_OF_KANYE(5000) {
            public Item createInstance() {
                return new GateOfKanye();
            }
        },

        // Quest items
        KEY_OF_KANYE(6000) {
            public Item createInstance() {
                return new KeyOfKanye();
            }
        },

        //Potions
        SMALL_HEALTH_POTION(7001) {
            public Item createInstance() {
                return new SmallHealthPotion(new smallDecoration());
            }
        },
        SMALL_MANA_POTION(7011) {
            public Item createInstance() {
                return new SmallManaPotion(new smallDecoration());
            }
        };


        private int ID;

        ItemDictionary(int ID) {

            this.ID = ID;

        }

        // Not sure if this is the best way to get an instance of an item from an id
        // Will be used when putting items on the map and loading + instantiating them.
        public static Item itemFromID(int id) {
            for (ItemDictionary entry : values()) {
                if (entry.getID() == id) {
                    return entry.createInstance();
                }
            }
            // Item with provided id not found..
            return null;
        }

        public static ItemDictionary getDictionaryItemFromID(int id) {

            for (int i = 0; i < values().length; i++) {

                if (values()[i].getID() == id) {

                    return values()[i];

                }

            }

            return null;

        }

        public abstract Item createInstance();

        public int getID() {

            return ID;

        }

    }


}
