package com.changeandsuccess.nofapchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.changeandsuccess.nofapchallenge.message_activity.InputMessage;
import com.changeandsuccess.nofapchallenge.message_activity.InsideMessageLoad;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import java.util.ArrayList;

/**
 * Created by albert on 7/21/14.
 */
public class MessageInside extends ActionBarActivity{

    //String hasMessageID;
    ArrayList<LoginItem> generatedLoginItem;
    View rootview;
    Button sendMessageBtn;
    EditText inputMessageEdit;
    String inputText;
    Context context;
    String[] messageArray= new String[3];
    String userIndex;
    String prevActivity;
    String otherguyname, other_guy_index;
    Activity activity = this;
    Button live_chat_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;


//get id
        Intent intent = getIntent();
       // hasMessageID = intent.getStringExtra("hasMessageID");
        prevActivity= intent.getStringExtra("prevActivity");
        otherguyname = intent.getStringExtra("otherguyname");
        other_guy_index =intent.getStringExtra("other_guy_index");

        //the acitonbar title

        getSupportActionBar().setTitle("hi"+otherguyname);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.titlecolor));

        //get userid
        LoginHelper loginHelper = new LoginHelper();



        String[][] loginData = loginHelper.checkLogin(this);

        generatedLoginItem = generateData(loginData);


        if(generatedLoginItem.toString() =="[]") {


            rootview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.message_____click_to_login, null);
            setContentView(rootview);


            Button loginTanggoalBTN = (Button)  rootview.findViewById(R.id.to_logon_page_button);

            loginTanggoalBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(activity,
                            LoginActivity.class);
                    startActivity(i);

                }

            });


        }else{

            rootview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.commentsfrag_____message_input_part, null);
            setContentView(rootview);


        UserDatabase info = new UserDatabase(context);
        info.open();
        String[][] data = info.getData();
        info.close();

        userIndex = data[0][1];

        if(other_guy_index!=null){

            InsideMessageLoad insideMessageLoad = new InsideMessageLoad(userIndex, other_guy_index, rootview, this, getSupportActionBar());
            insideMessageLoad.execute(other_guy_index);
        }

        //hide keyboard
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //get the message input button
        sendMessageBtn = (Button) rootview.findViewById(R.id.send_message_btn);
        inputMessageEdit = (EditText) rootview.findViewById(R.id.input_edit_text);
            live_chat_btn =  (Button)  rootview.findViewById(R.id.live_chat_btn);

            if(live_chat_btn !=null){

                live_chat_btn.setVisibility(View.GONE);

            }// hi there

        //onclick
        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get the text
                inputText = (String) inputMessageEdit.getText().toString();

                //messageArray = new String[3];
                messageArray[0] = userIndex;
                messageArray[1] = inputText;
                messageArray[2] = other_guy_index;

                if(!inputText.isEmpty()){
                    InputMessage inputMessage = new InputMessage(activity, messageArray, getSupportActionBar());
                    inputMessage.execute(messageArray);


                    inputMessageEdit.setText("");
                    ProgressBar progressBar = (ProgressBar) rootview.findViewById(R.id.input_progress_bar);
                    progressBar.setVisibility(View.VISIBLE);

                    Button   sendMessageBtn = (Button) rootview.findViewById(R.id.send_message_btn);
                    sendMessageBtn.setEnabled(false);

                }


            }
        });

        //set backbutton
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //title
        if(otherguyname!=null){
            actionBar.setTitle(otherguyname);

        }

        }//end if logged
    } //end crreate



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

               if(prevActivity==null){

                   Intent i = new Intent(this,
                           MainActivity.class);
                   startActivity(i);
               }else{
                     finish();
               }

                //NavUtils.navigateUpFromSameTask(this);
                return true;
            default:


                finish();
                return super.onOptionsItemSelected(item);
        }
    }//end



    public static ArrayList<LoginItem> generateData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){
            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6], data[i][7]));

        }
        return items;
    } //end generate



} //end activity
