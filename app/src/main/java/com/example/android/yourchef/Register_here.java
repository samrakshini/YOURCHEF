package com.example.android.yourchef;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Register_here extends AppCompatActivity {
    TextView chef,client;
    EditText username,mobile_no,password,full_name,email;
    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
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
   email=(EditText)findViewById(R.id.email_id1);
   firebaseDatabase=FirebaseDatabase.getInstance();
   databaseReference=firebaseDatabase.getReference("Users");
    mAuth=FirebaseAuth.getInstance();

    }
    public void chef_reg(View view)
    {

        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register_here.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
        User_chef user_chef=new User_chef(username.getText().toString(),mobile_no.getText().toString(),password.getText().toString(),full_name.getText().toString(),email.getText().toString());
        databaseReference.child("").setValue(user_chef);

        Toast.makeText(Register_here.this,"Registered successfully as chef",Toast.LENGTH_SHORT);
        final Intent ch = new Intent(this,Chef_pane.class);
        startActivity(ch);

    }
    public void client_reg(View view)
    {
        User_client user_client=new User_client(username.getText().toString(),mobile_no.getText().toString(),password.getText().toString(),full_name.getText().toString(),email.getText().toString());
        databaseReference.child(user_client.username).setValue(user_client);
        Toast.makeText(Register_here.this,"Registered successfully as client",Toast.LENGTH_SHORT);
        final Intent cl = new Intent(this,Client_pane.class);
        startActivity(cl);
    }
}
