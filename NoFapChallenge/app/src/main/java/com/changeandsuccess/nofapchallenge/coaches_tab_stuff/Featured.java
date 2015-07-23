package com.changeandsuccess.nofapchallenge.coaches_tab_stuff;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.changeandsuccess.nofapchallenge.CoachProfile;
import com.changeandsuccess.nofapchallenge.R;

import com.changeandsuccess.nofapchallenge.adapter.FeaturedListAdapter;
import com.changeandsuccess.nofapchallenge.coaches_tab_stuff.LoadCoachesTab;
import com.changeandsuccess.nofapchallenge.model.FeaturedItem;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 4/30/14.
 */
public class Featured extends Fragment{


    ListView featuredListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.featured_frag, container, false);

        super.onCreate(savedInstanceState);

        featuredListView = (ListView) rootView.findViewById(R.id.featuredListView);


        new LoadCoachesTab(featuredListView, getActivity()).execute();


        return rootView;
    }







/*
    protected void onListItemClick(ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(this,  "my id to pass along the subview is " + position,Toast.LENGTH_LONG).show();

    }
*/
}