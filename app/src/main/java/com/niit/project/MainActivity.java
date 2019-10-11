package com.niit.project;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class  MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView1,recyclerView2;
    FoodsAdapter adapter;
    PopularFoodsAdapter popularFoodsAdapter;
    List<Foods> foodsList;
    List<PopularFoods> popularFoodsList;
    TextView greeting1,greeting2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);


        greeting1 = (TextView) findViewById(R.id.greeting);
        greeting2 = (TextView) findViewById(R.id.greeting2);
        isTime("00:00:00","12:00:00","12:00:00","4:00:00");


        popularFoodsList = new ArrayList<>();
        recyclerView1 = (RecyclerView) findViewById(R.id.rcv1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

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

        popularFoodsAdapter = new PopularFoodsAdapter(this,popularFoodsList);
        recyclerView1.setAdapter(popularFoodsAdapter);


        foodsList = new ArrayList<>();
        recyclerView2 = (RecyclerView) findViewById(R.id.rcv2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));


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

        adapter = new FoodsAdapter(this, foodsList);
        recyclerView2.setAdapter(adapter);
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

    @Override
    public void onClick(View v) {

    }
}
