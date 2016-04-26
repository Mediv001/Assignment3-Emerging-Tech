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

    ArrayList<Float> speed;
    float width;
    Paint color;
    int index;
    int add;
    Paint white;

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
        white = new Paint();
        white.setColor(Color.WHITE);
        speed = new ArrayList<>();
    }

    public void onDraw(Canvas canvas) {
        width = canvas.getWidth();
        float space = width;
        for(int i = 0; i < 6; i++) {
            canvas.drawLine(0, space, width, space, white);
            space -= width / 6f;
        }
    }

    public void add(Location p){
        speed.add(p.getSpeed()*3.6f);
        if(speed.size() > 100){
            speed.remove(0);
        }
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
