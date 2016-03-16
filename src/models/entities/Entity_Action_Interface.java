package models.entities;

import utilities.EntityAction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by aseber on 3/15/16.
 */
public interface Entity_Action_Interface {

    LinkedList<EntityAction> actions = new LinkedList<>();

    void pushAction(EntityAction newAction);

    void consumeAction();

}
