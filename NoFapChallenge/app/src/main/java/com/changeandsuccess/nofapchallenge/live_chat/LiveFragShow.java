package com.changeandsuccess.nofapchallenge.live_chat;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.level_stuff.LoadLevelRank;

/**
 * Created by battlegoal on 7/24/15.
 */
public class LiveFragShow extends Fragment{

    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.livefrag_show, container, false);


        return  rootView;
    }//end on create



}
