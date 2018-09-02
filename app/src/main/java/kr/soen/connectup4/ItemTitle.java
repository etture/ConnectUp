package kr.soen.connectup4;

/**
 * Created by ettur on 2017-11-23.
 */

public class ItemTitle extends ItemParent{
    private String title;

    public ItemTitle(){
        super();
    }

    public ItemTitle(String title){
        super();
        this.title = title;
        type = ITEM_TITLE;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }


}
