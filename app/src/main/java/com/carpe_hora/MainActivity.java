package com.carpe_hora;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.*;
import com.google.firebase.firestore.*;

import com.carpe_hora.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    private TextView mTextView;
    private ActivityMainBinding binding;
    private GoogleApiClient googleApiClient;
    public static String acctId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button logBtn = (Button) findViewById(R.id.button2);

        logBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, LogActivity.class));
                }
        });

        Button todayBtn = (Button) findViewById(R.id.button);

        todayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TodayListActivity.class));
            }
        });

        GoogleSignInOptions signInConfigBuilder = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInConfigBuilder)
                .build();

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (signInResult.isSuccess()) {
                GoogleSignInAccount acct = signInResult.getSignInAccount();
                acctId = acct.getId();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("users").document(acctId);

                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(Task<DocumentSnapshot> documentSnapshot) {
                        if (!documentSnapshot.isSuccessful() || !documentSnapshot.getResult().exists()) {
                            Toast.makeText(getApplicationContext(), "Welcome new user " + acct.getGivenName(), Toast.LENGTH_SHORT).show();
                            Map<String, Object> user = new HashMap<>();
                            user.put("days", new ArrayList<Object>());
                            db.collection("users").document(acctId).set(user);
                        }
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Sign in failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
