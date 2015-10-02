package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;

/**
 * Created by tanggames on 2015-09-17.
 */
public class SingleItemView extends LinearLayout{
    TextView title;
    TextView price;

    public SingleItemView(Context context) {
        super(context);
        init(context);
    }

    public SingleItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.allstoretabsfrag_____single_item,this,true);

        title = (TextView)findViewById(R.id.storeItemTitle);
        price = (TextView)findViewById(R.id.storeItemPrice);
    }

    public void setTitle(String _title){
        title.setText(_title);
    }

    public void setPrice(String _price){
        price.setText(_price);
    }
}
