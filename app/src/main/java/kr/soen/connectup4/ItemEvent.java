package kr.soen.connectup4;


/**
 * Created by ettur on 2017-11-28.
 */

public class ItemEvent extends ItemParent {
    private String name;
    private String date;
    private String location;

    private int imgID;
    private int index;

    public ItemEvent(int index){
        super();
        this.name = StudentEventData.NAME[index];
        this.date = StudentEventData.PERIOD[index];
        this.location = StudentEventData.LOCATION[index];
        this.imgID = StudentEventData.THUMBNAIL[0];
        this.index = index;
        type = ITEM_EVENT;
    }

    public String getName(){
        return name;
    }

    public String getDate(){
        return date;
    }
    public String getLocation(){
        return location;
    }
    public int getImgID(){
        return imgID;
    }
    public int getIndex(){return index;}
}
