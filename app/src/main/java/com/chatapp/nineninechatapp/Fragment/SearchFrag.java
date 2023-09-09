package com.chatapp.nineninechatapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.Utility;

public class SearchFrag extends Fragment implements View.OnClickListener {
    EditText search;
    ImageView cancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search,container,false);
        Utility.darkMode(getActivity());

        search=view.findViewById(R.id.ed_search);
        cancel=view.findViewById(R.id.img_cancel);

        cancel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_cancel:

                search.setText("");
                search.setHint(R.string.search);

                break;
        }
    }
}
