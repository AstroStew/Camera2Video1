package com.yorku.mstew.camera2videoimage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by mstew on 2017-06-22.
 */

public class ImageViewMainActivity extends AppCompatActivity {
    ImageView mImageView;
    private static final int REQUEST_OPEN_RESULT_CODE=0;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_main);

       /* mImageView=(ImageView)findViewById(R.id.ImageView);
        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_OPEN_RESULT_CODE );*/


    }



}
