package com.changeandsuccess.nofapchallenge.model;

/**
 * Created by albert on 7/21/14.
 */
public class MessageTabItem {


    private String messages_index,
            messages_body,
            has_message_index,
            members_index,
            profile_picture,
            username,
            timestamp,
            other_person_pic,
            other_person_name,
            other_person_index;


    public MessageTabItem(
            String messages_index,
            String messages_body,
            String has_message_index,
            String members_index,
            String profile_picture,
            String username,
            String timestamp,
            String other_person_pic,
            String other_person_name,
            String other_person_index) {


        this.messages_index = messages_index;
        this.messages_body = messages_body;
        this.has_message_index = has_message_index;
        this.other_person_name = other_person_name;
        this.other_person_pic = other_person_pic;

        this.profile_picture = profile_picture;
        this.username = username;

        this.members_index = members_index;
        this. other_person_index= other_person_index;

        this.timestamp =timestamp;



    }// end loginitem


    public String getmembers_index(){

        return members_index;
    }



    public String getmessages_body() {
        return messages_body;
    }

    public String getuser_name(){
       return username;

    }

    //message profile pic

    public String getprofile_picture(){

       return profile_picture;

    }

    public String gethas_message_index() {

        return has_message_index;
    }

    public String getother_person_name() {

        return other_person_name;
    }

    public String getother_person_pic() {

        return other_person_pic;
    }

    public String getother_person_index(){

       return  other_person_index;
    }

    public String get_timestamp(){

        return timestamp;
    }

}