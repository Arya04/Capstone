package com.example.nicholaskirschke.capappcpsc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nicholaskirschke.capappcpsc.helper.SQLiteHandler;
import com.example.nicholaskirschke.capappcpsc.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private Button changePW;
    private TextView userName;
    private SQLiteHandler db;
    private SessionManager session;
    private Button updateProfile;
    private Uri temp;
    private File mediaStorageDir;
    private ImageView myView;
    private Bitmap bm;
    private Bitmap takenImage;
    private byte[] bitmapData;
    private String photo_string;
    private String uid;

    private EditText etBio;
    private EditText etClassYear;



    public final String APP_TAG = "MyCustomApp";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Profile Page");

        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        updateProfile = (Button)findViewById(R.id.updateProfile);
        // image code that we never implemented
        //myView = (ImageView)findViewById(R.id.profPic);
//        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams)myView.getLayoutParams();
//        params.width = 600;
//        params.height = 700;

        etClassYear = (EditText)findViewById(R.id.myClassYearInfo);
        etBio = (EditText)findViewById(R.id.myBio);


        if (!session.isLoggedIn()) {
            logoutUser();
        }

        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        uid = user.get("uid");
//        grabImage(uid);
        changePW = (Button)findViewById(R.id.changePW);
        userName = (TextView)findViewById(R.id.userName);

        userName.setText("Welcome, "+name);
        changePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chgPwIntent = new Intent(getApplicationContext(),ChangePassword.class);
                startActivity(chgPwIntent);

            }
        });

        populateProfile(uid);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                temp = getPhotoFileUri(photoFileName);
//
//
//                camIntent.putExtra(MediaStore.EXTRA_OUTPUT,temp);
//
//
//                if (camIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivityForResult(camIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
//                }
                updateProfileMethod(uid,etBio.getText().toString(),etClassYear.getText().toString());
                
                
                
                
            }
        });



    }

    private void updateProfileMethod(final String uid, final String bio, final String classYear) {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_UPDATEPROFILE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // Log.d(TAG, "Login Response: " + response.toString());
                //  hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check to see if there were any php errors
                    if (!error) {
                        Intent i = new Intent(ProfileActivity.this,ProfileActivity.class);
                        startActivity(i);


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
                params.put("classYear", classYear);
                params.put("bio", bio);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "tag forum");

    }

    public Uri getPhotoFileUri(String fileName){
        mediaStorageDir = new File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(APP_TAG, "failed to create directory");
        }
        return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
    }
    // Method never utilized due to incomplete image handling in DB
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                takenImage = BitmapFactory.decodeFile(temp.getPath());

                // Load the taken image into a preview

                //rotate Image because otherwise it rotates on it's own.
                // bm = rotateImage(takenImage, 90);
                // bm2 = rotateImage(takenImage2,90);
                bm = takenImage;
//                ByteArrayOutputStream blob = new ByteArrayOutputStream();
//                takenImage.compress(Bitmap.CompressFormat.JPEG,100, blob);
//
//                bitmapData = blob.toByteArray();
//
//                takenImage.recycle();
//                takenImage = null;
//
//                photo_string = bitmapData.toString();
                myView.setImageBitmap(bm);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 90, stream); //compress to which format you want.
                byte [] byte_arr = stream.toByteArray();
                String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);
                insertImage(uid, image_str);
//                try{
//                    blob.close();
//                    blob = null;
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


            } else { // Result was a failure
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }

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
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private void insertImage(final String uid, final String image) {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_FORUM_INSERTIMAGE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // Log.d(TAG, "Login Response: " + response.toString());
                //  hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    // Check to see if there were any php errors
                    if (!error) {
                        Toast.makeText(getApplicationContext(), "image worked", Toast.LENGTH_LONG).show();

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
                params.put("image", image);

                return params;
            }

        };

    }
    // Method never implemented due to problems with BLOBS
    private void grabImage(final String uid){
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_FORUM_GRABIMAGE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // Log.d(TAG, "Login Response: " + response.toString());
                //  hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    // Check to see if there were any php errors
                    if(!error) {
                        System.out.println("Image string: " + jObj.getString("image"));

                        byte[] decodedString = Base64.decode(jObj.getString("image"), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        myView.setImageBitmap(decodedByte);

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
                //params.put("description", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "tag forum");

    };
    private void populateProfile(final String uid) {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_POPPROFILE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // Log.d(TAG, "Login Response: " + response.toString());
                //  hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    // Check to see if there were any php errors
                    if (!error) {
                        etClassYear.setText(jObj.getString("classYear"));
                        etBio.setText(jObj.getString("bio"));

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

                //params.put("description", password);

                return params;
            }

        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "tag forum");
    }
}
