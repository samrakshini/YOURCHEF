package com.example.android.yourchef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Register_here extends AppCompatActivity {
    TextView chef,client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_here);
   chef=(TextView)findViewById(R.id.sub_chef);
   client=(TextView)findViewById(R.id.sub_client);

    }
    public void chef_reg(View view)
    {
        final Intent ch = new Intent(this,Chef_pane.class);
        startActivity(ch);

    }
    public void client_reg(View view)
    {
        final Intent cl = new Intent(this,reg_as_client.class);
        startActivity(cl);
    }
}
