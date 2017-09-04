package com.example.nicholaskirschke.capappcpsc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nicholaskirschke.capappcpsc.helper.SQLiteHandler;
import com.example.nicholaskirschke.capappcpsc.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nicholaskirschke on 10/18/16.
 */

public class ForumCategoryActivity extends AppCompatActivity {
    private SQLiteHandler db;
    private SessionManager session;
    private int forumThreadSelect;
    ListView listView;
    private CustomAdapter adapter;
    private List<ForumData> fD;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forumcategory);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("forumName"));


        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        listView = (ListView)findViewById(R.id.lv2);
        forumThreadSelect = getIntent().getIntExtra("id",0);
        forumCategoryGrab(forumThreadSelect);
        setUpListViewListener();

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

    private void forumCategoryGrab(final int id){
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_FORUM_CATEGORIES, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // Log.d(TAG, "Login Response: " + response.toString());
                //  hideDialog();
                fD = new ArrayList<ForumData>();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    // Check to see if there were any php errors
                    if(!error) {
                        int numForums = jObj.length() - 1;
                        for(int i = 0; i < numForums; i++){
                            JSONObject rec = new JSONObject(jObj.getString(Integer.toString(i)));
                            ForumData.Builder myDataBuilder = new ForumData.Builder(rec.getInt("id"),rec.getString("title"),rec.getString("description"));
                            fD.add(myDataBuilder.build());

                        }
                        adapter = new CustomAdapter(ForumCategoryActivity.this,fD);
                        listView.setAdapter(adapter);
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e(TAG, "Login Error: " + error.getMessage());

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id +"");
                //params.put("description", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "tag forum");

    };

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(ForumCategoryActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private void setUpListViewListener(){

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //make method for taking in thread post int and php shows all threads.
                Intent intent = new Intent(ForumCategoryActivity.this,ForumThreadActivity.class);
                intent.putExtra("id", fD.get(position).getBuilderID());
                intent.putExtra("categoryName", fD.get(position).getTitle());
                //put Extra threat ID so you know which to pull for the next activity and listview
                startActivity(intent);
            }


        });

    }


}
