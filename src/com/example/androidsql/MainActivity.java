package com.example.androidsql;
import java.util.List;
 
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.view.Menu;

public class MainActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        DatabaseHandler db = new DatabaseHandler(this);
 
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
      //  db.addBee(new ZombieClass("Bee1", "/mnt/sdcard/existing.jpg", "Found in Golden Gate Park"));
        //db.addBee(new ZombieClass("Bee2", "Found in Golden Gate Park2", "/mnt/sdcard/existing.jpg"));
        //db.addBee(new ZombieClass("Bee3", "Found in Golden Gate Park3", "/mnt/sdcard/existing.jpg"));

        
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<ZombieClass> contacts = db.getAllObservations();       
 
        for (ZombieClass cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Location: " + cn.getLocation();
                // Writing Contacts to log
        Log.d("Name: ", log);
    }
    }
}