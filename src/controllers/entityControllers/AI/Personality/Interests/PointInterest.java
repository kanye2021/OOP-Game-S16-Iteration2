package controllers.entityControllers.AI.Personality.Interests;

import java.awt.*;

/**
 * Created by aseber on 3/13/16.
 */
public abstract class PointInterest extends Interest {

    public PointInterest(double interestLevel) {

        super(interestLevel);

    }

    public final Type getInterestType() { return Type.POINT; }
    public abstract Interest createRuntimeInterest(Point pointOfInterest);

}
