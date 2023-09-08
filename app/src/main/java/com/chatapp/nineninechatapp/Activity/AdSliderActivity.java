package com.chatapp.nineninechatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chatapp.nineninechatapp.Adapter.OnboardingAdapter;
import com.chatapp.nineninechatapp.Model.OnboardingItem;
import com.chatapp.nineninechatapp.R;

import java.util.ArrayList;
import java.util.List;

public class AdSliderActivity extends AppCompatActivity {

    OnboardingAdapter onboardingAdapter;
    LinearLayout layoutOnboardingIndicator;
    TextView tvNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_slider);
        FullScreen();

        layoutOnboardingIndicator = findViewById(R.id.layoutOnboardingIndicators);
        tvNext=findViewById(R.id.txtNext);
        setUpOnBoardingItems();
        ViewPager2 onBoardingViewPager = findViewById(R.id.onBoardingViewPager);
        onBoardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicator();
        setCurrentOnboardingIndicator(0);

        onBoardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdSliderActivity.this,LoginActivity.class));
            }
        });
    }

    private  void setUpOnBoardingItems(){
        List<OnboardingItem> onboardingItem = new ArrayList<>();

        OnboardingItem onBoardOne = new OnboardingItem();
        onBoardOne.setTitle(getString(R.string.no_one_know_your_privacy));
        onBoardOne.setDescription(getString(R.string.the_choice_for_smart_and_safety));
        onBoardOne.setImage(R.drawable.slider1);

        OnboardingItem onBoardTwo = new OnboardingItem();
        onBoardTwo.setTitle(getString(R.string.boundless_chat));
        onBoardTwo.setDescription(getString(R.string.connecting_people_bridging_worlds));
        onBoardTwo.setImage(R.drawable.slider2);

        OnboardingItem onBoardThree = new OnboardingItem();
        onBoardThree.setTitle(getString(R.string.unbelievable_ux));
        onBoardThree.setDescription(getString(R.string.the_world_is_your_neighbourhood_now));
        onBoardThree.setImage(R.drawable.slider3);

        onboardingItem.add(onBoardOne);
        onboardingItem.add(onBoardTwo);
        onboardingItem.add(onBoardThree);

        onboardingAdapter = new OnboardingAdapter(onboardingItem);
    }

    public static void setWindowFlag(AppCompatActivity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void FullScreen(){
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setupOnboardingIndicator(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(5,0,5,0);
        for (int i= 0; i< indicators.length;i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicator.addView(indicators[i]);

        }
    }

    private void setCurrentOnboardingIndicator(int index){
        int  childCount = layoutOnboardingIndicator.getChildCount();
        for (int i = 0 ; i <childCount; i++){
            ImageView imageView =(ImageView) layoutOnboardingIndicator.getChildAt(i);
            if(i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active)
                );
            }else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive)
                );
            }
        }

    }
}