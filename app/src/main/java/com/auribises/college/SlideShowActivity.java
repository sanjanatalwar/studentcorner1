package com.auribises.college;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class SlideShowActivity extends AppCompatActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);
        imageView=(ImageView)findViewById(R.id.imageView);

        AnimationDrawable animationDrawable= (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();


    }
}
