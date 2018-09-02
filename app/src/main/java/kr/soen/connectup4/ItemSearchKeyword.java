package kr.soen.connectup4;

/**
 * Created by ettur on 2017-11-23.
 */

public class ItemSearchKeyword extends ItemParent{
    private String btn1;
    private String btn2;
    private String btn3;

    public ItemSearchKeyword(){
        super();
    }

    public ItemSearchKeyword(String btn1, String btn2, String btn3){
        super();
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.btn3 = btn3;
        type = ITEM_SEARCHKEYWORD;
    }

    public String getBtn1(){
        return btn1;
    }
    public String getBtn2(){
        return btn2;
    }
    public String getBtn3(){
        return btn3;
    }

}
