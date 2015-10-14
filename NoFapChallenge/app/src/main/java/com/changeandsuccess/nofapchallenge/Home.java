package com.changeandsuccess.nofapchallenge;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.HomeTabStuff.PressButtons;
import com.changeandsuccess.nofapchallenge.battle_stuff.AllBattleTab;
import com.changeandsuccess.nofapchallenge.coaches_tab_stuff.AllCoachTabs;
import com.changeandsuccess.nofapchallenge.comment_stuff.CommentsFrag;
import com.changeandsuccess.nofapchallenge.fragments.ComingSoon;
import com.changeandsuccess.nofapchallenge.level_stuff.LevelFrag;
import com.changeandsuccess.nofapchallenge.level_stuff.MyLevelStuff;
import com.changeandsuccess.nofapchallenge.message_activity.Message;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.profile_inside_tab.ProfileTab;
import com.changeandsuccess.nofapchallenge.profile_inside_tab.SettingsFrag;
import com.changeandsuccess.nofapchallenge.store_puchase_stuff.AllStoreTabsFrag;
import com.changeandsuccess.nofapchallenge.utils.DatabaseStuff;
import com.changeandsuccess.nofapchallenge.utils.ProgressWheel;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by albert on 4/27/14.
 */
public class Home extends Fragment {

    View rootViewman;
    Activity activity;
    //counter stuff
    int counter;
    ImageButton add;
    ImageButton  restart;
    ImageButton shareBTN;


    ImageButton userSettingBtn;

    TextView nfDay, hourText;

    EditText achieveNote;

    //wheel
    int progress = 0;
    ProgressWheel pw_two;

    ActionBar actionBar;
    ArrayList<LoginItem> generatedLoginItem;
    Vibrator vibe;
    String lastDay;
    int mySuccess;
    int plussone;
    Context context = getActivity();

    View mainActLayout;
    boolean running;
    MainActivity mainActivity;

    String userLevel;

    int levelRuler;
    boolean islogg;


    //FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        rootViewman = inflater.inflate(R.layout.home_____home_frag, container, false);


        LoginActivity loginActivity = new LoginActivity();
        islogg= loginActivity.isLoggedIn(getActivity());

//progress_peak
        activity= getActivity();
        mainActLayout = inflater.inflate( R.layout.mainactivity_____activity_main, container, false);
        context = getActivity();
        mainActivity = new MainActivity();

//progress wheel

        pw_two = (ProgressWheel) rootViewman.findViewById(R.id.progressBarTwo);

        achieveNote = (EditText) rootViewman.findViewById(R.id.achieve_note);
        //menu buttons
        //click on main page buttons for counter
        add = (ImageButton) rootViewman.findViewById(R.id.bAdd);

        restart = (ImageButton) rootViewman.findViewById(R.id.bRestart);


        shareBTN = (ImageButton) rootViewman.findViewById(R.id.setting_btn);

        nfDay = (TextView) rootViewman.findViewById(R.id.tvDay);

        hourText = (TextView) rootViewman.findViewById(R.id.hours_text);


        userSettingBtn = (ImageButton) rootViewman.findViewById(R.id.userSetting);

        // String hoursPast = new HomeDateCount(activity).getDiffTime();
        //hourText.setText(""+hoursPast);
        //get the last day from database

        DatabaseStuff lastDayInfo = new DatabaseStuff(context);
        lastDayInfo.open();
        lastDay=lastDayInfo.getLastDay();
        lastDayInfo.close();

        try{

            counter = Integer.parseInt(nfDay.getText().toString());

        }catch(NumberFormatException nfe){
            System.out.println("could not parse" + nfe);
        }



