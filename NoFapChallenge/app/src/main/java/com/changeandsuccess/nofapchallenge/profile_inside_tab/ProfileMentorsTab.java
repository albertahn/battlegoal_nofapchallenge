package com.changeandsuccess.nofapchallenge.profile_inside_tab;

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
import com.changeandsuccess.nofapchallenge.adapter.Profile_MyMentors_Adapter;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.model.MyMentorsTabItem;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 7/4/14.
 */
public class ProfileMentorsTab extends Fragment {

    final static String STREAMURL = "http://mobile.tanggoal.com/follow/people_im_following/23/";
    ListView myMentorsListView;
    JSONArray jsonArray;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

       View rootView = inflater.inflate(R.layout.profilementorstab_____profile_my_mentors, container, false);

        myMentorsListView = (ListView) rootView.findViewById(R.id.myment_listview);


        new ShowStream().execute();


        return rootView;



    }//end oncreate view//read stream
    public class ShowStream extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String...params) {
            try {
                jsonArray = JsonReader.readJsonArrayFromUrl(STREAMURL);


                //return params[0];
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.d("here", "noJSON");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);



            if ( jsonArray != null) {

                Profile_MyMentors_Adapter mentAdapter = new Profile_MyMentors_Adapter(myMentorsListView.getContext(), generateData(jsonArray));

                myMentorsListView.setAdapter(mentAdapter);

                //click action

                myMentorsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        int cock = view.getId();

                        Intent i = new Intent(getActivity(),
                                CoachProfile.class);

                        i.putExtra("coachID", ""+cock);
                        // i.putExtra("coachName", ""+)
                        startActivity(i);


                    }
                });


            }else{

                Log.d("emptyarray", "sptmey man");
            }

            //now array

        }

    }// end read

    //getnerate
    ArrayList<MyMentorsTabItem> generateData(JSONArray jsondata){

        ArrayList<MyMentorsTabItem> items = new ArrayList<MyMentorsTabItem>();

        for (int i =0; i<jsondata.length() ; i++){


            try{

                items.add(new MyMentorsTabItem(jsondata.getJSONObject(i).getString("username"),
                        jsondata.getJSONObject(i).getString("index"),
                        jsondata.getJSONObject(i).getString("profile_picture")
                ));

            }catch(JSONException e){

                e.printStackTrace();

            }



        }

        return items;

    }// end getnerate item


    public static ArrayList<LoginItem> generateProData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){

            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));

        }
        return items;
    } //end generate




}