package com.dwiromadon.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import com.dwiromadon.myapplication.R;
import com.dwiromadon.myapplication.session.PrefSetting;
import com.dwiromadon.myapplication.session.SessionManager;
import com.dwiromadon.myapplication.users.LoginActivity;

public class HomeAdminActivity extends AppCompatActivity {

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;
    CardView cardExit, cardDataBuku, cardInputData, cardProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreferances();

        session = new SessionManager(HomeAdminActivity.this);

        prefSetting.isLogin(session, prefs);


        cardExit = (CardView) findViewById(R.id.cardExit);
        cardDataBuku = (CardView) findViewById(R.id.cardDataBuku);
        cardInputData = (CardView) findViewById(R.id.cardInputData);
        cardProfile = (CardView) findViewById(R.id.cardProfile);
        cardExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomeAdminActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        cardDataBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, ActivityDataBuku.class);
                startActivity(i);
                finish();
            }
        });

        cardInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, InputDataBuku.class);
                startActivity(i);
                finish();
            }
        });

        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, ProfileActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
