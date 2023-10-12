package com.chatapp.nineninechatapp.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.chatapp.nineninechatapp.Adapter.ViewPagerAdapter;
import com.chatapp.nineninechatapp.EventBusModel.StringBus;
import com.chatapp.nineninechatapp.Fragment.AccountFrag;
import com.chatapp.nineninechatapp.Fragment.DiscoveryFrag;
import com.chatapp.nineninechatapp.Fragment.FeedFrag;
import com.chatapp.nineninechatapp.Fragment.FriendFrag;
import com.chatapp.nineninechatapp.Fragment.HomeFrag;
import com.chatapp.nineninechatapp.Fragment.SearchFrag;


import com.chatapp.nineninechatapp.Model.Login.UserObj;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.CustomViewPager;
import com.chatapp.nineninechatapp.Utils.Utility;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPagerAdapter viewPagerAdapter;
    CustomViewPager viewPager;
    AccountFrag accountFrag;
    DiscoveryFrag discoveryFrag;
    FeedFrag feedFrag;
    HomeFrag homeFrag;
    FriendFrag friendFrag;
    LinearLayout home,search,disc,account;
    RelativeLayout video;
    ImageView imgVideo,imgHome,imgSearch,imgDisc,imgAccount;
    UserObj userObj;

    public static boolean feed_con=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utility.darkMode(this);

        intiUI();
      //  Utility.FullScreen(this);

    }

    private void intiUI() {
        userObj=Utility.query_UserProfile(this);

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

        feedNormal();

        home.setOnClickListener(this);
        search.setOnClickListener(this);
        video.setOnClickListener(this);
        disc.setOnClickListener(this);
        account.setOnClickListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFrag = new HomeFrag();
        friendFrag = new FriendFrag();
        feedFrag = new FeedFrag();
        discoveryFrag = new DiscoveryFrag();
        accountFrag=new AccountFrag();

        viewPagerAdapter.addFragment(homeFrag);
        viewPagerAdapter.addFragment(friendFrag);
        viewPagerAdapter.addFragment(feedFrag);
        viewPagerAdapter.addFragment(discoveryFrag);
        viewPagerAdapter.addFragment(accountFrag);

        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(viewPagerAdapter);
    }

    public void position_0(){
        viewPager.setCurrentItem(0);
        if (AppStorePreferences.getBoolean(MainActivity.this,"dark_mode")){
            imgAccount.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.account));
            imgHome.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.home_w));
            imgSearch.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.search));
            imgDisc.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.dis));
        }else {
            imgAccount.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.account));
            imgHome.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.home_d));
            imgSearch.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.search));
            imgDisc.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.dis));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home:
                position_0();
                replaceFragment(homeFrag);
                feedNormal();

                break;
            case R.id.search:

                viewPager.setCurrentItem(1);
                if (AppStorePreferences.getBoolean(MainActivity.this, "dark_mode")) {
                    imgAccount.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.account));
                    imgHome.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.home));
                    imgSearch.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.search_w));
                    imgDisc.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.dis));
                } else {
                    imgAccount.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.account));
                    imgHome.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.home));
                    imgSearch.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.search_d));
                    imgDisc.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.dis));
                }
                replaceFragment(friendFrag);
                feedNormal();

                break;
            case R.id.video:

                if (feed_con==true){
                    viewPager.setCurrentItem(2);
                }else if (feed_con==false){
                    startActivity(new Intent(MainActivity.this,FeedVideoActivity.class));
                }
                imgAccount.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.account));
                imgHome.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.home));
                imgSearch.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.search));
                imgDisc.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.dis));

                if (feed_con==true){
                    feed_con=false;
                    if (AppStorePreferences.getBoolean(MainActivity.this,"dark_mode")){
                        imgVideo.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.plus_one));
                    }else {
                        imgVideo.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.plus));
                    }
                }else if (feed_con==false){
                    feedNormal();
                }
                replaceFragment(feedFrag);

                break;
            case R.id.discovery:

                viewPager.setCurrentItem(3);
                if (AppStorePreferences.getBoolean(MainActivity.this, "dark_mode")) {
                    imgAccount.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.account));
                    imgHome.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.home));
                    imgSearch.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.search));
                    imgDisc.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.dis_w));
                } else {
                    imgAccount.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.account));
                    imgHome.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.home));
                    imgSearch.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.search));
                    imgDisc.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.dis_d));
                }
                replaceFragment(discoveryFrag);
                feedNormal();

                break;
            case R.id.account:

                viewPager.setCurrentItem(4);
                if (AppStorePreferences.getBoolean(MainActivity.this, "dark_mode")) {
                    imgAccount.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.account_w));
                    imgHome.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.home));
                    imgSearch.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.search));
                    imgDisc.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.dis));
                } else {
                    imgAccount.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.account_d));
                    imgHome.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.home));
                    imgSearch.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.search));
                    imgDisc.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.dis));
                }
                replaceFragment(accountFrag);
                feedNormal();
                break;
        }

    }

    public void feedNormal(){
        feed_con=true;
        if (AppStorePreferences.getBoolean(MainActivity.this,"dark_mode")){
            imgVideo.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.res_white));
        }else {
            imgVideo.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.res));
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.viewpager_container, fragment)
                .commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getStringBus(StringBus stringBus){
        if (stringBus.getEvent_name().equalsIgnoreCase("feed")){
            feed_con=false;
            if (AppStorePreferences.getBoolean(MainActivity.this,"dark_mode")){
                imgVideo.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.plus_one));
            }else {
                imgVideo.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.plus));
            }
        }
    }

}