package com.changeandsuccess.nofapchallenge;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.changeandsuccess.nofapchallenge.adapter.ProgressAdapter;
import com.changeandsuccess.nofapchallenge.utils.DatabaseStuff;

import java.util.ArrayList;

/**
 * Created by albert on 5/23/14.
 */
public class HomeListFrag  extends Fragment {

    View rootView;
    ListView progList;
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.homelistfrag_____home_list_frag, container, false);
        progList = (ListView)  rootView.findViewById(R.id.home_tvProgList);
        context = getActivity();


        DatabaseStuff info = new DatabaseStuff(context);
        info.open();
        String[][] data = info.getData();
        info.close();

// 1. pass context and data to the custom adapter
        ProgressAdapter proAdapter = new ProgressAdapter(context, generateData(data));

        progList.setAdapter(proAdapter);



        return rootView;

    }//end bundle


    public static ArrayList<Item> generateData(String[][] data){
        ArrayList<Item> items = new ArrayList<Item>();

        for (int i =0; i<data.length ; i++){

            items.add(new Item(data[i][0], data[i][1], data[i][2], data[i][3]));

        }

        return items;
    }




    public static void refreshData(Context context, ListView listview){


        DatabaseStuff info = new DatabaseStuff(context);
        info.open();
        String[][] data = info.getData();
        info.close();



        // 1. pass context and data to the custom adapter
        ProgressAdapter proAdapter = new ProgressAdapter(context, generateData(data));


        // 3. setListAdapter
        listview.setAdapter(proAdapter);



        //  progList.setAdapter(proAdapter);
    }

}
