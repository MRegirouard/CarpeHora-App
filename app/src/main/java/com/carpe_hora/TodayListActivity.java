package com.carpe_hora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TodayListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_list);


        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TodayListActivity.this, LogActivity.class);
                i.putExtra("time", v.getId());
                System.out.println(v.getContext());
                startActivity(i);
            }        
        };

        Button btn = (Button) findViewById(R.id.am12activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.am1activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.am2activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.am3activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.am4activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.am5activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.am6activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.am7activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.am8activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.am9activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.am10activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.am11activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.pm12activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.pm1activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.pm2activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.pm3activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.pm4activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.pm5activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.pm6activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.pm7activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.pm8activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.pm9activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.pm10activity);
        btn.setOnClickListener(listener);

        btn = (Button) findViewById(R.id.pm11activity);
        btn.setOnClickListener(listener);

    }
}