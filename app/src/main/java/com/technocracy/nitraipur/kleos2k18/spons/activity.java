package com.technocracy.nitraipur.kleos2k18.spons;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.technocracy.nitraipur.kleos2k18.R;

import java.util.ArrayList;

public class activity extends AppCompatActivity {
RecyclerView rv;
RecyclerView.Adapter adapter;
RecyclerView.LayoutManager lm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsors_rv);
        rv=(RecyclerView)findViewById(R.id.spons_rv);
       rv.setHasFixedSize(false);
        lm=new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        ArrayList<Datalist> list=new ArrayList<Datalist>();
        list.add(new Datalist(R.drawable.abc));
        adapter=new Adapter(list);
        rv.setAdapter(adapter);


    }
}
