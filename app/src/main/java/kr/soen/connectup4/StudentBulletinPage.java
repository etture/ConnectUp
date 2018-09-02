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
 * Created by ettur on 2017-11-29.
 */

public class StudentBulletinPage extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView title, content;
    private int index;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_student_bulletin);


        //툴바
        toolbar = (Toolbar)findViewById(R.id.page_student_bulletin_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("게시글");

        //툴바 백 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);
        assignElements();
        setElements(index);

        //finish();
    }

    public void assignElements(){
        title = (TextView)findViewById(R.id.page_student_bulletin_text_title);
        content = (TextView)findViewById(R.id.page_student_bulletin_text_content);
    }

    public void setElements(int index){
        title.setText(StudentBulletinData.TITLE[index]);
        content.setText(StudentBulletinData.CONTENT[index]);
    }
}
