package com.example.asus.lism1.utils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.asus.lism1.R;

/**
 * Created by asus on 2018/10/20.
 */


public class CustomAudioIcon extends View implements View.OnTouchListener {
    private static final int defaultType = -1;
    private static final int start = 0;
    private int type;
    private int color;
    private Paint upPaint;
    private Paint pressPaint;
    private Paint boxPaint;
    private Paint paint;
    private int width,height;
    private boolean pressed = false;



    //Only for StartStopButton

    private boolean flagStart = true;




    public CustomAudioIcon(Context context, AttributeSet attrs) {

        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,

                R.styleable.CustomAudioIcon);

        type = typedArray.getInt(R.styleable.CustomAudioIcon_type, defaultType);

        color = typedArray.getColor(R.styleable.CustomAudioIcon_color,
                Color.BLACK);
        typedArray.recycle();
        init();

        setClickable(true);//In order to make this view can accept the OnClickListener

        setOnTouchListener(this);

    }

    private void init() {

        boxPaint = new Paint();

        boxPaint.setColor(color);

        boxPaint.setAntiAlias(true);

        boxPaint.setStrokeWidth(1);



        upPaint = new Paint();

        upPaint.setColor(Color.BLACK);

        upPaint.setAntiAlias(true);

        upPaint.setStrokeWidth(1);



        pressPaint = new Paint();

        pressPaint.setColor(Color.GRAY);

        pressPaint.setAntiAlias(true);

        pressPaint.setStrokeWidth(1);

    }



    public void onDraw(Canvas canvas) {

        paint = pressed ? pressPaint : upPaint;

        width = getMeasuredWidth();

        height = getMeasuredHeight();



        if(pressed){

            canvas.drawColor(Color.parseColor("#447744"));

        }

        switch (type) {

            case start:

                if(flagStart){

                    drawStart(canvas, pressed);

                }else{

                    drawStop(canvas, pressed);

                }

                break;


        }



        boxPaint.setStyle(Paint.Style.STROKE);

        Rect rect = canvas.getClipBounds();

        rect.bottom--;

        rect.right--;

        canvas.drawRect(rect, boxPaint);

    }

    private void drawStart(Canvas canvas, boolean pressed) {

        float scaleWidth = width < height ? width : height;

        // calculate the vertexes.

        float[] verticles = { (float) (0.21 * scaleWidth),

                (float) (0.1 * scaleWidth), (float) (0.21 * scaleWidth),

                (float) (0.9 * scaleWidth), (float) (0.9 * scaleWidth),

                (float) (0.5 * scaleWidth) };

        canvas.drawLine(verticles[0], verticles[1], verticles[2], verticles[3],paint);

        canvas.drawLine(verticles[0], verticles[1], verticles[4], verticles[5],paint);

        canvas.drawLine(verticles[2], verticles[3], verticles[4], verticles[5],paint);

    }



    private void drawStop(Canvas canvas, boolean pressed) {

        float scaleWidth = width < height ? width : height;

        // calculate the vertexes.

        float[] verticles = { (float) (0.4 * scaleWidth), (float) (0.1 * scaleWidth),

                (float) (0.4 * scaleWidth), (float) (0.9 * scaleWidth),

                (float) (0.6 * scaleWidth), (float) (0.1 * scaleWidth),

                (float) (0.6 * scaleWidth), (float) (0.9 * scaleWidth)};

        canvas.drawLine(verticles[0], verticles[1], verticles[2], verticles[3],paint);

        canvas.drawLine(verticles[4], verticles[5], verticles[6], verticles[7],paint);

    }

    @Override

    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                pressed = true;

                invalidate();

                break;

            case MotionEvent.ACTION_UP:

                pressed = false;

                invalidate();

                if(type == start){

                    flagStart = !flagStart;

                }

                break;

        }

        return false;

    }

    public boolean isStartStatus() {

        return flagStart;

    }



    /**

     * Change the flag outside

     * @param flagStart

     */

    public void setFlagStart(boolean flagStart) {

        this.flagStart = flagStart;

        invalidate();

    }
}
