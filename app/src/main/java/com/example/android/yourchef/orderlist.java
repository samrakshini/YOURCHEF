package com.example.android.yourchef;


public class orderlist {
    String date,time,client_uid;
   boolean d1,d2,d3;
    public orderlist()
    {
        //return this;
    }

    public orderlist(String d,String t,String clientuid) {
        this.date=d;
        this.client_uid=clientuid;
        this.time=t;
        this.d1=false;
        this.d2=false;
        this.d3=false;

    }

    public String getdate()
    {
        return this.date;
    }
    public String gettime()
    {
        return this.time;
    }
    public String getclient_uid(){return this.client_uid;}
    public Boolean getdone()
    {
        return this.d1;
    }
    public Boolean getdtwo()
    {
        return this.d2;
    }
    public Boolean getdthree()
    {
        return this.d3;
    }

}
