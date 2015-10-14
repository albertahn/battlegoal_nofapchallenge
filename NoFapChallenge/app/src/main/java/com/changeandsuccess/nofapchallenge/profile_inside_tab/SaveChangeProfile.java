package com.changeandsuccess.nofapchallenge.profile_inside_tab;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.LoginHelper;
import com.changeandsuccess.nofapchallenge.MainActivity;
import com.changeandsuccess.nofapchallenge.R;
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
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by albert on 3/19/15.
 */
public class SaveChangeProfile extends AsyncTask<String, Integer, String> {

    Activity activity;
    View rootView;
    String username, email, pro_pic_url , members_index;



    public SaveChangeProfile(String members_index,
                             String username,
                        String email,
                        String pro_pic_url,
                        Activity activity){
        this.members_index = members_index;
        this.username = username;
        this.email = email;
        this.activity = activity;
        this.pro_pic_url = pro_pic_url;



        rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);

        ViewGroup reg_scrollview = (ViewGroup)  rootView.findViewById(R.id.reg_scrollview);
        reg_scrollview.removeAllViewsInLayout();

        ProgressBar progressBar = new  ProgressBar(activity);
        progressBar.setVisibility(View.VISIBLE);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        reg_scrollview.addView(progressBar,params);

        //reg_scrollview.addView(progressBar);

    }

    @Override
    protected String doInBackground(String... params) {

        try {


            File sourceFile = new File(pro_pic_url.toString());

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            FileBody fileBody = new FileBody(sourceFile);

            if(!pro_pic_url.isEmpty()) {

                builder.addPart("file", fileBody);

            }
            builder.addTextBody("members_index", members_index, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("username", username, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("email", email, ContentType.create("text/plain", MIME.UTF8_CHARSET));



            HttpClient httpClient = new DefaultHttpClient();

            httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                    System.getProperty("http.agent"));

            HttpPost httpPost = new HttpPost("http://mobile.tanggoal.com/register/edit_profile/");

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
        try {

            UserDatabase info = new UserDatabase(activity);
            info.open();
            info.deleteAllData();
            info.close();

            JSONObject json = new JSONObject(result);
            String user_index = json.get("index").toString();
            String username = json.get("username").toString();
            String email = json.get("email").toString();
            String password =  json.get("password").toString();
            String profile_picture = json.get("profile_picture").toString();
            String FID =  json.get("FID").toString();
            String level =  json.get("level").toString();
            String text_profile = json.get("profile").toString();
String exp_points = json.get("points").toString();
            //Log.d("jsonfuck", ""+ username + email+password+profile_picture+FID);

            UserDatabase entry = new UserDatabase(activity);
            entry.open();
            entry.createEntry(user_index, username, email, password, profile_picture, FID,level, text_profile, exp_points);
            entry.close();

           // new GetLastDayFromServer(activity, user_index).execute();


            // String course_index =result;

            if(!result.isEmpty()){

                Dialog dt = new Dialog(activity);
                dt.setTitle("Updated");
                TextView tt = new TextView(activity);
                tt.setText("Your profile will be changed when you restart the app or restart your phone.");
                dt.setContentView(tt);
                dt.show();
//end intent
                Intent i = new Intent(activity,
                        MainActivity.class);

                activity.startActivity(i);


            }

            //run the set stuff





        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
