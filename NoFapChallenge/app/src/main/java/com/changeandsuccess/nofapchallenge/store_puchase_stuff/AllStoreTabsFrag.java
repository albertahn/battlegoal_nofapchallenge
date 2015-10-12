package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.changeandsuccess.nofapchallenge.R;

import java.util.ArrayList;

/**
 * Created by albert on 1/18/15.
 */

public class AllStoreTabsFrag extends Fragment {
    View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView =  inflater.inflate(R.layout.allstoretabsfrag_____store_tabs_frag, container, false);

 new LoadStoreProducts(getActivity()).execute();




        return rootView;
    }//end oncreate


}
