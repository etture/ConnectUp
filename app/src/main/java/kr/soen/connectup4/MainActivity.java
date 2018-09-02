package kr.soen.connectup4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    public final static int LOGIN_RETURN_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        if (!login.getBoolean("studentLoggedIn", false) && !login.getBoolean("companyLoggedIn", false)) { //로그인 실행
            callLogin();
        } else if (login.getBoolean("studentLoggedIn", false) && !login.getBoolean("companyLoggedIn", false)) { //학생 로그인
            Intent studentLoginIntent = new Intent(this, StudentSide.class);
            studentLoginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(studentLoginIntent);
        }else if(!login.getBoolean("studentLoggedIn", false) && login.getBoolean("companyLoggedIn", false)){ //기업 로그인
            Intent companyLoginIntent = new Intent(this, CompanySide.class);
            companyLoginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(companyLoginIntent);
        }
    }


    protected void callLogin(){
        Intent intent = new Intent(this, LogIn.class);
        startActivityForResult(intent, LOGIN_RETURN_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case LOGIN_RETURN_CODE:
                String type = data.getStringExtra("type");
                if(type.equals("student")){
                    Intent studentLoginIntent = new Intent(this, StudentSide.class);
                    studentLoginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(studentLoginIntent);
                }else if(type.equals("company")){
                    Intent companyLoginIntent = new Intent(this, CompanySide.class);
                    companyLoginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(companyLoginIntent);
                }
                break;
        }
    }
}
