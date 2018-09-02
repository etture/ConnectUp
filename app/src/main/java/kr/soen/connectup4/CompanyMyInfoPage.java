package kr.soen.connectup4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ettur on 2017-11-21.
 */

public class CompanyMyInfoPage extends AppCompatActivity{
    private Toolbar toolbar;

    private TextView id;
    //private TextView pw;
    private TextView name;
    private TextView number;
    private TextView address;
    private TextView employee;
    private TextView website;

    private SharedPreferences companyPref;
    private SharedPreferences login;
    private SharedPreferences companyInfoPref;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_myinfo_company);

        //툴바
        toolbar = (Toolbar)findViewById(R.id.company_myinfo_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("내 기업 정보");

        //툴바 백 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        id = (TextView)findViewById(R.id.company_profile_id);
        //pw = (TextView)findViewById(R.id.profile_pw);
        name = (TextView)findViewById(R.id.company_profile_name);
        employee = (TextView)findViewById(R.id.company_profile_employee);
        number = (TextView)findViewById(R.id.company_profile_number);
        website = (TextView)findViewById(R.id.company_profile_website);
        address = (TextView)findViewById(R.id.company_profile_address);

        companyPref = getSharedPreferences("companyPref", MODE_PRIVATE);
        login = getSharedPreferences("login", MODE_PRIVATE);
        companyInfoPref = getSharedPreferences("companyInfo", MODE_PRIVATE);

        //내 기업 정보 표시
        String[] thisArray = getUserInfo();
        String estDate = thisArray[CompanyProfile.ESTYEAR] + "년 " + thisArray[CompanyProfile.ESTMONTH] + "월";

        id.setText(thisArray[CompanyProfile.ID]);
        name.setText(thisArray[CompanyProfile.NAME] + " (설립일: " + estDate + ")");
        employee.setText("직원 규모: " + thisArray[CompanyProfile.EMPLOYEE]);
        number.setText(thisArray[CompanyProfile.NUMBER]);
        website.setText(thisArray[CompanyProfile.WEBSITE]);
        address.setText(thisArray[CompanyProfile.ADDRESS]);
    }

    private String getThisId(){
        companyPref = this.getSharedPreferences("companyPref", MODE_PRIVATE);
        return companyPref.getString("currentId", "");
    }

    private String[] getUserInfo(){
        String id = getThisId();
        String[] userInfo = new String[9];
        companyInfoPref = this.getSharedPreferences("companyInfo", MODE_PRIVATE);

        userInfo[CompanyProfile.ID] = companyInfoPref.getString(id + "_id", "");
        userInfo[CompanyProfile.PW] = companyInfoPref.getString(id + "_pw", "");
        userInfo[CompanyProfile.NAME] = companyInfoPref.getString(id + "_name", "");
        userInfo[CompanyProfile.NUMBER] = companyInfoPref.getString(id + "_number", "");
        userInfo[CompanyProfile.ADDRESS] = companyInfoPref.getString(id + "_address", "");
        userInfo[CompanyProfile.ESTYEAR] = companyInfoPref.getString(id + "_estYear", "");
        userInfo[CompanyProfile.ESTMONTH] = companyInfoPref.getString(id + "_estMonth", "");
        userInfo[CompanyProfile.EMPLOYEE] = companyInfoPref.getString(id + "_employee", "");
        userInfo[CompanyProfile.WEBSITE] = companyInfoPref.getString(id + "_website", "");

        return userInfo;
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.companyLogoutBtn:
                logout();
                finish();
                break;
        }
    }

    public void logout(){
        editor = login.edit();
        editor.putBoolean("companyLoggedIn", false);
        editor.commit();

        editor = companyPref.edit();
        editor.putString("currentId", "");
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
