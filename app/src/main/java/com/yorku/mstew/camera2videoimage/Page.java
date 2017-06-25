package com.yorku.mstew.camera2videoimage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.FileDescriptor;
import java.io.IOException;

public class Page extends AppCompatActivity {

    ImageView mImageView;
    PinchZoomImageView mPinchZoomImageView;
    private static final int REQUEST_OPEN_RESULT_CODE=0;
    private Uri mImageUri;
    private Animator mCurrentAnimator;
    private int mLongAnimationDuration;
    ImageButton newImageButton;
    Boolean IsPictureViewingBoolean=false;



    ImageButton closepage;

    @Override
    public void onBackPressed() {
        if(IsPictureViewingBoolean){
            Intent newintent=new Intent(getApplicationContext(),Page.class);
            startActivity(newintent);
            IsPictureViewingBoolean=false;
            super.onBackPressed();
        }else{
            Intent newintent2=new Intent(getApplicationContext(), Camera2VideoImageActivity.class);
            startActivity(newintent2);

        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_main);

         //newImageButton=(ImageButton)findViewById(R.id.CloseButton);
        //ewImageButton.setVisibility(View.INVISIBLE);
        /*closepage=(ImageButton)findViewById(R.id.pageclose);
        closepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent closepage=new Intent(getApplicationContext(), Camera2VideoImageActivity.class);
                startActivity(closepage);
            }
        });*/
        mImageView=(ImageView)findViewById(R.id.ImageView);
        mPinchZoomImageView=(PinchZoomImageView) findViewById(R.id.PinchZoomImageView);
        mLongAnimationDuration=getResources().getInteger(android.R.integer.config_longAnimTime);
        mImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //zoomImageFromThumb();
                pinchZoompan();
                return true;
            }
        });

        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_OPEN_RESULT_CODE );



    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView=getWindow().getDecorView();
        if(hasFocus){
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

            );


        }


    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent resultData){


        if(requestCode==REQUEST_OPEN_RESULT_CODE && resultCode == RESULT_OK){

            IsPictureViewingBoolean=true;

            if(resultData != null){
                mImageUri=resultData.getData();

                /*try {
                    Bitmap bitmap = getBitmapFromUri(uri);
                    mImageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                Glide.with(this)
                        .load(mImageUri)
                        .into(mImageView);




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
    private void zoomImageFromThumb(){
        //newImageButton.setVisibility(View.VISIBLE);
        if(mCurrentAnimator != null){
            mCurrentAnimator.cancel();
        }
        Glide.with(this)
                .load(mImageUri)
                .into(mPinchZoomImageView);
        Rect startBounds=new Rect();
        Rect finalBounds=new Rect();
        Point globalOffset=new Point();
        mImageView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container2)
                .getGlobalVisibleRect(finalBounds,globalOffset);
        startBounds.offset(-globalOffset.x,-globalOffset.y);
        startBounds.offset(-globalOffset.x,-globalOffset.y);

        float startScale;
        if((float) finalBounds.width()/finalBounds.height()>(float)startBounds.width()/startBounds.height()){
            startScale=(float)startBounds.height()/finalBounds.height();
            float startWidth=startScale*finalBounds.width();
            float deltaWidth=(startWidth-startBounds.width())/2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;


        }else {
            startScale=(float) startBounds.width()/ finalBounds.width();
            float startHeigth=(float) startScale * finalBounds.height();
            float delatHeight= (startHeigth-startBounds.height())/2;
            startBounds.top -= delatHeight;
            startBounds.bottom += delatHeight;
        }
        mImageView.setAlpha(0f);
        mPinchZoomImageView.setVisibility(View.VISIBLE);
        mPinchZoomImageView.setPivotX(0f);
        mPinchZoomImageView.setPivotY(0f);

        AnimatorSet set=new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(mPinchZoomImageView, View.X,startBounds.left,finalBounds.left))
                .with(ObjectAnimator.ofFloat(mPinchZoomImageView,View.Y,startBounds.top,finalBounds.top))
                .with(ObjectAnimator.ofFloat(mPinchZoomImageView,View.SCALE_X,startScale,1f))
                .with(ObjectAnimator.ofFloat(mPinchZoomImageView,View.SCALE_Y,startScale,1f));

        set.setDuration(mLongAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                mCurrentAnimator=null;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mCurrentAnimator=null;
            }
        });
        set.start();
        mCurrentAnimator=set;



    }
    private void pinchZoompan(){
        IsPictureViewingBoolean=true;
        mPinchZoomImageView.setImageUri(mImageUri);
        mImageView.setAlpha(0.f);
        mPinchZoomImageView.setVisibility(View.VISIBLE);
    }
}
