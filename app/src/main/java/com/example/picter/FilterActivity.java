package com.example.picter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.picter.Utility.Helper;
import com.example.picter.Utility.TransformImage;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class FilterActivity extends AppCompatActivity {
    static {
        System.loadLibrary("NativeImageProcessor");
    }

    final static int PICK_IMAGE = 2;
    final static int MY_PERMISSION_REQUEST=3;
    private static final String TAG=FilterActivity.class.getSimpleName();

    Uri mselectedImageUri;
    int mFilterValue;


    ImageView mCenterImage;

    SeekBar mSeekbar;
    ImageView mCancelImage;
    ImageView mTickImage;

    ImageView m1stFilterImage;
    ImageView m2ndFilterImage;
    ImageView m3rdFilterImage;
    ImageView m4thFilterImage;

    TransformImage mtransformImage;

    int mScreenWidth;
    int mScreenHeight;

    //////////////////////////////////////////////////////////////////////////

    Target mSeekedFilter = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar mSeekbar, int progress, boolean fromUser) {
//                    int CurrentFilterValue = mSeekbar.getProgress();
                    if (mFilterValue == TransformImage.FILTER_BRIGHTNESS) {
                        mtransformImage.addBrightnessSubFilter(progress);
                        Helper.writeToExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS), mtransformImage.getBitmap(TransformImage.FILTER_BRIGHTNESS));
                        Picasso.with(FilterActivity.this).invalidate(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS)));
                        Picasso.with(FilterActivity.this).load(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS))).resize(0, (mScreenHeight / 2)).into(mCenterImage);


                    } else if (mFilterValue == TransformImage.FILTER_CONTRAST) {
                        mtransformImage.addContrastSubFilter(progress/6);
                        Helper.writeToExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_CONTRAST), mtransformImage.getBitmap(TransformImage.FILTER_CONTRAST));
                        Picasso.with(FilterActivity.this).invalidate(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_CONTRAST)));
                        Picasso.with(FilterActivity.this).load(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_CONTRAST))).resize(0, (mScreenHeight / 2)).into(mCenterImage);


                    } else if (mFilterValue == TransformImage.FILTER_SATURATION) {
                        mtransformImage.addSaturationSubFilter(progress/2);
                        Helper.writeToExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_SATURATION), mtransformImage.getBitmap(TransformImage.FILTER_SATURATION));
                        Picasso.with(FilterActivity.this).invalidate(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_SATURATION)));
                        Picasso.with(FilterActivity.this).load(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_SATURATION))).resize(0, (mScreenHeight / 2)).into(mCenterImage);


                    } else if (mFilterValue == TransformImage.FILTER_VIGNETTE) {
                        mtransformImage.addVignetteSubFilter(progress);
                        Helper.writeToExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_VIGNETTE), mtransformImage.getBitmap(TransformImage.FILTER_VIGNETTE));
                        Picasso.with(FilterActivity.this).invalidate(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_VIGNETTE)));
                        Picasso.with(FilterActivity.this).load(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_VIGNETTE))).resize(0, (mScreenHeight / 2)).into(mCenterImage);


                    }


                }

                @Override
                public void onStartTrackingTouch(SeekBar mSeekbar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar mSeekbar) {

                }
            });




        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

////////////////////////////////////////////////////////////////////////////

    Target mSmallTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mtransformImage = new TransformImage(FilterActivity.this, bitmap);
//brightness
            mtransformImage.addBrightnessSubFilter(TransformImage.DEFAULT_BRIGHTNESS);
            Helper.writeToExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS), mtransformImage.getBitmap(TransformImage.FILTER_BRIGHTNESS));
            Picasso.with(FilterActivity.this).load(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS))).fit().centerInside().into(m1stFilterImage);
//contrast
            mtransformImage.addContrastSubFilter(TransformImage.DEFAULT_CONTRAST);
            Helper.writeToExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_CONTRAST), mtransformImage.getBitmap(TransformImage.FILTER_CONTRAST));
            Picasso.with(FilterActivity.this).load(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_CONTRAST))).fit().centerInside().into(m2ndFilterImage);
//saturation
            mtransformImage.addSaturationSubFilter(TransformImage.DEFAULT_SATURATION);
            Helper.writeToExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_SATURATION), mtransformImage.getBitmap(TransformImage.FILTER_SATURATION));
            Picasso.with(FilterActivity.this).load(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_SATURATION))).fit().centerInside().into(m3rdFilterImage);
