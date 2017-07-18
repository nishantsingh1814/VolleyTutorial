package com.example.volleytut;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    Button button;
    String serverUrl = "http://192.168.1.3/getInfo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,DisplayActivity.class));
//                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, serverUrl, null
//                        , new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            name.setText(response.getString("name"));
//                            email.setText(response.getString("email"));
//                            mobile.setText(response.getString("mobile"));
//                        }
//                        catch (JSONException e){
//
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//                MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);
            }
        });
    }
}
