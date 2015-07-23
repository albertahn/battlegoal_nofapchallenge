package com.changeandsuccess.nofapchallenge.register_acitvity;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.LoginHelper;
import com.changeandsuccess.nofapchallenge.R;

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
 * Created by albert on 8/13/14.
 */
public class SaveRegister extends AsyncTask<String, Integer, String> {

    Activity activity;
    View rootView;
    String username, email, password, pro_pic_url;



    public SaveRegister(String username,
                        String email,
                        String password,
                        String pro_pic_url,
                        Activity activity){

        this.username = username;
        this.email = email;
        this.activity = activity;
        this.password = password;
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

            builder.addTextBody("username", username, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("email", email, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("password",  password, ContentType.create("text/plain", MIME.UTF8_CHARSET));


            HttpClient httpClient = new DefaultHttpClient();

            httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                    System.getProperty("http.agent"));

            HttpPost httpPost = new HttpPost("http://mobile.tanggoal.com/register/reg");

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

            JSONObject json = new JSONObject(result);


            // String course_index =result;

            if(!result.isEmpty()){

               /* Dialog d = new Dialog(activity);
                d.setTitle(""+result);
                TextView t = new TextView(activity);
                t.setText(""+result);
                d.setContentView(t);
                d.show();*/

String email_inputed = json.get("email").toString();
String password_inputed = password;
                //now login the person

                LoginHelper loginHelper = new LoginHelper();

                loginHelper.loginUser(email_inputed,password_inputed, activity );


            }

            //run the set stuff





        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
