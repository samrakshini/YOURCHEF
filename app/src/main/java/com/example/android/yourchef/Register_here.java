package com.example.android.yourchef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Register_here extends AppCompatActivity {
    TextView chef,client;
    EditText username,mobile_no,password,full_name,email;
    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_here);
   chef=(TextView)findViewById(R.id.sub_chef);
   client=(TextView)findViewById(R.id.sub_client);
   databaseReference= FirebaseDatabase.getInstance().getReference();
   username=(EditText)findViewById(R.id.username);
   mobile_no=(EditText)findViewById(R.id.mob_no);
   password=(EditText)findViewById(R.id.password);
   full_name=(EditText)findViewById(R.id.full_name);
   email=(EditText)findViewById(R.id.email_id);
   firebaseDatabase=FirebaseDatabase.getInstance();
   databaseReference=firebaseDatabase.getReference();


    }
    public void chef_reg(View view)
    {
        User_chef user_chef=new User_chef(username.getText().toString(),mobile_no.getText().toString(),password.getText().toString(),full_name.getText().toString(),email.getText().toString());
        final Intent ch = new Intent(this,Chef_pane.class);
        startActivity(ch);

    }
    public void client_reg(View view)
    {
        final Intent cl = new Intent(this,Client_pane.class);
        startActivity(cl);
    }
}
