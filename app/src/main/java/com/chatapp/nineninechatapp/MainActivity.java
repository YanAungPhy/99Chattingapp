package com.chatapp.nineninechatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.chatapp.nineninechatapp.Adapter.ViewPagerAdapter;
import com.chatapp.nineninechatapp.Fragment.AccountFrag;
import com.chatapp.nineninechatapp.Fragment.DiscoveryFrag;
import com.chatapp.nineninechatapp.Fragment.FeedFrag;
import com.chatapp.nineninechatapp.Fragment.HomeFrag;
import com.chatapp.nineninechatapp.Fragment.SearchFrag;
import com.chatapp.nineninechatapp.Utils.CustomViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    ViewPagerAdapter viewPagerAdapter;
    CustomViewPager viewPager;
    AccountFrag accountFrag;
    DiscoveryFrag discoveryFrag;
    FeedFrag feedFrag;
    HomeFrag homeFrag;
    SearchFrag searchFrag;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=findViewById(R.id.viewpager_container);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setItemIconTintList(null);
        intiUI();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }


    private void intiUI() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                if (position == 0) {
                    viewPager.setCurrentItem(0);
                } else if (position == 1) {
                    viewPager.setCurrentItem(1);
                }else if (position == 2){
                    viewPager.setCurrentItem(2);
                }else if (position == 3){
                    viewPager.setCurrentItem(3);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
        viewPager.disableScroll(true);
        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFrag = new HomeFrag();
        searchFrag = new SearchFrag();
        feedFrag = new FeedFrag();
        discoveryFrag = new DiscoveryFrag();
        accountFrag=new AccountFrag();

        viewPagerAdapter.addFragment(homeFrag);
        viewPagerAdapter.addFragment(searchFrag);
        viewPagerAdapter.addFragment(feedFrag);
        viewPagerAdapter.addFragment(discoveryFrag);
        viewPagerAdapter.addFragment(accountFrag);

        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:

                viewPager.setCurrentItem(0);

                break;

            case R.id.search:

                viewPager.setCurrentItem(1);

                break;
            case R.id.video:

                viewPager.setCurrentItem(2);

                break;
            case R.id.discovery:

                viewPager.setCurrentItem(3);

                break;
            case R.id.account:

                viewPager.setCurrentItem(4);

                break;

        }
        return false;
    }
}