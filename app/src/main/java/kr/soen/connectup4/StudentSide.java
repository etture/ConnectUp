package kr.soen.connectup4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by ettur on 2017-11-21.
 */

public class StudentSide extends AppCompatActivity{
    private BottomNavigationView bottomNavigationView; //하단바

    private HomeFragment homeFragment;
    private ClassifiedFragment classifiedFragment;
    private EventFragment eventFragment;
    private BulletinFragment bulletinFragment;
    private ScrapFragment scrapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_student_main);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView); //하단바 쉬프트 해제

        homeFragment = new HomeFragment();
        classifiedFragment = new ClassifiedFragment();
        eventFragment = new EventFragment();
        bulletinFragment = new BulletinFragment();
        scrapFragment = new ScrapFragment();

        initFragment(); //처음 실행 시 홈화면

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //하단바 버튼별 화면
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch(item.getItemId()){
                    case R.id.action_home:
                        transaction.replace(R.id.studentContentContainer, homeFragment);
                        transaction.commit();
                        break;
                    case R.id.action_classified:
                        transaction.replace(R.id.studentContentContainer, classifiedFragment);
                        transaction.commit();
                        break;
                    case R.id.action_event:
                        transaction.replace(R.id.studentContentContainer, eventFragment);
                        transaction.commit();
                        break;
                    case R.id.action_bulletin:
                        transaction.replace(R.id.studentContentContainer, bulletinFragment);
                        transaction.commit();
                        break;
                }
                return true;
            }
        });
    }

    private void initFragment(){ //처음 실행 시 홈 화면 보임
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.studentContentContainer, homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
