package com.example.android.yourchef;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckBox indian,thai,chinese,mexican,french;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    UserInfo user;
    TextView name_value,email_value,phone_value;
    private OnFragmentInteractionListener mListener;
    String uid;
    User_chef details;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myView = inflater.inflate(R.layout.fragment_blank, container, false);
        indian=myView.findViewById(R.id.indian_chkbox);


        french=myView.findViewById(R.id.french_chkbox);
        thai=myView.findViewById(R.id.thai_chkbox);
        mexican=myView.findViewById(R.id.mexican_chkbox);
        chinese=myView.findViewById(R.id.chinese_chkbox);
        name_value=(TextView)myView.findViewById(R.id.name_value);
        email_value=(TextView)myView.findViewById(R.id.email_value);
        phone_value=(TextView)myView.findViewById(R.id.phone_value);
        user=FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        //for toolbar





        databaseReference=FirebaseDatabase.getInstance().getReference("Users").child("chef").child(uid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                details=dataSnapshot.getValue(User_chef.class);
                name_value.setText(details.getFull_name());
                email_value.setText(details.getEmail());
                phone_value.setText(details.getMob_no());
                if(details.getChinese())
                {
                    chinese.setChecked(true);
                }
                if(details.getFrench())
                {
                    french.setChecked(true);
                }
                if(details.getIndian())
                {
                    indian.setChecked(true);
                }
                if(details.getMexican())
                {
                    mexican.setChecked(true);
                }
                if(details.getThai())
                {
                    thai.setChecked(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        indian.setOnClickListener(this);
        french.setOnClickListener(this);
        thai.setOnClickListener(this);
        mexican.setOnClickListener(this);
        chinese.setOnClickListener(this);

        return myView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    public void onClick(View view) {
        Toast.makeText(getActivity(),"Reached on click",Toast.LENGTH_LONG).show();
        firebaseDatabase=FirebaseDatabase.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();

        switch (view.getId())
        {
            case R.id.indian_chkbox:
                databaseReference=firebaseDatabase.getReference("Users").child("chef").child(uid).child("indian");
                if(indian.isChecked()){
                    databaseReference.setValue(true);
                }
                else {
                    databaseReference.setValue(false);
                }
            case R.id.chinese_chkbox:
                databaseReference=firebaseDatabase.getReference("Users").child("chef").child(uid).child("chinese");
                if(chinese.isChecked()){
                    databaseReference.setValue(true);
                }
                else {
                    databaseReference.setValue(false);
                }
            case R.id.thai_chkbox:
                databaseReference=firebaseDatabase.getReference("Users").child("chef").child(uid).child("thai");
                if(thai.isChecked()){
                    databaseReference.setValue(true);
                }
                else {
                    databaseReference.setValue(false);
                }
            case R.id.french_chkbox:
                databaseReference=firebaseDatabase.getReference("Users").child("chef").child(uid).child("french");
                if(french.isChecked()){
                    databaseReference.setValue(true);
                }
                else {
                    databaseReference.setValue(false);
                }
            case R.id.mexican_chkbox:
                databaseReference=firebaseDatabase.getReference("Users").child("chef").child(uid).child("mexican");
                if(mexican.isChecked()){
                    databaseReference.setValue(true);
                }
                else {
                    databaseReference.setValue(false);
                }


        }
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void set_values()
    {

    }

}
