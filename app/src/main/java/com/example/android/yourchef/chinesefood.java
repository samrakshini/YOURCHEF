package com.example.android.yourchef;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chinesefood extends AppCompatActivity{
    String food_type;
    ListView listview;
    String chefuid;
    ArrayList<String> food;
    Button sendButton;
    boolean itemSelected;
    EditText itime,idate;
    private int mYear, mMonth, mDay, mHour, mMinute;
    UserInfo user;
    String uid;
    Bundle extras;
    CheckBox foodlist;
    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    String chef_id;
String name;
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

        Button datebutton=(Button)findViewById(R.id.datebutton);
        Button timebutton=(Button)findViewById(R.id.timebutton);

        timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timepicker=new TimePickerDialog(chinesefood.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker timePicker, int i, int i1) {
                        itime.setText(i+":"+i1);
                    }
                },mHour,mMinute, android.text.format.DateFormat.is24HourFormat(chinesefood.this));
                timepicker.show();
            }

        });
        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(cheforderActivity.this,"blabla",Toast.LENGTH_LONG).show();
                DatePickerDialog pickdate=new DatePickerDialog(chinesefood.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker datePicker, int i, int i1, int i2) {
                        idate.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                },mYear,mMonth,mDay);
                pickdate.show();
            }
        });
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
        final DatabaseReference cldata =  firebaseDatabase.getReference("Users").child("client").child(uid);
        final DatabaseReference finalDatabaseReference =  firebaseDatabase.getReference("Users").child("chef").child(chef_id).child("orders");
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date=idate.getText().toString().trim();
                String time=itime.getText().toString().trim();
                mAuth= FirebaseAuth.getInstance();
                orderlist user_chef=new orderlist(idate.getText().toString(),itime.getText().toString(),uid);
                finalDatabaseReference.push().setValue(user_chef);
                Toast.makeText(chinesefood.this, "Request Sent to chef!",
                        Toast.LENGTH_SHORT).show();
                cldata.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        User_client cl  = dataSnapshot.getValue(User_client.class);
                        //Log.d("check",value.getFull_name());
                        name =cl.getFull_name();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Intent intent = new Intent(chinesefood.this, Dashboard_client.class);
                PendingIntent pending = PendingIntent.getActivity(chinesefood.this, 0, intent, 0);
                android.app.Notification noti = new android.app.Notification.Builder(chinesefood.this).setContentTitle("New Order from"+uid.toString()).setContentText(idate.getText().toString()).setSmallIcon(R.mipmap.ic_launcher).setContentIntent(pending).build();
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                noti.flags |= Notification.FLAG_AUTO_CANCEL;
                manager.notify(0, noti);


            }
        });
    }
}
