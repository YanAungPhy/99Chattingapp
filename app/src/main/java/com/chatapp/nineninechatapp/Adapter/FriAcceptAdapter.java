package com.chatapp.nineninechatapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chatapp.nineninechatapp.Fragment.RequestFrag;
import com.chatapp.nineninechatapp.Fragment.SearchFrag;
import com.chatapp.nineninechatapp.Model.Login.UserObj;
import com.chatapp.nineninechatapp.Model.ReqFriendList.ReqFriDataModel;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.Utility;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriAcceptAdapter extends RecyclerView.Adapter<FriAcceptAdapter.MyViewHolder> {

    Context context;
    List<ReqFriDataModel> list;
    UserObj userObj=new UserObj();

    public RequestFrag click;

    public FriAcceptAdapter(FragmentActivity activity, ArrayList<ReqFriDataModel> arrayList) {
        this.context=activity;
        this.list=arrayList;
    }

    public void setClick(RequestFrag requestFrag) {
        this.click=requestFrag;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView name,accept;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.user_image);
            name=itemView.findViewById(R.id.tvName);
            accept=itemView.findViewById(R.id.tvAccept);
            userObj= Utility.query_UserProfile(context);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vv = inflater.inflate(R.layout.item_request_friend, parent, false);
        return new MyViewHolder(vv);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        ReqFriDataModel obj=list.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.profile_default);
        requestOptions.error(R.drawable.profile_default);
        Glide.with(context).load(APIURL.ImageUrl+obj.getRequestFriendImagePath()).apply(requestOptions).into(holder.imageView);
        holder.name.setText(String.valueOf(obj.getRequestFriendName()));

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.AdapterClick(obj);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface Click{
        public void AdapterClick(ReqFriDataModel obj);
    }
}
