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
    float espace;
    Paint white;
    float departx;
    float departy;
    Paint green;

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
        green = new Paint();
        green.setColor(Color.GREEN);
    }

    public void onDraw(Canvas canvas) {
        width = canvas.getWidth();
        espace = width/6f;
        float space = width;
        for(int i = 0; i < 6; i++) {
            canvas.drawLine(0, space, width, space, white);
            space -= espace;
        }
        if(speed.size() < 100) {
            departx = 0;
            departy = width;
        }else{
            departx = 0;
            departy = width - speed.get(0)/10 * espace;
        }
        for(int i = 0; i < speed.size(); i++){
            canvas.drawLine(departx, departy, departx + width/100f, width - speed.get(i)/10 * espace, green);
            departx += width/100f;
            departy = width - speed.get(i)/10 * espace;

        }
        invalidate();
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
