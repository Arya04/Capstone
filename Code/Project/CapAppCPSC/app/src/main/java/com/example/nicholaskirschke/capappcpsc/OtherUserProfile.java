package com.example.nicholaskirschke.capappcpsc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nicholaskirschke.capappcpsc.helper.SQLiteHandler;
import com.example.nicholaskirschke.capappcpsc.helper.SessionManager;

import org.w3c.dom.Text;

public class OtherUserProfile extends AppCompatActivity {
    private SQLiteHandler db;
    private SessionManager session;
    private TextView name;
    private TextView bio;
    private TextView classYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        name = (TextView)findViewById(R.id.otherUserName);
        bio = (TextView)findViewById(R.id.otherUserBio);
        classYear = (TextView)findViewById(R.id.userClass);

        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        String otherName = getIntent().getStringExtra("otherName");
        String otherBio = getIntent().getStringExtra("otherBio");
        String otherClassYear = getIntent().getStringExtra("otherClassYear");

        name.setText(otherName + "'s Profile");
        bio.setText(""+otherBio);
        classYear.setText("Class Year: "+otherClassYear);

        if (!session.isLoggedIn()) {
            logoutUser();
        }
    }
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(OtherUserProfile.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                Intent myIntent = new Intent(this, LoginActivity.class);
                logoutUser();
                startActivity(myIntent);
                return true;
            case R.id.action_profile:
                Intent profIntent = new Intent(this, ProfileActivity.class);
                startActivity(profIntent);
                return true;
            case R.id.action_home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
