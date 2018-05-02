package com.example.android.yourchef;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Rate_chef.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Rate_chef#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Rate_chef extends Fragment {
    RatingBar ratingBar;
    Button rate_btn;
    TextView result;

    String uid;
    UserInfo user;
    Bundle extras;
    String chef_id;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private OnFragmentInteractionListener mListener;

    public Rate_chef() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Rate_chef.
     */
    // TODO: Rename and change types and number of parameters
    public static Rate_chef newInstance(String param1, String param2) {
        Rate_chef fragment = new Rate_chef();
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


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_rate_chef, container, false);
        ratingBar=(RatingBar)myView.findViewById (R.id.rating);
        rate_btn=(Button)myView.findViewById(R.id.rate_btn);
        result=(TextView) myView.findViewById(R.id.result);




        chef_id= extras.getString("chef_key");
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });
        rate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("Result is:"+ratingBar);

                firebaseDatabase=FirebaseDatabase.getInstance();
                FirebaseAuth.getInstance().getCurrentUser();
                user=FirebaseAuth.getInstance().getCurrentUser();
                uid=user.getUid();
                final DatabaseReference finalDatabaseReference =  firebaseDatabase.getReference("Users").child("chef")
                        .child(chef_id).child("Rates ");
                mAuth= FirebaseAuth.getInstance();

                finalDatabaseReference.push().setValue(ratingBar);


            }
        });
        // Inflate the layout for this fragment
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
}