package com.example.android.yourchef;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.*;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.app.Notification;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;

public class cheforderActivity extends AppCompatActivity{
    String food_type;
    ListView listview;
   String chefuid,date1,time1;
    ArrayList<String> food;
    Button sendButton,datebutton,timebutton;
    boolean itemSelected;
    UserInfo user;
    String uid;
    Bundle extras;
    EditText idate,itime;
    CheckBox foodlist;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
   String chef_id;
String name;
    public cheforderActivity()
    {
        //return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheforder);
        listview = (ListView)findViewById(R.id.food_list);
        datebutton=(Button)findViewById(R.id.datebutton);
        timebutton=(Button)findViewById(R.id.timebutton);
        //string array

        itime=(EditText)findViewById(R.id.time);

        idate=(EditText)findViewById(R.id.cdate);
        sendButton = (Button) findViewById(R.id.send_order);

        sendButton.setEnabled(false);
        extras = getIntent().getExtras();
        chef_id= extras.getString("chef_key");
        food=new ArrayList<String>();

      timebutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               TimePickerDialog timepicker=new TimePickerDialog(cheforderActivity.this, new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        itime.setText(i+":"+i1);
                   }
               },mHour,mMinute, android.text.format.DateFormat.is24HourFormat(cheforderActivity.this));
               timepicker.show();
           }

       });
        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(cheforderActivity.this,"blabla",Toast.LENGTH_LONG).show();
                DatePickerDialog pickdate=new DatePickerDialog(cheforderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        idate.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                },mYear,mMonth,mDay);
                pickdate.show();
            }
        });
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Users").child("indian_food");
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.cheforderlistview,food);
        listview.setAdapter(arrayAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value=dataSnapshot.getValue(String.class);
                food.add(value);

                listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                arrayAdapter.notifyDataSetChanged();
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
        user=FirebaseAuth.getInstance().getCurrentUser();
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
                Toast.makeText(cheforderActivity.this, "Request Sent to chef!",
                        Toast.LENGTH_SHORT).show();
                // prepare intent which is triggered if the
// notification is selected
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
                Intent intent = new Intent(cheforderActivity.this, Dashboard_client.class);
                PendingIntent pending = PendingIntent.getActivity(cheforderActivity.this, 0, intent, 0);
                Notification noti = new Notification.Builder(cheforderActivity.this).setContentTitle("New Order from"+uid.toString()).setContentText(idate.getText().toString()).setSmallIcon(R.mipmap.ic_launcher).setContentIntent(pending).build();
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                noti.flags |= Notification.FLAG_AUTO_CANCEL;
                manager.notify(0, noti);



            }
        });

    }

}




