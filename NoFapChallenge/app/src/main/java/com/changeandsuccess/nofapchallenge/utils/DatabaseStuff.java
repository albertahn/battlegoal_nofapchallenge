package com.changeandsuccess.nofapchallenge.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseStuff {
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_DAY = "day";
	public static final String KEY_NOTE = "note";
    public static final String KEY_DATE = "date";
	
	//db stuff 
	
	private static final String DATABASE_NAME = "NoFapDb";
	private static final String DATABASE_TABLE ="progressTable";
	private static final int DATABASE_VERSION = 4;
	
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
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + 
			KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_DAY + " TEXT NOT NULL, " +
                    KEY_NOTE + " TEXT NOT NULL, " +
                    KEY_DATE+ " TEXT NOT NULL);"
					
					);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			//INSERT STUFF FOR FIRST TIME 
			
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
			
		}
	}
	//init context
	
	public DatabaseStuff(Context c){
		ourContext = c;
	}
	//	initialize open database
		public DatabaseStuff open() throws SQLException {
			
			ourHelper =  new DbHelper(ourContext);
			ourDatabase = ourHelper.getWritableDatabase();
			return this;
			
		}
		
//close database
		public void close(){
			ourHelper.close();
		}
		
//insert data
		public long createEntry(String day, String note, String date){
			//write to database and close db
			
			ContentValues cv = new ContentValues();
			cv.put(KEY_DAY, day);
			cv.put(KEY_NOTE, note);
            cv.put(KEY_DATE, date);
			return ourDatabase.insert(DATABASE_TABLE, null, cv);
			
		}
		
//get all the database data with string array		
		public String[][] getData() {
			// get all the data from database
			
			String[] columns = new String[] {KEY_ROWID, KEY_DAY, KEY_NOTE, KEY_DATE};
			Cursor c = ourDatabase.query( DATABASE_TABLE, columns, null, null, null, null, KEY_ROWID+" DESC");
			
			
			int iRow = c.getColumnIndex(KEY_ROWID);
			int iDay = c.getColumnIndex(KEY_DAY);
			int iNote = c.getColumnIndex(KEY_NOTE);
            int iDate = c.getColumnIndex(KEY_DATE);
			
			String[][] result = null;
			result= new String[c.getCount()][4];
			
			if(c.moveToFirst()){

				 for (int i =0; i<c.getCount(); i++){


					 result[i][0] =c.getString(iRow);
                     result[i][1] =c.getString(iDay);
                     result[i][2] =c.getString(iNote);
                     result[i][3] =c.getString(iDate);



					  //" "+c.getString(iDay) + "            " + c.getString(iNote) + "    "+ c.getString(iDate) ;
					c.moveToNext(); 
				 }
			}// end if move to first
			
			return result;
		}
		
	//get the last dayfor main
		public String getLastDay() {
			
			String[] columns = new String[] {KEY_ROWID, KEY_DAY, KEY_NOTE, KEY_DATE};
			Cursor c = ourDatabase.query( DATABASE_TABLE, columns, null, null, null, null, null);
			
			int iRow =  c.getColumnIndex(KEY_ROWID);
			int iDay= c.getColumnIndex(KEY_DAY);
			int iNote= c.getColumnIndex(KEY_NOTE);
            int iDate = c.getColumnIndex(KEY_DATE);
			
			String result = "";
			
			if(c.moveToLast()){
				result= c.getString(iDay);
			}
			
			return result;
		}


    public String getFinalDate() {

        String[] columns = new String[] {KEY_ROWID, KEY_DAY, KEY_NOTE, KEY_DATE};
        Cursor c = ourDatabase.query( DATABASE_TABLE, columns, null, null, null, null, null);

        int iRow =  c.getColumnIndex(KEY_ROWID);
        int iDay= c.getColumnIndex(KEY_DAY);
        int iNote= c.getColumnIndex(KEY_NOTE);
        int iDate = c.getColumnIndex(KEY_DATE);

        String result = "";

        if(c.moveToLast()){
            result= c.getString(iDate);
        }

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


    public String getLastRestart(){

        String[] columns = new String[] {KEY_ROWID, KEY_DAY, KEY_NOTE, KEY_DATE};

        Cursor lastrestart = ourDatabase.query(DATABASE_TABLE, columns, KEY_DAY+"=" + "'1'", null, null, null, KEY_ROWID+" DESC");
        String result= "";

        int iRow = lastrestart.getColumnIndex(KEY_ROWID);
        int iDay = lastrestart.getColumnIndex(KEY_DAY);
        int iNote = lastrestart.getColumnIndex(KEY_NOTE);
        int iDate = lastrestart.getColumnIndex(KEY_DATE);


        // end if move to first

        if(lastrestart.moveToLast()){

            result = lastrestart.getString(iDate);

        }

        if(result==null){

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date nowis = new Date();
            String date =  sdf.format(nowis);

            return date;

        }else{

            return result;
        }




    }
	
}
