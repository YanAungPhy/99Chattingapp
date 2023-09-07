package com.chatapp.nineninechatapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
=======
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
>>>>>>> YAP

import com.chatapp.nineninechatapp.Adapter.ViewPagerAdapter;
import com.chatapp.nineninechatapp.Fragment.AccountFrag;
import com.chatapp.nineninechatapp.Fragment.DiscoveryFrag;
import com.chatapp.nineninechatapp.Fragment.FeedFrag;
import com.chatapp.nineninechatapp.Fragment.HomeFrag;
import com.chatapp.nineninechatapp.Fragment.SearchFrag;
<<<<<<< HEAD
import com.chatapp.nineninechatapp.Model.Login.UserObj;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
=======
import com.chatapp.nineninechatapp.R;
>>>>>>> YAP
import com.chatapp.nineninechatapp.Utils.CustomViewPager;
import com.chatapp.nineninechatapp.Utils.Utility;
import com.google.android.material.bottomnavigation.BottomNavigationView;

<<<<<<< HEAD
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

=======
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
>>>>>>> YAP
    ViewPagerAdapter viewPagerAdapter;
    CustomViewPager viewPager;
    AccountFrag accountFrag;
    DiscoveryFrag discoveryFrag;
    FeedFrag feedFrag;
    HomeFrag homeFrag;
    SearchFrag searchFrag;
<<<<<<< HEAD
    LinearLayout home,search,disc,account;
    RelativeLayout video;
    UserObj userObj;
    ImageView imgVideo,imgHome,imgSearch,imgDisc,imgAccount;
=======
    MenuItem prevMenuItem;
>>>>>>> YAP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utility.darkMode(this);
<<<<<<< HEAD
        userObj=Utility.query_UserProfile(this);
        Log.e("mtt_userObj>>>",userObj.getNickname());


        intiUI();

    }

    private void intiUI() {
        viewPager=findViewById(R.id.viewpager_container);
        home=findViewById(R.id.home);
        search=findViewById(R.id.search);
        video=findViewById(R.id.video);
        disc=findViewById(R.id.discovery);
        account=findViewById(R.id.account);
        imgVideo=findViewById(R.id.img_video);
        imgAccount=findViewById(R.id.img_account);
        imgDisc=findViewById(R.id.img_discovery);
        imgSearch=findViewById(R.id.img_search);
        imgHome=findViewById(R.id.img_home);
        viewPager.disableScroll(true);
        setupViewPager(viewPager);
        position_0();

        if (AppStorePreferences.getBoolean(MainActivity.this,"dark_mode")){
            imgVideo.setImageDrawable(getResources().getDrawable(R.drawable.res_white));
        }else {
            imgVideo.setImageDrawable(getResources().getDrawable(R.drawable.res));
        }

        home.setOnClickListener(this);
        search.setOnClickListener(this);
        video.setOnClickListener(this);
        disc.setOnClickListener(this);
        account.setOnClickListener(this);

    }

    @Override
    protected void onResume() {

        super.onResume();
=======

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

>>>>>>> YAP
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

<<<<<<< HEAD
    public void position_0(){
        viewPager.setCurrentItem(0);
        if (AppStorePreferences.getBoolean(MainActivity.this,"dark_mode")){
            imgAccount.setImageDrawable(getResources().getDrawable(R.drawable.account));
            imgHome.setImageDrawable(getResources().getDrawable(R.drawable.home_w));
            imgSearch.setImageDrawable(getResources().getDrawable(R.drawable.search));
            imgDisc.setImageDrawable(getResources().getDrawable(R.drawable.dis));
        }else {
            imgAccount.setImageDrawable(getResources().getDrawable(R.drawable.account));
            imgHome.setImageDrawable(getResources().getDrawable(R.drawable.home_d));
            imgSearch.setImageDrawable(getResources().getDrawable(R.drawable.search));
            imgDisc.setImageDrawable(getResources().getDrawable(R.drawable.dis));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home:

                position_0();
=======
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:

                viewPager.setCurrentItem(0);
>>>>>>> YAP

                break;

            case R.id.search:

                viewPager.setCurrentItem(1);
<<<<<<< HEAD
                if (AppStorePreferences.getBoolean(MainActivity.this,"dark_mode")){
                    imgAccount.setImageDrawable(getResources().getDrawable(R.drawable.account));
                    imgHome.setImageDrawable(getResources().getDrawable(R.drawable.home));
                    imgSearch.setImageDrawable(getResources().getDrawable(R.drawable.search_w));
                    imgDisc.setImageDrawable(getResources().getDrawable(R.drawable.dis));
                }else {
                    imgAccount.setImageDrawable(getResources().getDrawable(R.drawable.account));
                    imgHome.setImageDrawable(getResources().getDrawable(R.drawable.home));
                    imgSearch.setImageDrawable(getResources().getDrawable(R.drawable.search_d));
                    imgDisc.setImageDrawable(getResources().getDrawable(R.drawable.dis));
                }
=======
>>>>>>> YAP

                break;
            case R.id.video:

                viewPager.setCurrentItem(2);

                break;
            case R.id.discovery:

                viewPager.setCurrentItem(3);
<<<<<<< HEAD
                if (AppStorePreferences.getBoolean(MainActivity.this,"dark_mode")){
                    imgAccount.setImageDrawable(getResources().getDrawable(R.drawable.account));
                    imgHome.setImageDrawable(getResources().getDrawable(R.drawable.home));
                    imgSearch.setImageDrawable(getResources().getDrawable(R.drawable.search));
                    imgDisc.setImageDrawable(getResources().getDrawable(R.drawable.dis_w));
                }else {
                    imgAccount.setImageDrawable(getResources().getDrawable(R.drawable.account));
                    imgHome.setImageDrawable(getResources().getDrawable(R.drawable.home));
                    imgSearch.setImageDrawable(getResources().getDrawable(R.drawable.search));
                    imgDisc.setImageDrawable(getResources().getDrawable(R.drawable.dis_d));
                }
=======
>>>>>>> YAP

                break;
            case R.id.account:

                viewPager.setCurrentItem(4);
<<<<<<< HEAD
                if (AppStorePreferences.getBoolean(MainActivity.this,"dark_mode")){
                    imgAccount.setImageDrawable(getResources().getDrawable(R.drawable.account_w));
                    imgHome.setImageDrawable(getResources().getDrawable(R.drawable.home));
                    imgSearch.setImageDrawable(getResources().getDrawable(R.drawable.search));
                    imgDisc.setImageDrawable(getResources().getDrawable(R.drawable.dis));
                }else {
                    imgAccount.setImageDrawable(getResources().getDrawable(R.drawable.account_d));
                    imgHome.setImageDrawable(getResources().getDrawable(R.drawable.home));
                    imgSearch.setImageDrawable(getResources().getDrawable(R.drawable.search));
                    imgDisc.setImageDrawable(getResources().getDrawable(R.drawable.dis));
                }


                break;
        }
=======

                break;

        }
        return false;
>>>>>>> YAP
    }
}