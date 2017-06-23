package com.yorku.mstew.camera2videoimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.FileDescriptor;
import java.io.IOException;

public class Page extends AppCompatActivity {
    ImageView mImageView;
    private static final int REQUEST_OPEN_RESULT_CODE=0;

    ImageButton closepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_main);
        /*closepage=(ImageButton)findViewById(R.id.pageclose);
        closepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent closepage=new Intent(getApplicationContext(), Camera2VideoImageActivity.class);
                startActivity(closepage);
            }
        });*/
        mImageView=(ImageView)findViewById(R.id.ImageView);
        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_OPEN_RESULT_CODE );



    }
    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent resultData){
        if(requestCode==REQUEST_OPEN_RESULT_CODE && resultCode == RESULT_OK){
            Uri uri=null;
            if(resultData != null){
                uri=resultData.getData();

                try {
                    Bitmap bitmap = getBitmapFromUri(uri);
                    mImageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    private Bitmap getBitmapFromUri(Uri uri)throws IOException{
        ParcelFileDescriptor parcelFileDescriptor=getContentResolver().openFileDescriptor(uri,"r");
        FileDescriptor fileDescriptor=parcelFileDescriptor.getFileDescriptor();
        Bitmap bitmap= BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return bitmap;

    }
}
