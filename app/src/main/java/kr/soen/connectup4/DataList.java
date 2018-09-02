package kr.soen.connectup4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ettur on 2017-11-23.
 */

public final class DataList {
    public static final int DEFAULT = 0;
    public static final int STUDENT_HOME = 1;
    public static final int STUDENT_CLASSIFIED = 2;
    public static final int STUDENT_EVENT = 3;
    public static final int STUDENT_BULLETIN = 4;
    public static final int STUDENT_SCRAP = 5;
    public static final int COMPANY_HOME = 6;
    public static final int COMPANY_CLASSIFIED = 7;
    public static final int COMPANY_EVENT = 8;
    public static final int COMPANY_BULLETIN = 9;
    public static final int COMPANY_SCRAP = 10;
    public static final int STUDENT_BULLETIN_LIST_FREE = 11;
    public static final int STUDENT_BULLETIN_LIST_REST = 12;

    public static List<ItemParent> getData(int type){
        List<ItemParent> list = new ArrayList<>();

        switch(type){
            case DEFAULT:
                break;
            case STUDENT_HOME:
                //list.add(new ItemSearchKeyword("스타트업", "인턴십", "설명회"));
                list.add(new ItemTitle("즐겨찾는 게시판"));
                list.add(new ItemEntry("자유게시판", "학교 취업지원센터에서 취업컨설팅도 해주나요?", 0));
                list.add(new ItemEntry("비밀게시판", "어제 스타트업 박람회 갔다온 후기", 1));
                list.add(new ItemEntry("홍보게시판", "내일 건국대학교에서 설명회 개최합니다!", 2));
                list.add(new ItemEntry("정보게시판", "중소기업 취업율", 3));
                list.add(new ItemAd(R.drawable.img_samplead2));
                list.add(new ItemTitle("최근 올라온 채용공고"));
                list.add(new ItemClassifiedMini(0, 1, 2, 3, 4));
                list.add(new ItemTitle("최근 올라온 행사정보"));
                list.add(new ItemEventMini(0, 1, 2, 3, 4));
                list.add(new ItemAd(R.drawable.img_samplead));
                break;
            case STUDENT_CLASSIFIED:
                for(int i = 0; i < StudentClassifiedData.NAME.length; i++){
                    list.add(new ItemClassified(i));
                }
                break;
            case STUDENT_EVENT:
                for(int i = 0; i < StudentEventData.NAME.length; i++){
                    list.add(new ItemEvent(i));
                }
                break;
            case STUDENT_BULLETIN:
                for(int i = 0; i < StudentBulletinData.MAINBULLETIN.length; i++){
                    list.add(new ItemBulletin(i));
                }
                break;
            case STUDENT_BULLETIN_LIST_FREE:
                for(int i = 0; i < StudentBulletinData.TITLE.length; i++){
                    list.add(new ItemBulletinList(i));
                }
                break;
            case STUDENT_BULLETIN_LIST_REST:
                list.add(new ItemBulletinList("게시판 준비 중입니다...!", "조금만 기다려주세요! :)"));
                break;
        }

        return list;
    }
}
