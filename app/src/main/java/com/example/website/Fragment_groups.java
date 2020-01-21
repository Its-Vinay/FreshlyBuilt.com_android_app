package com.example.website;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.website.Adapters.Adapter_groups_1;
import com.example.website.model.Groups;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_groups extends Fragment {
    RecyclerView recyclerView1,recyclerView2;
    private ArrayList<Groups> groups;
    FloatingActionButton floatingActionButton_create;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.activity_fragment_groups,container,false);
        floatingActionButton_create = view1.findViewById(R.id.create_group);
        floatingActionButton_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://freshlybuilt.com/groups/create/step/group-details/"));
                startActivity(intent);
            }
        });
        recyclerView1 = view1.findViewById(R.id.recycler_groups_1);
        recyclerView2 = view1.findViewById(R.id.recycler_groups_2);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView1.setAdapter(new Adapter_groups_1(groups));
        recyclerView2.setAdapter(new Adapter_groups_1(groups));
    return view1;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    groups = new ArrayList<>();
    groups.add(new Groups("https://www.keyholesurgerykerala.com/wp-content/uploads/2016/12/coverpic-1.jpg","https://live.staticflickr.com/7374/12548594803_e43af7a6a8_b.jpg","VIPS Hackathon","Private group"));
        groups.add(new Groups("https://www.keyholesurgerykerala.com/wp-content/uploads/2016/12/coverpic-1.jpg","https://live.staticflickr.com/7374/12548594803_e43af7a6a8_b.jpg","VIPS Hackathon","Private group"));
        groups.add(new Groups("https://www.keyholesurgerykerala.com/wp-content/uploads/2016/12/coverpic-1.jpg","https://live.staticflickr.com/7374/12548594803_e43af7a6a8_b.jpg","VIPS Hackathon","Private group"));
    }

    }


