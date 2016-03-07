package models.items.takeable.equippable;

import models.conditions.ConditionList;
import models.entities.Entity;
import models.Equipment;
import models.items.takeable.TakeableItem;
import models.stats.StatModificationList;

/**
 * Created by aseber on 2/21/16.
 */
public class EquippableItem extends TakeableItem {

    protected Equipment.Component component;
    protected StatModificationList onEquipModifications = new StatModificationList();
    protected ConditionList equipConditions = new ConditionList();

    protected int requiredLv;

    //TODO:Go back to the items classes and put the constants in oh boi
    protected final int WOODCOST = 10;
    protected final int IRONCOST = 50;
    protected final int STEELCOST = 100;
    protected final int MITHRILCOST = 200;
    protected final int GOLDCOST = 400;
    protected final int RUNITECOST = 800;
    protected final int CHUCKNORRISCOST = 10000;
    protected final int WOODATK = 10;
    protected final int IRONATK = 20;
    protected final int STEELATK = 30;
    protected final int MITHRILATK = 40;
    protected final int GOLDATK = 60;
    protected final int RUNITEATK = 80;
    protected final int CHUCKNORRISATK = 200;
    protected final int WOODDEF= 5;
    protected final int IRONDEF = 10;
    protected final int STEELDEF = 20;
    protected final int MITHRILDEF = 30;
    protected final int GOLDDEF = 40;
    protected final int RUNITEDEF = 60;
    protected final int CHUCKNORRISDEF = 100;
    protected final int WOODWEIGHT = 2;
    protected final int IRONWEIGHT = 4;
    protected final int STEELWEIGHT = 6;
    protected final int MITHRILWEIGHT = 8;
    protected final int GOLDWEIGHT = 10;
    protected final int RUNITEWEIGHT = 12;
    protected final int CHUCKNORRISWEIGHT = 0;
    protected final int WOODLV = 1;
    protected final int IRONLV = 5;
    protected final int STEELLV = 10;
    protected final int MITHRILLV = 20;
    protected final int GOLDLV = 30;
    protected final int RUNITELV = 40;
    protected final int CHUCKNORRISLV = 60;

    protected int itemWeight;
    //Currently range is kind of bad design since range is only for weapons but putting it here
    //For now In case of special stuff that increases range make it
    protected int range;

    protected final int RANGE = 0;

    //Used for weapons
    protected final int SMASHERRANGE = 1;
    protected final int SNEAKRANGE = 4;
    protected final int SUMMONERRANGE = 4;

    //Concatenate the ranges and get 144? Last number of muliplication table in
    //elementary school?  Illuminati confirmed

    @Override
    public boolean onTouch(Entity entity) {
        // This super call will add to inventory
        // Via takeableitem's implementation
        return super.onTouch(entity);
    }

    // Equivalent to equipping.
    public final void onUse(Entity entity) {
        if (equipConditions.checkCondition()) {
            entity.applyStatMod(onEquipModifications);
            // add item to inventory

        }

    }
    // function for unequipping.
    public final void onUnequip(Entity entity) {
        entity.removeStatMod(onEquipModifications);
    }

    public final Equipment.Component getComponent() { return component; }
    public final StatModificationList getOnEquipModifications() { return onEquipModifications; }
    public final ConditionList getEquipConditions(){ return equipConditions; }

    @Override
    public String getType(){
        return "equippable";
    }

    @Override
    public boolean isEquipable() {
        return true;
    }
}
