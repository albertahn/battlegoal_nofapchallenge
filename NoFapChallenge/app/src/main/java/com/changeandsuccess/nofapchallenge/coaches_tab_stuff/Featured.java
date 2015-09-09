package com.changeandsuccess.nofapchallenge.coaches_tab_stuff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.changeandsuccess.nofapchallenge.R;

/**
 * Created by albert on 4/30/14.
 */
public class Featured extends Fragment{


    ListView featuredListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.featured_____featured_frag, container, false);

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