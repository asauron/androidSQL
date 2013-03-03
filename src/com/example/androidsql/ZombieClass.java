package com.example.androidsql;

public class ZombieClass {
	 
    //private variables
    int _id;
    String _name;
    String _message;
    String _location;
    
    // Empty constructor
    public ZombieClass(){
 
    }
    // constructor
    public ZombieClass(int id, String name, String location,String message){
        this._id = id;
        this._name = name;
        this._location = location;
        this._message = message;
    }
 
    // constructor
    public ZombieClass(String name, String location,String message){
        this._name = name;
        this._location = location;
        this._message = message;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
 
    // setting id
    public void setID(int id){
        this._id = id;
    }
 
    // getting name
    public String getName(){
        return this._name;
    }
 
    // setting name
    public void setName(String name){
        this._name = name;
    }
 
    // getting phone number
    public String getLocation(){
        return this._location;
    }
 
    // setting phone number
    public void setLocation(String location){
        this._location = location;
    }
    
    public String getMessage(){
    	return this._message; 
    }
    
    public void setMessage(String message){
    	this._message  = message;
    }
    
}