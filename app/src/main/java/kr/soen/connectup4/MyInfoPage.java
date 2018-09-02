package kr.soen.connectup4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ettur on 2017-11-14.
 */

public class MyInfoPage extends AppCompatActivity {
    private Toolbar toolbar;

    private TextView id;
    //private TextView pw;
    private TextView nameAge;
    private TextView birthday;
    private TextView uniMajor;

    private SharedPreferences studentPref;
    private SharedPreferences login;
    private SharedPreferences studentInfoPref;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_myinfo);

        //툴바
        toolbar = (Toolbar)findViewById(R.id.myinfo_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("내 정보");

        //툴바 백 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        id = (TextView)findViewById(R.id.profile_id);
        //pw = (TextView)findViewById(R.id.profile_pw);
        nameAge = (TextView)findViewById(R.id.profile_nameAge);
        birthday = (TextView)findViewById(R.id.profile_birthday);
        uniMajor = (TextView)findViewById(R.id.profile_uniMajor);

        studentPref = getSharedPreferences("studentPref", MODE_PRIVATE);
        login = getSharedPreferences("login", MODE_PRIVATE);
        studentInfoPref = getSharedPreferences("studentInfo", MODE_PRIVATE);

        //내 정보 표시
        String[] thisArray = getUserInfo();
        String bday = "";
        bday += thisArray[StudentProfile.BIRTH_YEAR] + "년 " + thisArray[StudentProfile.BIRTH_MONTH] + "월 " + thisArray[StudentProfile.BIRTH_DAY] + "일";
        String grd = thisArray[StudentProfile.GRADE];

        id.setText(thisArray[StudentProfile.ID]);
        nameAge.setText(thisArray[StudentProfile.NAME] + " / " + thisArray[StudentProfile.AGE] + "세");
        birthday.setText(bday);
        uniMajor.setText(thisArray[StudentProfile.UNIVERSITY] + " " + thisArray[StudentProfile.MAJOR] + " " + grd);
    }

    private String getThisId(){
        studentPref = this.getSharedPreferences("studentPref", MODE_PRIVATE);
        return studentPref.getString("currentId", "");
    }

    private String[] getUserInfo(){
        String id = getThisId();
        String[] userInfo = new String[10];
        studentInfoPref = this.getSharedPreferences("studentInfo", MODE_PRIVATE);

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

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.logoutBtn:
                logout();
                finish();
                break;
        }
    }

    public void logout(){
        editor = login.edit();
        editor.putBoolean("studentLoggedIn", false);
        editor.commit();

        editor = studentPref.edit();
        editor.putString("currentId", "");
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
