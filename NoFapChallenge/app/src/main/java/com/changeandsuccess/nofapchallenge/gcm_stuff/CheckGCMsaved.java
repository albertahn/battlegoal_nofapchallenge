package com.changeandsuccess.nofapchallenge.gcm_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by albert on 7/27/14.
 *
 * for the servers saved regid
 */
public class CheckGCMsaved extends AsyncTask<String,String,String> {

    String regid;

    JSONObject jsonObj;

    Context context;
    String userindex;

    Activity activity;

    String devicename;
    String userprofileURL;
    String returing;
    JSONArray jarray;

    public CheckGCMsaved(Context context, String regid, Activity activity, String devicename, String userindex){

         this.regid = regid;
        this.context = context;
        this.activity = activity;

        this.devicename =devicename;
        this.userindex = userindex;


       /* Dialog d = new Dialog(activity);
        d.setTitle("mon");
        TextView t = new TextView(activity);
        t.setText(""+regid);
        d.setContentView(t);
        d.show();*/

    }//


    @Override
    protected String doInBackground(String... arg0) {
        try {

            userprofileURL = "http://mobile.tanggoal.com/google_cloud/check_gcm_device/"+userindex+"/"+regid+"/"+devicename+"";

            jsonObj = JsonReader.readJsonFromUrl(userprofileURL);

             returing = jsonObj.toString();

            // jarray = JsonReader.readJsonArrayFromUrl(userprofileURL);



            return returing;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("dicksucking", "noJSON");
        }
        return null;
    }

    @Override
    protected void onPostExecute(String msg) {
//do something later but now just server insert

      /*  Dialog d = new Dialog(activity);
         d.setTitle(""+msg);
        TextView t = new TextView(activity);
        t.setText(""+returing);
        d.setContentView(t);
        d.show();*/


    }


}