package kr.soen.connectup4;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ettur on 2017-11-14.
 */

public class LogIn extends AppCompatActivity {
    private final int STUDENT = 1;
    private final int COMPANY = 2;

    private CheckBox student, company;
    private EditText idInput, pwInput;
    private CheckBox rememberLogin;
    private Button loginBtn;
    private boolean loginChecked;
    private SharedPreferences studentPref;
    private SharedPreferences companyPref;
    private SharedPreferences login;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_login);
        //getSupportActionBar().hide(); //액션바 숨김

        student = (CheckBox)findViewById(R.id.studentCheck);
        company = (CheckBox)findViewById(R.id.companyCheck);
        idInput = (EditText)findViewById(R.id.idinput);
        pwInput = (EditText)findViewById(R.id.pwinput);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        rememberLogin = (CheckBox)findViewById(R.id.checkBox);

        studentPref = getSharedPreferences("studentPref", MODE_PRIVATE);
        companyPref = getSharedPreferences("companyPref", MODE_PRIVATE);
        login = getSharedPreferences("login", MODE_PRIVATE);


        pwInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    switch(keyCode){
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            loginBtn.performClick();
                            return true;
                        default:
                            break;
                    }
                }

                return false;
            }
        });

        if(studentPref.getBoolean("loginChecked", false)){
            idInput.setText(studentPref.getString("lastLoginId", ""));
            pwInput.setText(studentPref.getString("lastLoginPw", ""));
            student.setChecked(true);
            company.setChecked(false);
            rememberLogin.setChecked(true);
        }else if(companyPref.getBoolean("loginChecked", false)){
            idInput.setText(companyPref.getString("lastLoginId", ""));
            pwInput.setText(companyPref.getString("lastLoginPw", ""));
            company.setChecked(true);
            student.setChecked(false);
            rememberLogin.setChecked(true);
        }

        /*else{

            String id = idInput.getText().toString();
            String pw = pwInput.getText().toString();
            boolean validation = loginValidation(id, pw);

            if(validation){
                Toast.makeText(LogIn.this, "로그인 성공", Toast.LENGTH_LONG).show();

                if(loginChecked){
                    editor.putString("id", id);
                    editor.putString("pw", pw);
                    editor.putBoolean("autoLogin", true);
                    editor.commit();

                    Intent backIntent = new Intent();
                    backIntent.putExtra("id", pref.getString("id", ""));
                    backIntent.putExtra("pw", pref.getString("pw", ""));
                    backIntent.putExtra("loginChecked", true);
                    setResult(RESULT_OK, backIntent);
                    finish();

                }
            }else{
                Toast.makeText(LogIn.this, "로그인 실패", Toast.LENGTH_LONG).show();
            }*/


        /*rememberLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    loginChecked = true;
                }else{
                    loginChecked = false;
                    editor = studentPref.edit();
                    editor.remove("lastLoginId");
                    editor.remove("lastLoginPw");
                    editor.putBoolean("loginChecked", false);
                    editor.commit();

                    editor = companyPref.edit();
                    editor.remove("lastLoginId");
                    editor.remove("lastLoginPw");
                    editor.putBoolean("loginChecked", false);
                    editor.commit();
                }
            }
        });*/
    }


    public void mOnClick(View v){
        switch(v.getId()){
            case R.id.loginBtn:
                String id = idInput.getText().toString();
                String pw = pwInput.getText().toString();
                if(rememberLogin.isChecked()){
                    loginChecked = true;
                }else{
                    loginChecked = false;
                }

                if(!student.isChecked() && !company.isChecked()){
                    Toast.makeText(LogIn.this, "유저 타입을 선택하세요.", Toast.LENGTH_SHORT).show();
                    break;
                }else if(student.isChecked() && company.isChecked()){
                    Toast.makeText(LogIn.this, "유저 타입을 하나만 선택하세요.", Toast.LENGTH_SHORT).show();
                    break;
                }else if(student.isChecked() && !company.isChecked()) { //학생 로그인 정보
                    boolean validation = loginValidation(id, pw, STUDENT);
                    if (validation) {
                        Toast.makeText(LogIn.this, "로그인 성공", Toast.LENGTH_SHORT).show();

                        editor = companyPref.edit();
                        editor.putBoolean("loginChecked", false);
                        editor.commit();

                        if (loginChecked) {
                            editor = studentPref.edit();
                            editor.putBoolean("loginChecked", true);
                            editor.putString("lastLoginId", id);
                            editor.putString("lastLoginPw", pw);
                            editor.putString("currentId", id);
                            editor.commit();
                        }else{
                            editor = studentPref.edit();
                            editor.putBoolean("loginChecked", false);
                            editor.remove("lastLoginId");
                            editor.remove("lastLoginPw");
                            editor.putString("currentId", id);
                            editor.commit();
                        }

                        editor = studentPref.edit();
                        editor.putString(id, pw);
                        editor.commit();

                        editor = login.edit();
                        editor.putBoolean("studentLoggedIn", true);
                        editor.commit();

                        Intent intent = new Intent();
                        intent.putExtra("type", "student");
                        setResult(RESULT_OK, intent);

                        finish();
                        break;
                    } else {
                        //Toast.makeText(LogIn.this, "로그인 실패", Toast.LENGTH_LONG).show();
                        break;
                    }
                }else if(!student.isChecked() && company.isChecked()){
                    boolean validation = loginValidation(id, pw, COMPANY);
                    if (validation) {
                        Toast.makeText(LogIn.this, "로그인 성공", Toast.LENGTH_SHORT).show();

                        editor = studentPref.edit();
                        editor.putBoolean("loginChecked", false);
                        editor.commit();

                        if (loginChecked) {
                            editor = companyPref.edit();
                            editor.putBoolean("loginChecked", true);
                            editor.putString("lastLoginId", id);
                            editor.putString("lastLoginPw", pw);
                            editor.putString("currentId", id);
                            editor.commit();
                        }else{
                            editor = companyPref.edit();
                            editor.putBoolean("loginChecked", false);
                            editor.remove("lastLoginId");
                            editor.remove("lastLoginPw");
                            editor.putString("currentId", id);
                            editor.commit();
                        }

                        editor = companyPref.edit();
                        editor.putString(id, pw);
                        editor.commit();

                        editor = login.edit();
                        editor.putBoolean("companyLoggedIn", true);
                        editor.commit();

                        Intent intent = new Intent();
                        intent.putExtra("type", "company");
                        setResult(RESULT_OK, intent);

                        finish();
                        break;
                    } else {
                        //Toast.makeText(LogIn.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                break;
            case R.id.signupBtn:
                if(!student.isChecked() && !company.isChecked()){
                    Toast.makeText(LogIn.this, "유저 타입을 선택하세요.", Toast.LENGTH_SHORT).show();
                    break;
                }else if(student.isChecked() && company.isChecked()){
                    Toast.makeText(LogIn.this, "유저 타입을 하나만 선택하세요.", Toast.LENGTH_SHORT).show();
                    break;
                }else if(student.isChecked() && !company.isChecked()) { //학생 회원가입
                    Intent studentSignupIntent = new Intent(this, StudentSignUp.class);
                    startActivity(studentSignupIntent);
                    break;
                }else if(!student.isChecked() && company.isChecked()) { //기업 회원가입
                    Intent companySignupIntent = new Intent(this, CompanySignUp.class);
                    startActivity(companySignupIntent);
                    break;
                }
        }
    }

    private boolean loginValidation(String id, String pw, int type){
        SharedPreferences pref = null;

        if(type == STUDENT){
            pref = studentPref;
        }else if(type == COMPANY){
            pref = companyPref;
        }

        if(id.equals("")){
            Toast.makeText(LogIn.this, "ID를 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }else if(pw.equals("")){
            Toast.makeText(LogIn.this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }else if(pref.getString(id, "").equals("")){
            Toast.makeText(LogIn.this, "등록되지 않은 ID입니다.", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!pref.getString(id, "").equals(pw)){
            Toast.makeText(LogIn.this, "틀린 비밀번호입니다.", Toast.LENGTH_SHORT).show();
            return false;
        }else if(pref.getString(id, "").equals(pw)){
            return true;
        }else{
            return false;
        }

    }
}
