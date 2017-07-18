package com.example.volleytut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter adapter;
    ArrayList<Contact> arrayList;
    String json_url = "http://192.168.1.3/contactInfo.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        arrayList=new ArrayList<>();
        adapter=new RecyclerAdapter(arrayList);
        getList();
        recyclerView.setAdapter(adapter);

    }
    public void getList() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;

                        while (count < response.length()) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                Contact contact = new Contact(jsonObject.getString("name"), jsonObject.getString("email"));
                                arrayList.add(contact);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            count++;
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("hello", "onBindViewHolder: "+error.getMessage());
            }
        });
        MySingleton.getInstance(DisplayActivity.this).addToRequestQueue(jsonArrayRequest);

    }
}
