package com.example.english.assignment3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by english on 26/04/2016.
 */
public class GraphView extends View {

    ArrayList<Integer> speed;
    int width;
    Paint color;
    int valuespeed;

    public GraphView(Context c) {
        super(c);
        init();
    }

    public GraphView(Context c, AttributeSet as) {
        super(c, as);
        init();
    }

    public GraphView(Context c, AttributeSet as, int default_style) {
        super(c, as, default_style);
        init();
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredWidth > measuredHeight) {
            setMeasuredDimension(measuredHeight, measuredHeight);
        } else {
            setMeasuredDimension(measuredWidth, measuredWidth);

        }
    }

    private void init() {
        width = getMeasuredWidth();
        color = new Paint();
        color.setColor(Color.BLACK);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawLine(0,300,200,100,color);
    }

    public void add(Location p){
        speed.add((int)p.getSpeed()*3,6);
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
