package com.changeandsuccess.nofapchallenge.coaches_tab_stuff;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

/**
 * Created by albert on 11/26/14.
 */
public class ApplyBeCoach extends Fragment {



    View rootView;

    EditText myBlurb, aboutMe;
    String myIndex;
    UserDatabase userDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //rootView = inflater.inflate(R.layout.coming_soon, container, false);
        rootView = inflater.inflate(R.layout.applybecoach_____apply_coach_page, container, false);

       myBlurb = (EditText) rootView.findViewById(R.id.apply_blurb);
        aboutMe = (EditText) rootView.findViewById(R.id.about_me_edit_text);

        userDatabase = new UserDatabase(getActivity());

        userDatabase.open();
        String[][] data = userDatabase.getData();
        userDatabase.close();

        Boolean islogin = new LoginActivity().isLoggedIn(getActivity());

        if(islogin){

            myIndex = data[0][1];

        }else{

            Intent i = new Intent(getActivity(),
                    LoginActivity.class);
            startActivity(i);
        }

        //check if applied

        checkHasApplied(rootView, myIndex, getActivity());


        Button sendButton = (Button) rootView.findViewById(R.id.send_apply_btn);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String blurbString = (String) myBlurb.getText().toString();
                String abtmeString = (String) aboutMe.getText().toString();

                new SendApplyCoach(getActivity(), blurbString, abtmeString, myIndex).execute();

            }
        });

        return rootView;

    }


    public void checkHasApplied(View rootView, String myIndex, Activity activity){


        new GetMyApply(activity, myIndex, rootView).execute();

    }

}