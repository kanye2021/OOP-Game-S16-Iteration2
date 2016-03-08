package controllers.entityControllers.AI.Thought;

/**
 * Created by Bradley on 3/5/16.
 */
public enum Decision {

    ATTACK,
    TRADE,
    GET_ITEM,
    FOLLOW,
    DEFAULT;

    private Object attachment; // Kinda wierd, but decisions need to have a thing attached to them (Either entity or item.)

    public void addAttachment(Object attachment){
        this.attachment = attachment;
    }
    public Object getAttachment(){
        return attachment;
    }
}
