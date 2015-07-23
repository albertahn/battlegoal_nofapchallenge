package com.changeandsuccess.nofapchallenge.gcm_stuff;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.changeandsuccess.nofapchallenge.MainActivity;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by albert on 7/22/14.
 */
public class RegisterBackground  extends AsyncTask<String,String,String> {

    String regid;
    Context context;
    final String sender_id = "104340151333";

    String devicename;
    String userindex;

    //public static final String EXTRA_MESSAGE = "message";
    public String property_reg_id = "registration_id";
    public String property_app_version = "appVersion";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    GoogleCloudMessaging gcm;




    public RegisterBackground(Context context, String  devicename, String userindex){

       // this.regid = regid;

        this.context = context;
        this.devicename= devicename;
        this.userindex= userindex;
        gcm = GoogleCloudMessaging.getInstance(context);

    }


   // GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);

    @Override
    protected String doInBackground(String... arg0) {
        // TODO Auto-generated method stub
        String msg = "";
        try {

            if (gcm == null) {

                gcm = GoogleCloudMessaging.getInstance(context);

                Log.d("cocky", ""+context);
            }


            Log.e("gcmman", ""+gcm.toString());

            regid = gcm.register(sender_id);



            msg = "Device registered, registration ID=" + regid;
            Log.d("111", msg);

            sendRegistrationIdToBackend();

            // Persist the regID - no need to register again.
            storeRegistrationId(context, regid);

        } catch (IOException ex) {
            msg = "Error :" + ex.getMessage();
        }
        return msg;
    }

    @Override
    protected void onPostExecute(String msg) {

        /*Dialog d = new Dialog(context);
        d.setTitle(msg+"id");
        TextView tv = new TextView(context);
        tv.setText(msg+"");
        d.setContentView(tv);
        d.show();*/

    }
    private void sendRegistrationIdToBackend() {

        // Your implementation here.
        try {

            String userprofileURL = "http://mobile.tanggoal.com/google_cloud/check_gcm_device/"+userindex+"/"+regid+"/"+devicename+"";
           JSONObject jsonObj = JsonReader.readJsonFromUrl(userprofileURL);


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("dicksucking", "noJSON");
        }

    } //end sendReg

    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGCMPreferences(context);
       // int appVersion = getAppVersion(context);
       // Log.i("TAG", "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(property_reg_id, regId);
      //  editor.putInt(property_app_version, appVersion);
        editor.commit();
    }//

    private SharedPreferences getGCMPreferences(Context context) {

        return context.getSharedPreferences(MainActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }


}