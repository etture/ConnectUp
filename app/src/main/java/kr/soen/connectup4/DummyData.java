package kr.soen.connectup4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ettur on 2017-11-23.
 */

public final class DummyData {
    public static List<ItemParent> getData(){
        List<ItemParent> list = new ArrayList<>();

        list.add(new ItemTitle("title #1"));
        list.add(new ItemTitle("title #2"));
        list.add(new ItemTitle("title #3"));
        list.add(new ItemTitle("title #4"));
        list.add(new ItemTitle("title #5"));
        list.add(new ItemAd(R.drawable.img_samplead));

        return list;
    }
}
