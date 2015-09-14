package com.changeandsuccess.nofapchallenge.message_activity;

/**
 * Created by albert on 4/30/14.
 */


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.LoginHelper;
import com.changeandsuccess.nofapchallenge.MainActivity;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.gcm_stuff.CheckGCMsaved;
import com.changeandsuccess.nofapchallenge.gcm_stuff.GoogleCloudMessageStuff;
import com.changeandsuccess.nofapchallenge.gcm_stuff.RegisterBackground;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class Message extends Fragment {

    ArrayList<LoginItem> generatedLoginItem;
    ActionBar bar;
    final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.message_____message_frag, container, false);

        //get userid
        LoginHelper loginHelper = new LoginHelper();
        String[][] loginData = loginHelper.checkLogin(getActivity());

         generatedLoginItem = generateData(loginData);

        //ads

        /*getFragmentManager().beginTransaction()
                .add(R.id.ad_container, new LoadMessageListAd())
                .commit();
*/
        if(generatedLoginItem.toString() !="[]") {

            LoadMessageFrag loadMessageFrag = new LoadMessageFrag(loginData[0][1].toString(), rootView, getActivity());
            loadMessageFrag.execute(loginData[0][1].toString());

            //check gcm
            checkDeviceGCM(getActivity(), getActivity());
//adshow
            AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);


            return rootView;

        }else{

           /* Dialog d = new Dialog(getActivity());
            d.setTitle("Please login to view ");
            TextView tv = new TextView(getActivity());
            tv.setText("");
            d.setContentView(tv);
            d.show();
*/
            View unrootView = inflater.inflate(R.layout.message_____click_to_login, container, false);


            Intent i = new Intent(getActivity(),
                    LoginActivity.class);
            startActivity(i);


            Button loginTanggoalBTN = (Button)  unrootView.findViewById(R.id.to_logon_page_button);

            loginTanggoalBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(getActivity(),
                            LoginActivity.class);
                    startActivity(i);

                }

            });

            // Close Registration View

            //add the advertisement

           // if (savedInstanceState == null) {


               /* getFragmentManager().beginTransaction()
                        .add(R.id.ad_container, new LoadMessageListAd())
                        .commit();

*/
           // }


            return unrootView;



        }//end else not logged in


    }


    public static ArrayList<LoginItem> generateData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){

            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));

        }
        return items;
    } //end generate


    //gcm




    //check GCM
    private void checkDeviceGCM(Context context, Activity activity){

        UserDatabase info = new UserDatabase(context);
        info.open();
        String[][] data = info.getData();
        info.close();

        String userindex = data[0][1];

        String manufacturer = Build.MANUFACTURER;
        String devmodel = Build.MODEL;
        String devicename = manufacturer+devmodel;


        Log.e("shla", "" + this + devicename + userindex);


        GoogleCloudMessageStuff googleCloudMessageStuff = new GoogleCloudMessageStuff();

        Boolean checkPlay= googleCloudMessageStuff.checkPlayServices(getActivity());

        if(checkPlay){
            //GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
            //String  regid = googleCloudMessageStuff.getRegistrationId(this);


            SharedPreferences prefs = getGCMPreferences(context);

            String regid = prefs.getString("registration_id", "");
            //prefs.registerOnSharedPreferenceChangeListener();





           // if(regid.isEmpty()){

                RegisterBackground registerBackground = new RegisterBackground(getActivity(), devicename, userindex);
                registerBackground.execute();

                /*Dialog d =new Dialog(context);
                d.setTitle("regid is empty .creating new id");*/
               // d.show();
          //  }else {//s

              /*  Dialog d =new Dialog(context);
                d.setTitle("regid " + regid);
                TextView t = new TextView(context);
                t.setText(""+regid);
                d.setContentView(t);*/
                //d.show();

                Log.e("regid",""+regid);

           // }//end else

            CheckGCMsaved checkGCMsaved = new CheckGCMsaved(context, regid, activity, devicename, userindex);
            checkGCMsaved.execute();

        }//end if check play
    }


    private SharedPreferences getGCMPreferences(Context context) {

        return context.getSharedPreferences(MainActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }



}//end frag