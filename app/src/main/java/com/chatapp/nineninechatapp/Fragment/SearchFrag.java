package com.chatapp.nineninechatapp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chatapp.nineninechatapp.Adapter.SearchAdapter;
import com.chatapp.nineninechatapp.Model.Data;
import com.chatapp.nineninechatapp.Model.FindNickName.AddFriend.AddFriendModel;
import com.chatapp.nineninechatapp.Model.FindNickName.AddFriend.AddFriendObj;
import com.chatapp.nineninechatapp.Model.FindNickName.NickNameModel;
import com.chatapp.nineninechatapp.Model.FindNickName.NickNameObj;
import com.chatapp.nineninechatapp.Model.FindNickName.Noti_Obj;
import com.chatapp.nineninechatapp.Model.FindNickName.Notification;
import com.chatapp.nineninechatapp.Model.FindNickName.ResponNoti;
import com.chatapp.nineninechatapp.Model.Login.UserObj;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.NetworkServiceProvider;
import com.chatapp.nineninechatapp.Utils.RetrofitNoti.FCMNetworkServiceProvider;
import com.chatapp.nineninechatapp.Utils.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFrag extends Fragment implements View.OnClickListener,SearchAdapter.Click {
    EditText search;
    ImageView cancel;
    NetworkServiceProvider serviceProvider;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;
    ArrayList<UserObj> arrayList=new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    UserObj userObj;
    FCMNetworkServiceProvider fcmNetworkServiceProvider;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search,container,false);
        Utility.darkMode(getActivity());

        userObj=Utility.query_UserProfile(getActivity());
        serviceProvider=new NetworkServiceProvider(getActivity());
        fcmNetworkServiceProvider=new FCMNetworkServiceProvider(getActivity());
        progressBar=view.findViewById(R.id.progressBar);
        search=view.findViewById(R.id.ed_search);
        cancel=view.findViewById(R.id.img_cancel);
        recyclerView=view.findViewById(R.id.recycler);

        searchAdapter= new SearchAdapter(getActivity(),arrayList);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
        searchAdapter.setClick(this);

        cancel.setOnClickListener(this);
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    // Handle the "click" event here
                    if (search.getText().toString().equalsIgnoreCase("")){
                        search.startAnimation(Utility.shakeError());
                    }else {
                        NickNameObj nameObj=new NickNameObj();
                        nameObj.setNickname(search.getText().toString());
                        CallFindNickName(nameObj);
                    }

                    return true; // Event handled
                }
                return false;
            }
        });

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

    private void CallFindNickName(NickNameObj authObj) {
        if (Utility.isOnline(getActivity())){
            progressBar.setVisibility(View.VISIBLE);
            serviceProvider.FindNickName(APIURL.DomainName+APIURL.findByNickname,authObj).enqueue(new Callback<NickNameModel>() {
                @Override
                public void onResponse(Call<NickNameModel> call, Response<NickNameModel> response) {
                    progressBar.setVisibility(View.GONE);

                    if (response.body().getCode()==1){

                        arrayList.clear();
                        arrayList.addAll(response.body().getData());

                        searchAdapter.notifyDataSetChanged();


                    }else if (response.body().getCode()==0){
                        Utility.showToast(getContext(),response.body().getMsg());
                    }
                }
                @Override
                public void onFailure(Call<NickNameModel> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }else {
            Utility.showToast(getActivity(),getString(R.string.check_internet));
        }
    }

    @Override
    public void AdapterClick(UserObj obj) {
        AddFriendObj friendObj=new AddFriendObj();
        friendObj.setRequestFriendId(userObj.getId());
        friendObj.setFriendId(obj.getId());
        CallAddFriend(friendObj,obj);
    }

    private void CallAddFriend(AddFriendObj authObj,UserObj obj) {
        if (Utility.isOnline(getActivity())){
            progressBar.setVisibility(View.VISIBLE);
            serviceProvider.AddFriend(APIURL.DomainName+APIURL.add_friend,authObj).enqueue(new Callback<AddFriendModel>() {
                @Override
                public void onResponse(Call<AddFriendModel> call, Response<AddFriendModel> response) {
                    progressBar.setVisibility(View.GONE);

                    //For delete
                    sendNoti(obj);

                    if (response.body().getCode()==1){

                       // sendNoti(obj);
                        Utility.showToast(getContext(),response.body().getMsg());

                    }else if (response.body().getCode()==0){
                        Utility.showToast(getContext(),getString(R.string.already_add));
                    }
                }
                @Override
                public void onFailure(Call<AddFriendModel> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }else {
            Utility.showToast(getActivity(),getString(R.string.check_internet));
        }
    }

    public void sendNoti(UserObj obj){
        Data data=new Data();
        data.setNotiData("nineninechat");

        Notification notification=new Notification();
        notification.setTitle(getString(R.string.request_friend));
        notification.setBody(userObj.getName());

        Noti_Obj notiObj=new Noti_Obj();
        notiObj.setPriority("HIGH");
        notiObj.setData(data);
        notiObj.setNotification(notification);
        notiObj.setTo(obj.getFirebaseToken());
        //  notiObj.setTo("fELTgBNhSS2CGRMkIaYFdC:APA91bFZp0sOnXzxC0KP953ATmm7dIT5gH7AKLyFAX4oKrTS_X3-M8AhvPa33_HUFkSyKjfslZRMakyOs_FlFLdwW76eqke8FToxtXw0jSwLA7hSjS78vabVOhWO6577oHbEaKAIc9GP");

        Call_Notification(notiObj);
    }

    public void Call_Notification(Noti_Obj obj) {

        if (Utility.isOnline(getActivity())){
            progressBar.setVisibility(View.VISIBLE);
            fcmNetworkServiceProvider.Notification("https://fcm.googleapis.com/fcm/send",obj).enqueue(new Callback<ResponNoti>() {
                @Override
                public void onResponse(Call<ResponNoti> call, Response<ResponNoti> response) {
                    progressBar.setVisibility(View.GONE);
                    // doing_update();

                }
                @Override
                public void onFailure(Call<ResponNoti> call, Throwable t) {
                    Utility.showToast(getActivity(),t.getLocalizedMessage());


                }
            });
        }

    }
}
