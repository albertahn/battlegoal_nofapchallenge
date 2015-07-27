package com.changeandsuccess.nofapchallenge.comment_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.All_Message_Frag;
import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.comment_stuff.InputComment;
import com.changeandsuccess.nofapchallenge.comment_stuff.LargeCommentActivity;
import com.changeandsuccess.nofapchallenge.comment_stuff.LoadComments;
import com.changeandsuccess.nofapchallenge.live_chat.LiveFragShow;
import com.changeandsuccess.nofapchallenge.message_activity.InputMessage;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by albert on 8/25/14.
 */
public class CommentsFrag extends Fragment {

    ArrayList<LoginItem> generatedLoginItem;
    View rootView;
    int RESULT_LOAD_IMAGE= 1;
    Button sendMessageBtn;
    TextView inputMessageEdit;
    String inputText;
    String[] messageArray;
    String userIndex;
    Button imageInsertBtn;

    Button live_chat_btn;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        //if logged in else

        LoginActivity loginActivity = new LoginActivity();
        boolean islogged = loginActivity.isLoggedIn(getActivity());

    UserDatabase userDatabase = new UserDatabase(getActivity());
    userDatabase.open();
    String[][] data = userDatabase.getData();
    userDatabase.close();


        generatedLoginItem = generateData(data);

        if(generatedLoginItem.toString() !="[]"){
            userIndex = data[0][1];

        }else{
            userIndex ="1";
        }

        rootView = inflater.inflate(R.layout.message_input_part, container, false);

        new LoadComments(userIndex ,rootView, getActivity()).execute();


        //button

        sendMessageBtn = (Button) rootView.findViewById(R.id.send_message_btn);
        inputMessageEdit = (TextView) rootView.findViewById(R.id.input_edit_text);
        imageInsertBtn = (Button) rootView.findViewById(R.id.add_image_btn);

        live_chat_btn = (Button) rootView.findViewById(R.id.live_chat_btn);

        imageInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),
                        LargeCommentActivity.class);
                startActivity(i);

                /*LargeCommentActivity L = new LargeCommentActivity(rootView, getActivity());

                L.onDialogShowImage(rootView, getActivity());*/

            }
        });

        live_chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment  fragment = new LiveFragShow();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        //onclick
        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputMessageEdit = (EditText) rootView.findViewById(R.id.input_edit_text);
                //get the text
                inputText = (String) inputMessageEdit.getText().toString();

                //messageArray = new String[3];
               // messageArray[0] = userIndex;
               // messageArray[1] = inputText;

                if(!inputText.isEmpty()){

                    InputComment inputMessage = new InputComment(getActivity(), userIndex, inputText);
                    inputMessage.execute();

                    inputMessageEdit.setText("");
                    sendMessageBtn.setEnabled(false);

                    ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.input_progress_bar);
                    progressBar.setVisibility(View.VISIBLE);
                }//end if


            }
        });

//language spinner
        ActionBar actionBar =((ActionBarActivity)getActivity()).getSupportActionBar();
//set the spinner element
        setbarNavSpinner();

//set the default language
        int navInt = getActivity().getResources().getInteger(R.integer.navigation_int);

        actionBar.setSelectedNavigationItem(navInt);

return  rootView;
    }//end on create



    public static ArrayList<LoginItem> generateData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){
            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));

        }
        return items;
    } //end generate

    public void setbarNavSpinner(){

        ActionBar actionBar =((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.action_list, android.R.layout.simple_spinner_dropdown_item);


        // assigning the spinner navigation
        actionBar.setListNavigationCallbacks(mSpinnerAdapter, new ActionBar.OnNavigationListener( ) {

            @Override
            public boolean onNavigationItemSelected ( int arg0 , long arg1 ) {

//                Log.e("navid", ""+arg0+":"+ arg1);

                if (arg0 ==0) {


                    String languageToLoad  = "en"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

                    new LoadComments(userIndex, rootView, getActivity()).execute();

                } else if (arg0==1) {

                    String languageToLoad  = "es"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);
                    new LoadComments(userIndex, rootView,getActivity()).execute();


                } else if (arg0== 2 ) {


                    String languageToLoad  = "ko"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);


                    new LoadComments(userIndex, rootView,getActivity()).execute();

                } else if ( arg0== 3 ) {


                    String languageToLoad  = "pt"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

                    new LoadComments(userIndex, rootView,getActivity()).execute();

                } else if ( arg0==4  ) {


                    String languageToLoad  = "de"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

                    new LoadComments(userIndex, rootView,getActivity()).execute();

                }else if ( arg0==5 ) {


                    String languageToLoad  = "fr"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

                    new LoadComments(userIndex, rootView,getActivity()).execute();

                }else if ( arg0==6 ) {

                    String languageToLoad  = "ja"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);
                    new LoadComments(userIndex, rootView,getActivity()).execute();

                }else if ( arg0==7 ) {

                    String languageToLoad  = "it"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);
                    new LoadComments(userIndex, rootView,getActivity()).execute();

                }else if ( arg0==8 ) {

                    String languageToLoad  = "fa"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

                    new LoadComments(userIndex, rootView,getActivity()).execute();

                }else if ( arg0==9 ) {

                    String languageToLoad  = "ar"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

                    new LoadComments(userIndex, rootView,getActivity()).execute();

                }else{
                    //email suggest
                }

                return false;

            }

        } );

    }
}
