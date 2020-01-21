package com.example.website.Adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.website.R;
import com.example.website.model.Posts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_home extends RecyclerView.Adapter<Adapter_home.views> {
    private ArrayList<Posts> posts;
    public Adapter_home(ArrayList<Posts> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public views onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_home_card,parent,false);
        return new views(view);
    }

    @Override
    public void onBindViewHolder(@NonNull views holder, int i) {
        Posts p = posts.get(i);
        holder.text.setText(p.getPost());
        holder.date.setText(p.getDate());
        holder.heading.setText(p.getHeading());
        Picasso.get().load(p.getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        if(posts!=null){
        return posts.size();}
        else{
            return 0;
        }
    }

    public class views extends RecyclerView.ViewHolder {
        TextView text,date,heading;
        ImageView image;
        public views(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.post_home);
            date = itemView.findViewById(R.id.date_home);
            heading = itemView.findViewById(R.id.heading_home);
            image = itemView.findViewById(R.id.image_home);
        }
    }
}
