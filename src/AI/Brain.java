package AI;

import AI.Memory.Memory;
import AI.Movement.MotorCortex;
import AI.Personality.Personality;
import AI.Thought.FrontalLobe;
import AI.Vision.VisualCortex;
import models.entities.Entity_Action_Interface;

/**
 * Created by Bradley on 3/5/16.
 */
public class Brain {

    // TODO: I don't think that the brain should have the npc (it should only be the other way around). Try to find a way
    // around having to do this.
    protected Entity_Action_Interface npc;
    protected VisualCortex visualCortex;
    protected FrontalLobe frontalLobe;
    protected MotorCortex motorCortex;
    protected Memory memory;

    public Brain(Entity_Action_Interface npc, Personality personality) {
        this.npc = npc;
        memory = new Memory(npc, personality);
        visualCortex = new VisualCortex(memory);
        frontalLobe = new FrontalLobe(memory);
        motorCortex = new MotorCortex(memory);
    }

    // This is called once per cycle to determine what the AI_Interface should do.
    public void think() {
        visualCortex.process(); // What do you see in your surroundings.
        frontalLobe.process(); // What should you do based off of what you saw.
        motorCortex.process(); // Carry out the actions.
        memory.process();
    }

    public void processAttack() {

    }

    // This will return true if the pickpocket was succesfull. It will also initiate an attack if the npc noticed or got mad.
    public boolean processPickpocket(double entityPickpocketAbility) {
        return true;
    }

    // Returns a boolean if the NPC will be persuaded.
    public boolean processPersuasion(double entityPersuasiveness) {
        return true;
    }

}
