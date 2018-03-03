package com.example.android.yourchef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class login_screen extends AppCompatActivity {
    TextView register_here;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    register_here=(TextView)findViewById(R.id.reg_here);


    }

    public void open_register_here(View view)
    {
        final Intent s = new Intent(this,Register_here.class);
        startActivity(s);
    }
}
