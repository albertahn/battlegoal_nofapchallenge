package com.changeandsuccess.nofapchallenge.profile_inside_tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.store_puchase_stuff.LoadMyPurchases;
import com.changeandsuccess.nofapchallenge.store_puchase_stuff.LoadStoreProducts;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

/**
 * Created by albert on 10/12/15.
 */
public class MyPurchase_frag extends Fragment {
    View rootView;

    String user_index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView =  inflater.inflate(R.layout.allstoretabsfrag_____store_tabs_frag, container, false);

        UserDatabase info = new UserDatabase(getActivity());
        info.open();
        String[][] data = info.getData();
        info.close();

        user_index = data[0][1];

        new LoadMyPurchases(user_index, getActivity()).execute();




        return rootView;
    }//end oncreate


}