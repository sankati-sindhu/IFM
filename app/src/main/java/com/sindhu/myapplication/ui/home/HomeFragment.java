package com.sindhu.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.sindhu.myapplication.R;
import com.sindhu.myapplication.event_static_data.EventContent;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private boolean mTwoPane;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_list_home_bookmarks, container, false);
        if (root.findViewById(R.id.event_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        View recyclerView = root.findViewById(R.id.event_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);



        //final TextView textView = root.findViewById(R.id.text_home);
        //homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
          //  @Override
          //      textView.setText(s);
            //}
       // });
        return root;
    }
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new EventListRecylerAdapter(this, EventContent.ITEMS, mTwoPane, R.layout.event_home_content));
    }
    /*public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<HomeFragment.SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final HomeFragment mParentActivity;
        private final List<EventContent.EventItem> mValues;
        private final boolean mTwoPane;
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

        SimpleItemRecyclerViewAdapter(HomeFragment parent,
                                      List<EventContent.EventItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public HomeFragment.SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_home_content, parent, false);
            return new HomeFragment.SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final HomeFragment.SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.mYearView.setText(mValues.get(position).year);
            holder.mEventNameView.setText(mValues.get(position).eventTitle);
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

         class ViewHolder extends RecyclerView.ViewHolder {
             final TextView mYearView;
             final TextView mEventNameView;
             final ImageView mEventImg;

            ViewHolder(View view) {
                super(view);
                mYearView = (TextView) view.findViewById(R.id.year);
                mEventNameView = (TextView) view.findViewById(R.id.eventName);
                mEventImg = (ImageView) view.findViewById(R.id.eventImage);
            }
        }
    }
*/

}