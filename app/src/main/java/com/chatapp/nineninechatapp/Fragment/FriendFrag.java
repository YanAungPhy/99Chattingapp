package com.chatapp.nineninechatapp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.chatapp.nineninechatapp.Adapter.PagerTwoAdapter;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.Utility;
import com.google.android.material.tabs.TabLayout;

public class FriendFrag extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_friend,container,false);
        Utility.darkMode(getActivity());

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.search)).setIcon(R.drawable.search));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.request_friend)).setIcon(R.drawable.account));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager= (ViewPager) view.findViewById(R.id.pager);
        mPager();


        return view;
    }

    public void mPager(){
        PagerTwoAdapter adapter = new PagerTwoAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    public void onResume() {

        mPager();

        super.onResume();
    }
}
