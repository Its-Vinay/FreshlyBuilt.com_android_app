package com.example.website;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.website.Adapters.Adapter_home;
import com.example.website.model.Posts;

import java.util.ArrayList;
import java.util.List;

public class Fragment_home extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Posts> post;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.activity_home_fragment,container,false);
        recyclerView =  v.findViewById(R.id.recycler_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new Adapter_home(post));
        return  v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        post = new ArrayList<>();
        post.add(new Posts("What is Water purifier","Water is considered as an essential component in our daily life. We cannot think a single day without water. The researcher says that the human body is made of 70% water. As per the USGS water science school, 71% of the earth is water, the rest island. In our earth"," January 08, 2020 ","https://previews.123rf.com/images/antonioguillem/antonioguillem1601/antonioguillem160100125/50532402-girl-drinking-water-sitting-on-a-couch-at-home-and-looking-at-camera.jpg"));
        post.add(new Posts("FreshlyBuilt","first project, A platform fro social education","18 january 2020","https://freshlybuilt.com/wp-content/uploads/2019/10/freshlybuilt-header1.jpg"));
        post.add(new Posts("FreshlyBuilt","first project, A platform fro social education","18 january 2020","https://www.fastweb.com/uploads/article_photo/photo/2036398/crop635w_summer-reading-tips-for-high-school-students.jpg"));


    }
}

