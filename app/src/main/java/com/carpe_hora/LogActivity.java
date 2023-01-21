package com.carpe_hora;

import android.app.Activity;
import android.os.Bundle;

import com.carpe_hora.databinding.ActivityLogBinding;

public class LogActivity extends Activity {
	private ActivityLogBinding binding;

	public static final String[] timeValues = {
        "12 AM", "1 AM", "2 AM", "3 AM", "4 AM", "5 AM", "6 AM", "7 AM", "8 AM", "9 AM", "10 AM", "11 AM",
        "12 PM", "1 PM", "2 PM", "3 PM", "4 PM", "5 PM", "6 PM", "7 PM", "8 PM", "9 PM", "10 PM", "11 PM"
    };

    public static final String[] activities = {
        "Sleep", "Work", "Exercise", "Eat", "Socialize", "Relax", "Other"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startNp.setMinValue(0);
        binding.startNp.setMaxValue(timeValues.length - 1);
        binding.startNp.setDisplayedValues(timeValues);
        binding.startNp.setWrapSelectorWheel(false);

        binding.endNp.setMinValue(0);
        binding.endNp.setMaxValue(timeValues.length - 1);
        binding.endNp.setDisplayedValues(timeValues);
        binding.endNp.setWrapSelectorWheel(false);

        binding.activityNp.setMinValue(0);
        binding.activityNp.setMaxValue(activities.length - 1);
        binding.activityNp.setDisplayedValues(activities);
    }
}