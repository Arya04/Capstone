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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends AppCompatActivity {
    private EditText newPW;
    private EditText confPW;
    private Button submit;
    private SQLiteHandler db;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        newPW = (EditText)findViewById(R.id.newPW);
        confPW = (EditText)findViewById(R.id.confirmPW);
        submit = (Button)findViewById(R.id.submit);
        db = new SQLiteHandler(getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();
        final String uid = user.get("uid");
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwA = newPW.getText().toString().trim();
                String pwB = confPW.getText().toString().trim();
                if(pwA.equals(pwB) && !pwA.equals("")){
                    passwordChange(uid,pwA);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Passwords do not match or password is blank!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void passwordChange(final String uid, final String newPassword){
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_CHANGE_PASSWORD, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // Log.d(TAG, "Login Response: " + response.toString());
                //  hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    // Check to see if there were any php errors
                    if(!error) {
                        logoutUser();
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
                params.put("uid", uid);
                params.put("password", newPassword);
                //params.put("description", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "tag forum");

    };
    private void logoutUser() {
        session.setLogin(false);
        Toast.makeText(getApplicationContext(),
                "Password changed!", Toast.LENGTH_SHORT).show();
        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(ChangePassword.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
