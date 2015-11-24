package com.changeandsuccess.nofapchallenge.comment_stuff.comment_sqlite;

import android.app.Activity;
import android.app.Dialog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by albertan on 10/30/15.
 */
public class LanguageAll_db {


    public static final String KEY_ROWID = "_id";
    public static final String KEY_MEMBERSINDEX = "members_index";
    public static final String KEY_PROFILE_PICTURE = "profile_picture";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_LEVEL = "level";

    public static final String KEY_COMMENT_INDEX = "comment_index";
    public static final String KEY_COMMENT_TEXT = "comment_text";

    public static final String KEY_COURSES_INDEX = "courses_index";
    public static final String KEY_COMMENT_PICTURE = "comment_picture";

    public static final String KEY_REPLY_NUM = "reply_num";
    public static final String KEY_LIKES = "likes";
    public static final String KEY_NEWS_TYPE = "news_type";
    public static final String KEY_COURSES_NAME = "courses_name";
    public static final String KEY_COURSE_PRIVACY = "course_privacy";
    public static final String KEY_TIMESTAMP = "timestamp";
    public static final String KEY_REPLY_TO = "reply_to";





    //db stuff

    private static final String DATABASE_NAME = "commentsDatabase";
    private static final String DATABASE_TABLE ="commentsTable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // make the database

           /* db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            KEY_DAY + " TEXT NOT NULL, " +
                            KEY_NOTE + " TEXT NOT NULL, " +
                            KEY_DATE+ " TEXT NOT NULL);"

            );*/
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
             KEY_ROWID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
             KEY_MEMBERSINDEX + " TEXT NOT NULL, " +
             KEY_PROFILE_PICTURE+ " TEXT NOT NULL, " +
             KEY_USERNAME+ " TEXT NOT NULL, " +
             KEY_LEVEL+ " TEXT NOT NULL, " +

             KEY_COMMENT_INDEX+ " TEXT NOT NULL, " +
             KEY_COMMENT_TEXT+ " TEXT NOT NULL, " +

             KEY_COURSES_INDEX+ " TEXT NOT NULL, " +
             KEY_COMMENT_PICTURE+ " TEXT NOT NULL, " +

