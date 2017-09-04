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

import static com.example.nicholaskirschke.capappcpsc.R.id.submit;

public class CreatePostPage extends AppCompatActivity {
    private EditText etPost;
    private Button submit;
    private SQLiteHandler db;
    private SessionManager session;
    private int idThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post_page);

        etPost = (EditText) findViewById(R.id.etPost);
        submit = (Button)findViewById(R.id.btPost);
        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        final HashMap<String, String> user = db.getUserDetails();



        idThread = getIntent().getIntExtra("idThread",0);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String post = etPost.getText().toString().trim();
                if(!post.equals("")){
                    createPost(idThread, user.get("uid"), post);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Post is blank please enter information!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void createPost(final int idThread, final String uId, final String post){
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_ADD_POST, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // Log.d(TAG, "Login Response: " + response.toString());
                //  hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    // Check to see if there were any php errors
                    if(!error) {
                        Intent intent = new Intent(CreatePostPage.this, ForumPostActivity.class);
                        intent.putExtra("id",idThread);
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
                params.put("idThread", idThread + "");
                params.put("uId", uId);
                params.put("post", post);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "tag forum");

    };
}