//vignette
            mtransformImage.addVignetteSubFilter(TransformImage.DEFAULT_VIGNETTE);
            Helper.writeToExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_VIGNETTE), mtransformImage.getBitmap(TransformImage.FILTER_VIGNETTE));
            Picasso.with(FilterActivity.this).load(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_VIGNETTE))).fit().centerInside().into(m4thFilterImage);

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    //////////////////////////////////////////////////////////////

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        mSeekbar = findViewById(R.id.seekBar);
        mCenterImage = findViewById(R.id.CenterImage);

        m1stFilterImage = findViewById(R.id.imageView4);
        m2ndFilterImage = findViewById(R.id.imageView5);
        m3rdFilterImage = findViewById(R.id.imageView6);
        m4thFilterImage = findViewById(R.id.imageView7);

        mTickImage = findViewById(R.id.imageView2);
        mTickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(FilterActivity.this).load(mselectedImageUri).into(mSeekedFilter);
//                Intent intent = new Intent(FilterActivity.this, ImagePriviewActivity.class);
//                startActivity(intent);
            }
        });
//
//        loadImagesLayout();


        /////////////////////////////

        mCenterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestStoragePermission();

                if(ContextCompat.checkSelfPermission(FilterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
            }
        });


        ////////////////////////////////////////////////////////


        m1stFilterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSeekbar.setMax(TransformImage.MAX_BRIGHTNESS);
                mSeekbar.setProgress(TransformImage.DEFAULT_BRIGHTNESS);
                mFilterValue = TransformImage.FILTER_BRIGHTNESS;
                Picasso.with(FilterActivity.this).load(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS))).resize(0, (mScreenHeight / 2)).into(mCenterImage);

            }
        });
        m2ndFilterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSeekbar.setMax(TransformImage.MAX_CONTRAST);
                mSeekbar.setProgress(TransformImage.DEFAULT_CONTRAST);
                mFilterValue = TransformImage.FILTER_CONTRAST;
                Picasso.with(FilterActivity.this).load(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_CONTRAST))).resize(0, (mScreenHeight / 2)).into(mCenterImage);

            }
        });
        m3rdFilterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSeekbar.setMax(TransformImage.MAX_SATURATION);
                mSeekbar.setProgress(TransformImage.DEFAULT_SATURATION);
                mFilterValue = TransformImage.FILTER_SATURATION;
                Picasso.with(FilterActivity.this).load(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_SATURATION))).resize(0, (mScreenHeight / 2)).into(mCenterImage);


            }
        });
        m4thFilterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSeekbar.setMax(TransformImage.MAX_VIGNETTE);
                mSeekbar.setProgress(TransformImage.DEFAULT_VIGNETTE);
                mFilterValue = TransformImage.FILTER_VIGNETTE;
                Picasso.with(FilterActivity.this).load(Helper.getFileFromExternalStorage(FilterActivity.this, mtransformImage.getFilename(TransformImage.FILTER_VIGNETTE))).resize(0, (mScreenHeight / 2)).into(mCenterImage);


            }
        });

        mTickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(FilterActivity.this).load(mselectedImageUri).into(mSeekedFilter);
            }
        });


        mCancelImage = findViewById(R.id.imageView3);
        mCancelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilterActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        mScreenHeight= dm.heightPixels;
        mScreenWidth=dm.widthPixels;


        //////////////////////////////////////////////////////////////

    }

//    private void loadImagesLayout() {
//
//        Intent intent = getIntent();
//        String image_path= intent.getStringExtra("imagePath");
//        Uri fileUri = Uri.parse(image_path);
//        Picasso.with(FilterActivity.this).load(fileUri).fit().centerInside().into(mCenterImage);
//        Picasso.with(FilterActivity.this).load(fileUri).into(mSmallTarget);
//
//
//    }
//
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
//
//
//            mselectedImageUri = data.getData();
//
//
//            Picasso.with(FilterActivity.this).load(mselectedImageUri).fit().centerInside().into(mCenterImage);
//            Picasso.with(FilterActivity.this).load(mselectedImageUri).into(mSmallTarget);
//
//        }
//    }
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
            mselectedImageUri= data.getData();

            Picasso.with(FilterActivity.this).load(mselectedImageUri).fit().centerInside().into(mCenterImage);
            Picasso.with(FilterActivity.this).load(mselectedImageUri).into(mSmallTarget);

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
