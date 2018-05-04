package com.example.tung.hireme.Activities.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tung.hireme.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tung on 5/3/18.
 */

public class SavedStudentAdapter extends RecyclerView.Adapter<SavedStudentAdapter.ViewHolder> {
    private List<String> names;
    private Context context;
    private int lastSelectedPosition;

    public SavedStudentAdapter(Context context) {
        this.names = new ArrayList<>();
        this.context = context;
    }

    public void addNames(List<String> names) {
        this.names.addAll(names);
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
        String name = names.get(position);
        holder.name.setText(name);

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.savedstudentname);
        }
    }
}
