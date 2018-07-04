package com.picter;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.Toolbar;

import static com.picter.R.id.toolbar;

public class FilterActivity extends Activity {
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        mToolbar= findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setLogo(R.drawable.icon);

    }

}
