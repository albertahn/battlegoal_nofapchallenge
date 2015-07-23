package com.changeandsuccess.nofapchallenge.model;

/**
 * Created by albert on 8/25/14.
 */
public class Battle_partner_item {

    private String friends_key_index,
             my_index,
            other_friend_index,
             status,
            timestamp,
              news_type,
            battle,
              friend_username,
            friend_profile_picture,
              friend_points,
    friend_score;



    public Battle_partner_item(
            String friends_key_index,
            String  my_index,
            String   other_friend_index,
            String   status,
            String   timestamp,
            String news_type,
            String  battle,
            String   friend_username,
            String   friend_profile_picture,
            String    friend_points,
            String friend_score) {



                this.friends_key_index=friends_key_index;
                this.my_index=my_index;
                this.other_friend_index=other_friend_index;
                this.status=status;
                this.timestamp=timestamp;
                this.news_type=news_type;
                this. battle= battle;
                this. friend_username=friend_username;
                this. friend_profile_picture=friend_profile_picture;
                this.friend_points=friend_points;
        this.friend_score = friend_score;

    }// end loginitem



    public String getfriends_key_index() {
        return friends_key_index;
    }

    public String getmy_index() {
        return my_index;
    }

    public String getother_friend_index() {
        return other_friend_index;
    }


    public String getstatus() {
        return status;
    }


    public String gettimestamp() {
        return timestamp;
    }


    public String getbattle() {
        return battle;
    }

    public String getfriend_username() {
        return friend_username;
    }

    public String getfriend_profile_picture() {
        return friend_profile_picture;
    }
    public String getfriend_points() {
        return friend_points;
    }

    public String getfriend_score(){

        return friend_score;
    }


}