        //vibrator
        vibe=(Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if(!lastDay.equals("")) {

            mySuccess = Integer.parseInt(lastDay);
            plussone = mySuccess+1;

            if(mySuccess<10){
                nfDay.setText("0"+lastDay);

            }else{
                nfDay.setText(lastDay);
            }



        }else{

            mySuccess =0;
            plussone = 1;

            nfDay.setText("00");
        }
//add button click
        add.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    add.setBackgroundResource(R.drawable.btn_done_p);

                } else if (action == MotionEvent.ACTION_UP) {
                    add.setBackgroundResource(R.drawable.btn_done_n);

                    if (islogg) {
                        if (equalLastDay() == false) {
                            new PressButtons(activity, rootViewman).add();
                            new MyLevelStuff(activity).checkLevelUpdate(mySuccess, userLevel);
                            wheelRun(plussone);
                        } else {
                        }//emd
                    } else {//logged
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        startActivity(i);
                    }//else/
                }

                return true;
            }
        });

        restart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    restart.setBackgroundResource(R.drawable.btn_reload_p);

                } else if (action == MotionEvent.ACTION_UP) {
                    restart.setBackgroundResource(R.drawable.btn_reload_n);

                    if (islogg) {

                        //new PressButtons(activity, rootViewman).restart();
                        setRestartDialog();
                    } else {
                        Intent i = new Intent(getActivity(),
                                LoginActivity.class);
                        startActivity(i);
                    }
                }

                return true;
            }
        });

        //info btn


        shareBTN.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    shareBTN.setBackgroundResource(R.drawable.btn_share_p);

                } else if (action == MotionEvent.ACTION_UP) {
                    shareBTN.setBackgroundResource(R.drawable.btn_share_n);

                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "Try out this app to become better and help people! http://bit.ly/1lt0Xiv ";
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "http://bit.ly/1lt0Xiv");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                }

                return true;
            }
        });

       /* userSettingBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
*/

        //hide keyboard
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //check user login and level
        UserDatabase info = new UserDatabase(context);
        info.open();
        String[][] data = info.getData();
        info.close();

        generatedLoginItem = generateData(data);

        TextView my_level = (TextView) rootViewman.findViewById(R.id.my_level);

        if( islogg){

            userLevel = data[0][7];

            Log.e("userLevel", ""+userLevel);

            if(userLevel ==null) {

                my_level.setText("LV.00");

            }else{

                try{

                    int levelInt = Integer.parseInt(userLevel);

                    if(levelInt<10){

                        my_level.setText("LV.0"+userLevel);

                    }else{
                        my_level.setText("LV."+userLevel);

                    }


                }catch (Exception e){

                    Log.e("levelIntfuck",userLevel.toString()+""+e);


                }


            }//end else




            levelRuler =myLevelRuler(userLevel);

//run the wheel
            wheelRun(mySuccess);

        }else{

            userLevel= "Log In";

            my_level.setText(userLevel);
            //my_level.setBackgroundResource(R.drawable.add_btn);

            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) my_level
                    .getLayoutParams();

            // mlp.setMargins(0, 500, 0, 0);

            String strColor = "#aacccc99";

            my_level.setTextColor(Color.parseColor(strColor));  //cccc99

            //LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //llp.setMargins(50, 0, 0, 0); // llp.setMargins(left, top, right, bottom);
            //my_level.setLayoutParams(llp);


            my_level.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(getActivity(),
                            LoginActivity.class);
                    startActivity(i);

                }
            });


        }//else



        return rootViewman;
    }//end oncreate



    public void setRestartDialog(){



        final View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle("Confirm Restart...");

        // Setting Dialog Message
        alertDialog.setMessage("Did you slip?");

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                new PressButtons(activity, rootViewman).restart();

            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                Toast.makeText(context, "not restarted", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }


    //run the wheel
    public void wheelRun(int intSuccess){

       /* Toast toast = Toast.makeText(activity,
                ""+intSuccess, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(activity);
        imageCodeProject.setImageResource(R.drawable.ic_launcher);
        toastView.addView(imageCodeProject, 0);
        toast.show();
*/

        final int iuccess = intSuccess;

        levelRuler=myLevelRuler(userLevel);
        levelRuler++;

        //  Log.e("levelRuler", ""+levelRuler);

        final Runnable r = new Runnable() {
            public void run() {
                running = true;
                while(progress< iuccess*361/levelRuler) {
                    pw_two.incrementProgress();
                    progress++;

                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                }
                running = false;
            }
        };

        if(!running) {
            progress = 0;
            pw_two.resetCount();
            Thread s = new Thread(r);
            s.start();
        }

    }//end wheel run



    public boolean equalLastDay(){

        DatabaseStuff lastDayInfo = new DatabaseStuff(context);
        lastDayInfo.open();
        String finalDate =  lastDayInfo.getFinalDate();
        lastDayInfo.close();


        if(finalDate==""){
            return false;
        }



        String dateString = finalDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date convertedDate = new Date();
        try {

            convertedDate = dateFormat.parse(dateString);
            Calendar lastcal = Calendar.getInstance();
            lastcal.setTime(convertedDate);




            int lastday = lastcal.get(lastcal.DAY_OF_YEAR);


            Calendar currentCal = Calendar.getInstance();
            Date date = new Date();

            currentCal.setTime(date);

            if(lastday==currentCal.get(currentCal.DAY_OF_YEAR)){

               /* Dialog da = new Dialog(getActivity());

                da.setTitle("Clicked Today");

                TextView ta = new TextView(getActivity());
                ta.setText("come back tomorrow!");
                da.setContentView(ta);
                da.show();*/

                Toast toast = Toast.makeText(activity,
                        "", Toast.LENGTH_SHORT);

                toast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastView = (LinearLayout) toast.getView();

                ImageView imageCodeProject = new ImageView(activity);
                imageCodeProject.setMaxWidth(340);
                imageCodeProject.setMaxHeight(340);
                imageCodeProject.setMinimumHeight(100);
                imageCodeProject.setMinimumWidth(100);



                imageCodeProject.setImageResource(R.drawable.clicked_today);//.setImageBitmap(mainbitmap);//.setImageResource(bitmap);

                toastView.setBackgroundColor(Color.parseColor("#91000000"));
                toastView.setMinimumWidth(100);
                toastView.setMinimumHeight(100);
                LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER);

                toastView.addView(imageCodeProject, para);
                // toastView.addView(imageCodeProject);

                toast.show();


                Fragment fragment = new Home();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment).commit();

                return  true;

                //return false;

            }else {

                return  false;
            } //end else

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// end catch

        return  true;
    } //end equals last day





    public static ArrayList<LoginItem> generateData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){

            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));

        }
        return items;
    } //end generate

    public int myLevelRuler(String userLevel) {
        int levelInt;

        if(!userLevel.equals("")) {

            levelInt = Integer.parseInt(userLevel);

        }else{

            levelInt =0;

        }

        int returingRuler;

        switch (levelInt) {

            case 0:

                returingRuler=context.getResources().getInteger(R.integer.lv1);
                break;
            case 1:

                returingRuler=context.getResources().getInteger(R.integer.lv2);
                break;
            case 2:
                returingRuler= context.getResources().getInteger(R.integer.lv3);
                break;
            case 3:
                returingRuler= context.getResources().getInteger(R.integer.lv4);
                break;

            case 4:
                returingRuler= context.getResources().getInteger(R.integer.lv5);
                break;

            case 5:
                returingRuler= context.getResources().getInteger(R.integer.lv6);
                break;

            case 6:
                returingRuler= context.getResources().getInteger(R.integer.lv7);
                break;

            case 7:
                returingRuler= context.getResources().getInteger(R.integer.lv8);
                break;

            case 8:
                returingRuler= context.getResources().getInteger(R.integer.lv9);
                break;

            case 9:
                returingRuler= context.getResources().getInteger(R.integer.lv10);
                break;

            case 10:
                returingRuler= context.getResources().getInteger(R.integer.lv10)+1;
                break;



            default:
                returingRuler= 0;
                break;

        }

        return returingRuler;
    }
}//end
