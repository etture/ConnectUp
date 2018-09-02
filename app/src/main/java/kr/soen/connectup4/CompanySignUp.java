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

/**
 * Created by ettur on 2017-11-21.
 */

public class CompanySignUp extends AppCompatActivity{
    private String[] years = {"년", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010",
            "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999",
            "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988",
            "1987", "1986", "1985", "1984", "1983", "1982",
            "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971",
            "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960"};
    private String[] months = {"월", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    private String[] employees = {"직원 수", "1-10", "11-50", "51-200", "201-500", "501-1000", "1001-5000", "5001-"};


    private EditText idInput, pwInput;
    private EditText nameInput, numberInput, addressInput;
    private Spinner spinnerEstYear, spinnerEstMonth, spinnerEmployee;
    private EditText websiteInput;
    private Button confirmBtn;

    private SharedPreferences companyInfoPref;
    private SharedPreferences companyPref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_signup_company);
        //getSupportActionBar().hide(); //액션바 숨김

        idInput = (EditText) findViewById(R.id.companySignupIdInput);
        pwInput = (EditText) findViewById(R.id.companySignupPwInput);
        nameInput = (EditText) findViewById(R.id.companySignupNameInput);
        numberInput = (EditText) findViewById(R.id.companySignupNumberInput);
        addressInput = (EditText) findViewById(R.id.companySignupAddressInput);
        websiteInput = (EditText) findViewById(R.id.companySignupWebsiteInput);
        confirmBtn = (Button) findViewById(R.id.signupConfirm_company);

        companyInfoPref = getSharedPreferences("companyInfo", MODE_PRIVATE);
        companyPref = getSharedPreferences("companyPref", MODE_PRIVATE);

        websiteInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
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
        });

        spinnerEstYear = (Spinner)findViewById(R.id.company_spinner_estYear);
        ArrayAdapter<String> estYearAdapter = new ArrayAdapter<String>(
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
        estYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerEstYear.setAdapter(estYearAdapter);

        spinnerEstYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        spinnerEstYear.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm=(InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(idInput.getWindowToken(), 0);
                return false;
            }
        });

        spinnerEstMonth = (Spinner)findViewById(R.id.company_spinner_estMonth);
        ArrayAdapter<String> estMonthAdapter = new ArrayAdapter<String>(
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
        estMonthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerEstMonth.setAdapter(estMonthAdapter);

        spinnerEstMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        spinnerEstMonth.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm=(InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(idInput.getWindowToken(), 0);
                return false;
            }
        });

        spinnerEmployee = (Spinner)findViewById(R.id.company_spinner_employee);
        ArrayAdapter<String> employeeAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, employees){
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
        employeeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerEmployee.setAdapter(employeeAdapter);

        spinnerEmployee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        spinnerEmployee.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm=(InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(idInput.getWindowToken(), 0);
                return false;
            }
        });
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.signupConfirm_company:
                String id = idInput.getText().toString();
                String pw = pwInput.getText().toString();
                String name = nameInput.getText().toString();
                String number = numberInput.getText().toString();
                String address = addressInput.getText().toString();
                String estYear = spinnerEstYear.getSelectedItem().toString();
                String estMonth = spinnerEstMonth.getSelectedItem().toString();
                String employee = spinnerEmployee.getSelectedItem().toString();
                String website = websiteInput.getText().toString();

                if(id.equals("")||pw.equals("")||name.equals("")||address.equals("")|| number.equals("")||
                        estYear.equals("년")||estMonth.equals("월")||employee.equals("직원 수")||website.equals("")){
                    Toast.makeText(CompanySignUp.this, "모든 정보를 입력하세요.", Toast.LENGTH_SHORT).show();
                    break;
                }else if(companyInfoPref.getString(id + "_id", "").equals(id)){
                    Toast.makeText(CompanySignUp.this, "이미 있는 ID입니다.", Toast.LENGTH_SHORT).show();
                    break;
                }else{

                    editor = companyInfoPref.edit();
                    editor.putString(id + "_id", id);
                    editor.putString(id + "_pw", pw);
                    editor.putString(id + "_name", name);
                    editor.putString(id + "_number", number);
                    editor.putString(id + "_address", address);
                    editor.putString(id + "_estYear", estYear);
                    editor.putString(id + "_estMonth", estMonth);
                    editor.putString(id + "_employee", employee);
                    editor.putString(id +"_website", website);
                    editor.commit();


                    editor = companyPref.edit();
                    editor.putString(id, pw);
                    editor.commit();

                    finish();
                    break;
                }
            case R.id.signupCancel_company:
                finish();
                break;
        }
    }
}
