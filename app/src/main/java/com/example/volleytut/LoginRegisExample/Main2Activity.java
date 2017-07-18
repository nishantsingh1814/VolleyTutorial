package com.example.volleytut.LoginRegisExample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.volleytut.MainActivity;
import com.example.volleytut.MySingleton;
import com.example.volleytut.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    Button signupBtn;
    Button loginBtn;
    EditText username,password;
    String login_url="http://192.168.1.3/login.php";
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        builder=new AlertDialog.Builder(this);

        signupBtn=(Button)findViewById(R.id.sign_up_btn);
        loginBtn=(Button)findViewById(R.id.login_btn);
        username=(EditText)findViewById(R.id.login_name);
        password=(EditText)findViewById(R.id.login_password);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,RegisterActivity.class));
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameStr=username.getText().toString();
                final String passwordStr=password.getText().toString();

                StringRequest stringRequest=new StringRequest(Request.Method.POST, login_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("helo", "onResponse: "+response.toString());
                                try {
                                    JSONArray arr=new JSONArray(response);
                                    JSONObject ob=arr.getJSONObject(0);
                                    String name=ob.getString("name");
                                    String email=ob.getString("email");
                                    builder.setTitle(name);
                                    builder.setMessage(email);
                                    builder.create().show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map=new HashMap<String, String>();
                        map.put("username",usernameStr);
                        map.put("password",passwordStr);
                        return map;
                    }
                };
                MySingleton.getInstance(Main2Activity.this).addToRequestQueue(stringRequest);
            }
        });
    }
}
