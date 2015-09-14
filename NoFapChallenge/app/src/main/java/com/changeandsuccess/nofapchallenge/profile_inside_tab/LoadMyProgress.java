package com.changeandsuccess.nofapchallenge.profile_inside_tab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.adapter.ProgressAdapter;
import com.changeandsuccess.nofapchallenge.Item;
import com.changeandsuccess.nofapchallenge.utils.DatabaseStuff;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by albert on 8/8/14.
 */
public class LoadMyProgress extends AsyncTask<String, Integer, String> {

    JSONObject jsonOb;
    String propicname;
    JSONArray jsonArray;
    Context contextman;

    Activity activity;
    View rootView;
    ListView mycourseListView;

    final static String USERCOURSESURL = "http://mobile.tanggoal.com/user/user_courses/";


    public LoadMyProgress(Activity activity, View rootView){

        this.activity = activity;
        this.rootView = rootView;
        //rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);
        mycourseListView = (ListView) rootView.findViewById(R.id.profile_course_list);

    }

    @Override
    protected String doInBackground(String...params) {
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);




        DatabaseStuff info = new DatabaseStuff(activity);
        info.open();
        String[][] data = info.getData();
        info.close();



        // 1. pass context and data to the custom adapter
        ProgressAdapter proAdapter = new ProgressAdapter(activity, generateData(data));

        // 2. Get ListView from activity_main.xml
       // final ListView listView = (ListView) rootView.findViewById(R.id.tvProgList);

        // 3. setListAdapter
        // listView.setAdapter(proAdapter);



        mycourseListView.setAdapter(proAdapter);

//on row click
        mycourseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                int rowID = view.getId();


                setDeleteDialog(rowID);



            }
        });

        //now array
    }



    public static ArrayList<Item> generateData(String[][] data){
        ArrayList<Item> items = new ArrayList<Item>();

        for (int i =0; i<data.length ; i++){

            items.add(new Item(data[i][0], data[i][1], data[i][2], data[i][3]));


        }

        return items;
    }//



    public void setDeleteDialog(int rowID){

        final int rowingID = rowID;


        final View rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);

        // Setting Dialog Title
        alertDialog.setTitle("Confirm Delete...");

        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want delete this?");

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {



                DatabaseStuff databaseStuff = new DatabaseStuff(activity);
                databaseStuff.open();
                databaseStuff.deleteFromID(""+rowingID);
                databaseStuff.close();

                //ListView progList = (ListView)  rootView.findViewById(R.id.profile_course_list);


                refreshData(activity, mycourseListView);

                // Write your code here to invoke YES event
                Toast.makeText(activity, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                Toast.makeText(activity, "not deleted", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
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





}




