package kr.soen.connectup4;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ettur on 2017-11-29.
 */

public class ItemBulletin extends ItemParent {

    private String text;
    private int index;

    public ItemBulletin(int index){
        super();
        this.text = StudentBulletinData.MAINBULLETIN[index];
        this.index = index;
        type = ITEM_BULLETIN;

    }

    public String getText(){
        return text;
    }
    public int getIndex(){
        return index;
    }
}
