package com.chatapp.nineninechatapp.Adapter;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.chatapp.nineninechatapp.Fragment.FeedFrag;
import com.chatapp.nineninechatapp.Model.Feed.FeedModel;
import com.chatapp.nineninechatapp.Model.Login.UserObj;
import com.chatapp.nineninechatapp.R;
import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    Context context;
    List<FeedModel> list;
    UserObj userObj=new UserObj();
    public FeedFrag click;
    private boolean isPlaying = true;

    SeekBar seekBar;

    VideoView videoView;

    public FeedAdapter(FragmentActivity activity, ArrayList<FeedModel> datalist) {
        this.context=activity;
        this.list=datalist;
    }

    public void setClick(FeedFrag feedFrag) {
        this.click=feedFrag;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView like_count,share_count;
        ImageView like,share,play;



        public MyViewHolder(View itemView) {
            super(itemView);

            videoView=itemView.findViewById(R.id.video_view);
            like_count=itemView.findViewById(R.id.like_count);
            share_count=itemView.findViewById(R.id.share_count);
            like=itemView.findViewById(R.id.like);
            share=itemView.findViewById(R.id.share);
            seekBar=itemView.findViewById(R.id.seek_bar);
            play=itemView.findViewById(R.id.img_play);


        }
    }

    @Override
    public FeedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vv = inflater.inflate(R.layout.feed_adapter, parent, false);
        return new FeedAdapter.MyViewHolder(vv);
    }

    @Override
    public void onBindViewHolder(final FeedAdapter.MyViewHolder holder, int position) {

        FeedModel obj=list.get(position);

        videoView.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/" +
                obj.getVideoUrl()));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                seekBar.setMax(videoView.getDuration());
                seekBar.postDelayed(onEverySecond,1000);
                videoView.start();

            }
        });

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlaying){
                    holder.play.setVisibility(View.VISIBLE);
                    videoView.pause();
                }else {
                    holder.play.setVisibility(View.GONE);
                    videoView.start();
                    seekBar.setMax(videoView.getDuration());
                    seekBar.postDelayed(onEverySecond,1000);
                }
                isPlaying=!isPlaying;
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                isPlaying=true;
                mediaPlayer.start();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    videoView.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                videoView.pause();
                seekBar.setMax(videoView.getDuration());
                seekBar.postDelayed(onEverySecond,1000);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                videoView.start();
                seekBar.setMax(videoView.getDuration());
                seekBar.postDelayed(onEverySecond,1000);

            }
        });


    }

    private Runnable onEverySecond=new Runnable() {

        @Override
        public void run() {

            if(seekBar != null) {
                seekBar.setProgress(videoView.getCurrentPosition());
            }

            if(videoView.isPlaying()) {
                seekBar.postDelayed(onEverySecond, 1000);
            }

        }
    };

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
        public void AdapterClick(FeedModel obj);
    }


}
