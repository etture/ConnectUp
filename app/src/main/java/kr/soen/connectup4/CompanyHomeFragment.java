package kr.soen.connectup4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ettur on 2017-11-14.
 */

public class CompanyHomeFragment extends Fragment{
    private TextView textView;
    private Toolbar myToolbar;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.company_fragment_home, null);

        //툴바 생성
        myToolbar = (Toolbar)view.findViewById(R.id.company_home_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);
        setHasOptionsMenu(true);



        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.company_home_toolbar_menu, menu);
        return;
    }

    //툴바 아이콘 색 설정
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        // find the menu item by ID
        MenuItem item = menu.findItem(R.id.company_home_toolbar_myinfo);

        // Retrieve the icon as a drawable
        Drawable icon = getResources().getDrawable(R.drawable.ic_company);

        // Apply a tint to the drawable icon
        icon.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);

        // Set the colorized icon back to the item
        item.setIcon(icon);
    }

    //툴바 버튼 눌렀을 때 액션
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.company_home_toolbar_myinfo:
                Intent intent = new Intent(getActivity(), CompanyMyInfoPage.class);
                getActivity().startActivity(intent);
                return true;
        }
        return onOptionsItemSelected(item);
    }

}
