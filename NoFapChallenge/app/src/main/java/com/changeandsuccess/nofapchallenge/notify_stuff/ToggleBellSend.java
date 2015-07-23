package com.changeandsuccess.nofapchallenge.notify_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.comment_stuff.LoadComments;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by albert on 9/26/14.
 */
public class ToggleBellSend extends AsyncTask<String, Integer, String> {

    Activity activity;
    String userIndex;
    JSONObject jsonObj;
View rootView;
    ImageButton bellButton;

    public ToggleBellSend(Activity activity, String userIndex, View rootView) {
        this.activity = activity;

        this.userIndex = userIndex;
        this.rootView = rootView;
    }

    //interface to get result
    @Override
    protected String doInBackground(String... params) {

        // Log.e("dickhole", ""+messageArray[2]+" :"+context);


        try {

            final String STREAMURL = "http://mobile.tanggoal.com/notify/notify_comment_toggle/" + userIndex;

            jsonObj = JsonReader.readJsonFromUrl(STREAMURL);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("here", "noJSON");
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);
        try {


            //final View rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);

            bellButton = (ImageButton) rootView.findViewById(R.id.notify_button);
            Log.e("button", ""+bellButton.toString());

            if (jsonObj.get("result").equals("no") ) {

                bellButton.setImageResource(R.drawable.no_bell);




            } else { //


                bellButton.setImageResource(R.drawable.bell);



               /* Dialog das = new Dialog(activity);
                das.setTitle("" + jsonObj);
                TextView tvsa = new TextView(activity);
                tvsa.setText("" + jsonObj.toString());
                das.setContentView(tvsa);
                das.show();
*/

            }//end else

        } catch (Exception e) {

            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }

}//end class


