package com.changeandsuccess.nofapchallenge.comment_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;

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
 * Created by albert on 9/7/14.
 */
public class InputReply extends AsyncTask<String, Integer, String> {


    //String[] messageArray= new String[3];
    String jsonString;
    Activity activity;
    HttpResponse httpResponse;
    String userindex, inputText;

    int commentIndex;

    View repliesInputpart;

    public InputReply(Activity context, String userindex, String inputText, int commentIndex, View repliesInputpart){
        this.activity = context;
        this.userindex = userindex;

        this.commentIndex =commentIndex;
        this.repliesInputpart = repliesInputpart;
        this.inputText = inputText;

        /*try{

            this.inputText = java.net.URLEncoder.encode(inputText, "UTF-8");
        }catch (IOException e){

            Log.e("inputText comment", "" + e);
        }//end try*/

    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {

        // Log.e("dickhole", ""+messageArray[2]+" :"+context);


        try {

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //Charset chars = Charset.forName("UTF-8");
            //builder.setCharset(chars);

            builder.addTextBody("reply",inputText , ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("user_index", userindex, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("comment_index", ""+commentIndex, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("courses_index", ""+activity.getResources().getInteger(R.integer.course_int));


            HttpClient httpClient = new DefaultHttpClient();

            httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                    System.getProperty("http.agent"));

            HttpPost httpPost = new HttpPost("http://mobile.tanggoal.com/reply/insert_reply/");

            httpPost.setEntity( builder.build());


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
        try {

           /* Dialog das = new Dialog(context);
            das.setTitle(""+ result);
            TextView tvsa = new TextView(context);
            tvsa.setText(""+result);
            das.setContentView(tvsa);
            das.show();
*/

            JSONObject json = new JSONObject(result);

            String[] dataArray = new String[5];

            String user_index = json.get("members_index").toString();
            String username = json.get("username").toString();
            String profile_picture = json.get("profile_picture").toString();

            String comment_index =  json.get("comment_index").toString();
            String comment_text =  json.get("comment_text").toString();

            dataArray[0] = user_index;
            dataArray[1] = username;
            dataArray[2] = comment_index;
            dataArray[3] = comment_text;
            dataArray[4]= profile_picture;




            if(user_index!=""){

                messageSuccess(activity, dataArray);
                replySuccess(repliesInputpart, dataArray);

            }else{

                Dialog ds = new Dialog(activity);
                ds.setTitle("Comment did not go through");
                TextView tvs = new TextView(activity);
                tvs.setText("");
                ds.setContentView(tvs);
                ds.show();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public void messageSuccess(Context context, String[] dataArray){


        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // View rootView = inflater.inflate(R.layout.message_input_part, null, false);

        View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);

        ListView insideMessageListView = (ListView) rootView.findViewById(R.id.message_frag_list);


        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.messageinsideadapter_____message_bubble_my, null, false); //inflater.inflate(rowList, null, false);

        //add the rowview

       // insideMessageListView.addHeaderView(rowView);

        //reload the thing
        new LoadComments(userindex,rootView, activity).execute();

        // 3. Get the two text view from the rowView
        /*TextView text_body_sent = (TextView) rowView.findViewById(R.id.text_body);
        TextView username =(TextView) rowView.findViewById(R.id.user_name);


        // 4. Set the text for textView
        text_body_sent.setText(dataArray[3]);
        username.setText(dataArray[1]);
*/
        // insideMessageListView.setSelection(insideMessageListView.getCount() - 1);

        EditText inputTextpart = ( EditText) rootView.findViewById(R.id.input_edit_text);
        inputTextpart.setText("");

        ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.input_progress_bar);
        progressBar.setVisibility(View.GONE);

        Button sendMessageBtn = (Button) rootView.findViewById(R.id.send_message_btn);
        sendMessageBtn.setEnabled(true);


    }// end main comment part


    //now the reply part

   public void replySuccess(View repliesInputpart, String[] dataArray){

       LayoutInflater inflater = (LayoutInflater) activity
               .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


       ListView insideMessageListView = (ListView) repliesInputpart.findViewById(R.id.message_frag_list);


       // int rowList = R.layout.message_bubble_my;
       // 2. Get rowView from inflater
       View rowView = inflater.inflate(R.layout.messageinsideadapter_____message_bubble_my, null, false); //inflater.inflate(rowList, null, false);

       //add the rowview

       //insideMessageListView.addFooterView(rowView);

       new LoadComments(userindex,insideMessageListView, activity).execute();

       //new LoadReplies(userindex, activity, commentIndex).execute();

      /* new LoadComments(userindex,insideMessageListView, activity).execute();

       // 3. Get the two text view from the rowView
       TextView text_body_sent = (TextView) rowView.findViewById(R.id.text_body);
       TextView username =(TextView) rowView.findViewById(R.id.user_name);


       // 4. Set the text for textView
       text_body_sent.setText(dataArray[3]);
       username.setText(dataArray[1]);*/

       // insideMessageListView.setSelection(insideMessageListView.getCount() - 1);

       EditText inputTextpart = ( EditText) repliesInputpart.findViewById(R.id.input_edit_text);
       inputTextpart.setText("");

       ProgressBar progressBar = (ProgressBar) repliesInputpart.findViewById(R.id.input_progress_bar);
       progressBar.setVisibility(View.GONE);

       Button sendMessageBtn = (Button) repliesInputpart.findViewById(R.id.send_message_btn);
       sendMessageBtn.setEnabled(true);


   }





}// end read