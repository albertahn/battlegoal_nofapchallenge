package com.changeandsuccess.nofapchallenge.model;

/**
 * Created by albert on 6/22/14.
 */
public class FeaturedItem {


    private String membersIndex;
    private String profilePicture;
    private String publish;
    private String username;
    private String blurb;
    private String category;
    private String followNum;




    public FeaturedItem(String members_index, String profile_picture, String publish, String username, String blurb, String category, String follow_num) {
        super();

        this.membersIndex = members_index;
         this.profilePicture= profile_picture;
        this.publish= publish;
        this.username =username;
        this.blurb=blurb;
        this.category=category;
        this.followNum=follow_num;

    }
    // getters and setters...

    public CharSequence getmembersIndex() {

        // TODO Auto-generated method stub
        return membersIndex;
    }


    public CharSequence getprofilePicture() {

        return profilePicture;

    }


    public CharSequence getpublish() {

        return publish;

    }


        public CharSequence getusername() {
            return username;
        }



            public CharSequence getblurb() {
                return blurb;
            }

                public CharSequence getcategory() {

                    return category;
                }
                    public CharSequence getfollowNum() {
                     return followNum;
                    }





 }
