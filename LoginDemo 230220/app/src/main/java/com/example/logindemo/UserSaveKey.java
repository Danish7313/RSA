package com.example.logindemo;

public class UserSaveKey {

    private String id;
    private String name;

    public UserSaveKey(){

    }

    public UserSaveKey(String id, String name){
        this.id = id;
        this.name = name;
    }

    public UserSaveKey(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }



}
