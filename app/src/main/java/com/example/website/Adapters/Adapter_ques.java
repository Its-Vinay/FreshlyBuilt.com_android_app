package com.example.website.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.website.R;
import com.example.website.model.Question;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_ques extends RecyclerView.Adapter<Adapter_ques.views_ques> {
    private ArrayList<Question> ques;
    public Adapter_ques(ArrayList<Question> ques) {
        this.ques = ques;
    }

    @NonNull
    @Override
    public Adapter_ques.views_ques onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_question_card,parent,false);
        return new Adapter_ques.views_ques(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_ques.views_ques holder, int i) {
        Question q = ques.get(i);
        Picasso.get().load(q.getImg()).into(holder.img);
        holder.questions.setText(q.getQuestions());
        holder.views.setText(q.getViews());
        holder.date.setText(q.getDate());
    }

    @Override
    public int getItemCount() {
        if(ques!=null){
            return ques.size();}
        else{
            return 0;
        }
    }

    public class views_ques extends RecyclerView.ViewHolder {
        TextView questions,date,views;
        ImageView img;
        Button ans;
        public views_ques(@NonNull View itemView) {
            super(itemView);
            questions = itemView.findViewById(R.id.questions_ques);
            date = itemView.findViewById(R.id.date_ques);
            views = itemView.findViewById(R.id.views_ques);
            img = itemView.findViewById(R.id.ques_image);
            ans = itemView.findViewById(R.id.answers_ques);
        }
    }
}

