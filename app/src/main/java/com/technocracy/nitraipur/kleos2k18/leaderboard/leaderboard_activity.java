package com.technocracy.nitraipur.kleos2k18.leaderboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.technocracy.nitraipur.kleos2k18.R;

import java.util.ArrayList;

public class leaderboard_activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);


        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        ArrayList<DataList> dataList = new ArrayList<DataList>();

        for (int i = 0; i < 5; i ++ ) {

            dataList.add(new DataList(

                    "Names",
                    R.drawable.n1,R.drawable.abc

            ));
        }

        mAdapter = new Adapter(dataList);
        mRecyclerView.setAdapter(mAdapter);

}}
