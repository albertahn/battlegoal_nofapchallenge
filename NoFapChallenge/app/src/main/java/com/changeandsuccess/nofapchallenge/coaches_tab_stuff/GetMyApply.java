package com.changeandsuccess.nofapchallenge.coaches_tab_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.comment_stuff.LoadComments;

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
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by albert on 12/22/14.
 */

public class GetMyApply extends AsyncTask<String, Integer, String> {

    Activity context;
    //String[] messageArray= new String[3];
    String jsonString;

    HttpResponse httpResponse;
    String blurb, aboutMe, myIndex;

    View rootView;
ProgressBar progressBar;

    public GetMyApply(Activity context, String myIndex, View rootView){
        this.context = context;
        this.myIndex = myIndex;
        this.rootView = rootView;

        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar_apply);

        progressBar.setVisibility(View.INVISIBLE);

    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {


        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

           /* builder.addTextBody("blurb", blurb, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("about_me", aboutMe, ContentType.create("text/plain", MIME.UTF8_CHARSET));
*/
            builder.addTextBody("my_index", myIndex, ContentType.create("text/plain", MIME.UTF8_CHARSET));

            HttpClient httpClient = new DefaultHttpClient();
            httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                    System.getProperty("http.agent"));

            HttpPost httpPost = new HttpPost("http://mobile.tanggoal.com/feature/get_my_apply/"+myIndex);

            httpPost.setHeader("Connection", "Keep-Alive");
            httpPost.setHeader("Accept-Charset", HTTP.UTF_8);
            httpPost.setHeader("ENCTYPE", "multipart/form-data");

            httpPost.setEntity( builder.build());

            //httpPost.setEntity( new UrlEncodedFormEntry( nameValuePairs, HTTP.UTF_8 ) );
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String is;

            try {

                BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "MS949"));
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
        try {

            /*Dialog das = new Dialog(context);
            das.setTitle(""+ result);
            TextView tvsa = new TextView(context);
            tvsa.setText(""+result);
            das.setContentView(tvsa);
            das.show();*/

            JSONObject json = new JSONObject(result);


            String user_index = json.get("members_index").toString();
            blurb = json.get("blurb").toString();
            aboutMe =  json.get("about_me").toString();

            if(user_index!=""){

                messageSuccess(context, blurb, aboutMe);

            }else{

                Dialog ds = new Dialog(context);
                ds.setTitle("Comment did not go through");
                TextView tvs = new TextView(context);
                tvs.setText(""+blurb);
                ds.setContentView(tvs);
                ds.show();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

           /* Dialog ds = new Dialog(context);
            ds.setTitle("Check Internet");
            TextView tvs = new TextView(context);
            tvs.setText("Check Internet connection");
            ds.setContentView(tvs);
            ds.show();*/

        }
    }//

    public void messageSuccess(Activity context,String blurb, String abtme){

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);

//set the blurb
        TextView blurb_text_view= (TextView) rootView.findViewById(R.id.blurb_text_view);
        blurb_text_view.setVisibility(View.VISIBLE);
        blurb_text_view.setText(""+blurb);

//set about me

        TextView about_me_edit_text = (TextView) rootView.findViewById(R.id.about_me_text_view);
        about_me_edit_text.setVisibility(View.VISIBLE);

        about_me_edit_text.setText(""+abtme);
//edit button show

        Button edit_apply_btn = (Button) rootView.findViewById(R.id.edit_apply_btn);
edit_apply_btn.setVisibility(View.VISIBLE);

//send_apply_btn hide
        Button send_apply_btn = (Button) rootView.findViewById(R.id.send_apply_btn);
        send_apply_btn.setVisibility(View.INVISIBLE);

//make the edittext invisible

        EditText blurbEdit = (EditText) rootView.findViewById(R.id.apply_blurb);
        blurbEdit.setVisibility(View.INVISIBLE);
        blurbEdit.setText(blurb);

       EditText aboutMeEdit = (EditText) rootView.findViewById(R.id.about_me_edit_text);
        aboutMeEdit.setVisibility(View.INVISIBLE);
        aboutMeEdit.setText(abtme);

//set the click listener for edit text
        setEditClick(context, edit_apply_btn,blurbEdit, aboutMeEdit );

    } //

    public void setEditClick(Activity activity,
                             Button editApplyButton,
                             EditText blurbEdit,
                             EditText aboutMeEdit){

        final Activity activityfinal =  activity;
        final EditText blurbEditfinal =  (EditText) blurbEdit; //.setVisibility(View.VISIBLE);
        final EditText aboutMeEditfinal = aboutMeEdit;//.setVisibility(View.VISIBLE);
        final Button editApplyButtonfinal = editApplyButton;

        editApplyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
                blurbEditfinal.setVisibility(View.VISIBLE);
                aboutMeEditfinal.setVisibility(View.VISIBLE);

                //show submit btn
                Button send_apply_btn = (Button) rootView.findViewById(R.id.send_apply_btn);
                send_apply_btn.setVisibility(View.VISIBLE);

                //hide the edit button and hide the textviews
                editApplyButtonfinal.setVisibility(View.INVISIBLE);

                TextView blurb_text_view= (TextView) rootView.findViewById(R.id.blurb_text_view);
                blurb_text_view.setVisibility(View.INVISIBLE);

                TextView about_me_edit_text = (TextView) rootView.findViewById(R.id.about_me_text_view);
                about_me_edit_text.setVisibility(View.INVISIBLE);
            }
        });

    }


}// end read