package com.example.volleytut;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Nishant on 7/18/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    ArrayList<Contact> arrayList=new ArrayList<>();
    public RecyclerAdapter(ArrayList<Contact> arrayList){
        this.arrayList=arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        Log.i("hello", "onB ");

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.email.setText(arrayList.get(position).getEmail());
        holder.name.setText(arrayList.get(position).getEmail());
        Log.i("hello", "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView email;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            email=(TextView)itemView.findViewById(R.id.email);
        }
    }
}
