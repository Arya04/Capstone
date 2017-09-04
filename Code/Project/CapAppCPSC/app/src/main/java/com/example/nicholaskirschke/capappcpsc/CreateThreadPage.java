package com.example.nicholaskirschke.capappcpsc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nicholaskirschke.capappcpsc.helper.SQLiteHandler;
import com.example.nicholaskirschke.capappcpsc.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateThreadPage extends AppCompatActivity {
    private EditText tvThreadTitle;
    private EditText tvThreadDescription;
    private Button submit;
    private SQLiteHandler db;
    private SessionManager session;
    private int idCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_thread_page);
        tvThreadTitle = (EditText) findViewById(R.id.etThreadTitle);
        tvThreadDescription = (EditText) findViewById(R.id.etThreadDescription);
        submit = (Button)findViewById(R.id.btThread);
        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());


        idCategory = getIntent().getIntExtra("id",0);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = tvThreadTitle.getText().toString().trim();
                String Description = tvThreadDescription.getText().toString().trim();
                if(!Title.equals("") && !Description.equals("")){
                    createThread(idCategory, Title,Description);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Fields are blank please enter information!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void createThread(final int idCategory,final String Title, final String Description){
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_ADD_THREAD, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // Log.d(TAG, "Login Response: " + response.toString());
                //  hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    // Check to see if there were any php errors
                    if(!error) {
                        Intent intent = new Intent(CreateThreadPage.this, ForumThreadActivity.class);
                        intent.putExtra("id", CreateThreadPage.this.idCategory);
                        startActivity(intent);
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
                // Posting parameters to url
                Map<String, String> params = new HashMap<String, String>();
                params.put("idCategory", idCategory + "");
                params.put("title", Title);
                params.put("description", Description);


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "tag forum");

    };
}
