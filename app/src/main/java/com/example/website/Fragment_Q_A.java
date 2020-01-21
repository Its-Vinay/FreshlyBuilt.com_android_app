package com.example.website;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.website.Adapters.Adapter_ques;
import com.example.website.model.Question;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Q_A extends Fragment {
    RecyclerView recyclerView;
    public ArrayList<Question> ques;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.activity_fragment_ques,container,false);
        recyclerView = v.findViewById(R.id.recyclerview_ques);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new Adapter_ques(ques));
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ques=new ArrayList<>();
        ques.add(new Question("https://freshlybuilt.com/wp-content/uploads/avatars/9/5d0126f0f03de-bpfull.jpg","How to delete or remove locally uploaded file/folder on the google colab?","3.55K views","December 13, 2019"));
    }
}

