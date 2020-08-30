package com.sindhu.myapplication.ui.event;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
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
import com.sindhu.myapplication.bookmark_room_data.EventItem;
import com.sindhu.myapplication.event_static_data.EventContent;


import java.util.List;

public class EventListRecylerAdapter extends RecyclerView.Adapter<EventListRecylerAdapter.ViewHolder> {
    private final Fragment mParentFragment;
    private final List<EventItem> mValues;
    private List<EventItem> mEventItems; // Cached copy of words
    private final boolean mTwoPane;
    private int content;

    public EventListRecylerAdapter(Fragment mParentFragment, boolean mTwoPane, int content) {
        this.mParentFragment = mParentFragment;
        this.mValues = EventContent.ITEMS;
        this.mTwoPane = mTwoPane;
        this.content = content;
        if (mTwoPane && content != R.layout.event_content) {
            Bundle arguments = new Bundle();
            arguments.putString(EventDetailFragment.ARG_ITEM_ID,"eic");
            EventDetailFragment fragment = new EventDetailFragment();
            fragment.setArguments(arguments);
            AppCompatActivity activity = (AppCompatActivity) mParentFragment.getContext();
            assert activity != null;
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.event_detail_container, fragment)
                    .commit();
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //EventContent.EventItem eventItem;
        EventItem eventItem = null;
        //if the recycler is called for bookmarks fragment
       // if(mBookmarks!= null) {
            if (content == R.layout.event_content) {
                if (mEventItems.get(position).getEventBookmarked())
                    eventItem = mEventItems.get(position);
                //eventItem  = EventContent.ITEM_MAP.get(mBookmarks.get(position).getId());
            }
            //recycler called for Home fragment
            else {
                eventItem = mValues.get(position);
                //eventItem = mValues.get(position);
            }

            Log.d("view bookmark", ""+ eventItem);
            if (eventItem != null) {
                holder.mYearView.setText(eventItem.getYear());
                holder.mEventNameView.setText(eventItem.getEventTitle());
                holder.itemView.setTag(eventItem.getmId());
                holder.mEventImg.setImageResource(eventItem.getEventImageId());

                holder.itemView.setOnClickListener(view -> {
                    //EventContent.EventItem item = (EventContent.EventItem) view.getTag();

                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(EventDetailFragment.ARG_ITEM_ID, (String) view.getTag());
                        EventDetailFragment fragment = new EventDetailFragment();
                        fragment.setArguments(arguments);
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        activity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.event_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = view.getContext();

                        Intent intent = new Intent(context, EventDetailActivity.class);
                        intent.putExtra(EventDetailFragment.ARG_ITEM_ID, (String) view.getTag());
                        //if the version is below 16 shared activity transitions are not applied while new activity launched
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            // Apply activity transition
                            Pair[] pairs = new Pair[3];
                            pairs[0] = new Pair<View, String>(holder.mEventImg, "imageTransaction");
                            pairs[1] = new Pair<View, String>(holder.mEventNameView, "titleTransaction");
                            pairs[2] = new Pair<View, String>(holder.mYearView, "yearTransaction");
                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mParentFragment.getActivity(),
                                    pairs);
                            context.startActivity(intent, options.toBundle());
                        } else {
                            // Swap without transition
                            context.startActivity(intent);
                        }

                    }
                });
            }


    }
    public void setBookmarks(List<EventItem> eventItems){
        //if the recycler is set for bookmarks fragment
        if(content == R.layout.event_content){
            mEventItems = eventItems;
            notifyDataSetChanged();
        }
    }
    @Override
    public int getItemCount() {
        if(content == R.layout.event_content) {
            if (mEventItems != null)
                return mEventItems.size();
            else return 0;
        }
        return mValues.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
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

