package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import java.util.ArrayList;

/**
 * Created by albert on 1/2/15.
 */
public class FreeStoreFrag extends Fragment {

    ArrayList<LoginItem> generatedLoginItem;
    View rootView;

    ImageButton notifyButton;
    String userIndex;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //if logged in else
        UserDatabase userDatabase= new UserDatabase(getActivity());
        userDatabase.open();
        String[][] data = userDatabase.getData();
        userDatabase.close();

        generatedLoginItem = generateData(data);

        if(generatedLoginItem.toString() !="[]"){
            userIndex = data[0][1];

        }else{

//login user
            //userIndex ="1";
        }

        //rootView = inflater.inflate(R.layout.coming_soon, container, false);

        rootView = inflater.inflate(R.layout.freestorefrag_____store_frag, container, false);
        new LoadStoreProducts(getActivity()).execute();

        return  rootView;
    }//end on create

    public static ArrayList<LoginItem> generateData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){

            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));

        }//
        return items;
    } //end generate
}
