package com.example.volleytut.LoginRegisExample;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.volleytut.MySingleton;
import com.example.volleytut.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText name,username,email,password,retypePassword;
    Button regidterBtn;
    AlertDialog.Builder builder;
    String registration_url="http://192.168.1.3/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=(EditText)findViewById(R.id.name);
        username=(EditText)findViewById(R.id.username);
        email=(EditText)findViewById(R.id.email);
        builder=new AlertDialog.Builder(this);
        password=(EditText)findViewById(R.id.password);
        retypePassword=(EditText)findViewById(R.id.retype_password);
        regidterBtn=(Button)findViewById(R.id.register);

        regidterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameStr=name.getText().toString();
                final String usernameStr=username.getText().toString();
                final String emailStr=email.getText().toString();
                final String passwordStr=password.getText().toString();
                final String retypePassStr=retypePassword.getText().toString();

                if(nameStr.equals("")||usernameStr.equals("")||emailStr.equals("")||passwordStr.equals("")||retypePassStr.equals("")){
                    builder.setTitle("Something went wrong");
                    builder.setMessage("Please fill all feild");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            password.setText("");
                            retypePassword.setText("");
                            return;
                        }
                    });
                    AlertDialog dialog=builder.create();
                    dialog.show();
                }
                if(!(passwordStr.equals(retypePassStr))){
                    builder.setTitle("Something went wrong");
                    builder.setMessage("password doesnt match");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            password.setText("");
                            retypePassword.setText("");
                        }
                    });
                    AlertDialog dialog=builder.create();
                    dialog.show();
                }
                else{
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, registration_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONArray jsonArray= null;
                            try {
                                jsonArray = new JSONArray(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.i("hell", "onResponse: "+response.toString());
                            try {
                                JSONObject object=jsonArray.getJSONObject(0);
                                String code=object.getString("code");
                                String message=object.getString("message");
                                builder.setTitle("server response");
                                builder.setMessage(message);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        password.setText("");
                                        retypePassword.setText("");
                                    }
                                });
                                AlertDialog dialog=builder.create();
                                dialog.show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.i("hellow", "onResponse: "+e.getMessage());
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("hello", "onErrorResponse: "+error.getMessage());
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> map=new HashMap<String, String>();
                            map.put("name",nameStr);
                            map.put("email",emailStr);
                            map.put("username",usernameStr);
                            map.put("password",passwordStr);
                            return map;
                        }

                    };
                    MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(stringRequest);
                }
            }
        });
    }
}
