package kr.soen.connectup4;


import android.support.v7.app.AppCompatActivity;

/**
 * Created by ettur on 2017-11-23.
 */

public class ItemEntry extends ItemParent{

    private String title;
    private String content;
    private int index;


    public ItemEntry(String title, String content, int index){
        super();
        this.title = title;
        this.content = content;
        this.index = index;
        type = ITEM_ENTRY;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public int getIndex(){
        return index;
    }


}
