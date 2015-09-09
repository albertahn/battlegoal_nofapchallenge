package com.changeandsuccess.nofapchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.changeandsuccess.nofapchallenge.coach_profile.CheckBattle;
import com.changeandsuccess.nofapchallenge.coach_profile.FollowButton;
import com.changeandsuccess.nofapchallenge.coach_profile.LoadCoachProfile;
import com.changeandsuccess.nofapchallenge.coach_profile.ShowMentorship;

import java.util.zip.Inflater;

/**
 * Created by albert on 7/13/14.
 */
public class CoachProfile extends ActionBarActivity {
    String coachIndex;
    Inflater inflater;
    //ListView coachProListView;
    Context context;
    Button follow_btn;
    Button message_btn,
    battleBTN;
    View proheaderView;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        context = this;

        setContentView(R.layout.coachprofile_____coach_profile_header);

        activity = this;

        Intent intent = getIntent();
        coachIndex = intent.getStringExtra("coachID"); //if it's a string you stored.
        //coachProListView = (ListView) findViewById(R.id.coach_profile_listview);
         proheaderView = findViewById(R.id.coach_profile_rel_lay); //LayoutInflater.from(getApplicationContext()).inflate(R.layout.coach_profile_header, null);// inflater.inflate(R.layout.profile_pic_top, null, false);
        //coachProListView.addHeaderView(proheaderView);
        //coachProListView.addHeaderView(proheaderView);

        //load coach data

        LoadCoachProfile reading = new  LoadCoachProfile(coachIndex, proheaderView, this);
        reading.execute();

        //show mentorship check if following

        new ShowMentorship(coachIndex, proheaderView, activity).execute();

//follow
       follow_btn = (Button) findViewById(R.id.follow_btn);

        follow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Dialog d = new Dialog(context);
                d.setTitle("coach"+coachIndex);
                d.show();*/

                new FollowButton(coachIndex, proheaderView, activity ).execute();



            }
        });

        message_btn = (Button) findViewById(R.id.message_btn);
        message_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,
                        MessageInside.class);
                i.putExtra("other_guy_index", coachIndex);
                startActivity(i);
            }
        });

        //coach battle button toggle
       new CheckBattle(coachIndex, proheaderView, this).execute();

   /*     battleBTN = (Button) findViewById(R.id.battle_btn);

        battleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new BattleSend(coachIndex, proheaderView, activity).execute();

            }
        }); //butoon click
*/


        //set the list of programs
      // new LoadCoachPrograms(this, coachProListView).execute(coachIndex);
        //set the back btn
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);

    }//end oncreate


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


}//end cclass
