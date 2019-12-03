package com.niit.project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.os.Looper.getMainLooper;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link home_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link home_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerView1,recyclerView2;
    FoodsAdapter adapter;
    PopularFoodsAdapter popularFoodsAdapter;
    List<Foods> foodsList;
    List<PopularFoods> popularFoodsList;
    TextView greeting1,greeting2;


    public home_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static home_fragment newInstance(String param1, String param2) {
        home_fragment fragment = new home_fragment();
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
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        greeting1 = (TextView) rootView.findViewById(R.id.greeting);
        greeting2 = (TextView) rootView.findViewById(R.id.greeting2);
        isTime("00:00:00","12:00:00","12:00:00","4:00:00");


        popularFoodsList = new ArrayList<>();
        recyclerView1 = (RecyclerView) rootView.findViewById(R.id.rcv1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        popularFoodsList.add(new PopularFoods(
                1,
                "Roasted chicken",
                4.9,
                R.drawable.barbecue
        ));
        popularFoodsList.add(new PopularFoods(
                2,
                "Frezzed Gizz",
                4.3,
                R.drawable.karim
        ));
        popularFoodsList.add(new PopularFoods(
                3,
                "Ogi",
                4.6,
                R.drawable.ogi
        ));
        popularFoodsList.add(new PopularFoods(
                4,
                "Roasted turkey",
                5.0,
                R.drawable.barbecue
        ));

        popularFoodsAdapter = new PopularFoodsAdapter(getContext(),popularFoodsList);
        recyclerView1.setAdapter(popularFoodsAdapter);


        foodsList = new ArrayList<>();
        recyclerView2 = (RecyclerView) rootView.findViewById(R.id.rcv2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));


        foodsList.add(new Foods(
                1,
                "Rice with chicken",
                "made with love",
                4.5,
                R.drawable.riceandchicken

        ));

        foodsList.add(new Foods(
                2,
                "Grilled steak",
                "made with love",
                4.2,
                R.drawable.grilledsteak

        ));

        foodsList.add(new Foods(
                3,
                "Pizza",
                "made with love",
                4.6,
                R.drawable.pizza

        ));

        foodsList.add(new Foods(
                4,
                "Sandwich",
                "made with love",
                4.9,
                R.drawable.sandwich

        ));
        foodsList.add(new Foods(
                5,
                "Sushi",
                "made with love",
                4.8,
                R.drawable.sushi

        ));

        adapter = new FoodsAdapter(getContext(), foodsList);
        recyclerView2.setAdapter(adapter);

        return rootView;
    }

    public void isTime(String valueToCheck, String morning, String afternoon, String evening) {
        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Calendar cal = Calendar.getInstance();
                int hourofday = cal.get(Calendar.HOUR_OF_DAY);
                if (hourofday <01) {
                    greeting1.setText("Good");
                    greeting2.setText("Morning");
                }else if(hourofday > 11 && hourofday < 16){
                    greeting1.setText("Good");
                    greeting2.setText("Afternoon");
                }else if(hourofday > 15){
                    greeting1.setText("Good");
                    greeting2.setText("Evening");
                }
                someHandler.postDelayed(this, 1000);
            }
        }, 10);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
