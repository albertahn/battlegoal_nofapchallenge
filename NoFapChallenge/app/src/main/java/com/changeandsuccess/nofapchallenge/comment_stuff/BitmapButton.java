package com.changeandsuccess.nofapchallenge.comment_stuff;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by tanggames on 2015-10-02.
 */
public class BitmapButton extends Button {
    public BitmapButton(Context context) {
        super(context);
        init();
    }

    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setBackgroundColor(Color.argb(00,00,00,00));
     setTextColor(Color.GRAY);
    }

    public void selected(){

        setTextColor(Color.BLACK);
        setBackgroundColor(Color.GRAY);
    }
    public void inSelected(){

        setTextColor(Color.GRAY);
        setBackgroundColor(Color.argb(00,00,00,00));

    }
}
