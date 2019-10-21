package com.example.labor3;

public class HobbyList {
    public static final String TABLE_NAME = "hobbies";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_HOBBY = "hobby";
    public static final String COLUMN_NAME = "name";

    private int id;
    private String hobby;
    private String name;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_HOBBY + " TEXT,"
                    + COLUMN_NAME + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";
    public HobbyList(){

    }
    public HobbyList(int id, String hobby, String name){
        this.id = id;
        this.hobby = hobby;
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getHobby() {
        return hobby;
    }

    public String getName() {
        return name;
    }


}
