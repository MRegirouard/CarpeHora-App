package com.carpe_hora;

import static java.lang.Integer.parseInt;

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

        Button buttons[] = {
                findViewById(R.id.am12activity),
                findViewById(R.id.am1activity),
                findViewById(R.id.am2activity),
                findViewById(R.id.am3activity),
                findViewById(R.id.am4activity),
                findViewById(R.id.am5activity),
                findViewById(R.id.am6activity),
                findViewById(R.id.am7activity),
                findViewById(R.id.am8activity),
                findViewById(R.id.am9activity),
                findViewById(R.id.am10activity),
                findViewById(R.id.am11activity),
                findViewById(R.id.pm12activity),
                findViewById(R.id.pm1activity),
                findViewById(R.id.pm2activity),
                findViewById(R.id.pm3activity),
                findViewById(R.id.pm4activity),
                findViewById(R.id.pm5activity),
                findViewById(R.id.pm6activity),
                findViewById(R.id.pm7activity),
                findViewById(R.id.pm8activity),
                findViewById(R.id.pm9activity),
                findViewById(R.id.pm10activity),
                findViewById(R.id.pm11activity)
        };

        Intent intent = getIntent();
        // this intent includes a start time, end time, and activity for a new log
        // for all buttons in TodayListActivity, if the button's id is between the start and end time, set the text of 
        // the button to the activity and give it a background color
        if (intent.hasExtra("start") && intent.hasExtra("end") && intent.hasExtra("activity")) {
            // get start time and end time
            int startTime = intent.getIntExtra("start", 0);
            int endTime = intent.getIntExtra("end", 1);
            // get activity
            String activity = intent.getStringExtra("activity");

            // set text of buttons between start and end time to activity
            for (int i = startTime; i < endTime; i++) {
                buttons[i].setText(activity);
                switch (activity) {
                    case "Sleep":
                        buttons[i].setBackgroundColor(getResources().getColor(R.color.sleep));
                        break;
                    case "Work":
                        buttons[i].setBackgroundColor(getResources().getColor(R.color.work));
                        break;
                    case "Exercise":
                        buttons[i].setBackgroundColor(getResources().getColor(R.color.exercise));
                        break;
                    case "Socialize":
                        buttons[i].setBackgroundColor(getResources().getColor(R.color.social));
                        break;
                    case "Relax":
                        buttons[i].setBackgroundColor(getResources().getColor(R.color.relax));
                        break;
                    case "Eat":
                        buttons[i].setBackgroundColor(getResources().getColor(R.color.eat));
                        break;
                    case "Other":
                        buttons[i].setBackgroundColor(getResources().getColor(R.color.other));
                        break;
                }
            }
        }

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TodayListActivity.this, LogActivity.class);
                i.putExtra("time", v.getId());
                startActivity(i);
            }        
        };

        for(int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(listener);
        }
    }
}