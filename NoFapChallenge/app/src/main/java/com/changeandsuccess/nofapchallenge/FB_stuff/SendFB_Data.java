package com.changeandsuccess.nofapchallenge.FB_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.MainActivity;
import com.changeandsuccess.nofapchallenge.loginStuff.GetLastDayFromServer;
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

/**
 * Created by albert on 8/15/14.
 */
public class SendFB_Data extends AsyncTask<String, Integer, String> {


    Activity activity;
    View rootView;
    String email, FID, access_token,username;

    public SendFB_Data(String email,
                       String username,
                       String FID,
                       String access_token,
                       Activity activity){


        this.email = email;
        this.username = username;
        this.FID = FID;
        this.access_token = access_token;
        this.activity = activity;

        rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);

        //prodListView = (ListView) rootView.findViewById(R.id.product_page_listview);



    }

    @Override
    protected String doInBackground(String... params) {
        try {


            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addTextBody("username", username, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("email", email, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("FID", FID, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("access_token",  access_token, ContentType.create("text/plain", MIME.UTF8_CHARSET));


            HttpClient httpClient = new DefaultHttpClient();

            httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                    System.getProperty("http.agent"));

            HttpPost httpPost = new HttpPost("http://mobile.tanggoal.com/fb/loginfacebook");

            httpPost.setEntity( builder.build());


            HttpResponse httpResponse = httpClient.execute(httpPost);
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
    protected void onPostExecute(String result){

        super.onPostExecute(result);

      /*  Dialog d = new Dialog(activity);
        d.setTitle("back from server "+result.toString());
        TextView t = new TextView(activity);
        t.setText(""+result.toString() );
        d.setContentView(t);
        d.show();*/

        //now login person


        try {
            JSONObject json = new JSONObject(result);
            String user_index = json.get("index").toString();
            String username = json.get("username").toString();
            String email = json.get("email").toString();
            String password =  json.get("password").toString();
            String profile_picture = json.get("profile_picture").toString();
            String FID =  json.get("FID").toString();
            String level = json.get("level").toString();
            String text_profile = json.get("profile").toString();
            String exp_points = json.get("points").toString();

            //Log.d("jsonfuck", ""+ username + email+password+profile_picture+FID);

            UserDatabase entry = new UserDatabase(activity);
            entry.open();
            entry.createEntry(user_index, username, email, password, profile_picture, FID, level, text_profile, exp_points);
            entry.close();

            if(user_index!=""){

                //getlastday

                new GetLastDayFromServer(activity, user_index).execute();

                //LoginActivity
                LoginActivity loginActivity = new LoginActivity();

                loginActivity.loginSuccess(activity);


                //start activity
                Intent i = new Intent(activity,
                        MainActivity.class);

                activity.startActivity(i);


                Dialog da2 = new Dialog(activity);
                da2.setTitle(""+text_profile);
                TextView tv1 = new TextView(activity);
                tv1.setText(""+text_profile);
                da2.setContentView(tv1);
                da2.show();


            }else{


                Dialog da = new Dialog(activity);
                da.setTitle("Email or Passord is wrong");
                TextView tv = new TextView(activity);
                tv.setText("");
                da.setContentView(tv);
                da.show();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }//end post

}