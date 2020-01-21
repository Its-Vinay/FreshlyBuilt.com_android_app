package com.example.website;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.website.Adapters.Adapter_about_us;
import com.example.website.model.About_us;

import java.util.ArrayList;

public class Fragment_about_us extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<About_us> aboutus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.activity_fragment_about_us,container,false);
        recyclerView = v.findViewById(R.id.recycler_about_us);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new Adapter_about_us(getContext(),aboutus));
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutus = new ArrayList<>() ;
        aboutus.add(new About_us("freshlyBuilt","https://freshlybuilt.com/wp-content/uploads/2019/07/rishab-yadav.jpg","Rishabh Yadav","Human Resource manager","Student : G.B Pant Govt Engineering College","https://www.linkedin.com/in/rishabh-yadav-994a9a178/"));
        aboutus.add(new About_us("freshlyBuilt","https://freshlybuilt.com/wp-content/uploads/2019/07/rishab-yadav.jpg","Rishabh Yadav","Human Resource manager","Student : G.B Pant Govt Engineering College","https://www.linkedin.com/in/rishabh-yadav-994a9a178/"));


    }
}

