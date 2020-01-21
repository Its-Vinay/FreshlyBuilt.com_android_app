package com.example.website.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.website.R;
import com.example.website.model.Groups;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_groups_1 extends RecyclerView.Adapter<Adapter_groups_1.View_group_1> {
    private ArrayList<Groups> groups;

    public Adapter_groups_1(ArrayList<Groups> groups) {
        this.groups = groups;
    }

    @NonNull
    @Override
    public View_group_1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_group_card,parent,false);
        return new View_group_1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_group_1 holder, int position) {
        Groups grp = groups.get(position);
        Picasso.get().load(grp.getCoverpic()).into(holder.coverpic);
        Picasso.get().load(grp.getProfilepic()).into(holder.profilepic);
        holder.group_name.setText(grp.getGroup_name());
        holder.grouptype.setText(grp.getGroup_type());

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class View_group_1 extends RecyclerView.ViewHolder {
        ImageView coverpic,profilepic;
        TextView group_name,grouptype;
        public View_group_1(@NonNull View itemView) {
            super(itemView);
            coverpic = itemView.findViewById(R.id.coverpic);
            profilepic = itemView.findViewById(R.id.profilepic);
            group_name = itemView.findViewById(R.id.group_name);
            grouptype = itemView.findViewById(R.id.group_type);
        }
    }

}
