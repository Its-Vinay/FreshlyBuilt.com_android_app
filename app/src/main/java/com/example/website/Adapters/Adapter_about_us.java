package com.example.website.Adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.website.R;
import com.example.website.model.About_us;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_about_us extends RecyclerView.Adapter<Adapter_about_us.View_about_us>{
    private Context context;
    private ArrayList<About_us> about;

    public Adapter_about_us(Context context, ArrayList<About_us> about) {
        this.context = context;
        this.about = about;
    }

    @NonNull
    @Override
    public View_about_us onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_aboutus_card,parent,false);
        return new View_about_us(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_about_us holder, int position) {
        final About_us a = about.get(position);
        Picasso.get().load(a.getImage()).into(holder.image);
        holder.name.setText(a.getName());
        holder.skills.setText(a.getSkills());
        holder.college.setText(a.getCollege());
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(a.getLinkedin()));
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return about.size();
    }

    public class View_about_us extends RecyclerView.ViewHolder {
        ImageView image;
        ImageButton imageButton;
        TextView name,skills,college;
        public View_about_us(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.member_image_about);
            imageButton = itemView.findViewById(R.id.linkedin_about);
            name = itemView.findViewById(R.id.name_about);
            skills = itemView.findViewById(R.id.skills_about);
            college = itemView.findViewById(R.id.college_about);


        }
    }

}