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

public class login_screen extends AppCompatActivity {
    TextView register_here;
    EditText username,password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    register_here=(TextView)findViewById(R.id.reg_here);
    username=(EditText)findViewById(R.id.username);
    password=(EditText)findViewById(R.id.password);
    mAuth = FirebaseAuth.getInstance();

    }

    public void open_register_here(View view)
    {
        startSignIn();
        final Intent s = new Intent(this,Register_here.class);
        startActivity(s);
    }

    public  void startSignIn()
    {
        String username_name=username.getText().toString();
        String password_password=password.getText().toString();
        mAuth.signInWithEmailAndPassword(username_name,password_password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    final Intent s = new Intent(login_screen.this,Register_here.class);
                    startActivity(s);
                    //updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.getException());
                    Toast.makeText(login_screen.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }

                // ...
            }
        });

    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
}
