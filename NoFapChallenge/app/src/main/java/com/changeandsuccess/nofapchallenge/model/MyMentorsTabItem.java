package com.changeandsuccess.nofapchallenge.model;

/**
 * Created by albert on 7/4/14.
 */
public class MyMentorsTabItem {



    private String username;
    private  String userIndex;
    private  String profilePicture;

    public MyMentorsTabItem(String user_name, String index, String profile_picture){

        super();
        this.username = user_name;
        this.userIndex = index;
        this.profilePicture = profile_picture;


    }// end loginitem

    // getters and setters...

    public CharSequence getusername() {
        // TODO Auto-generated method stub

        return username;
    }

    public CharSequence getuserIndex(){

        return userIndex;
    }

    public CharSequence getProfilePic(){

        return profilePicture;

    }//
}




