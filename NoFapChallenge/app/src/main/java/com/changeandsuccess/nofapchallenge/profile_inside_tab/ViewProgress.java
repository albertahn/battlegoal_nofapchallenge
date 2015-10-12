package com.changeandsuccess.nofapchallenge.profile_inside_tab;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.Item;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.adapter.ProgressAdapter;
import com.changeandsuccess.nofapchallenge.utils.DatabaseStuff;

import java.util.ArrayList;

public class ViewProgress extends Fragment{

    View rootView;
    ListView progList;
    ImageView btnBack;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       final View rootView  = inflater.inflate(R.layout.viewprogress_____progressview, container, false);
        progList = (ListView)  rootView.findViewById(R.id.tvProgList);
         context = getActivity();



        DatabaseStuff info = new DatabaseStuff(context);
        info.open();
        String[][] data = info.getData();
        info.close();



        // 1. pass context and data to the custom adapter
        ProgressAdapter proAdapter = new ProgressAdapter(context, generateData(data));

        // 2. Get ListView from activity_main.xml
        final ListView listView = (ListView) rootView.findViewById(R.id.tvProgList);



        progList.setAdapter(proAdapter);

//on row click
        progList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int rowID = view.getId();
                setDeleteDialog(rowID);



            }
        });



       /* btnBack = (ImageView) rootView.findViewById(R.id.BtnBack);

   //bDeleteAllData



        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                refreshData(context, progList );


            }});*/




        refreshData(context, progList );
        return rootView;

    }//end bundle


    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.icon) { //app icon in action bar clicked; go back
            //do something

            Dialog d = new Dialog(context);
            d.setTitle("Keep Climbing!");
            TextView tv = new TextView(context);
            tv.setText("You can do this!");
            d.setContentView(tv);
            d.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static ArrayList<Item> generateData(String[][] data){
        ArrayList<Item> items = new ArrayList<Item>();

        for (int i =0; i<data.length ; i++){

            items.add(new Item(data[i][0], data[i][1], data[i][2], data[i][3]));


        }

        return items;
    }


    public void refreshData(Context context, ListView listview){


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


    public void setDeleteDialog(int rowID){

       final int rowingID = rowID;


        final View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle("Confirm Delete...");

        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want delete this?");

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {



                DatabaseStuff databaseStuff = new DatabaseStuff(getActivity());
                databaseStuff.open();
                databaseStuff.deleteFromID(""+rowingID);
                databaseStuff.close();

                ListView progList = (ListView)  rootView.findViewById(R.id.tvProgList);
                ViewProgress viewProgress = new ViewProgress();

                context = getActivity();


                viewProgress.refreshData(context, progList);

                // Write your code here to invoke YES event
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                Toast.makeText(context, "not deleted", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

}