             KEY_REPLY_NUM+ " TEXT NOT NULL, " +
             KEY_LIKES+ " TEXT NOT NULL, " +
             KEY_NEWS_TYPE+ " TEXT NOT NULL, " +
             KEY_COURSES_NAME+ " TEXT NOT NULL, " +
             KEY_COURSE_PRIVACY+ " TEXT NOT NULL, " +
             KEY_TIMESTAMP+ " TEXT NOT NULL, " +
             KEY_REPLY_TO+ " TEXT NOT NULL);"
             );



        }// oncreate

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //INSERT STUFF FOR FIRST TIME

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);

        }
    }



    //init context

    public LanguageAll_db(Context c){
        ourContext = c;
    }
    //	initialize open database
    public LanguageAll_db open() throws SQLException {

        ourHelper =  new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;

    }

    //close database
    public void close(){
        ourHelper.close();
    }

    /*//insert data
    public long createEntry(String day,
                            String note,
                            String date


                            ){
        //write to database and close db

        ContentValues cv = new ContentValues();
        cv.put(KEY_DAY, day);
        cv.put(KEY_NOTE, note);
        cv.put(KEY_DATE, date);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);

    }*/

    public long createEntry(String MEMBERSINDEX,
                            String      PROFILE_PICTURE,
                            String USERNAME,
                            String  LEVEL,

                            String  COMMENT_INDEX,
                            String  COMMENT_TEXT,

                            String  COURSES_INDEX,
                            String  COMMENT_PICTURE,
                            String  REPLY_NUM,
                            String  LIKES,
                            String  NEWS_TYPE,
                            String  COURSES_NAME,
                            String  COURSE_PRIVACY,
                            String  TIMESTAMP,
                            String REPLY_TO

    ){
        //write to database and close db

        ContentValues cv = new ContentValues();
       // cv.put(KEY_DAY, day);


                cv.put(KEY_MEMBERSINDEX ,MEMBERSINDEX);
                        cv.put (KEY_PROFILE_PICTURE ,PROFILE_PICTURE);
                                cv.put  (KEY_USERNAME ,USERNAME);
                                        cv.put  (KEY_LEVEL ,LEVEL);

                cv.put ( KEY_COMMENT_INDEX ,COMMENT_INDEX);
                cv.put  (KEY_COMMENT_TEXT ,COMMENT_TEXT );

                cv.put  ( KEY_COURSES_INDEX ,COURSES_INDEX);
                cv.put(KEY_COMMENT_PICTURE ,COMMENT_PICTURE);

                cv.put(KEY_REPLY_NUM ,REPLY_NUM );
                cv.put (KEY_LIKES ,LIKES);
                cv.put ( KEY_NEWS_TYPE ,NEWS_TYPE);
                cv.put ( KEY_COURSES_NAME, COURSES_NAME);
                cv.put ( KEY_COURSE_PRIVACY, COURSE_PRIVACY);
                cv.put ( KEY_TIMESTAMP, TIMESTAMP);
                cv.put ( KEY_REPLY_TO, REPLY_TO);


        return ourDatabase.insert(DATABASE_TABLE, null, cv);

    }



    //get all the database data with string array
    public String[][] getCourseComments(String courses_index) {
        // get all the data from database

        String[] columns = new String[] {
                         KEY_ROWID,
                        KEY_MEMBERSINDEX ,
                        KEY_PROFILE_PICTURE,
                        KEY_USERNAME,
                        KEY_LEVEL,

                        KEY_COMMENT_INDEX,
                        KEY_COMMENT_TEXT,

                        KEY_COURSES_INDEX,
                        KEY_COMMENT_PICTURE,

                        KEY_REPLY_NUM,
                        KEY_LIKES,
                        KEY_NEWS_TYPE,
                        KEY_COURSES_NAME,
                        KEY_COURSE_PRIVACY,
                        KEY_TIMESTAMP,
                        KEY_REPLY_TO
        };


        //Cursor c = ourDatabase.query( DATABASE_TABLE, columns, null, null, null, null, KEY_ROWID+" DESC");
        Cursor c = ourDatabase.query( DATABASE_TABLE, columns, "courses_index="+courses_index, null, null, null, KEY_ROWID+" DESC");

        int iRow = c.getColumnIndex(KEY_ROWID);

        int iMEMBERSINDEX = c.getColumnIndex(KEY_MEMBERSINDEX);
        int iPROFILE_PICTURE = c.getColumnIndex(KEY_PROFILE_PICTURE);
        int iUSERNAME = c.getColumnIndex(KEY_USERNAME);
        int iLEVEL = c.getColumnIndex(KEY_LEVEL);
        int iCOMMENT_INDEX = c.getColumnIndex(KEY_COMMENT_INDEX);
        int iCOMMENT_TEXT = c.getColumnIndex(KEY_COMMENT_TEXT);
        int iCOURSES_INDEX = c.getColumnIndex(KEY_COURSES_INDEX);



        int iCOMMENT_PICTURE = c.getColumnIndex(KEY_COMMENT_PICTURE);
        int iREPLY_NUM = c.getColumnIndex(KEY_REPLY_NUM);
        int iLIKES = c.getColumnIndex(KEY_LIKES);


        int iNEWS_TYPE = c.getColumnIndex(KEY_NEWS_TYPE);
        int iCOURSES_NAME = c.getColumnIndex(KEY_COURSES_NAME);
        int iCOURSE_PRIVACY = c.getColumnIndex(KEY_COURSE_PRIVACY);
        int iTIMESTAMP = c.getColumnIndex(KEY_TIMESTAMP);
        int iREPLY_TO = c.getColumnIndex(KEY_REPLY_TO);

        String[][] result = null;
        result= new String[c.getCount()][16];

        if(c.moveToFirst()){

            for (int i =0; i<c.getCount(); i++){

                result[i][0] =c.getString(iRow);
                result[i][1] =c.getString(iMEMBERSINDEX);
                result[i][2] =c.getString(iPROFILE_PICTURE);
                result[i][3] =c.getString(iUSERNAME);

                result[i][4] =c.getString(iLEVEL);
                result[i][5] =c.getString(iCOMMENT_INDEX);
                result[i][6] =c.getString(iCOMMENT_TEXT );
                result[i][7] =c.getString(iCOURSES_INDEX);
                result[i][8] =c.getString(iCOMMENT_PICTURE);
                result[i][9] =c.getString(iREPLY_NUM);

                result[i][10] =c.getString(iLIKES);
                result[i][11] =c.getString(iNEWS_TYPE);
                result[i][12] =c.getString(iCOURSES_NAME);
                result[i][13] =c.getString(iCOURSE_PRIVACY);
                result[i][14] =c.getString(iTIMESTAMP);
                result[i][15] =c.getString(iREPLY_TO);

                //" "+c.getString(iDay) + "            " + c.getString(iNote) + "    "+ c.getString(iDate) ;
                c.moveToNext();
            }//for
        }// end if move to first

        return result;
    }




    //delete all the data in database
    public void deleteAllData() {
        // TODO Auto-generated method stub
        ourDatabase.delete(DATABASE_TABLE, null, null);

    }

    //TODO SELECT day FROM TABLE WHERE ID = (SELECT MAX(ID) FROM TABLE); get last day

    public void deleteFromID(String rowid){

        String table = "progressTable";
        String whereClause = "_id"+"=?";
        String[]whereArgs = new String[] {String.valueOf(rowid)};
        ourDatabase.delete(table, whereClause , whereArgs);


    }//delete


}
