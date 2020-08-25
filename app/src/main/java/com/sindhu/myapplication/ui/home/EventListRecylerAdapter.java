package com.sindhu.myapplication.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sindhu.myapplication.R;
import com.sindhu.myapplication.event_static_data.EventContent;
import com.sindhu.myapplication.ui.event.EventDetailActivity;
import com.sindhu.myapplication.ui.event.EventDetailFragment;


import java.util.List;

public class EventListRecylerAdapter extends RecyclerView.Adapter<EventListRecylerAdapter.ViewHolder> {
    private final Fragment mParentFragment;
    private final List<EventContent.EventItem> mValues;
    private final boolean mTwoPane;
    private int content;

    public EventListRecylerAdapter(Fragment mParentFragment, List<EventContent.EventItem> mValues, boolean mTwoPane, int content) {
        this.mParentFragment = mParentFragment;
        this.mValues = mValues;
        this.mTwoPane = mTwoPane;
        this.content = content;
    }




    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EventContent.EventItem item = (EventContent.EventItem) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putString(EventDetailFragment.ARG_ITEM_ID, item.id);
                EventDetailFragment fragment = new EventDetailFragment();
                fragment.setArguments(arguments);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.event_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra(EventDetailFragment.ARG_ITEM_ID, item.id);
                context.startActivity(intent);
            }
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(content, parent, false);
        return new EventListRecylerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mYearView.setText(mValues.get(position).year);
        holder.mEventNameView.setText(mValues.get(position).eventTitle);
        holder.itemView.setTag(mValues.get(position));
        holder.mEventImg.setImageResource(mValues.get(position).eventImageId);
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mYearView;
        final TextView mEventNameView;
        final ImageView mEventImg;
        public ViewHolder(@NonNull View view) {
            super(view);
            mYearView = (TextView) view.findViewById(R.id.eventYear);
            mEventNameView = (TextView) view.findViewById(R.id.eventName);
            mEventImg = (ImageView) view.findViewById(R.id.eventImage);
        }
    }

}

