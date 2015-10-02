package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;

/**
 * Created by tanggames on 2015-09-16.
 */
public class coach_info extends LinearLayout {
    ImageView portrait;
    TextView lv,name;

    public coach_info(Context context) {
        super(context);
        init(context);
    }

    public coach_info(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.allstoretabsfrag_____coach_info,this,true);
        lv = (TextView)findViewById(R.id.lvTextView);
        name = (TextView)findViewById(R.id.nameTextView);
        portrait = (ImageView)findViewById(R.id.imageView);
    }

    public void setLv(String _lv){
        lv.setText(_lv);
    }

    public void setName(String _name){
        name.setText(_name);
    }

    public void setPortrait(){


    }
}
