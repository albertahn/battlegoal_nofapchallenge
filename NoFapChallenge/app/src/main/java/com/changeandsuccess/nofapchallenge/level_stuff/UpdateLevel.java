package com.changeandsuccess.nofapchallenge.level_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.MainActivity;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.message_activity.InsideMessageLoad;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by albert on 10/8/14.
 */
public class UpdateLevel extends AsyncTask<String, Integer, String> {

    Activity activity;
    String userIndex;

    String level;

    HttpResponse httpResponse;

    public UpdateLevel(Activity activity, String userIndex, String level){
        this.activity = activity;
        this.userIndex = userIndex;
        this.level =level;


    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {

        // Log.e("dickhole", ""+messageArray[2]+" :"+context);


        try {

            //return response;

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addTextBody("user_index", userIndex, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("new_level", level, ContentType.create("text/plain", MIME.UTF8_CHARSET));

            HttpClient httpClient = new DefaultHttpClient();

            httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                    System.getProperty("http.agent"));
            HttpPost httpPost = new HttpPost("http://mobile.tanggoal.com/level/update_level/");
            httpPost.setEntity(builder.build());


            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String is;

            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
                String body = "";
                String content = "";

                while ((body = rd.readLine()) != null) {
                    content += body + "\n";
                }

                return content;
            } catch (IOException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


        View rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);

        TextView mylevel = (TextView) rootView.findViewById(R.id.my_level);

        mylevel.setText("lv "+level);

        Toast toast = Toast.makeText(activity,
                "LEVEL UP! "+level, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(activity);
        imageCodeProject.setImageResource(R.drawable.ic_launcher);
        toastView.addView(imageCodeProject, 0);
        toast.show();

    }// end  poste





}// end read