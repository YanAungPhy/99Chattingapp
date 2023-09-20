package com.chatapp.nineninechatapp.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.chatapp.nineninechatapp.Model.Login.LoginModel;
import com.chatapp.nineninechatapp.Model.Register.LocationRegister;
import com.chatapp.nineninechatapp.Model.Register.RegisterModel;
import com.chatapp.nineninechatapp.Model.Register.RegisterObj;
import com.chatapp.nineninechatapp.Model.Register.VerifyOTP.VerifyObj;
import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.APIURL;
import com.chatapp.nineninechatapp.Utils.AppStorePreferences;
import com.chatapp.nineninechatapp.Utils.GpsTracker;
import com.chatapp.nineninechatapp.Utils.NetworkServiceProvider;
import com.chatapp.nineninechatapp.Utils.Utility;
import com.ozcanalasalvar.library.utils.DateUtils;
import com.ozcanalasalvar.library.view.datePicker.DatePicker;
import com.ozcanalasalvar.library.view.popup.DatePickerPopup;

import java.util.ArrayList;
import java.util.List;

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
    VerifyObj verifyObj;
    int sexName=1;
    private static int MY_FINE_LOCATION_REQUEST = 99;
    GpsTracker gpsTracker;

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
        verifyObj=(VerifyObj)getIntent().getSerializableExtra("verify_obj");
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
                .startDate(DateUtils.getTimeMiles(1995, 0, 1))
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

                    RegisterObj registerObj=new RegisterObj();
                    registerObj.setAreaCode(String.valueOf(verifyObj.getAreaCode()));
                    registerObj.setTelephone(String.valueOf(verifyObj.getTelephone()));
                    registerObj.setUserName(name.getText().toString());
                    registerObj.setPassword(password.getText().toString());
                    registerObj.setBirthday(birth_date.getText().toString());
                    registerObj.setSex(sexName);
                    registerObj.setCountryId("11");
                    registerObj.setProvinceId("11");
                    registerObj.setCityId("11");
                    registerObj.setAreaId("11");
                    List<Double> cList=new ArrayList<>();
                    cList.add(gpsTracker.getLatitude());
                    cList.add(gpsTracker.getLongitude());
                    LocationRegister location=new LocationRegister();
                    location.setType("Point");
                    location.setCoordinates(cList);
                    registerObj.setLocation(location);
                    CallRegister(registerObj);
                }

                break;

            case R.id.date_layout:

                datePickerPopup.show();

                break;
        }
    }

    private void CallRegister(RegisterObj authObj) {
        if (Utility.isOnline(this)){
            progressBar.setVisibility(View.VISIBLE);
            serviceProvider.Register(APIURL.DomainName+APIURL.register,authObj).enqueue(new Callback<RegisterModel>() {
                @Override
                public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                    progressBar.setVisibility(View.GONE);

                    if (response.body().getCode()==1){

                        Intent intent=new Intent(RegisterDetailsActivity.this,RegisterProfileActivity.class);
                        intent.putExtra("telephone",verifyObj.getTelephone());
                        startActivity(intent);

                    }else if (response.body().getCode()==0){


                    }
                }
                @Override
                public void onFailure(Call<RegisterModel> call, Throwable t) {
                    Log.e("mtt>>",t.getLocalizedMessage());
                    progressBar.setVisibility(View.GONE);
                }
            });
        }else {
            Utility.showToast(this,getString(R.string.check_internet));
        }
    }
}
