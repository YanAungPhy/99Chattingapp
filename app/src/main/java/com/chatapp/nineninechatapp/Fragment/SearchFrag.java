package com.chatapp.nineninechatapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.Utility;

public class SearchFrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
<<<<<<< HEAD
        View view=inflater.inflate(R.layout.search,container,false);
        Utility.darkMode(getActivity());

=======
        View view=inflater.inflate(R.layout.fragment_search,container,false);
>>>>>>> origin/master

        return view;
    }
}
