package com.chatapp.nineninechatapp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.chatapp.nineninechatapp.Adapter.FriAcceptAdapter;
import com.chatapp.nineninechatapp.Model.AcceptFriend.AcceptModel;
import com.chatapp.nineninechatapp.Model.AcceptFriend.AcceptObj;
import com.chatapp.nineninechatapp.Model.Login.UserObj;
import com.chatapp.nineninechatapp.Model.ReqFriendList.ReqFriDataModel;
import com.chatapp.nineninechatapp.Model.ReqFriendList.ReqFriModel;
import com.chatapp.nineninechatapp.Model.ReqFriendList.ReqFriendListObj;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.NetworkServiceProvider;
import com.chatapp.nineninechatapp.Utils.Utility;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestFrag extends Fragment implements FriAcceptAdapter.Click {
    NetworkServiceProvider serviceProvider;
    ProgressBar progressBar;
    UserObj userObj;
    FriAcceptAdapter adapter;
    ArrayList<ReqFriDataModel> arrayList=new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    TextView noData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_request,container,false);
        Utility.darkMode(getActivity());
        userObj=Utility.query_UserProfile(getActivity());
        serviceProvider=new NetworkServiceProvider(getActivity());
        progressBar=view.findViewById(R.id.progressBar);
        recyclerView=view.findViewById(R.id.recycler);
        refreshLayout=view.findViewById(R.id.swipeLayout);
        noData=view.findViewById(R.id.no_data);

        adapter= new FriAcceptAdapter(getActivity(),arrayList);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setClick(this);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestFriend();
            }
        });

        return view;
    }

    private void CallReqFriend(ReqFriendListObj authObj) {
        if (Utility.isOnline(getActivity())){
            refreshLayout.setRefreshing(true);
            serviceProvider.ReqFriend(APIURL.DomainName+APIURL.requestFriendList,authObj).enqueue(new Callback<ReqFriModel>() {
                @Override
                public void onResponse(Call<ReqFriModel> call, Response<ReqFriModel> response) {
                    refreshLayout.setRefreshing(false);

                    if (response.body().getCode()==1){
                        if (response.body().getData().size()!=0){
                            noData.setVisibility(View.GONE);
                        }else {
                            noData.setVisibility(View.VISIBLE);
                        }
                       arrayList.clear();
                       arrayList.addAll(response.body().getData());
                       adapter.notifyDataSetChanged();
                    }else if (response.body().getCode()==0){
                        Utility.showToast(getContext(),response.body().getMsg());
                    }
                }
                @Override
                public void onFailure(Call<ReqFriModel> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }else {
            Utility.showToast(getActivity(),getString(R.string.check_internet));
        }
    }

    @Override
    public void onResume() {
        requestFriend();
        super.onResume();
    }

    public void requestFriend(){
        ReqFriendListObj friendListObj=new ReqFriendListObj();
        friendListObj.setFriendId(userObj.getId());
        CallReqFriend(friendListObj);
    }

    @Override
    public void AdapterClick(ReqFriDataModel obj) {
        AcceptObj acceptObj=new AcceptObj();
        acceptObj.setRequestFriendId(obj.getRequestFriendId());
        CallAccept(acceptObj);
    }

    private void CallAccept(AcceptObj authObj) {
        if (Utility.isOnline(getActivity())){
            progressBar.setVisibility(View.VISIBLE);
            serviceProvider.AcceptFriend(APIURL.DomainName+APIURL.acceptFriend,authObj).enqueue(new Callback<AcceptModel>() {
                @Override
                public void onResponse(Call<AcceptModel> call, Response<AcceptModel> response) {
                    progressBar.setVisibility(View.GONE);

                    if (response.body().getCode()==1){
                        Utility.showToast(getContext(),response.body().getMsg());
                        requestFriend();
                    }else if (response.body().getCode()==0){
                        Utility.showToast(getContext(),response.body().getMsg());
                    }
                }
                @Override
                public void onFailure(Call<AcceptModel> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }else {
            Utility.showToast(getActivity(),getString(R.string.check_internet));
        }
    }
}
