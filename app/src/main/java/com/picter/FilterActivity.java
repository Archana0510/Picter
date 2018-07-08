package com.picter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static com.picter.R.id.toolbar;

public class FilterActivity extends Activity {
    Toolbar mToolbar;
    ImageView mTickImage;
    ImageView mCenterImage;
    final static int PICK_IMAGE =2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        mToolbar= findViewById(R.id.toolbar);
        mCenterImage=(ImageView)findViewById(R.id.CenterImage);

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
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"), PICK_IMAGE);

                }
        }
        );
    }
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode==PICK_IMAGE&&resultCode==Activity.RESULT_OK){
            Uri selectedImageUri= data.getData();

            Picasso.get().load(selectedImageUri).fit().centerInside().into(mCenterImage);

        }

    }


}
