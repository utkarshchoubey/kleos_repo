package com.technocracy.nitraipur.kleos2k18.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.technocracy.nitraipur.kleos2k18.R;

import java.util.Random;

public class question6_dialer extends Activity  implements View.OnClickListener{
    private static Random rno=new Random();
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10;
    private Button B1,B2,B3,B4,B5,B6,B7,B8,B9,B10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qn6);
        tv1 = (TextView) findViewById(R.id.tv1);
        B1 = (Button) findViewById(R.id.B1);
        tv2 = (TextView) findViewById(R.id.tv2);B2 = (Button) findViewById(R.id.B2);
        tv3 = (TextView) findViewById(R.id.tv3);
        B3 = (Button) findViewById(R.id.B3); tv4 = (TextView) findViewById(R.id.tv4);
        B4 = (Button) findViewById(R.id.B4); tv5 = (TextView) findViewById(R.id.tv5);
        B5 = (Button) findViewById(R.id.B5); tv6 = (TextView) findViewById(R.id.tv6);
        B6 = (Button) findViewById(R.id.B6); tv7 = (TextView) findViewById(R.id.tv7);
        B7 = (Button) findViewById(R.id.B7); tv8 = (TextView) findViewById(R.id.tv8);
        B8 = (Button) findViewById(R.id.B8); tv9 = (TextView) findViewById(R.id.tv9);
        B9 = (Button) findViewById(R.id.B9); tv10 = (TextView) findViewById(R.id.tv10);
        B10 = (Button) findViewById(R.id.B10);
        B1.setOnClickListener(this);
        B2.setOnClickListener(this);
        B3.setOnClickListener(this);
        B4.setOnClickListener(this);
        B5.setOnClickListener(this);
        B6.setOnClickListener(this);
        B7.setOnClickListener(this);
        B8.setOnClickListener(this);
        B9.setOnClickListener(this);
        B10.setOnClickListener(this);

    }
        public void onClick(View v) {

            int random=rno.nextInt(50)+1;
            B1.setText(""+random);
            tv1.setText(String.valueOf(random+8));
            random=rno.nextInt(50)+1;
            B2.setText(""+random);
            tv2.setText(String.valueOf(random+3));
            random=rno.nextInt(50)+1;
            B3.setText(""+random);
            tv3.setText(String.valueOf(random+4));
            random=rno.nextInt(50)+1;
            B4.setText(""+random);
            tv4.setText(String.valueOf(random+9));
            random=rno.nextInt(50)+1;
            B5.setText(""+random);
            tv5.setText(String.valueOf(random+0));
            random=rno.nextInt(50)+1;
            B6.setText(""+random);
            tv6.setText(String.valueOf(random+4));
            random=rno.nextInt(50)+1;
            B7.setText(""+random);
            tv7.setText(String.valueOf(random+3));
            random=rno.nextInt(50)+1;
            B8.setText(""+random);
            tv8.setText(String.valueOf(random+0));
            random=rno.nextInt(50)+1;
            B9.setText(""+random);
            tv9.setText(String.valueOf(random+2));
            random=rno.nextInt(50)+1;
            B10.setText(""+random);
            tv10.setText(String.valueOf(random+7));




        }



    }


