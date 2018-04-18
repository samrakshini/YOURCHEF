package com.example.android.yourchef;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class chinesefood extends AppCompatActivity {
    String food_type;
    ListView listview;
    String chefuid;
    ArrayList<String> food;
    Button sendButton;
    boolean itemSelected;
    EditText itime,idate;
    UserInfo user;
    String uid;
    Bundle extras;
    CheckBox foodlist;
    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    String chef_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheforder);
        listview = (ListView)findViewById(R.id.food_list);
        //string array
        sendButton = (Button) findViewById(R.id.send_order);
        sendButton.setEnabled(false);
        itime=(EditText)findViewById(R.id.time);


        idate=(EditText)findViewById(R.id.cdate);

        extras = getIntent().getExtras();
        chef_id= extras.getString("chef_key");
        food=new ArrayList<String>();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Users").child("chinese_food");
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.cheforderlistview,food);
        listview.setAdapter(arrayAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value=dataSnapshot.getValue(String.class);
                food.add(value);
                arrayAdapter.notifyDataSetChanged();
                listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

                listview.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent,
                                                    View view, int position, long id) {

                                itemSelected = true;
                                sendButton.setEnabled(true);
                            }
                        });

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        firebaseDatabase=FirebaseDatabase.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        final DatabaseReference finalDatabaseReference =  firebaseDatabase.getReference("Users").child("chef").child(chef_id).child("orders");
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date=idate.getText().toString().trim();
                String time=itime.getText().toString().trim();
                mAuth= FirebaseAuth.getInstance();
                orderlist user_chef=new orderlist(idate.getText().toString(),itime.getText().toString());
                finalDatabaseReference.push().setValue(user_chef);
                Toast.makeText(chinesefood.this, "Request Sent to chef!",
                        Toast.LENGTH_SHORT).show();


            }
        });
    }
}
