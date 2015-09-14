package com.changeandsuccess.nofapchallenge.battle_stuff;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;
import java.util.ArrayList;

/**
 * Created by albert on 11/10/14.
 */
public class AcceptBattle_frag extends Fragment {

    ArrayList<LoginItem> generatedLoginItem;
    View rootView;
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

            userIndex ="1";
            Intent i = new Intent(getActivity(),
                    LoginActivity.class);
            getActivity().startActivity(i);
        }//end else

        rootView = inflater.inflate(R.layout.message_____message_frag, container, false);
//load people im battling with

        new LoadRequests(userIndex, rootView, getActivity()).execute();
        //search battle button

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
