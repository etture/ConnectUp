package kr.soen.connectup4;

import android.content.ClipData;

/**
 * Created by ettur on 2017-11-28.
 */

public class ItemClassified extends ItemParent {
    //NAME, IMGID, INTRODUCTION, CULTURE, HIRING, WELFARE, FOUNDING, WEBSITE, EMAIL, PHONE, ADDRESS

    private String name;
    private String introduction;
    private String job;
    private String salary;
    private String experience;
    private int imgID;
    private int index;

    public ItemClassified(int index){
        super();
        this.name = StudentClassifiedData.NAME[index];
        this.introduction = StudentClassifiedData.SHORTINTRO[index];
        this.job = StudentClassifiedData.JOB[index];
        this.salary = StudentClassifiedData.SALARY[index];
        this.experience = StudentClassifiedData.EXPERIENCE[index];
        this.imgID = StudentClassifiedData.IMGID[index];
        this.index = index;
        type = ITEM_CLASSIFIED;
    }


    public String getName(){
        return name;
    }
    public String getIntroduction(){
        return introduction;
    }
    public String getJob(){
        return job;
    }
    public String getSalary(){
        return salary;
    }
    public String getExperience(){
        return experience;
    }
    public int getImgID(){
        return imgID;
    }

    public int getIndex(){
        return index;
    }


}
