package com.example.english.assignment3;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by english on 20/04/2016.
 */
public class CustomView extends LinearLayout {

    public CustomView(Context c) {
        super(c);
        init();
    }

    public CustomView(Context c, AttributeSet as) {
        super(c, as);
        init();
    }

    public CustomView(Context c, AttributeSet as, int default_style) {
        super(c, as, default_style);
        init();
    }

    private void init() {
        inflate(this.getContext(), R.layout.customviewxml, this);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {
// if we get to this point they we have not handled the touch
// ask the system to handle it instead
        return super.onTouchEvent(event);
    }
}
