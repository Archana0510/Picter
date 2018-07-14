package com.picter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;

import static com.picter.R.id.toolbar;


public class FilterActivity extends Activity {
    Toolbar mToolbar;
    ImageView mTickImage;
    ImageView mCenterImage;
    final static int PICK_IMAGE =2;
    final static int MY_PERMISSION_REQUEST=3;

    private static final String TAG=FilterActivity.class.getSimpleName();

    ImageView m1stFilterImage;
    ImageView m2ndFilterImage;
    ImageView m3rdFilterImage;
    ImageView m4thFilterImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        mToolbar= findViewById(R.id.toolbar);
        mCenterImage=(ImageView)findViewById(R.id.CenterImage);

        m1stFilterImage=(ImageView)findViewById(R.id.imageView4);
        m2ndFilterImage=(ImageView)findViewById(R.id.imageView5);
        m3rdFilterImage=(ImageView)findViewById(R.id.imageView6);
        m4thFilterImage=(ImageView)findViewById(R.id.imageView7);

        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setLogo(R.drawable.icon);

        mTickImage=(ImageView)findViewById(R.id.imageView2);
        mTickImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(FilterActivity.this, ImagePriviewActivity.class);
                startActivity(intent);
            }
        });

        mCenterImage.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v) {
                requestStoragePermission();
                if (ContextCompat.checkSelfPermission(FilterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);

            }
        });
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults ){
        switch(requestCode){
            case MY_PERMISSION_REQUEST:
                    if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        new MaterialDialog.Builder(FilterActivity.this).title(R.string.permission_granted_title).content(R.string.permission_granted)
                                .positiveText("OK")
                                .canceledOnTouchOutside(true).show();
                    }else{
                Log.d(TAG,"Permission denied!");
                    }
        }
    }

    public void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode==PICK_IMAGE&&resultCode==Activity.RESULT_OK){
            Uri selectedImageUri= data.getData();

            Picasso.get().load(selectedImageUri).fit().centerInside().into(mCenterImage);

            Picasso.get().load(selectedImageUri).fit().centerInside().into(m1stFilterImage);
            Picasso.get().load(selectedImageUri).fit().centerInside().into(m2ndFilterImage);
            Picasso.get().load(selectedImageUri).fit().centerInside().into(m3rdFilterImage);
            Picasso.get().load(selectedImageUri).fit().centerInside().into(m4thFilterImage);


        }

    }
    public  void  requestStoragePermission(){
        if (ContextCompat.checkSelfPermission(FilterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(FilterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new MaterialDialog.Builder(FilterActivity.this).title(R.string.permission_request_title)
                        .content(R.string.permission_request)
                        .positiveText("Yes").negativeText("No")
                        .canceledOnTouchOutside(true)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                startActivityForResult(new Intent(Settings.ACTION_SETTINGS),0);
                            }
                        })
                        .show();
            } else {
                ActivityCompat.requestPermissions(FilterActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
            return;
        }
    }


}
