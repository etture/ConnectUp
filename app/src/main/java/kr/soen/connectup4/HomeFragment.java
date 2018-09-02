package kr.soen.connectup4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ettur on 2017-11-14.
 */

public class HomeFragment extends Fragment{

    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView mRecyclerView;
    protected ItemAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    private Toolbar myToolbar;
    private TextView myinfo;

    private SharedPreferences studentPref;
    private SharedPreferences login;
    private SharedPreferences studentInfoPref;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home, null);
        view.setTag(TAG);

        studentPref = getActivity().getSharedPreferences("studentPref", MODE_PRIVATE);
        login = getActivity().getSharedPreferences("login", MODE_PRIVATE);
        studentInfoPref = getActivity().getSharedPreferences("studentInfo", MODE_PRIVATE);

        myinfo = (TextView)view.findViewById(R.id.home_myinfo);
        myinfo.setText(getUnivMajor());

        //툴바 생성
        myToolbar = (Toolbar)view.findViewById(R.id.home_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);
        setHasOptionsMenu(true);

        

        //RecyclerView 설정
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_student_home);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
        mAdapter = new ItemAdapter(DataList.getData(DataList.STUDENT_HOME)); //Data List 넣기
        mRecyclerView.setAdapter(mAdapter);
        //RecyclerView 설정 끝

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); //키보드 안뜨게 하기



        return view;
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_toolbar_menu, menu);
        return;
    }

    //툴바 아이콘 색 설정
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        // find the menu item by ID
        MenuItem item = menu.findItem(R.id.home_toolbar_myinfo);

        // Retrieve the icon as a drawable
        Drawable icon = getResources().getDrawable(R.drawable.ic_info);

        // Apply a tint to the drawable icon
        icon.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);

        // Set the colorized icon back to the item
        item.setIcon(icon);
    }

    //툴바 버튼 눌렀을 때 액션
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_toolbar_myinfo:
                Intent intent = new Intent(getActivity(), MyInfoPage.class);

                getActivity().startActivity(intent);
                return true;
        }
        return onOptionsItemSelected(item);
    }

    private String getThisId(){
        studentPref = getActivity().getSharedPreferences("studentPref", MODE_PRIVATE);
        return studentPref.getString("currentId", "");
    }

    private String[] getUserInfo(){
        String id = getThisId();
        String[] userInfo = new String[10];
        studentInfoPref = getActivity().getSharedPreferences("studentInfo", MODE_PRIVATE);

        userInfo[StudentProfile.ID] = studentInfoPref.getString(id + "_id", "");
        userInfo[StudentProfile.PW] = studentInfoPref.getString(id + "_pw", "");
        userInfo[StudentProfile.NAME] = studentInfoPref.getString(id + "_name", "");
        userInfo[StudentProfile.AGE] = studentInfoPref.getString(id + "_age", "");
        userInfo[StudentProfile.BIRTH_YEAR] = studentInfoPref.getString(id + "_birth_year", "");
        userInfo[StudentProfile.BIRTH_MONTH] = studentInfoPref.getString(id + "_birth_month", "");
        userInfo[StudentProfile.BIRTH_DAY] = studentInfoPref.getString(id + "_birth_day", "");
        userInfo[StudentProfile.UNIVERSITY] = studentInfoPref.getString(id + "_uni", "");
        userInfo[StudentProfile.MAJOR] = studentInfoPref.getString(id + "_major", "");
        userInfo[StudentProfile.GRADE] = studentInfoPref.getString(id + "_grade", "");
        return userInfo;
    }

    private String getUnivMajor(){
        String[] arr = getUserInfo();
        String univ = arr[StudentProfile.UNIVERSITY];
        String major = arr[StudentProfile.MAJOR];
        String grade = arr[StudentProfile.GRADE];
        String info = univ + " " + major + " " + grade;
        return info;
    }
}
