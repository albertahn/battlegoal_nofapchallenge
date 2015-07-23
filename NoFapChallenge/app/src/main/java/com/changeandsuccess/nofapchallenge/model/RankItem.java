package com.changeandsuccess.nofapchallenge.model;

/**
 * Created by albert on 10/15/14.
 */
public class RankItem {

    String level, count;

    public RankItem(String level, String count){


       this.level=level;
        this.count = count;


    }//

    public String getLevel(){


return  level;

    }

    public String getCount(){

        return count;
    }
}
