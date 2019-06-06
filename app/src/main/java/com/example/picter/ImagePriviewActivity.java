package com.example.picter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImagePriviewActivity extends AppCompatActivity {
    ImageView mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_priview);
        mPreview = findViewById(R.id.CenterImage);


    }
}
