package com.changeandsuccess.nofapchallenge.comment_stuff;

/**
 * Created by tanggames on 2015-10-07.
 */
public class ReplyItem {
    String membersIndex;
    String userName;
    String bodyText;
    String portraitName;
    int reply_to;

    public ReplyItem( String membersIndex, String userName,String bodyText, String portraitName, int reply_to) {
        this.membersIndex =membersIndex;
        this.bodyText = bodyText;
        this.portraitName = portraitName;
        this.userName = userName;
        this.reply_to = reply_to;
    }
    public ReplyItem() {}

    public int get_reply_to() {
        return reply_to;
    }

    public String getMembersIndex() {
        return membersIndex;
    }

    public void setMembersIndex(String membersIndex) {
        this.membersIndex = membersIndex;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getPortraitName() {
        return portraitName;
    }

    public void setPortraitName(String portraitName) {
        this.portraitName = portraitName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
