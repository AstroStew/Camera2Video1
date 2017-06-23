package com.yorku.mstew.camera2videoimage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Page extends AppCompatActivity {
    ImageView mImageView;
    private static final int REQUEST_OPEN_RESULT_CODE=0;

    ImageButton closepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_main);
        closepage=(ImageButton)findViewById(R.id.pageclose);
        closepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent closepage=new Intent(getApplicationContext(), Camera2VideoImageActivity.class);
                startActivity(closepage);
            }
        });
        mImageView=(ImageView)findViewById(R.id.ImageView);
        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_OPEN_RESULT_CODE );





    }
}
