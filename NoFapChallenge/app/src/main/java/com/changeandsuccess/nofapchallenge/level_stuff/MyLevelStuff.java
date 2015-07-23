package com.changeandsuccess.nofapchallenge.level_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

/**
 * Created by albert on 10/8/14.
 */
public class MyLevelStuff {

    Activity activity;
    int lv1,lv2,lv3,lv4,lv5,lv6,lv7,lv8,lv9,lv10;

    String userIndex;
    String updateLevel;
    int intuserlevel;

//interface
    public MyLevelStuff(Activity activity){

this.activity = activity;

      lv1 = activity.getResources().getInteger(R.integer.lv1);
      lv2 = activity.getResources().getInteger(R.integer.lv2);
      lv3 = activity.getResources().getInteger(R.integer.lv3);
      lv4 = activity.getResources().getInteger(R.integer.lv4);
     lv5 = activity.getResources().getInteger(R.integer.lv5);
     lv6 = activity.getResources().getInteger(R.integer.lv6);
     lv7 = activity.getResources().getInteger(R.integer.lv7);
     lv8 = activity.getResources().getInteger(R.integer.lv8);
     lv9 = activity.getResources().getInteger(R.integer.lv9);
     lv10 = activity.getResources().getInteger(R.integer.lv10);



        UserDatabase info = new UserDatabase(activity);
        info.open();
        String[][] data = info.getData();
        info.close();

        userIndex = data[0][1];

    }//interface

    //is betreen
    public static boolean isBetween(int x, int lower, int upper) {

        return lower <= x && x <= upper;

    }//end between days

    public void checkLevelUpdate(int days, String userLevel){


        //check if user has level
        //int intuserlevel=0;

        if(userLevel.equals("")){

            intuserlevel = 0;

        }else{

            intuserlevel = Integer.parseInt(userLevel);
        }//end else integer


        /*Dialog d = new Dialog(activity);
        d.setTitle(""+ intuserlevel);
        d.show();*/

//now check what the potention level is now by day

        if (isBetween(days, lv1, lv2)) {


                if(intuserlevel <1){

                    realUpdate("1");

                }//if less than update end


        } else if (isBetween(days, lv2, lv3)) {

            if(intuserlevel <2) {
                //update


                    realUpdate("2");
            }

        } else if (isBetween(days, lv3, lv4)) {
            if(intuserlevel <3) {
                //update


                realUpdate("3");
            }

        }else if (isBetween(days, lv4, lv5)) {
            if(intuserlevel <4) {
            //update


            realUpdate("4");
             }

        }else if (isBetween(days, lv5, lv6)) {
            //update
            if(intuserlevel <5) {
                //update


                realUpdate("5");
            }
        }else if (isBetween(days, lv6, lv7)) {
            //update
            if(intuserlevel <6) {
                //update

                realUpdate("6");
            }
        }else if (isBetween(days, lv7, lv8)) {
            //update
            if(intuserlevel <7) {
                //update


                realUpdate("7");
            }
        }else if (isBetween(days, lv8, lv9)) {
            if(intuserlevel <8) {
                //update


                realUpdate("8");
            }
        }else if (isBetween(days, lv9, lv10)) {
            if(intuserlevel <9) {
                //update


                realUpdate("9");
            }

        }else if (days>lv10) {
            if(intuserlevel <10) {
                //update


                realUpdate("10");
            }
        }


    }//end chek level update


    public void realUpdate(String level){

        new UpdateLevel(activity, userIndex, level).execute();
        UserDatabase info = new UserDatabase(activity);
        info.open();
        String[][] data = info.getData();
//update
        info.updateLevel(level, data[0][0]);
        info.close();



    }
}
