package com.example.android.yourchef;

/**
 * Created by $4MR4K5h!N! on 4/4/2018.
 */

public class User_chef {
    String username,mob_no,password,full_name,email,type;
    Boolean indian,chinese,french,mexican,thai;

    public User_chef()
    {
        //return this;
    }

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
    public Boolean getIndian()
    {
        return this.indian;
    }
    public Boolean getChinese()
    {
        return this.chinese;
    }
    public Boolean getFrench()
    {
        return this.french;
    }
    public Boolean getMexican()
    {
        return this.mexican;
    }
    public Boolean getThai()
    {
        return this.thai;
    }
}
