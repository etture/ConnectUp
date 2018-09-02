package kr.soen.connectup4;

/**
 * Created by ettur on 2017-11-29.
 */

public class ItemBulletinList extends ItemParent {
    private String name, title;
    private int index;

    public ItemBulletinList(int index){
        super();
        this.title = StudentBulletinData.TITLE[index];
        this.index = index;
        this.name = null;
        type = ITEM_BULLETIN_LIST;
    }

    public ItemBulletinList(String name, String title){
        super();
        this.name = name;
        this.title = title;
        this.index = -1;
        type = ITEM_BULLETIN_LIST;
    }

    public String getTitle(){
        return title;
    }
    public String getName(){
        return name;
    }
    public int getIndex(){
        return index;
    }
}
