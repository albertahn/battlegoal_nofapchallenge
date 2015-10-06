package com.changeandsuccess.nofapchallenge.model;

/**
 * Created by albert on 8/25/14.
 */
public class CommentItem {

    private String
            members_index,
            profile_picture,
            username,
            comment_index,
            comment_text,
            reply_num,
            likes,timestamp;

    int reply_to, position;

    public CommentItem(
            String members_index,
            String profile_picture,
            String  username,
            String  comment_index,
            String   comment_text,
            int reply_to,
            String reply_num,
            String likes,
            String timestamp) {


        this.members_index = members_index;
        this.profile_picture = profile_picture;
        this.username = username;

       this.comment_index= comment_index;
       this.comment_text=   comment_text;
        this.reply_to=reply_to;
        this.reply_num= reply_num;
        this.likes = likes;
        this.timestamp= timestamp;

    }// end loginitem


    public String getmembers_index() {

        return members_index;
    }


    public String getprofile_picture() {

        return profile_picture;
    }




    public String getusername() {

        return username;
    }



    public String getcomment_index() {

        return comment_index;
    }



    public String getcomment_text() {

        return comment_text;
    }

    public int getreply_to() {

        return reply_to;
    }


    public String getreply_num(){

        return reply_num;
    }

    public int getposition(){
        return position;
    }

    public String getLikes(){

        return likes;
    }

    public String getTimeStamp(){

        return timestamp;
    }
}
