package com.yorku.mstew.camera2videoimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Created by mstew on 2017-06-23.
 */

public class PinchZoomImageView extends android.support.v7.widget.AppCompatImageView{
    private Bitmap mBitmap;
    private int mImageWidth;
    private int mImageHeight;
    private final static float mMinZoom=1.0f;
    private final static float mMaxZoom=3.f;
    private float mScaleFactor=1.f;
    private ScaleGestureDetector mScaleGestureDectector;
    private final static int NONE=0;
    private final static int PAN=1;
    private final static int ZOOM=2;
    private int mEventState;
    private float mstartx=0;
    private float mStartY=0;
    private float mTranslateX=0;
    private float mTranslateY=0;
    private float mPreviousTranslateX=0;
    private float mPreviousTranslateY=0;
    ImageButton CloseImageButton;




    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{




        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            //CloseImageButton=(ImageButton)findViewById(R.id.CloseButton);

            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(mMinZoom, Math.min(mMaxZoom,mScaleFactor));
            //invalidate();
            //requestLayout();

            return super.onScale(detector);
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return super.onScaleBegin(detector);

        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            super.onScaleEnd(detector);
            CloseImageButton=(ImageButton)findViewById(R.id.CloseButton);
            CloseImageButton.setVisibility(VISIBLE);
        }
    }




    public PinchZoomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
           mScaleGestureDectector=new ScaleGestureDetector(getContext(),new ScaleListener());


    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()&MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                mEventState=PAN;
                mstartx=event.getX()-mPreviousTranslateX;
                mStartY=event.getY()-mPreviousTranslateY;
                break;
            case MotionEvent.ACTION_UP:
                mEventState=NONE;
                mPreviousTranslateX=event.getX()-mstartx;
                mPreviousTranslateY=event.getY()-mStartY;

                break;
            case MotionEvent.ACTION_MOVE:
                mTranslateX=event.getX()-mstartx;
                mTranslateY=event.getY()-mStartY;

                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mEventState=ZOOM;

                break;
        }
        mScaleGestureDectector.onTouchEvent(event);
        if((mEventState==PAN && mScaleFactor != mMinZoom)||mEventState==ZOOM){
            invalidate();
            requestLayout();
        }

        return true;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int imageWidth=MeasureSpec.getSize(widthMeasureSpec);
        int imageHeight=MeasureSpec.getSize(heightMeasureSpec);
        int scaledWidth=Math.round(mImageWidth*mScaleFactor);
        int scaledHeight=Math.round(mImageHeight*mScaleFactor);


        setMeasuredDimension(
                Math.min(imageWidth,scaledWidth),
                Math.min(imageHeight,scaledHeight)
        );


    }

    @Override protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor,mScaleFactor);
        //canvas.scale(mScaleFactor,mScaleFactor,mScaleGestureDectector.getFocusX(),mScaleGestureDectector.getFocusY());
        if((mTranslateX*-1)<0){
            mTranslateX=0;
        }else if ((mTranslateX*-1)>mImageWidth*mScaleFactor-getWidth()){
            mTranslateX=(mImageWidth*mScaleFactor-getWidth())*-1;

        }if((mTranslateY*-1)<0){
            mTranslateY=0;
        }else if ((mTranslateY*-1)>mImageHeight*mScaleFactor-getHeight()){
            mTranslateY=(mImageHeight*mScaleFactor-getHeight())*-1;

        }
        canvas.translate(mTranslateX/mScaleFactor,mTranslateY/mScaleFactor);
        canvas.drawBitmap(mBitmap,0,0,null);
        canvas.restore();

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w,h,oldw,oldh);
    }
    public void setImageUri(Uri uri){
        try {
            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
            float aspectRatio=(float)bitmap.getHeight()/(float) bitmap.getWidth();
            DisplayMetrics displayMetrics= getResources().getDisplayMetrics();
            mImageWidth=displayMetrics.widthPixels;
            mImageHeight=Math.round(mImageWidth*aspectRatio);
            mBitmap=Bitmap.createScaledBitmap(bitmap,mImageWidth, mImageHeight,false);
            invalidate();
            requestLayout();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
