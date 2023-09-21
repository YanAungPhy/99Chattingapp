package com.chatapp.nineninechatapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chatapp.nineninechatapp.Fragment.SearchFrag;
import com.chatapp.nineninechatapp.Model.Login.UserObj;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.Utility;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    Context context;
    List<UserObj> list;
    UserObj userObj=new UserObj();

    public SearchFrag click;


    public SearchAdapter(FragmentActivity activity, ArrayList<UserObj> arrayList) {
        this.context=activity;
        this.list=arrayList;
    }

    public void setClick(SearchFrag searchFrag) {
        this.click=searchFrag;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView name,add_friend;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.user_image);
            name=itemView.findViewById(R.id.tvName);
            add_friend=itemView.findViewById(R.id.tvAddFriend);
            userObj= Utility.query_UserProfile(context);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vv = inflater.inflate(R.layout.item_add_friend, parent, false);
        return new MyViewHolder(vv);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        UserObj obj=list.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.profile_default);
        requestOptions.error(R.drawable.profile_default);
        Glide.with(context).load(obj.getImagePath()).apply(requestOptions).into(holder.imageView);
        holder.name.setText(String.valueOf(obj.getNickname()));

        if (userObj.getId().equalsIgnoreCase(obj.getId())){
            holder.add_friend.setText(context.getString(R.string.you));
        }else {
            holder.add_friend.setText(context.getString(R.string.add_friend));
        }

        holder.add_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.add_friend.getText().toString().equalsIgnoreCase(context.getString(R.string.you))){
                    click.AdapterClick(obj);
                }

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
        public void AdapterClick(UserObj obj);
    }
}
