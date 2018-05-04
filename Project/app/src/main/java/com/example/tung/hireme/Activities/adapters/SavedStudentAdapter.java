package com.example.tung.hireme.Activities.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tung.hireme.R;

import java.util.List;

/**
 * Created by tung on 5/3/18.
 */

public class SavedStudentAdapter extends RecyclerView.Adapter<SavedStudentAdapter.ViewHolder> {
    private List<String> names;
    private Context context;
    private int lastSelectedPosition;

    public SavedStudentAdapter(Context context, List<String> names) {
        this.names = names;
        this.context = context;
    }

    @Override
    public SavedStudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_profile_fragment, parent, false);
        SavedStudentAdapter.ViewHolder viewHolder = new SavedStudentAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(SavedStudentAdapter.ViewHolder holder, int position) {
        String name = names.get(position);

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
