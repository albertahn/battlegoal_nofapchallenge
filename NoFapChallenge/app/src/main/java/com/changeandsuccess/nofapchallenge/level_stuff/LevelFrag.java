package com.changeandsuccess.nofapchallenge.level_stuff;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.LoginItem;

import java.util.ArrayList;

/**
 * Created by albert on 8/25/14.
 */
public class LevelFrag extends Fragment {
    Context context;
    ArrayList<LoginItem> generatedLoginItem;
    View rootView;

    Button sendMessageBtn;
    EditText inputMessageEdit;
    String inputText;
    String[] messageArray;
    String userIndex;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.levelfrag_____level_frag, container, false);


      new LoadLevelRank(rootView,getActivity()).execute();


        return  rootView;
    }//end on create



}//frag
