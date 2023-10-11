package com.chatapp.nineninechatapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.chatapp.nineninechatapp.EventBusModel.StringBus;
import com.chatapp.nineninechatapp.Fragment.DialogImagePicker;
import com.chatapp.nineninechatapp.Model.Register.UploadImgModel;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.AppENUM;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.NetworkSync;
import com.chatapp.nineninechatapp.Utils.RetrofitFactory;
import com.chatapp.nineninechatapp.Utils.Utility;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterProfileActivity extends AppCompatActivity implements View.OnClickListener {

    CircleImageView imageView;
    File file_profile;
    String imageUrl="null";
    Bitmap bitmap;
    File wallpaperDirectory;
    String uri_img;
    public static final int REQUEST_COARSE_LOCATION = 200;
    public static final int REQUEST_IMAGE = 100;
    private static final String IMAGE_DIRECTORY = "/nineninechat";
    RequestBody image;
    MultipartBody.Part user_image;
    String phone="";
    ProgressBar progressBar;
    TextView tvNext;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_profile);

        initView();
    }

    public void initView() {
        mtoolbar();
        phone=(String)getIntent().getSerializableExtra("telephone");
        progressBar=findViewById(R.id.progressBar);
        imageView=findViewById(R.id.user_image);
        tvNext=findViewById(R.id.tv_next);

        imageView.setOnClickListener(this);
        tvNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_image:

                call_DialogImagePicker();

                break;
            case R.id.tv_next:

                Call_Api();

                break;
            case R.id.back:

                finish();

                break;
        }
    }

    public void call_DialogImagePicker(){

        DialogImagePicker bt=new DialogImagePicker(this,R.style.BottomSheetDialogTheme);
        View view= LayoutInflater.from(this).inflate(R.layout.dialog_edit_profile,null);

        bt.setContentView(view);
        bt.show();

    }

    private void launchCameraIntent() {
        Intent intent = new Intent(RegisterProfileActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(RegisterProfileActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);
        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void getStringBus(StringBus stringBus){
        if (stringBus.getEvent_name().equalsIgnoreCase("camera")){
            launchCameraIntent();
        }else if (stringBus.getEvent_name().equalsIgnoreCase("gallery")){
            launchGalleryIntent();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    SaveProfile(bitmap);

                    // loading profile image from local cache
                    uri_img=uri.toString();

                    Glide.with(RegisterProfileActivity.this)
                            .load(uri.toString())
                            .into(imageView);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String SaveProfile(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            wallpaperDirectory=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+IMAGE_DIRECTORY);
        }else {
            wallpaperDirectory= new File(
                    Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        }
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
        try {
            file_profile = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            file_profile.createNewFile();
            FileOutputStream fo = new FileOutputStream(file_profile);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(RegisterProfileActivity.this,
                    new String[]{file_profile.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();

            return file_profile.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public void Call_Api(){

        if (Utility.isOnline(this)){
            progressBar.setVisibility(View.VISIBLE);
            if (file_profile!=null){
                image = RequestBody.create(MediaType.parse("image/*"), file_profile);
                user_image= MultipartBody.Part.createFormData("file", file_profile.getName(), image);

            }
            RequestBody telephone = RequestBody.create(MediaType.parse("text/plain"), phone);
            RequestBody firebase_token = RequestBody.create(MediaType.parse("text/plain"), AppStorePreferences.getString(RegisterProfileActivity.this,AppENUM.FCM_TOKEN));
            RetrofitFactory factory=new RetrofitFactory();
            Retrofit retrofit=factory.connector();
            NetworkSync.UserImgSync sync =retrofit.create(NetworkSync.UserImgSync.class);
            Call<UploadImgModel> call=sync.UserImg(APIURL.DomainName+APIURL.uploadImageUrl,telephone,firebase_token,user_image);
            call.enqueue(new Callback<UploadImgModel>() {
                @Override
                public void onResponse(Call<UploadImgModel> call, Response<UploadImgModel> response) {

                    progressBar.setVisibility(View.GONE);

                    if (response.body().getCode()==1){

                        Utility.showToast(RegisterProfileActivity.this,response.body().getMsg());
                        AppStorePreferences.putInt(RegisterProfileActivity.this, AppENUM.LOGIN_CON,1);
                        Utility.Save_UserProfile(RegisterProfileActivity.this,response.body().getData());
                        finish();
                        startActivity(new Intent(RegisterProfileActivity.this,MainActivity.class));

                    }else if (response.body().getCode()==0){
                        Utility.showToast(RegisterProfileActivity.this,response.body().getMsg());
                    }
                }
                @Override
                public void onFailure(Call<UploadImgModel> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });

        }else {
           Utility.showToast(RegisterProfileActivity.this,getString(R.string.check_internet));
        }

    }

    public void mtoolbar(){
        TextView toolbar=findViewById(R.id.toolbar_com);
        ImageView back=findViewById(R.id.back);
        toolbar.setText(R.string.register);
        back.setOnClickListener(this);
        if (AppStorePreferences.getBoolean(getApplicationContext(),"dark_mode")){
            back.setImageResource(R.drawable.back_white);
            toolbar.setTextColor(getResources().getColor(R.color.white));
        }else {
            back.setImageResource(R.drawable.back_black);
            toolbar.setTextColor(getResources().getColor(R.color.black));
        }
    }

}
