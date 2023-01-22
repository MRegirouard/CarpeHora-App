package com.carpe_hora;

import static java.lang.Integer.parseInt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodayListActivity extends Activity {

    Button buttons[];

    public static String getDateStr() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return now.format(dtf);
    }

    public void syncToDB() {
        String currentDate = TodayListActivity.getDateStr();
        Map<String, ArrayList<Integer>> hours = new HashMap<String, ArrayList<Integer>>();
        ArrayList<Integer> ints = new ArrayList<Integer>();

        for (Button b : buttons) {
            ints.add(LogActivity.actToIndex(b.getText().toString()));
        }

        hours.put("hours", ints);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(MainActivity.acctId)
                .collection("days").document(currentDate).set(hours);
    }

    public void updateColor(Button b) {
        switch (b.getText().toString()) {
            case "Sleep":
                b.setBackgroundColor(getResources().getColor(R.color.sleep));
                break;
            case "Work":
                b.setBackgroundColor(getResources().getColor(R.color.work));
                break;
            case "Exercise":
                b.setBackgroundColor(getResources().getColor(R.color.exercise));
                break;
            case "Socialize":
                b.setBackgroundColor(getResources().getColor(R.color.social));
                break;
            case "Relax":
                b.setBackgroundColor(getResources().getColor(R.color.relax));
                break;
            case "Eat":
                b.setBackgroundColor(getResources().getColor(R.color.eat));
                break;
            case "Other":
                b.setBackgroundColor(getResources().getColor(R.color.other));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_list);

        buttons = new Button[] {
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

        String currentDate = getDateStr();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(MainActivity.acctId)
                .collection("days").document(currentDate)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful() && task.getResult().exists()) {
                            System.out.println("From DB Hours:" + task.getResult().get("hours").toString());
                            List<Long> ints = (List<Long>) task.getResult().get("hours");
                            int i = 0;

                            for (Button b : buttons) {
                                b.setText(LogActivity.indexToAct(ints.get(i)));
                                updateColor(b);
                                i++;
                            }
                        } else {
                            System.out.println("Hours doesn't exist :(");
                        }

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
                                    updateColor(buttons[i]);
                                }

                                syncToDB();
                            }
                    }
                });

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodayListActivity.this, LogActivity.class);
                // get the tag of the button that was clicked and parse it to an int
                intent.putExtra("time", parseInt((String) v.getTag()));
                startActivity(intent);
            }        
        };

        for(int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(listener);
        }
    }
}