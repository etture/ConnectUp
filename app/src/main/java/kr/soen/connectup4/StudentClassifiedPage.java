package kr.soen.connectup4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ettur on 2017-11-28.
 */

public class StudentClassifiedPage extends AppCompatActivity {
    //NAME, IMGID, SHORTINTRO, INTRODUCTION, JOB, SALARY, EXPERIENCE, SKILLS, INSURANCE, WORKHOUR, VACATION, FOUNDING, SIZE, WEBSITE, EMAIL, PHONE, ADDRESS
    private int index;
    private ImageView icon;
    private TextView introduction, hiringName, hiringJob, hiringSalaryExperience, hiringSkill;
    private TextView welfareInsurance, welfareWorkhour, welfareVacation;
    private TextView informationFounding, informationSize, informationWebsite, informationEmail, informationPhone, informationAddress;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_student_classified);
        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);
        assignElements();
        setElements(index);

        //finish();
    }

    public void assignElements(){
        icon = (ImageView)findViewById(R.id.page_student_classified_icon);
        introduction = (TextView)findViewById(R.id.page_student_classified_introduction);
        hiringName = (TextView)findViewById(R.id.page_student_classified_hiring_name);
        hiringJob = (TextView)findViewById(R.id.page_student_classified_hiring_job);
        hiringSalaryExperience = (TextView)findViewById(R.id.page_student_classified_hiring_salary_experience);
        hiringSkill = (TextView)findViewById(R.id.page_student_classified_hiring_skill);
        welfareInsurance = (TextView)findViewById(R.id.page_student_classified_welfare_insurance);
        welfareWorkhour = (TextView)findViewById(R.id.page_student_classified_welfare_workhour);
        welfareVacation = (TextView)findViewById(R.id.page_student_classified_welfare_vacation);
        informationFounding = (TextView)findViewById(R.id.page_student_classified_information_founding);
        informationSize = (TextView)findViewById(R.id.page_student_classified_information_size);
        informationWebsite = (TextView)findViewById(R.id.page_student_classified_information_website);
        informationEmail = (TextView)findViewById(R.id.page_student_classified_information_email);
        informationPhone = (TextView)findViewById(R.id.page_student_classified_information_phone);
        informationAddress = (TextView)findViewById(R.id.page_student_classified_information_address);
    }

    public void setElements(int index){
        icon.setImageResource(StudentClassifiedData.IMGID[index]);
        introduction.setText(StudentClassifiedData.INTRODUCTION[index]);
        hiringName.setText(StudentClassifiedData.NAME[index]);
        hiringJob.setText(StudentClassifiedData.JOB[index]);
        hiringSalaryExperience.setText(StudentClassifiedData.SALARY[index] + " / " + StudentClassifiedData.EXPERIENCE[index]);
        hiringSkill.setText(StudentClassifiedData.SKILLS[index]);
        welfareInsurance.setText(StudentClassifiedData.INSURANCE[index]);
        welfareWorkhour.setText(StudentClassifiedData.WORKHOUR[index]);
        welfareVacation.setText(StudentClassifiedData.VACATION[index]);
        informationFounding.setText(StudentClassifiedData.FOUNDING[index]);
        informationSize.setText(StudentClassifiedData.SIZE[index]);
        informationWebsite.setText(StudentClassifiedData.WEBSITE[index]);
        informationEmail.setText(StudentClassifiedData.EMAIL[index]);
        informationPhone.setText(StudentClassifiedData.PHONE[index]);
        informationAddress.setText(StudentClassifiedData.ADDRESS[index]);
    }
}
