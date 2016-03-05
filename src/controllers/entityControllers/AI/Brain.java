package controllers.entityControllers.AI;

import controllers.entityControllers.AI.Movement.MotorCortex;
import controllers.entityControllers.AI.Thought.Decision;
import controllers.entityControllers.AI.Thought.FrontalLobe;
import controllers.entityControllers.AI.Thought.Personalities;
import controllers.entityControllers.AI.Vision.VisualCortex;
import controllers.entityControllers.AI.Vision.VisualInformation;
import models.entities.npc.NPC;

/**
 * Created by Bradley on 3/5/16.
 */
public class Brain {

    // TODO: I don't think that the brain should have the npc (it should only be the other way around). Try to find a way
    // around having to do this.
    private NPC npc;
    private VisualCortex visualCortex;
    private FrontalLobe frontalLobe;
    private MotorCortex motorCortex;

    public Brain(NPC npc, Personalities personality){
        this.npc = npc;
        visualCortex = new VisualCortex(npc);
        frontalLobe = new FrontalLobe(npc, personality);
        motorCortex = new MotorCortex(npc);
    }

    // This is called once per cycle to determine what the AI should do.
    public void think(){
        VisualInformation visualInfo = visualCortex.process(); // What do you see in your surroundings.
        Decision decision = frontalLobe.process(visualInfo); // What should you do based off of what you saw.
        motorCortex.process(decision); // Carry out the actions.
    }

    public void processAttack(){

    }

    // This will return true if the pickpocket was succesfull. It will also initiate an attack if the npc noticed or got mad.
    public boolean processPickpocket(double entityPickpocketAbility){
        return true;
    }

    // Returns a boolean if the NPC will be persuaded.
    public boolean processPersuasion(double entityPersuasiveness){
        return true;
    }
}
