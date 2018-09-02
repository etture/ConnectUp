package kr.soen.connectup4;

import android.widget.ImageView;

/**
 * Created by ettur on 2017-11-23.
 */

public class ItemAd extends ItemParent {
    int imgID;

    public ItemAd(int resID){
        super();
        type = ITEM_AD;
        imgID = resID;
    }

    public int getImgID(){
        return imgID;
    }
}
