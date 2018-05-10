package com.example.tung.hireme.Activities.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.tung.hireme.Activities.models.Card;
import com.example.tung.hireme.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class SavedStudentAdapter extends RecyclerView.Adapter<SavedStudentAdapter.ViewHolder> {
    private List<Card> students;
    private Context context;

    public SavedStudentAdapter(Context context) {
        this.students = new ArrayList<>();
        this.context = context;
    }

    public void addNames(List<Card> students) {
        this.students.addAll(students);
        notifyDataSetChanged();
    }

    @Override
    public SavedStudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        SavedStudentAdapter.ViewHolder viewHolder = new SavedStudentAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SavedStudentAdapter.ViewHolder holder, int position) {
        String name = students.get(position).getName();
        String summary = students.get(position).getSummary();
        Uri image =Uri.parse(students.get(position).getProfileImageUrl());
        holder.name.setText(name);
        holder.summary.setText(summary);
        holder.image.setImageURI(image);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView summary;
        private ImageView image;
        //private RatingBar rating;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.savedstudentname);
            summary = itemView.findViewById(R.id.savedstudentsummary);
            image = itemView.findViewById(R.id.savedstudentimage);
            //rating = itemView.findViewById(R.id.studentrating);
        }
    }
}
