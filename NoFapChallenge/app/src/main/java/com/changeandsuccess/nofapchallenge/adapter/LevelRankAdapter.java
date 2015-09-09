package com.changeandsuccess.nofapchallenge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.RankItem;

import java.util.ArrayList;

/**
 * Created by albert on 10/15/14.
 */
public class LevelRankAdapter extends ArrayAdapter<RankItem> {

    static int rowList = R.layout.levelrankadapter_____rank_list_in_row;

    private final Context context;
    private final ArrayList<RankItem> itemsArrayList;
    String levelstring, numppl;

    public LevelRankAdapter(Context context, ArrayList<RankItem> itemsArrayList) {


//ArrayList<HomeItem> itemsArrayList


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
        TextView text_body_sample = (TextView) rowView.findViewById(R.id.text_body_sample);
        TextView username =(TextView) rowView.findViewById(R.id.username);


        // 4. Set the text for textView


        if(itemsArrayList.get(position).getLevel().equals("")){

            levelstring = "Level 0: Rookie";
            numppl = itemsArrayList.get(position).getCount()+5;
        }else{

            String levelName = getLevelName(itemsArrayList.get(position).getLevel());

            levelstring = "Level "+itemsArrayList.get(position).getLevel()+": "+levelName ;
            numppl = itemsArrayList.get(position).getCount();
        }

        text_body_sample.setText(levelstring);
        username.setText("   "+numppl +" People");


        return rowView;
    }//end get view


    String getLevelName(String level){

        if(level.equals("1")){

            return "< Private > ";//+ context.getResources().getInteger(R.integer.lv1);

        }else if(level.equals("2")){

              return "< Private First Class > ";//+ context.getResources().getInteger(R.integer.lv2);

        }else if(level.equals("3")){
            return "< Specialist >  ";//+ context.getResources().getInteger(R.integer.lv1);

        }else if(level.equals("4")){
            return "< Corporal >";//+ context.getResources().getInteger(R.integer.lv1);

        }else if(level.equals("5")){
            return "< Sergeant >  ";// context.getResources().getInteger(R.integer.lv1);

        }else if(level.equals("6")){
            return "< Staff Sergeant > ";//: day number "+ context.getResources().getInteger(R.integer.lv1);

        }else if(level.equals("7")){
            return "< Sergeant First Class >";// : day number "+ context.getResources().getInteger(R.integer.lv1);

        }else if(level.equals("8")){
            return "< Master Sergeant > ";//: day number "+ context.getResources().getInteger(R.integer.lv1);

        }else if(level.equals("9")){
            return "< First Sergeant >";//: day number "+ context.getResources().getInteger(R.integer.lv1);

        }else if(level.equals("9")){
            return "< Sergeant Major >";// : day number "+ context.getResources().getInteger(R.integer.lv1);

        }else if(level.equals("10")) {
            return "< Command Sergeant Major >";// : day number "+ context.getResources().getInteger(R.integer.lv1);
        }else{
            return  "level 0";
        }


    }//get level end





}//end class
