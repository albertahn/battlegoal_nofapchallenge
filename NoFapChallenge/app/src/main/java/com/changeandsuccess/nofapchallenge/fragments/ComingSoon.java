package com.changeandsuccess.nofapchallenge.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.SearchActivity;
import com.changeandsuccess.nofapchallenge.battle_stuff.LoadBattles;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import java.util.ArrayList;

/**
 * Created by albert on 10/31/14.
 */
public class ComingSoon  extends Fragment {

    View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        rootView = inflater.inflate(R.layout.coming_soon, container, false);



        return  rootView;
    }//end on create




}
