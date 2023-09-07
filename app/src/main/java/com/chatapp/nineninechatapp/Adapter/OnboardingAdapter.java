package com.chatapp.nineninechatapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chatapp.nineninechatapp.Model.OnboardingItem;
import com.chatapp.nineninechatapp.R;

import java.util.List;

public class OnboardingAdapter extends  RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>{

    List<OnboardingItem>  onboardingItemList;

    public OnboardingAdapter(List<OnboardingItem> onboardingItemList) {
        this.onboardingItemList = onboardingItemList;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboarding,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(onboardingItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItemList.size();
    }

    class OnboardingViewHolder extends  RecyclerView.ViewHolder{

         private TextView txtTitle;
         private TextView txtDescription;
         private ImageView imageOnboarding;

         public OnboardingViewHolder(@NonNull View itemView) {
             super(itemView);
             txtTitle = itemView.findViewById(R.id.txtTitle);
             txtDescription = itemView.findViewById(R.id.txtDescription);
             imageOnboarding = itemView.findViewById(R.id.imgOnboarding);

         }

        void setOnboardingData(OnboardingItem onboardingItem){
             imageOnboarding.setImageResource(onboardingItem.getImage());
             txtTitle.setText(onboardingItem.getTitle());
             txtDescription.setText(onboardingItem.getDescription());
        }
     }
}
