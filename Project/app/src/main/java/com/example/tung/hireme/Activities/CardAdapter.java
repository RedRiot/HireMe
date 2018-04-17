package com.example.tung.hireme.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tung.hireme.R;

import java.util.List;

/**
 * Created by tung on 4/16/18.
 */

public class CardAdapter extends ArrayAdapter<Card> {
    Context context;

    public CardAdapter(Context context, int resourceId, List<Card> cards) {
        super(context, resourceId, cards);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Card cardItem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_view, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView summary = (TextView) convertView.findViewById(R.id.summary);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.profileImageView);

        name.setText(cardItem.getName());
        summary.setText(cardItem.getSummary());
        Glide.with(getContext()).load(cardItem.getProfileImageUrl()).into(imageView);

        return convertView;
    }
}
