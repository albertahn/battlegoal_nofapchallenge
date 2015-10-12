package com.changeandsuccess.nofapchallenge.profile_inside_tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.notify_stuff.CheckNotify;
import com.changeandsuccess.nofapchallenge.notify_stuff.ToggleBellSend;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import java.util.ArrayList;

/**
 * Created by albert on 8/25/14.
 */
public class SettingsFrag extends Fragment {

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

            userIndex ="1";
        }

        rootView = inflater.inflate(R.layout.settingsfrag_____settings_frag, container, false);



        //check notify state

        new CheckNotify(getActivity(), userIndex).execute();
        //button
/*
        notifyButton = (ImageButton) rootView.findViewById(R.id.notify_button);
        //onclick
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               new ToggleBellSend(getActivity(), userIndex, rootView).execute();


            }
        });

*/

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
