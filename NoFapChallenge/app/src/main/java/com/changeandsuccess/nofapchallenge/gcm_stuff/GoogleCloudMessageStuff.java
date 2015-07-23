package com.changeandsuccess.nofapchallenge.gcm_stuff;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.changeandsuccess.nofapchallenge.MainActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by albert on 7/22/14.
 */
public class GoogleCloudMessageStuff {


    public String property_reg_id = "registration_id";
    public String property_app_version = "appVersion";
    public int play_service_resolution_request = 9000;
    // please enter your sender id
    String SENDER_ID = "104340151333";



    public boolean checkPlayServices(Activity activity) {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
                        play_service_resolution_request).show();
            } else {
                Log.i("TAG", "This device is not supported.");
                activity.finish();
            }
            return false;
        }
        return true;
    }

    public String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGCMPreferences(context);
        String registrationId = prefs.getString("registration_id", "");
        if (registrationId.isEmpty()) {
            Log.i("TAG", "Registration not found.");
            return "";
        }

//        int registeredVersion = prefs.getInt(property_app_version, Integer.MIN_VALUE);

        return registrationId;
    }

    private SharedPreferences getGCMPreferences(Context context){

        return context.getSharedPreferences(MainActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);

    }



}
