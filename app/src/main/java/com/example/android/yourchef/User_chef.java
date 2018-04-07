package com.example.android.yourchef;

/**
 * Created by $4MR4K5h!N! on 4/4/2018.
 */

public class User_chef {
    String username,mob_no,password,full_name,email,type;
    Boolean indian,chinese,french,mexican,thai;

    public User_chef(String username,String mob_no,String password,String full_name,String email) {
        this.username=username;
        this.mob_no=mob_no;
        this.password=password;
        this.full_name=full_name;
        this.email=email;
        this.type="chef";
        this.chinese=false;
        this.indian=false;
        this.french=false;
        this.mexican=false;
        this.thai=false;
    }
}
