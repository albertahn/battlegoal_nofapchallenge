package com.changeandsuccess.nofapchallenge.model;

public class LoginItem {
	
	private String user_index, username, email, password, profile_picture, FID, level;
	
	
	public LoginItem(
            String user_index,
            String username,
            String email,
            String password,
            String profile_picture,
            String FID,
            String level){


		this.user_index= user_index;
		this.username = username;
		this.email = email;
		this.password = password;
		this.profile_picture = profile_picture;
		this.FID = FID;
		this.level = level;
		
	}// end loginitem
	
	
	public CharSequence getUsername(){
		
		return username;
	}

    public String getProfile_picture(){

        return profile_picture;


    }


    public String getUser_index(){

       return user_index;
    }

    public  String getLevel(){

        return level;
    }

}
