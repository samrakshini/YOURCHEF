package com.example.android.yourchef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class splash_launch extends AppCompatActivity {

private ImageView logo;//your chef logo display

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_launch);
        logo=(ImageView)findViewById(R.id.logoimg);

        //setting animation to first launching screen
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.mytransition1);
        logo.startAnimation(myanim);
        final Intent i = new Intent(this,login_screen.class);
        Thread timer =new Thread()
        {
            public void run()
            {
                try {
                    sleep(3000);

                }
                catch(InterruptedException e){
                e.printStackTrace();
            }
            finally {
                    startActivity(i);
                    finish();

                }
            }
        };
        timer.start();

    }
}
