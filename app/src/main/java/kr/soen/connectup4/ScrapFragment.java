package kr.soen.connectup4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ettur on 2017-11-14.
 */

public class ScrapFragment extends Fragment{
    private TextView textView;
    private Toolbar myToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_scrap, null);

        //툴바 생성
        myToolbar = (android.support.v7.widget.Toolbar)view.findViewById(R.id.scrap_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);
        //setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("스크랩");



        return view;
    }

}
