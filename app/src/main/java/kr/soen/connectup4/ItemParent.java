package kr.soen.connectup4;

/**
 * Created by ettur on 2017-11-23.
 */

public class ItemParent {
    public static final int ITEM_TITLE = 0;
    public static final int ITEM_AD = 1;
    public static final int ITEM_ENTRY = 2;
    public static final int ITEM_SEARCHKEYWORD = 3;
    public static final int ITEM_CLASSIFIED = 4;
    public static final int ITEM_EVENT = 5;
    public static final int ITEM_CLASSIFIED_MINI = 6;
    public static final int ITEM_EVENT_MINI = 7;
    public static final int ITEM_BULLETIN = 8;
    public static final int ITEM_BULLETIN_LIST = 9;

    protected int type;

    public ItemParent(){}

    public int getType(){
        return type;
    }
}
