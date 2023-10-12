package com.chatapp.nineninechatapp.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chatapp.nineninechatapp.Dialog.FilterPreviewDialog;
import com.chatapp.nineninechatapp.EventBusModel.StringBus;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.UI.RecordButton;
import com.chatapp.nineninechatapp.Utils.Camera.RecorderUtils;
import com.chatapp.nineninechatapp.Utils.Utility;
import com.joe.camera2recorddemo.View.CameraRecordView;

import org.greenrobot.eventbus.EventBus;

public class FeedVideoActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout back;

    private static final int STATE_INIT = 0;
    private static final int STATE_RECORDING = 1;
    private static final int STATE_PAUSE = 2;
    private static final int FILE_MANAGEMENT_PERMISSION_REQ_CODE=2296;
    private static final int REQUEST_CODE_SELECT_VIDEO = 10;
    BroadcastReceiver closeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };
    private CameraRecordView mRecordView;
  //  private List<EpVideo> videos;
    private int mRecorderState;
    private String mCurrBgmPath;
    private String mCurrBgmName;
    private String mCurrPath;
    //private SelectMusicDialog mSelectDialog;
    private RecordButton mRecordBtn;
    private RelativeLayout rlMore;
    FilterPreviewDialog.OnUpdateFilterListener listener = new FilterPreviewDialog.OnUpdateFilterListener() {
        @Override
        public void select(int type) {
            mRecordView.switchFilter(type);
        }

        @Override
        public void dismiss() {
            rlMore.setVisibility(View.VISIBLE);
            mRecordBtn.setVisibility(View.VISIBLE);
        }
    };
    private FilterPreviewDialog mDialog;
    private FrameLayout waitPar;
    private TextView tvBgName;
    private LinearLayout llMusic;
    private View ivDel;
    private View ivLocal;
    private boolean isStop = false;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            waitPar.setVisibility(View.GONE);
            switch (msg.what) {
                case RecorderUtils.ACTIVATE_BTN: // 激活按钮
                    mRecordBtn.setEnabled(true);
                    break;
                case RecorderUtils.MUSIC_SUCCESS:
                    intentPreview(mCurrPath);
                    break;
                case RecorderUtils.MERGE_FAILURE:
                 //   showToast(getString(R.string.flatten_failure));
                    break;
            }
            return false;
        }
    });
    private int handlerTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_video);
        Utility.FullScreen(this);

        init();

    }

    public void init(){
        back=findViewById(R.id.ll_back);

        back.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        controlBack();
        super.onBackPressed();
    }

    public void controlBack(){
        StringBus stringBus=new StringBus();
        stringBus.setEvent_name("feed");
        EventBus.getDefault().postSticky(stringBus);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_back:

                controlBack();

                break;
        }
    }

    private void intentPreview(String filePath) {
        isStop = true;

        /*videos.clear();
        xbar.reset();*/
        llMusic.setVisibility(View.VISIBLE);
        Log.d("TESTCURRENTPATH",filePath);
        Intent intent = new Intent(this, FeedVideoActivity.class);
        intent.putExtra("file_path", filePath);
        if (!TextUtils.isEmpty(mCurrBgmName)) {
            intent.putExtra("music_name", mCurrBgmName);
        }
        startActivity(intent);
    }
}
