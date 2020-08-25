package com.sindhu.myapplication.ui.bookmarks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sindhu.myapplication.R;
import com.sindhu.myapplication.bookmark_room_data.Bookmark;
import com.sindhu.myapplication.event_static_data.EventContent;
import com.sindhu.myapplication.ui.event.EventDetailActivity;
import com.sindhu.myapplication.ui.event.EventDetailFragment;

import java.util.List;

public class BookmarksListAdapter extends RecyclerView.Adapter<BookmarksListAdapter.ViewHolder> {
    private final Fragment mParentFragment;
    private final LayoutInflater mInflater;
    private List<Bookmark> mBookmarks; // Cached copy of words
    private final List<EventContent.EventItem> mValues;
    private final boolean mTwoPane;

    BookmarksListAdapter(Fragment mParentFragment,Context context, List<EventContent.EventItem> mValues, boolean mTwoPane) {
        this.mParentFragment = mParentFragment;
        mInflater = LayoutInflater.from(context);
        this.mValues = mValues;
        this.mTwoPane = mTwoPane;

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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.event_content, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mBookmarks != null) {
            Bookmark current = mBookmarks.get(position);
            holder.mYearView.setText(mValues.get(position).year);
            holder.mEventNameView.setText(mValues.get(position).eventTitle);
            holder.itemView.setTag(mValues.get(position));
            holder.mEventImg.setImageResource(mValues.get(position).eventImageId);

            holder.itemView.setOnClickListener(mOnClickListener);
        } else {
            // Covers the case of data not being ready yet.
            holder.mEventNameView.setText("No Bookmarks");
        }
    }

    void setBookmarks(List<Bookmark> Bookmarks){
        mBookmarks = Bookmarks;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mBookmarks != null)
            return mBookmarks.size();
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView idTextView;
        final TextView mYearView;
        final TextView mEventNameView;
        final ImageView mEventImg;
        private ViewHolder(View view) {
            super(view);
            idTextView = itemView.findViewById(R.id.eventYear);
            mYearView = (TextView) view.findViewById(R.id.eventYear);
            mEventNameView = (TextView) view.findViewById(R.id.eventName);
            mEventImg = (ImageView) view.findViewById(R.id.eventImage);
        }
    }
}