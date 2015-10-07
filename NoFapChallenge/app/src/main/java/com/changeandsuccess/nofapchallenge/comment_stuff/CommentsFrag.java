package com.changeandsuccess.nofapchallenge.comment_stuff;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.live_chat.LiveFragShow;
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

    com.changeandsuccess.nofapchallenge.comment_stuff.BitmapButton englishBtn, espanolBtn,koreanBtn,portuguesBtn,deutschBtn;
    com.changeandsuccess.nofapchallenge.comment_stuff.BitmapButton francaisBtn, japaneseBtn,italianoBtn,farsiBtn,arabicBtn;



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

        rootView = inflater.inflate(R.layout.commentsfrag_____message_input_part, container, false);

        new LoadComments(userIndex ,rootView, getActivity()).execute();


        //button

        sendMessageBtn = (Button) rootView.findViewById(R.id.send_message_btn);
        inputMessageEdit = (TextView) rootView.findViewById(R.id.input_edit_text);
        imageInsertBtn = (Button) rootView.findViewById(R.id.add_image_btn);
        live_chat_btn = (Button) rootView.findViewById(R.id.live_chat_btn);

        englishBtn = (com.changeandsuccess.nofapchallenge.comment_stuff.BitmapButton)rootView.findViewById(R.id.EnglishButton);
        espanolBtn = (com.changeandsuccess.nofapchallenge.comment_stuff.BitmapButton)rootView.findViewById(R.id.EspanolButton);
        koreanBtn = (com.changeandsuccess.nofapchallenge.comment_stuff.BitmapButton)rootView.findViewById(R.id.KoreanButton);
        portuguesBtn = (com.changeandsuccess.nofapchallenge.comment_stuff.BitmapButton)rootView.findViewById(R.id.PortuguesButton);
        deutschBtn = (com.changeandsuccess.nofapchallenge.comment_stuff.BitmapButton)rootView.findViewById(R.id.DeutscheButton);
        francaisBtn = (com.changeandsuccess.nofapchallenge.comment_stuff.BitmapButton)rootView.findViewById(R.id.FrancaiusButton);
        japaneseBtn = (com.changeandsuccess.nofapchallenge.comment_stuff.BitmapButton)rootView.findViewById(R.id.JapaneseButton);
        italianoBtn = (com.changeandsuccess.nofapchallenge.comment_stuff.BitmapButton)rootView.findViewById(R.id.ItalianoButton);
        farsiBtn = (com.changeandsuccess.nofapchallenge.comment_stuff.BitmapButton)rootView.findViewById(R.id.FarsiButton);
        arabicBtn = (com.changeandsuccess.nofapchallenge.comment_stuff.BitmapButton)rootView.findViewById(R.id.ArabicButton);

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


        englishBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String languageToLoad  = "en"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getActivity().getResources().updateConfiguration(config,
                        getActivity().getResources().getDisplayMetrics());

                View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

                new LoadComments(userIndex, rootView, getActivity()).execute();
                inSelectedProcess();
                englishBtn.selected();
            }

        });
        espanolBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String languageToLoad  = "es"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getActivity().getResources().updateConfiguration(config,
                        getActivity().getResources().getDisplayMetrics());

                View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);
                new LoadComments(userIndex, rootView,getActivity()).execute();

                inSelectedProcess();
                espanolBtn.selected();
            }
        });
        koreanBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String languageToLoad  = "ko"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getActivity().getResources().updateConfiguration(config,
                        getActivity().getResources().getDisplayMetrics());

                View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);


                new LoadComments(userIndex, rootView,getActivity()).execute();

                inSelectedProcess();
                koreanBtn.selected();
            }
        });
        portuguesBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String languageToLoad  = "pt"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getActivity().getResources().updateConfiguration(config,
                        getActivity().getResources().getDisplayMetrics());

                View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

                new LoadComments(userIndex, rootView,getActivity()).execute();

                inSelectedProcess();
                portuguesBtn.selected();
            }
        });
        deutschBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String languageToLoad  = "de"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getActivity().getResources().updateConfiguration(config,
                        getActivity().getResources().getDisplayMetrics());

                View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

                new LoadComments(userIndex, rootView,getActivity()).execute();

                inSelectedProcess();
                deutschBtn.selected();
            }
        });
        francaisBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String languageToLoad  = "fr"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getActivity().getResources().updateConfiguration(config,
                        getActivity().getResources().getDisplayMetrics());

                View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

                new LoadComments(userIndex, rootView,getActivity()).execute();

                inSelectedProcess();
                francaisBtn.selected();
            }
        });
        japaneseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String languageToLoad  = "ja"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getActivity().getResources().updateConfiguration(config,
                        getActivity().getResources().getDisplayMetrics());

                View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);
                new LoadComments(userIndex, rootView,getActivity()).execute();

                inSelectedProcess();
                japaneseBtn.selected();
            }
        });
        italianoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String languageToLoad  = "it"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getActivity().getResources().updateConfiguration(config,
                        getActivity().getResources().getDisplayMetrics());

                View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);
                new LoadComments(userIndex, rootView,getActivity()).execute();

                inSelectedProcess();
                italianoBtn.selected();
            }
        });
        farsiBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String languageToLoad  = "fa"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getActivity().getResources().updateConfiguration(config,
                        getActivity().getResources().getDisplayMetrics());

                View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

                new LoadComments(userIndex, rootView,getActivity()).execute();

                inSelectedProcess();
                farsiBtn.selected();
            }
        });
        arabicBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String languageToLoad  = "ar"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getActivity().getResources().updateConfiguration(config,
                        getActivity().getResources().getDisplayMetrics());

                View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

                new LoadComments(userIndex, rootView,getActivity()).execute();

                inSelectedProcess();
                arabicBtn.selected();
            }
        });

//language spinner
        ActionBar actionBar =((ActionBarActivity)getActivity()).getSupportActionBar();
//set the spinner element
        //setbarNavSpinner();

//set the default language
       // int navInt = getActivity().getResources().getInteger(R.integer.navigation_int);

        //actionBar.setSelectedNavigationItem(navInt);

        String xx = Locale.getDefault().getLanguage();
        setLanguageMarked(xx);
        return  rootView;
    }//end on create

    public static ArrayList<LoginItem> generateData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){
            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));

        }
        return items;
    } //end generate

    public void inSelectedProcess(){
        englishBtn.inSelected();
        espanolBtn.inSelected();
        koreanBtn.inSelected();
        portuguesBtn.inSelected();
        deutschBtn.inSelected();
        francaisBtn.inSelected();
        japaneseBtn.inSelected();
        italianoBtn.inSelected();
        farsiBtn.inSelected();
        arabicBtn.inSelected();
    }
    public void setLanguageMarked(String xx){
        inSelectedProcess();
        if(xx.equals("en")){
            englishBtn.selected();

        }else if(xx.equals("es")) {
            espanolBtn.selected();
        }else if(xx.equals("ko")) {
            koreanBtn.selected();
        }else if(xx.equals("pt")) {
            portuguesBtn.selected();
        }else if(xx.equals("de")) {
            deutschBtn.selected();
        }else if(xx.equals("fr")) {
            francaisBtn.selected();
        }else if(xx.equals("ja")) {
            japaneseBtn.selected();
        }else if(xx.equals("it")) {
            italianoBtn.selected();
        }else if(xx.equals("fa")) {
            farsiBtn.selected();
        }else if(xx.equals("ar")) {
            arabicBtn.selected();
        }
    }

}
