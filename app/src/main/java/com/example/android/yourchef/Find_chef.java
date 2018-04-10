package com.example.android.yourchef;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Find_chef.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Find_chef#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Find_chef extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    ListView listView;
    ArrayList<String> al;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View myView;
    ArrayAdapter<String> ad;
    TextView t_indian;

    private OnFragmentInteractionListener mListener;

    public Find_chef() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Find_chef.
     */
    // TODO: Rename and change types and number of parameters
    public static Find_chef newInstance(String param1, String param2) {
        Find_chef fragment = new Find_chef();
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
        myView = inflater.inflate(R.layout.fragment_find_chef, container, false);
        t_indian=(TextView)myView.findViewById(R.id.find_indian);
        t_indian.setOnClickListener(this);
        listView=(ListView)myView.findViewById(R.id.indian_list);
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

    @Override
    public void onClick(View view) {
        //Toast.makeText(getActivity(),"Reached on click",Toast.LENGTH_LONG).show();
        al=new ArrayList<String>();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Users");
        if(view==t_indian) {
            Query query = databaseReference.orderByChild("indian").equalTo(true);

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    al.clear();
                    for (DataSnapshot zoneSnapshot : dataSnapshot.getChildren()) {
                        User_chef value = zoneSnapshot.getValue(User_chef.class);
                        //Log.d("check",value.getFull_name());
                        al.add(value.getFull_name());
                    }
                    ad = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1, al);
                    listView.setAdapter(ad);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
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
}
