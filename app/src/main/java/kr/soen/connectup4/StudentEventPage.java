package kr.soen.connectup4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ettur on 2017-11-28.
 */

public class StudentEventPage extends AppCompatActivity {
    //NAME, IMGID, PERIOD, DATE, LOCATION
    private int index;
    private ImageView image;
    private TextView name, period, date, location;
    private TextView text;
    private RelativeLayout rel;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_student_event);
        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);
        assignElements();
        setElements(index);
        if(!StudentEventData.TEXT[index].equals("")){
            rel.setVisibility(View.VISIBLE);
        }

        //finish();
    }

    public void assignElements(){
        image = (ImageView)findViewById(R.id.page_student_event_image);
        name = (TextView)findViewById(R.id.page_student_event_name);
        period = (TextView)findViewById(R.id.page_student_event_info_period);
        date = (TextView)findViewById(R.id.page_student_event_info_date);
        location = (TextView)findViewById(R.id.page_student_event_info_location);
        text = (TextView)findViewById(R.id.page_student_event_text);
        rel = (RelativeLayout)findViewById(R.id.page_student_event_text_frame);
    }

    public void setElements(int index){
       image.setImageResource(StudentEventData.IMGID[index]);
       name.setText(StudentEventData.NAME[index]);
       period.setText(StudentEventData.PERIOD[index]);
       date.setText(StudentEventData.DATE[index]);
       location.setText(StudentEventData.LOCATION[index]);
       text.setText(StudentEventData.TEXT[index]);
    }
}
