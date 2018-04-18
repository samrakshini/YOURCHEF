package com.example.android.yourchef;

/**
 * Created by $4MR4K5h!N! on 4/4/2018.
 */
public class User_client {
    String username,mob_no,password,full_name,email,type;


    public User_client()
    {
        //return this;
    }
    public User_client(String username,String mob_no,String password,String full_name,String email) {
        this.username = username;
        this.mob_no = mob_no;
        this.password = password;
        this.full_name = full_name;
        this.email = email;
        this.type = "client";
    }
    public String getFull_name()
    {
        return this.full_name;
    }

    public String getMob_no()
    {
        return this.mob_no;
    }

    public String getEmail()
    {
        return this.email;
    }

}
