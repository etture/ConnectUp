package kr.soen.connectup4;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ettur on 2017-11-14.
 */

public class StudentSignUp extends AppCompatActivity {
    private String[] years = {"년", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993",
            "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982",
            "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971",
            "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960"};
    private String[] months = {"월", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    private String[] days = {"일", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
            "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
            "30", "31"};
    private String[] universities = {"대학교", "가천대학교", "가톨릭대학교", "건국대학교", "경기대학교", "경희대학교", "고려대학교",
            "광운대학교", "국민대학교", "단국대학교", "덕성여자대학교", "동국대학교",
            "동덕여자대학교", "명지대학교", "삼육대학교", "상명대학교", "상지대학교", "서강대학교", "서경대학교", "서울과학기술대학교",
            "서울교육대학교", "서울대학교", "서울시립대학교", "서울여자대학교", "성균관대학교", "성신여자대학교",
            "세종대학교", "숙명여자대학교", "숭실대학교", "아주대학교", "연세대학교", "이화여자대학교", "인천대학교",
            "중앙대학교", "한국기술교육대학교", "한국산업기술대학교", "한국외국어대학교", "한국항공대학교", "한성대학교",
            "한양대학교", "홍익대학교"};
    private String[] majors = {"학과", "대학교를 선택하세요"};
    private String[] majorList;
    private String[] grades = {"학년", "1학년", "2학년", "3학년", "4학년", "초과학기"};



    private EditText idInput, pwInput;
    private EditText nameInput;
    private Spinner spinnerYear, spinnerMonth, spinnerDay;
    private Spinner spinnerUni, spinnerMajor, spinnerGrade;
    //private EditText majorInput;
    private Button confirmBtn;
    //private Button cancelBtn;

    private SharedPreferences studentInfoPref;
    private SharedPreferences studentPref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_signup_student);
        //getSupportActionBar().hide(); //액션바 숨김

        idInput = (EditText)findViewById(R.id.signupIdInput);
        pwInput = (EditText)findViewById(R.id.signupPwInput);
        nameInput = (EditText)findViewById(R.id.signupNameInput);
        //majorInput = (EditText)findViewById(R.id.signupMajorInput);
        confirmBtn = (Button)findViewById(R.id.signupConfirm_student);
        //cancelBtn = (Button)findViewById(R.id.signupCancel_student);

        studentInfoPref = getSharedPreferences("studentInfo", MODE_PRIVATE);
        studentPref = getSharedPreferences("studentPref", MODE_PRIVATE);

        /*majorInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    switch(keyCode){
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            confirmBtn.performClick();
                            return true;
                        default:
                            break;
                    }
                }

                return false;
            }
        });*/

