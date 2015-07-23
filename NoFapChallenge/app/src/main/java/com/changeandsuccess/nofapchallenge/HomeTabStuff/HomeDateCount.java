package com.changeandsuccess.nofapchallenge.HomeTabStuff;

import android.app.Activity;
import android.util.Log;

import com.changeandsuccess.nofapchallenge.utils.DatabaseStuff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by albert on 11/9/14.
 */
public class HomeDateCount {

    DatabaseStuff databaseStuff;
    Activity activity;

    public HomeDateCount(Activity activity){

        this.activity = activity;


    }



    /*public String getDiffTime(){

        String lastRestart = getLastRestartArray();


// Custom date format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date drestart = null;

        Date nowis = new Date();
        try {

            drestart = format.parse(lastRestart);


        } catch (ParseException e) {
            e.printStackTrace();
        }

// Get msec from each, and subtract.
        long diff = nowis.getTime() - drestart.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000)%24;
        long diffDays = diff/(60 * 60 * 1000*24);
        System.out.println("Time in seconds: " + diffSeconds + " seconds.");
        System.out.println("Time in minutes: " + diffMinutes + " minutes.");
        System.out.println("Time in hours: " + diffHours + " hours.");

        return ""+diffHours+":"+diffMinutes+":"+diffSeconds;

    }//end
*/


    /*public long getDifferenceDays(){

        String lastRestart = getLastRestartArray();


// Custom date format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date drestart = null;

        Date nowis = new Date();
        try {

            drestart = format.parse(lastRestart);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.e("drestart", ""+drestart);


        if(drestart!=null){



// Get msec from each, and subtract.
        long diff = nowis.getTime() - drestart.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff/(60 * 60 * 1000*24)  +2;
        System.out.println("Time in seconds: " + diffSeconds + " seconds.");
        System.out.println("Time in minutes: " + diffMinutes + " minutes.");
        System.out.println("Time in hours: " + diffHours + " hours.");
            return diffDays;
        }else{
            return 0;
        }


    }//end*/



    /*public String getLastRestartArray(){

       databaseStuff = new DatabaseStuff(activity);
        databaseStuff.open();
       String reLast = databaseStuff.getLastRestart();
        databaseStuff.close();


        return reLast;



    }*/



}
