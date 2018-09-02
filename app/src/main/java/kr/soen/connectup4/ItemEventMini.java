package kr.soen.connectup4;

/**
 * Created by ettur on 2017-11-28.
 */

public class ItemEventMini extends ItemParent{
    private String text1, text2, text3, text4, text5;
    private String subtext1, subtext2, subtext3, subtext4, subtext5;
    private int img1, img2, img3, img4, img5;
    private int ind1, ind2, ind3, ind4, ind5;

    public ItemEventMini(int ind1, int ind2, int ind3, int ind4, int ind5){
        super();
        text1 = StudentEventData.NAME[ind1];
        text2 = StudentEventData.NAME[ind2];
        text3 = StudentEventData.NAME[ind3];
        text4 = StudentEventData.NAME[ind4];
        text5 = StudentEventData.NAME[ind5];

        subtext1 = StudentEventData.DATE[ind1];
        subtext2 = StudentEventData.DATE[ind2];
        subtext3 = StudentEventData.DATE[ind3];
        subtext4 = StudentEventData.DATE[ind4];
        subtext5 = StudentEventData.DATE[ind5];

        img1 = StudentEventData.IMGID[ind1];
        img2 = StudentEventData.IMGID[ind2];
        img3 = StudentEventData.IMGID[ind3];
        img4 = StudentEventData.IMGID[ind4];
        img5 = StudentEventData.IMGID[ind5];

        this.ind1 = ind1;
        this.ind2 = ind2;
        this.ind3 = ind3;
        this.ind4 = ind4;
        this.ind5 = ind5;

        type = ITEM_EVENT_MINI;
    }

    public String getText1(){
        return text1;
    }
    public String getText2(){
        return text2;
    }
    public String getText3(){
        return text3;
    }
    public String getText4(){
        return text4;
    }
    public String getText5(){
        return text5;
    }

    public String getSubtext1(){
        return subtext1;
    }
    public String getSubtext2(){
        return subtext2;
    }
    public String getSubtext3(){
        return subtext3;
    }
    public String getSubtext4(){
        return subtext4;
    }
    public String getSubtext5(){
        return subtext5;
    }

    public int getImg1(){
        return img1;
    }
    public int getImg2(){
        return img2;
    }
    public int getImg3(){
        return img3;
    }
    public int getImg4(){
        return img4;
    }
    public int getImg5(){
        return img5;
    }

    public int getInd1(){
        return ind1;
    }
    public int getInd2(){
        return ind2;
    }
    public int getInd3(){
        return ind3;
    }
    public int getInd4(){
        return ind4;
    }
    public int getInd5(){
        return ind5;
    }

}