        spinnerYear = (Spinner)findViewById(R.id.spinner_year);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, years){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerYear.setAdapter(yearAdapter);

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                /*if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerYear.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm=(InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(idInput.getWindowToken(), 0);
                return false;
            }
        });

        spinnerMonth = (Spinner)findViewById(R.id.spinner_month);
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, months){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerMonth.setAdapter(monthAdapter);

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                /*if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMonth.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm=(InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(idInput.getWindowToken(), 0);
                return false;
            }
        });

        spinnerDay = (Spinner)findViewById(R.id.spinner_day);
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, days){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerDay.setAdapter(dayAdapter);

        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                /*if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerDay.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm=(InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(idInput.getWindowToken(), 0);
                return false;
            }
        });

        spinnerUni = (Spinner)findViewById(R.id.spinner_uni);
        ArrayAdapter<String> uniAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, universities){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        uniAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerUni.setAdapter(uniAdapter);



        spinnerUni.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                /*if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }*/

                majorList = matchUniv(selectedItemText);
                spinnerMajor.setAdapter(callSpinner(majorList));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerUni.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm=(InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(idInput.getWindowToken(), 0);
                return false;
            }
        });


        spinnerMajor = (Spinner)findViewById(R.id.spinner_major);
        ArrayAdapter<String> majorAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, majors){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerMajor.setAdapter(majorAdapter);



        spinnerMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                /*if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMajor.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm=(InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(idInput.getWindowToken(), 0);
                return false;
            }
        });

        spinnerGrade = (Spinner)findViewById(R.id.spinner_grade);
        ArrayAdapter<String> gradeAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, grades){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerGrade.setAdapter(gradeAdapter);

        spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                /*if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerGrade.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm=(InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(idInput.getWindowToken(), 0);
                return false;
            }
        });
    }

    protected ArrayAdapter<String> callSpinner(String[] majorList){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, majorList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        return adapter;
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.signupConfirm_student:
                String id = idInput.getText().toString();
                String pw = pwInput.getText().toString();
                String name = nameInput.getText().toString();
                String birth_year = spinnerYear.getSelectedItem().toString();
                String birth_month = spinnerMonth.getSelectedItem().toString();
                String birth_day = spinnerDay.getSelectedItem().toString();
                String uni = spinnerUni.getSelectedItem().toString();
                String major = spinnerMajor.getSelectedItem().toString();
                String grade = spinnerGrade.getSelectedItem().toString();

                if(id.equals("")||pw.equals("")||name.equals("")||
                        birth_year.equals("년")||birth_month.equals("월")||birth_day.equals("일")||
                        uni.equals("대학교")||major.equals("학과")||grade.equals("학년")){
                    Toast.makeText(StudentSignUp.this, "모든 정보를 입력하세요.", Toast.LENGTH_SHORT).show();
                    break;
                }else if(studentInfoPref.getString(id + "_id", "").equals(id)){
                    Toast.makeText(StudentSignUp.this, "이미 있는 ID입니다.", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    /*String[] infoArray = {id, pw, name, age, birth_year, birth_month, birth_day, uni, major};
                    Set<String> infoSet = new HashSet<>(Arrays.asList(infoArray));

                    editor = studentInfoPref.edit();
                    editor.putStringSet(id, infoSet);
                    editor.commit();*/

                    /*editor = studentInfoPref.edit();
                    editor.putInt("Status_size", infoArray.length);
                    for(int i = 0; i < infoArray.length; i++){
                        editor.remove("Status_" + i);
                        editor.putString("Status_" + i, infoArray[i]);
                    }
                    editor.commit();*/

                    String age = getAge(Integer.parseInt(birth_year), Integer.parseInt(birth_month), Integer.parseInt(birth_day));

                    editor = studentInfoPref.edit();
                    editor.putString(id + "_id", id);
                    editor.putString(id + "_pw", pw);
                    editor.putString(id + "_name", name);
                    editor.putString(id + "_age", age);
                    editor.putString(id + "_birth_year", birth_year);
                    editor.putString(id + "_birth_month", birth_month);
                    editor.putString(id + "_birth_day", birth_day);
                    editor.putString(id + "_uni", uni);
                    editor.putString(id + "_major", major);
                    editor.putString(id+ "_grade", grade);
                    editor.commit();


                    editor = studentPref.edit();
                    editor.putString(id, pw);
                    editor.commit();

                    finish();
                    break;
                }
            case R.id.signupCancel_student:
                finish();
                break;
        }
    }

    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR) + 1;

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }

    private String[] matchUniv(String univ){
        String[] univList = null;
        switch(univ){
            case "대학교":
                univList = majors;
                break;
            case "가천대학교":
                univList = UniversityMajors.GACHEON;
                break;
            case "가톨릭대학교":
                univList = UniversityMajors.CATHOLIC;
                break;
            case "건국대학교":
                univList = UniversityMajors.KONKUK;
                break;
            case "경기대학교":
                univList = UniversityMajors.GYEONGGI;
                break;
            case "경희대학교":
                univList = UniversityMajors.KYUNGHEE;
                break;
            case "고려대학교":
                univList = UniversityMajors.KOREA;
                break;
            case "광운대학교":
                univList = UniversityMajors.GWANGWOON;
                break;
            case "국민대학교":
                univList = UniversityMajors.KOOKMIN;
                break;
            case "단국대학교":
                univList = UniversityMajors.DANKOOK;
                break;
            case "덕성여자대학교":
                univList = UniversityMajors.DEOKSEONG;
                break;
            case "동국대학교":
                univList = UniversityMajors.DONGGOOK;
                break;
            case "동덕여자대학교":
                univList = UniversityMajors.DONGDEOK;
                break;
            case "명지대학교":
                univList = UniversityMajors.MYEONGJI;
                break;
            case "삼육대학교":
                univList = UniversityMajors.SAMYOOK;
                break;
            case "상명대학교":
                univList = UniversityMajors.SANGMYEONG;
                break;
            case "상지대학교":
                univList = UniversityMajors.SANGJI;
                break;
            case "서강대학교":
                univList = UniversityMajors.SOGANG;
                break;
            case "서경대학교":
                univList = UniversityMajors.SEOKYEONG;
                break;
            case "서울과학기술대학교":
                univList = UniversityMajors.SEOULTECH;
                break;
            case "서울교육대학교":
                univList = UniversityMajors.SEOULEDU;
                break;
            case "서울대학교":
                univList = UniversityMajors.SNU;
                break;
            case "서울시립대학교":
                univList = UniversityMajors.UOFSEOUL;
                break;
            case "서울여자대학교":
                univList = UniversityMajors.SEOULWOMEN;
                break;
            case "성균관대학교":
                univList = UniversityMajors.SKKU;
                break;
            case "성신여자대학교":
                univList = UniversityMajors.SEONGSHIN;
                break;
            case "세종대학교":
                univList = UniversityMajors.SEJONG;
                break;
            case "숙명여자대학교":
                univList = UniversityMajors.SOOKMYEONG;
                break;
            case "숭실대학교":
                univList = UniversityMajors.SOONGSIL;
                break;
            case "아주대학교":
                univList = UniversityMajors.AJOO;
                break;
            case "연세대학교":
                univList = UniversityMajors.YONSEI;
                break;
            case "이화여자대학교":
                univList = UniversityMajors.EWHA;
                break;
            case "인천대학교":
                univList = UniversityMajors.INCHEON;
                break;
            case "중앙대학교":
                univList = UniversityMajors.CHUNGANG;
                break;
            case "한국기술교육대학교":
                univList = UniversityMajors.KOREATECH;
                break;
            case "한국산업기술대학교":
                univList = UniversityMajors.KOREAINDUSTRY;
                break;
            case "한국외국어대학교":
                univList = UniversityMajors.HUFS;
                break;
            case "한국항공대학교":
                univList = UniversityMajors.KOREAAVIATION;
                break;
            case "한성대학교":
                univList = UniversityMajors.HANSEONG;
                break;
            case "한양대학교":
                univList = UniversityMajors.HANYANG;
                break;
            case "홍익대학교":
                univList = UniversityMajors.HONGIK;
                break;
       }
       return univList;
    }
}
