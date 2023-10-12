package com.chatapp.nineninechatapp.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.chatapp.nineninechatapp.EventBusModel.StringBus;
import com.chatapp.nineninechatapp.Fragment.DialogImagePicker;
import com.chatapp.nineninechatapp.Model.Register.LocationRegister;
import com.chatapp.nineninechatapp.Model.Register.RegisterModel;
import com.chatapp.nineninechatapp.Model.Register.RegisterObj;
import com.chatapp.nineninechatapp.Model.Register.UploadImgModel;
import com.chatapp.nineninechatapp.Model.Register.VerifyOTP.VerifyDataModel;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.AppENUM;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.GpsTracker;
import com.chatapp.nineninechatapp.Utils.NetworkServiceProvider;
import com.chatapp.nineninechatapp.Utils.NetworkSync;
import com.chatapp.nineninechatapp.Utils.RetrofitFactory;
import com.chatapp.nineninechatapp.Utils.Utility;
import com.ozcanalasalvar.library.utils.DateUtils;
import com.ozcanalasalvar.library.view.datePicker.DatePicker;
import com.ozcanalasalvar.library.view.popup.DatePickerPopup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayAdapter<String> adapter;
    Spinner spinner;
    RelativeLayout dateLayout;
    TextView birth_date;
    private DatePickerPopup datePickerPopup;
    TextView toolbar;
    ImageView back,imgSH,imgSHC;
    EditText name,password,con_password;
    NetworkServiceProvider serviceProvider;
    ProgressBar progressBar;
    Button btnNext;
    VerifyDataModel verifyObj;
    int sexName=1;
    private static int MY_FINE_LOCATION_REQUEST = 99;
    GpsTracker gpsTracker;
    CircleImageView imageView;
    File file_profile;
    Bitmap bitmap;
    File wallpaperDirectory;
    String uri_img;
    public static final int REQUEST_COARSE_LOCATION = 200;
    public static final int REQUEST_IMAGE = 100;
    private static final String IMAGE_DIRECTORY = "/nineninechat";
    RequestBody image;
    MultipartBody.Part user_image;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_details);

        initView();
    }

    private void requestFineLocationPermission() {
        ActivityCompat.requestPermissions(this,  new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, MY_FINE_LOCATION_REQUEST);
    }

    public void initView(){
        gpsTracker=new GpsTracker(this);
        requestFineLocationPermission();
        verifyObj=(VerifyDataModel)getIntent().getSerializableExtra("verify_obj");
        serviceProvider=new NetworkServiceProvider(this);
        Utility.darkMode(this);
        mtoolbar();
        datePickerPopup = new DatePickerPopup.Builder()
                .from(this)
                .offset(3)
                .darkModeEnabled(true)
                .pickerMode(com.ozcanalasalvar.library.view.datePicker.DatePicker.MONTH_ON_FIRST)
                .textSize(19)
                .endDate(DateUtils.getTimeMiles(2050, 10, 25))
                .currentDate(DateUtils.getCurrentTime())
                .startDate(DateUtils.getTimeMiles(1888, 0, 1))
                .listener(new DatePickerPopup.OnDateSelectListener() {
                    @Override
                    public void onDateSelected(DatePicker dp, long date, int day, int month, int year) {
                        birth_date.setText(year + "-" + (month + 1) + "-" + day);
                    }
                }).build();
        String[] sexInfo = {getResources().getString(R.string.male),getResources().getString(R.string.female),getResources().getString(R.string.other)};
        spinner=findViewById(R.id.spinner_sex);
        dateLayout=findViewById(R.id.date_layout);
        birth_date=findViewById(R.id.tv_birth_date);
        name=findViewById(R.id.userName);
        password=findViewById(R.id.edt_password);
        con_password=findViewById(R.id.edt_confirmPassword);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        con_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        imgSH=findViewById(R.id.btn_showImageHide);
        imgSHC=findViewById(R.id.btn_showImageHideC);
        dateLayout=findViewById(R.id.date_layout);
        birth_date=findViewById(R.id.tv_birth_date);
        progressBar=findViewById(R.id.progressBar);
        btnNext=findViewById(R.id.btn_next);
        imageView=findViewById(R.id.user_image);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sexInfo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                if (spinner.getSelectedItem()=="Male"){
                    sexName=1;
                }else if (spinner.getSelectedItem()=="Female"){
                    sexName=0;
                }else if (spinner.getSelectedItem()=="Other"){
                    sexName=2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dateLayout.setOnClickListener(this);
        imgSH.setOnClickListener(this);
        imgSHC.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    public void mtoolbar(){
        toolbar=findViewById(R.id.toolbar_com);
        back=findViewById(R.id.back);
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

    private void passwordVisibleState() {
        if (password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            imgSH.setImageResource(R.drawable.visibility_off);
        } else {
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            imgSH.setImageResource(R.drawable.visibility_fill);
        }
    }

    private void passwordVisibleStateC() {
        if (con_password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
            con_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            imgSHC.setImageResource(R.drawable.visibility_off);
        } else {
            con_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            imgSHC.setImageResource(R.drawable.visibility_fill);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:

                finish();

                break;
            case R.id.btn_showImageHide:

                passwordVisibleState();

                break;
            case R.id.btn_showImageHideC:

                passwordVisibleStateC();

                break;
            case R.id.btn_next:

                String pass=password.getText().toString();
                String con_pass=con_password.getText().toString();
                if (pass.length()<6){
                    password.startAnimation(Utility.shakeError());
                    Utility.showToast(RegisterDetailsActivity.this,getString(R.string.six_number));
                }else if (con_pass.length()<6){
                    Utility.showToast(RegisterDetailsActivity.this,getString(R.string.six_number));
                    con_password.startAnimation(Utility.shakeError());
                }else if (!pass.equalsIgnoreCase(con_pass)){
                    Utility.showToast(RegisterDetailsActivity.this,getString(R.string.not_equal));
                    password.startAnimation(Utility.shakeError());
                    con_password.startAnimation(Utility.shakeError());
                }else {
                    Call_Api();
                }

                break;

            case R.id.date_layout:

                datePickerPopup.show();

                break;
            case R.id.user_image:

                call_DialogImagePicker();

                break;
        }
    }

    public void call_DialogImagePicker(){

        DialogImagePicker bt=new DialogImagePicker(this,R.style.BottomSheetDialogTheme);
        View view= LayoutInflater.from(this).inflate(R.layout.dialog_edit_profile,null);

        bt.setContentView(view);
        bt.show();

    }

    public void Call_Api(){

        if (Utility.isOnline(this)){
            progressBar.setVisibility(View.VISIBLE);
            if (file_profile!=null){
                image = RequestBody.create(MediaType.parse("image/*"), file_profile);
                user_image= MultipartBody.Part.createFormData("file", file_profile.getName(), image);
            }
            RequestBody nickname = RequestBody.create(MediaType.parse("text/plain"), name.getText().toString());
            RequestBody pass = RequestBody.create(MediaType.parse("text/plain"), password.getText().toString());
            RequestBody phone = RequestBody.create(MediaType.parse("text/plain"),"09960124865");
            RequestBody sex = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(sexName));
            RequestBody countryId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(1));
            RequestBody provinceId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(1));
            RequestBody cityId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(1));
            RequestBody birthday = RequestBody.create(MediaType.parse("text/plain"), birth_date.getText().toString());
            RequestBody firebaseToken = RequestBody.create(MediaType.parse("text/plain"), AppStorePreferences.getString(RegisterDetailsActivity.this,AppENUM.FCM_TOKEN));
            RequestBody areaCode = RequestBody.create(MediaType.parse("text/plain"), "+95");
            RetrofitFactory factory=new RetrofitFactory();
            Retrofit retrofit=factory.connector();
            NetworkSync.RegisterSync sync =retrofit.create(NetworkSync.RegisterSync.class);
            Call<RegisterModel> call=sync.UserImg(APIURL.DomainName+APIURL.register,nickname,pass,phone,sex,countryId,provinceId,cityId,birthday,firebaseToken,user_image,areaCode);
            call.enqueue(new Callback<RegisterModel>() {
                @Override
                public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {

                    progressBar.setVisibility(View.GONE);

                    if (response.body().getCon()==true){

                      //  Log.e("mtt>>>>","Register Successful");
                        /*Utility.showToast(RegisterDetailsActivity.this,response.body().getMsg());
                        AppStorePreferences.putInt(RegisterDetailsActivity.this, AppENUM.LOGIN_CON,1);
                      //  Utility.Save_UserProfile(RegisterDetailsActivity.this,response.body().getData());
                        finish();
                        startActivity(new Intent(RegisterDetailsActivity.this,MainActivity.class));*/

                    }else if (response.body().getCon()==false){
                        Utility.showToast(RegisterDetailsActivity.this,response.body().getMsg());
                    }
                }
                @Override
                public void onFailure(Call<RegisterModel> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });

        }else {
            Utility.showToast(RegisterDetailsActivity.this,getString(R.string.check_internet));
        }

    }


    private void launchCameraIntent() {
        Intent intent = new Intent(RegisterDetailsActivity.this, ImagePickerActivity.class);
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
        Intent intent = new Intent(RegisterDetailsActivity.this, ImagePickerActivity.class);
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

                    Glide.with(RegisterDetailsActivity.this)
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
            MediaScannerConnection.scanFile(RegisterDetailsActivity.this,
                    new String[]{file_profile.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();

            return file_profile.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }
}
