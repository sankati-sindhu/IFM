package com.sindhu.myapplication.ui.bookmarks;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sindhu.myapplication.R;
import com.sindhu.myapplication.bookmark_room_data.Bookmark;
import com.sindhu.myapplication.bookmark_room_data.BookmarkViewModel;
import com.sindhu.myapplication.event_static_data.EventContent;

import java.util.List;

/**
 * An activity representing a list of Events. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link } representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class BookmarksFragment extends Fragment {


    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    public static BookmarkViewModel mbookmarkViewModel;
    private boolean mTwoPane;
    BookmarksListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_home_bookmarks, container, false);
        mbookmarkViewModel = ViewModelProviders.of(this).get(BookmarkViewModel.class);
        Context context = root.getContext();
        if (root.findViewById(R.id.event_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        //Bookmark b = new Bookmark("1");
        //mbookmarkViewModel.insert(b);
        View recyclerView = root.findViewById(R.id.event_list);
        adapter = new BookmarksListAdapter(this, context, EventContent.ITEMS, mTwoPane);
        setupRecyclerView((RecyclerView) recyclerView, adapter);




        //final TextView textView = root.findViewById(R.id.text_home);
        //homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
        //  @Override
        //      textView.setText(s);
        //}
        // });
        return root;
    }
    private void setupRecyclerView(@NonNull RecyclerView recyclerView, BookmarksListAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mbookmarkViewModel.getAllBookmarks().observe((LifecycleOwner) this, new Observer<List<Bookmark>>() {
            @Override
            public void onChanged(List<Bookmark> bookmarks) {
                adapter.setBookmarks(bookmarks);
            }


        });
    }


}