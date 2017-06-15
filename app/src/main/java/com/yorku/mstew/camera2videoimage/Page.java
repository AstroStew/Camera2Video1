package com.yorku.mstew.camera2videoimage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Page extends AppCompatActivity {

    ImageButton closepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_layout);
        closepage=(ImageButton)findViewById(R.id.pageclose);
        closepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent closepage=new Intent(getApplicationContext(), Camera2VideoImageActivity.class);
                startActivity(closepage);
            }
        });




    }
}
