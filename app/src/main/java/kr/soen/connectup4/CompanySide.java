package kr.soen.connectup4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by ettur on 2017-11-21.
 */

public class CompanySide extends AppCompatActivity{
    private BottomNavigationView bottomNavigationView; //하단바

    private CompanyHomeFragment homeFragment;
    private CompanyClassifiedFragment tab2Fragment;
    private CompanyEventFragment tab3Fragment;
    private CompanyBulletinFragment tab4Fragment;
    private CompanyScrapFragment tab5Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_company_main);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.company_bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView); //하단바 쉬프트 해제

        homeFragment = new CompanyHomeFragment();
        tab2Fragment = new CompanyClassifiedFragment();
        tab3Fragment = new CompanyEventFragment();
        tab4Fragment = new CompanyBulletinFragment();
        tab5Fragment = new CompanyScrapFragment();

        initFragment(); //처음 실행 시 홈화면

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //하단바 버튼별 화면
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch(item.getItemId()){
                    case R.id.company_action_home:
                        transaction.replace(R.id.companyContentContainer, homeFragment);
                        transaction.commit();
                        break;
                    case R.id.company_action_tab2:
                        transaction.replace(R.id.companyContentContainer, tab2Fragment);
                        transaction.commit();
                        break;
                    case R.id.company_action_tab3:
                        transaction.replace(R.id.companyContentContainer, tab3Fragment);
                        transaction.commit();
                        break;
                    case R.id.company_action_tab4:
                        transaction.replace(R.id.companyContentContainer, tab4Fragment);
                        transaction.commit();
                        break;
                }
                return true;
            }
        });
    }

    private void initFragment(){ //처음 실행 시 홈 화면 보임
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.companyContentContainer, homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
