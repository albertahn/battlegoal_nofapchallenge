package com.changeandsuccess.nofapchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;

import com.changeandsuccess.nofapchallenge.battle_stuff.LoadScoreActivity;
import com.changeandsuccess.nofapchallenge.message_activity.InsideMessageLoad;

/**
 * Created by albert on 10/3/14.
 *
 * set the profile pic of each member and the day each are on the challenge
 * database day count in friend different
 *
 */
public class BattleScoreActivity extends ActionBarActivity {


    String friendIndex, myIndex;
    Activity activity;
    View rootview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.battlescoreactivity_____battle_score_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        activity = this;

        rootview = this.getWindow().getDecorView().findViewById(android.R.id.content);
        View battle_activity_rel_view = rootview.findViewById(R.id.battle_activity_rel_view);

        Intent intent = getIntent();
        friendIndex = intent.getStringExtra("friend_index");
        myIndex = intent.getStringExtra("my_index");

        //load info on the score of people
        new LoadScoreActivity(myIndex, friendIndex, activity).execute();
        new InsideMessageLoad(myIndex, friendIndex, battle_activity_rel_view, this, actionBar).execute();
        //Log.e("friendand mee", ""+ friendIndex+":"+myIndex);

    }//end on create




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:

                finish();
                return super.onOptionsItemSelected(item);
        }
    } //end onoptions selected


}
