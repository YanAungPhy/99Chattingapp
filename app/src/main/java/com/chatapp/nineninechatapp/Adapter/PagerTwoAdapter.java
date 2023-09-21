package com.chatapp.nineninechatapp.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.chatapp.nineninechatapp.Fragment.RequestFrag;
import com.chatapp.nineninechatapp.Fragment.SearchFrag;

public class PagerTwoAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerTwoAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SearchFrag tab1 = new SearchFrag();
                return tab1;
            case 1:
                RequestFrag tab2 = new RequestFrag();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
