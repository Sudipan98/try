package com.example.sudip.player;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    String personId;
    GoogleSignInClient  mGoogleSignInClient;
    final String[] res = {new String()};
    TextView textView2;
    ImageView img2;
    private EditText result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        //textView2=(TextView)findViewById(R.id.textView);
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        SignInButton b = (SignInButton) findViewById(R.id.sign_in_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();


            }
        });

            }

        //result = (EditText) findViewById(R.id.editTextResult);

        // add button listener



    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 1000) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        img2=(ImageView)findViewById(R.id.image2);
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null) {
                String personName = account.getDisplayName();
                String personGivenName = account.getGivenName();
                String personFamilyName = account.getFamilyName();
                String personEmail = account.getEmail();
                 personId = account.getId();
                Uri personPhoto = account.getPhotoUrl();
                Map<String,String> params = new HashMap<String, String>();
                params.put("Email",personEmail);
                params.put("Name",personGivenName);
                params.put("username",personId);
                if(personPhoto!=null){
                    params.put("profile_pic",personPhoto.toString());
                }else {
                    params.put("profile_pic", "");
                }
                personId=personGivenName+personId;
                params.put("playerId",personId);
                //textView2.setText(personEmail);

                try {
                    saveData((HashMap) params);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                 //api.post("save",getApplicationContext(), (HashMap) params);
                Log.d(" return out ",res[0]);
                //Log.d(" recived",res);
              /* LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editText2);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        //result.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();*/
                /*Intent txt = new Intent(MainActivity.this, Main7Activity.class);
                startActivity(txt);
                txt.putExtra("Player ID",personId);*/
            }

            // Signed in successfully, show authenticated UI.
            //updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
           // Log.d( "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account==null);//not signed in
        else

            ;//signed in

    }
    /*public void apiCallget(String personEmail, final HashMap param){
        String BASE_URL = "http://192.168.0.101/player/";

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        //Player playerObj = new Player(account.getId(),account.getGivenName(),account.getEmail(),"male","tisl",account.getDisplayName(),"task_id","1/1/1");
        StringRequest sr = new StringRequest(Request.Method.GET, BASE_URL+"isemail/"+personEmail,
                new Response.Listener<String>() {

                    public void onResponse(String response) {
                        Log.e("HttpClient", "success! response: " + response.toString());
                        res[0] =response.toString();
                        JSONObject result=new JSONObject();
                        try {
                             result = new JSONObject(res[0]);
                             String success = result.getString("success");
                             Log.d("success",success);
                             if(success=="true"){
                                 apiCallpost(param);

                             }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d(" return ",res[0]);


                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.e("HttpClient", "error: " + error.toString());
                    }
                });
        queue.add(sr);
    }*/
    public void saveData(final HashMap obj){
        //String BASE_URL = "http://192.168.0.104/player/";
        String BASE_URL = api.BASE_PLAYER_URL;


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        //Player playerObj = new Player(account.getId(),account.getGivenName(),account.getEmail(),"male","tisl",account.getDisplayName(),"task_id","1/1/1");
        StringRequest sr = new StringRequest(Request.Method.POST, BASE_URL+"save",
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        Log.e("HttpClient", "success! response: " + response.toString());
                        res[0] =response.toString();
                        JSONObject result=new JSONObject();
                        try {
                            result = new JSONObject(res[0]);
                            String success = result.getString("success");
                            String msg = result.getString("msg");
                            Log.d("success ",success);
                            Log.d("msg ",msg);
                            if(success.equals("true")){
                                Intent txt = new Intent(MainActivity.this, Main7Activity.class);
                                txt.putExtra("Player ID",personId);
                                startActivity(txt);
                                MainActivity.this.finish();
                            }else{
                                if(msg.equals("email exist")){
                                    String Gender = result.getString("Gender");
                                    String playerId = result.getString("playerId");
                                    if(Gender.equals("")){
                                        Intent txt = new Intent(MainActivity.this, Main7Activity.class);
                                        txt.putExtra("Player ID",personId);
                                        startActivity(txt);
                                        MainActivity.this.finish();
                                    }else {
                                        Intent txt = new Intent(MainActivity.this, Main2Activity.class);
                                        txt.putExtra("Player ID", playerId);
                                        startActivity(txt);
                                        MainActivity.this.finish();
                                    }
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {

                        Log.e("HttpClient", "error: " + error.toString());
                    }
                })
        {
            @Override
            protected Map<String,String> getParams(){

                Map<String,String> params = new HashMap<String, String>(obj);
                params = obj;
                Log.d(" hash",params.toString());
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }
}
