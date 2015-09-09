package com.changeandsuccess.nofapchallenge.battle_stuff;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.BattleScoreActivity;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.Battle_partner_item;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by albert on 10/3/14.
 */
public class MyBattleList_Adapter extends ArrayAdapter<Battle_partner_item> {

    Bitmap profilePhotoImage;

    static int rowList = R.layout.battlerequests_____adapter_battle_row_in_list;
    View rowView;

    TextView score_text;

    private final Context context;
    private final ArrayList<Battle_partner_item> itemsArrayList;
    ImageView profile_photo;

    Button seeScore_btn, acceptBattle_btn, duel_btn;

    public MyBattleList_Adapter(Context context, ArrayList<Battle_partner_item> itemsArrayList, String userID) {



        super(context, rowList, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        rowView = inflater.inflate(rowList, parent, false);

        // 3. Get the two text view from the rowView
        TextView usernameView = (TextView) rowView.findViewById(R.id.battle_username);


        // 4. Set the text for textView
        usernameView.setText(itemsArrayList.get(position).getfriend_username());

        //5. images for the profile pictures
        profile_photo = (ImageView) rowView.findViewById(R.id.profile_picture);
        String imageurl ="http://tanggoal.com/public/uploads/members_pic/"+ itemsArrayList.get(position).getfriend_profile_picture();


        ImageLoader imageloader = ImageLoader.getInstance();


        if(!imageloader.isInited()) {

            imageloader.init(ImageLoaderConfiguration.createDefault(context));
        }

        //imageloader.displayImage(url, view);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(50))
                .cacheOnDisc(true)
                .cacheInMemory(true)
                .considerExifParams(true)
                .build();
        imageloader.displayImage(imageurl, profile_photo, options);




//7. for the buttons on the site

        //1)see score
      final Button  seeScore_btn = (Button) rowView.findViewById(R.id.see_score_btn);

        //2) accept battle
        final Button  acceptBattle_btn =(Button) rowView.findViewById(R.id.accept_battle_btn);

        //3) duel
        final Button  duel_btn= (Button) rowView.findViewById(R.id.request_battle_btn);


        //4) accoring to battle yes or no show different btns


        String battleYes = itemsArrayList.get(position).getbattle();
        String isFriend = itemsArrayList.get(position).getstatus();

        if(battleYes.equals("yes")){

            seeScore_btn.setVisibility(View.VISIBLE);
            acceptBattle_btn.setVisibility(View.INVISIBLE);
            duel_btn.setVisibility(View.INVISIBLE);

            //if battle then show score


            score_text = (TextView) rowView.findViewById(R.id.score_text);

            score_text.setText(" "+itemsArrayList.get(position).getfriend_score());


        }else if(isFriend.equals("pending")){

            seeScore_btn.setVisibility(View.INVISIBLE);
            acceptBattle_btn.setVisibility(View.VISIBLE);
            duel_btn.setVisibility(View.INVISIBLE);

        }else if(battleYes.equals("no")){

            seeScore_btn.setVisibility(View.INVISIBLE);
            acceptBattle_btn.setVisibility(View.INVISIBLE);
            duel_btn.setVisibility(View.VISIBLE);

        }


        //now for the clickable buttons

        final String my_index = itemsArrayList.get(position).getmy_index();
        final String friend_index =  itemsArrayList.get(position).getother_friend_index();

        //1. seeScore_btn
        seeScore_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,
                        BattleScoreActivity.class);

                i.putExtra("friend_index",""+friend_index);
                i.putExtra("my_index", ""+my_index);
                context.startActivity(i);



            }
        });

        //2. duel click
        duel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seeScore_btn.setVisibility(View.VISIBLE);
                acceptBattle_btn.setVisibility(View.INVISIBLE);
                duel_btn.setVisibility(View.INVISIBLE);

               new RequestBattle(my_index,friend_index, rowView, context).execute();

                //show other btns


            }
        });

        //3. acept click
        acceptBattle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


        // 5. retrn rowView
        return rowView;
    }





} //end adapter
