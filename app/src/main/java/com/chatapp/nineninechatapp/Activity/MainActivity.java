package com.chatapp.nineninechatapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.chatapp.nineninechatapp.Adapter.ViewPagerAdapter;
import com.chatapp.nineninechatapp.Fragment.AccountFrag;
import com.chatapp.nineninechatapp.Fragment.DiscoveryFrag;
import com.chatapp.nineninechatapp.Fragment.FeedFrag;
import com.chatapp.nineninechatapp.Fragment.HomeFrag;
import com.chatapp.nineninechatapp.Fragment.SearchFrag;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.CustomViewPager;
import com.chatapp.nineninechatapp.Utils.Utility;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPagerAdapter viewPagerAdapter;
    CustomViewPager viewPager;
    AccountFrag accountFrag;
    DiscoveryFrag discoveryFrag;
    FeedFrag feedFrag;
    HomeFrag homeFrag;
    SearchFrag searchFrag;
    LinearLayout home,search,disc,account;
    RelativeLayout video;
    ImageView imgVideo,imgHome,imgSearch,imgDisc,imgAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utility.darkMode(this);

        intiUI();
        Utility.FullScreen(this);
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
            imgVideo.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.res_white));
        }else {
            imgVideo.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.res));
        }

        home.setOnClickListener(this);
        search.setOnClickListener(this);
        video.setOnClickListener(this);
        disc.setOnClickListener(this);
        account.setOnClickListener(this);
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
                replaceFragment(searchFrag);

                break;
            case R.id.video:

                viewPager.setCurrentItem(2);
                imgAccount.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.account));
                imgHome.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.home));
                imgSearch.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.search));
                imgDisc.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.dis));

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
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.viewpager_container, fragment)
                .commit();
    }

}