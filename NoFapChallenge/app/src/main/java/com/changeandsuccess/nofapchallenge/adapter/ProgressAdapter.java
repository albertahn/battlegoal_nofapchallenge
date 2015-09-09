package com.changeandsuccess.nofapchallenge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.Item;
import com.changeandsuccess.nofapchallenge.R;

import java.util.ArrayList;

/**
 * Created by albert on 8/25/14.
 */
public class ProgressAdapter  extends ArrayAdapter<Item> {





    static int rowList = R.layout.progressadapter_____row_in_list;

    private final Context context;
    private final ArrayList<Item> itemsArrayList;

    public ProgressAdapter(Context context, ArrayList<Item> itemsArrayList) {


        super(context, rowList, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(rowList, parent, false);

        // 3. Get the two text view from the rowView
        TextView dayView = (TextView) rowView.findViewById(R.id.day_label);
        TextView dateView = (TextView) rowView.findViewById(R.id.date_value);
        TextView noteView  = (TextView) rowView.findViewById(R.id.note_row);

        // 4. Set the text for textView
        dayView.setText(itemsArrayList.get(position).getDay());
        dateView.setText(itemsArrayList.get(position).getDate());
        noteView.setText(itemsArrayList.get(position).getNote());

        //set the id

        Integer memindex = Integer.parseInt(itemsArrayList.get(position).get_id().toString());

        rowView.setId(memindex);


        // 5. retrn rowView
        return rowView;
    }
}