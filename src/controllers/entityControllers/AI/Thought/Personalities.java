package controllers.entityControllers.AI.Thought;

/**
 * Created by Bradley on 3/5/16.
 */
public enum Personalities {

    HITLER(1.0, 0.0, 0.0, 1.0, 1.0), // Will attack no matter what
    HOSTILE(0.9, 0.1, 0.1, 0.9, 1.0), // Will almost definitely attack
    ANGRY(0.7, 0.2, 0.2, 0.5, 1.0), // Is very likely to attack
    IRITABLE(0.6, 0.5, 0.5, 0.4, 0.8), // Will probably attack
    AGNOSTIC(0.1, 0.1, 0.1, 0.3, 0.1 ), // Doesn't really want to help or hurt you
    KIND(0.1, 0.8, 0.3, 0.4, 0.6), // WIll probably help you ,will still attack if you attack back.
    FRIENDLY(0.0, 1.0, 0.5, 0.3, 0.5), // Very friendly, will want to help you. Will still attack if he catches you pick pocekting
    PUSHOVER(0.0, 1.0, 0.8, 0.2, 0.3), // Will almost always let thing slide,
    MICHAEL_SERA(0.0, 1.0, 1.0, 0.0, 0.0), // Complete pussy. You can walk all over this NPC.
    PET(0.1, 0.0, 0.1, 0.3, 0.1); // Basically an agnostic that won't trade.
    
    private double attackOnSightProbability; // How likely he is to engage automatically.
    private double tradeProbability; // How likely it is he will want to trade.
    private double pursuadeProbablity; // How likelly you will be able to barder.
    private double vigilance; // How likely he is to see you pickpocketing.
    private double attackWhenAttackedProbability; // How likely he is to attack you if you pickpocket (and get caught) or attack.
    
    Personalities(double attackOnSightProbability, double tradeProbability, double pursuadeProbablity, double vigilance, double attackWhenAttackedProbability){
        this.attackOnSightProbability = attackOnSightProbability;
        this.tradeProbability = tradeProbability;
        this.pursuadeProbablity = pursuadeProbablity;
        this.vigilance = vigilance;
        this.attackWhenAttackedProbability = attackWhenAttackedProbability;
    }

    public double getAttackOnSightProbability(){
        return attackOnSightProbability;
    }

    public double getTradeProbability(){
        return tradeProbability;
    }

    public double getPursuadeProbablity(){
        return pursuadeProbablity;
    }

    public double getVigilance(){
        return vigilance;
    }

    public double getAttackWhenAttackedProbability(){
        return attackWhenAttackedProbability;
    }
}
