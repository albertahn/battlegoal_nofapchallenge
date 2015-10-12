package com.changeandsuccess.nofapchallenge.HomeTabStuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.utils.DatabaseStuff;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;
//import com.google.android.gms.tagmanager.Container;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by albert on 8/28/14.
 */
public class PressButtons extends Activity{

    Activity activity;
    //user info

    String userIndex;

    EditText achieveNote;
    ImageButton add;
    ImageButton restart;
    TextView nfDay;
    View rootViewman;
    int counter;
    ListView  progList;
View viewGroup;
   // long getDayDif;

    String finalCountToUpdate;

    Vibrator vibe;
    public PressButtons(Activity activity, View viewGroup){

        this.activity = activity;
        this.counter = counter;
        this.viewGroup = viewGroup;
//vibrator
        vibe=(Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);

        //View mainview = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);

        rootViewman = viewGroup;

        achieveNote = (EditText) rootViewman.findViewById(R.id.achieve_note);
        //menu buttons
         progList = (ListView)  rootViewman.findViewById(R.id.home_tvProgList);


        //click on main page buttons for counter
        add = (ImageButton) rootViewman.findViewById(R.id.bAdd);
        restart = (ImageButton) rootViewman.findViewById(R.id.bRestart);
        // progress = (Button) rootViewman.findViewById(R.id.bViewProgress);

        nfDay = (TextView) rootViewman.findViewById(R.id.tvDay);

        DatabaseStuff lastDayInfo = new DatabaseStuff(activity);
        lastDayInfo.open();
       String lastDay = lastDayInfo.getLastDay();
        lastDayInfo.close();

        nfDay.setText(lastDay);

        if(nfDay.getText().toString()==""){
            counter =0;

        }else {

            counter = Integer.parseInt(nfDay.getText().toString());
        }


        //set user index

        UserDatabase userDatabase = new UserDatabase(activity);
        userDatabase.open();
        String[][] data = userDatabase.getData();

        userDatabase.close();

        userIndex = data[0][1];



    }//interface

//when pressing the add button

    public void add(){

        String noteString =  achieveNote.getText().toString();
        //reset adclicks

        SharedPreferences sp = activity.getSharedPreferences("your_prefs", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int myIntValue = 0;

        editor.putInt("clicked_ad",myIntValue);
        editor.commit();

        boolean didItWork = true;

        try {

            //getDayDif =  new HomeDateCount(activity).getDifferenceDays();
            counter++;

            finalCountToUpdate = Integer.toString(counter);

           /* if(getDayDif>counter){

                finalCountToUpdate = ""+getDayDif;
            }else{
                finalCountToUpdate = Integer.toString(counter);
            }*/

           /* Dialog d = new Dialog(activity);
            TextView t = new TextView(activity);
            t.setText(""+getDayDif);
            d.setContentView(t);
            d.show();*/


            String stringCounter = finalCountToUpdate;//Integer.toString(counter);

            nfDay.setText(stringCounter);
            String day = stringCounter;

            String note = noteString;
            //date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date nowis = new Date();
            String date =  sdf.format(nowis);

            //db stuff
            DatabaseStuff entry = new DatabaseStuff(activity);
            entry.open();
            entry.createEntry(day, note, date);
            entry.close();
            //show the profrag


        } catch (Exception e) {
            e.printStackTrace();

            didItWork = false;
            String error = e.toString();
            Dialog d = new Dialog(activity);
            d.setTitle("Something wrong...");
            TextView tv = new TextView(activity);
            tv.setText( counter+" :"+error+" :"+viewGroup);
            d.setContentView(tv);
            d.show();
        } finally {
            if (didItWork) {

                Toast toast = Toast.makeText(activity,
                        "", Toast.LENGTH_SHORT);

                toast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastView = (LinearLayout) toast.getView();

                ImageView imageCodeProject = new ImageView(activity);
                imageCodeProject.setMaxWidth(100);

                imageCodeProject.setMaxHeight(100);
                imageCodeProject.setImageResource(R.drawable.congrats);//.setImageBitmap(mainbitmap);//.setImageResource(bitmap);

                toastView.setBackgroundColor(Color.parseColor("#91000000"));
                toastView.setMinimumWidth(100);
                toastView.setMinimumHeight(100);
                LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER);

                toastView.addView(imageCodeProject, para);
                // toastView.addView(imageCodeProject);

                toast.show();


                                                     /*  Dialog d = new Dialog(activity);
                                                       //d.setTitle("Yeah you did it!");
                                                       TextView tv = new TextView(activity);
                                                       tv.setText("Congrats on a successful day!");

                                                       d.setContentView(tv);
                                                       d.show();*/
                achieveNote.setText("");

                //mainActivity.onTabChanged("progress");

//now set the remote server
                    new UpBtn_send_server(activity, userIndex,""+finalCountToUpdate ).execute();


            }
        }



    }//end add

    public void restart(){
        String noteString =  achieveNote.getText().toString();

        boolean didItWork = true;
        try {

            counter = 0;
            String stringCounter = Integer.toString(counter);

            nfDay.setText(stringCounter);

            String day = stringCounter;
// TODO : make note from id
            String note = "";

            //date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date nowis = new Date();
            String date =  sdf.format(nowis);
            //db stuff
            DatabaseStuff entry = new DatabaseStuff(activity);
            entry.open();
            entry.createEntry(day, note, date);
            entry.close();

            // viberator
            vibe.vibrate(50);
        } catch (Exception e) {

            didItWork = false;
            String error = e.toString();
            Dialog d = new Dialog(activity);
            d.setTitle("Something wrong with ur phone...");
            TextView tv = new TextView(activity);
            tv.setText(error);
            d.setContentView(tv);
            d.show();
        } finally {
            if (didItWork) {

                                                          /* Dialog d = new Dialog(activity);
                                                           d.setTitle("Forgive yourself.");
                                                           TextView tv = new TextView(activity);
                                                           tv.setText("Keep on climbing. You can do this!");
                                                           d.setContentView(tv);
                                                           d.show();*/

                Toast toast = Toast.makeText(activity,
                        "", Toast.LENGTH_SHORT);

                toast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastView = (LinearLayout) toast.getView();

                ImageView imageCodeProject = new ImageView(activity);
                imageCodeProject.setMaxWidth(340);

                imageCodeProject.setMaxHeight(340);
                imageCodeProject.setImageResource(R.drawable.forgive_yourself);//.setImageBitmap(mainbitmap);//.setImageResource(bitmap);

                toastView.setBackgroundColor(Color.parseColor("#91000000"));
                toastView.setMinimumWidth(1000);
                toastView.setMinimumHeight(1000);
                LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER);

                toastView.addView(imageCodeProject, para);
                // toastView.addView(imageCodeProject);

                toast.show();

                achieveNote.setText("");
            }
        }

    }


}
