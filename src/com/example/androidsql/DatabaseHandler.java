package com.example.androidsql;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "zombeeWatch";
 
    // Contacts table name
    private static final String TABLE_OBSERVATIONS= "zombieClass";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String COMMON_NAME = "name";
  
    private static final String KEY_Message = "message";
    private static final String IMAGE_NAME = "image";
   
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String SPECIES = "species";
    private static final String TIMESTAMP = "timestamp";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	String CREATE_OBSERVATION_TABLE = "CREATE TABLE " + TABLE_OBSERVATIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + COMMON_NAME + " TEXT,"
                + IMAGE_NAME + " TEXT," + KEY_Message + " TEXT," + LATITUDE + " TEXT," + LONGITUDE + " TEXT," + SPECIES + " TEXT," + TIMESTAMP + " TEXT " + ")";
        db.execSQL(CREATE_OBSERVATION_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OBSERVATIONS);
 
        // Create tables again
        onCreate(db);
    }
    
    // Drop the table
   /* public void droptable() {
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.execSQL("DROP TABLE IF EXISTS" + TABLE_CONTACTS);
    	
    }*/
    

    // Adding new contact
    public void addBee(ZombieClass observation) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(COMMON_NAME, observation.getName()); // Contact Name
        values.put(IMAGE_NAME, observation.getImageName()); // Contact Phone
        values.put(KEY_Message, observation.getMessage()); // Contact Phone
        values.put(LATITUDE, observation.getLatitude()); // Contact Phone
        values.put(LONGITUDE, observation.getLatitude()); // Contact Phone
      
        values.put(TIMESTAMP, observation.getTimeStamp()); // Contact Phone
 
        // Inserting Row
        db.insert(TABLE_OBSERVATIONS, null, values);
        db.close(); // Closing database connection
    }
    
    // Getting single contact
 
    public ZombieClass getObservation(int id) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	 
        Cursor cursor = db.query(TABLE_OBSERVATIONS, new String[] { KEY_ID,
                COMMON_NAME, IMAGE_NAME, KEY_Message, LATITUDE, LONGITUDE, SPECIES, TIMESTAMP }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        ZombieClass observation = new ZombieClass(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),cursor.getString(7));
        // return contact
        return observation;
    }

   
    // Getting All Contacts
    public List<ZombieClass> getAllObservations() {
       List<ZombieClass> observations = new ArrayList<ZombieClass>();
       // Select All Query
       String selectQuery = "SELECT  * FROM " + TABLE_OBSERVATIONS;
    
       SQLiteDatabase db = this.getWritableDatabase();
       Cursor cursor = db.rawQuery(selectQuery, null);
    
       // looping through all rows and adding to list
       if (cursor.moveToFirst()) {
           do {
               ZombieClass observation = new ZombieClass();
               observation.setId(Integer.parseInt(cursor.getString(0)));
               observation.setName(cursor.getString(1));
               observation.setImageName(cursor.getString(2));
               observation.setMessage(cursor.getString(3));
               observation.setLatitude(cursor.getString(4));
               observation.setLongitude(cursor.getString(5));
            
               observation.setTimeStamp(cursor.getString(7));
               // Adding contact to list
               observations.add(observation);
           } while (cursor.moveToNext());
       }

       // return contact list
       return observations;
   }
     
    
 // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_OBSERVATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }

 // Updating single contact
    public int updateObservation(ZombieClass observation) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(COMMON_NAME, observation.getName());
        values.put(IMAGE_NAME, observation.getImageName());
        values.put(KEY_Message, observation.getMessage()); // Contact Phone
        values.put(LATITUDE, observation.getLatitude()); // Contact Phone
        values.put(LONGITUDE, observation.getLongitude()); // Contact Phone
      
        values.put(TIMESTAMP, observation.getTimeStamp()); // Contact Phone
 
        // updating row
        return db.update(TABLE_OBSERVATIONS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(observation.getId()) });
    }
    // Deleting single contact
    public void deleteContact(ZombieClass observation) {
    	 SQLiteDatabase db = this.getWritableDatabase();
         db.delete(TABLE_OBSERVATIONS, KEY_ID + " = ?",
                 new String[] { String.valueOf(observation.getId()) });
         db.close();
    }
}

	