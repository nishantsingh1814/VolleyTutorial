package com.example.volleytut;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PostToServerActivity extends AppCompatActivity {

    EditText name, email;
    Button button;
    String server_url = "http://192.168.1.3/update_info.php";
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_to_server);
        button = (Button) findViewById(R.id.btn);
        name = (EditText) findViewById(R.id.name);
        builder = new AlertDialog.Builder(this);
        email = (EditText) findViewById(R.id.email);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameStr = name.getText().toString();
                final String emailStr = email.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server Response");
                        builder.setMessage(response);
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name.setText("");
                                email.setText("");
                            }
                        });
                        AlertDialog dialog=builder.create();
                        dialog.show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("name", nameStr);
                        map.put("email", emailStr);
                        return map;
                    }
                };
                MySingleton.getInstance(PostToServerActivity.this).addToRequestQueue(stringRequest);
            }
        });
    }
}
