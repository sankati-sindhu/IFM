package com.sindhu.myapplication.ui.event;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.sindhu.myapplication.R;
import com.sindhu.myapplication.bookmark_room_data.Bookmark;
import com.sindhu.myapplication.bookmark_room_data.BookmarkViewModel;
import com.sindhu.myapplication.event_static_data.EventContent;

import static android.icu.text.DisplayContext.LENGTH_SHORT;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link }
 * in two-pane mode (on tablets) or a {@link EventDetailActivity}
 * on handsets.
 */
public class EventDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private EventContent.EventItem mItem;
    private ImageView bookmarkIv, textSizeIncrease, textSizeDecrease;
    private BookmarkViewModel mbookmarkViewModel;
    private TextView yearTv, titleTv, contentTv;
    private View rootView;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = EventContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            mbookmarkViewModel = ViewModelProviders.of(this).get(BookmarkViewModel.class);
            Activity activity = this.getActivity();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.event_detail, container, false);
        textSizeIncrease = rootView.findViewById(R.id.eventTextIncrease);
        textSizeDecrease = rootView.findViewById(R.id.eventTextDecrease);
        yearTv = rootView.findViewById(R.id.eventYear);
        contentTv = rootView.findViewById(R.id.eventContent);
        titleTv = rootView.findViewById(R.id.eventName);
        bookmarkIv = rootView.findViewById(R.id.eventBookmark);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) titleTv).setText(mItem.eventTitle);
            ((TextView) contentTv).setText(mItem.eventContent);
            ((TextView) yearTv).setText(mItem.year);
            ((ImageView) rootView.findViewById(R.id.eventImage)).setImageResource(mItem.eventImageId);
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(textSizeDecrease == null){
            textSizeDecrease = getActivity().findViewById(R.id.eventTextDecrease);
        }
        if(textSizeIncrease == null){
            textSizeIncrease = getActivity().findViewById(R.id.eventTextIncrease);
        }
        if(bookmarkIv == null){
            bookmarkIv = getActivity().findViewById(R.id.eventBookmark);
        }
        textSizeDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float textSize;
                textSize = yearTv.getTextSize();
                if(textSize > 20.0) {
                    textSize = (textSize/2) - 2;
                    Log.d("text size", ""+textSize);
                    yearTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
                    contentTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, contentTv.getTextSize()/2 - 2);
                    titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleTv.getTextSize()/2 - 2);
                }
                else{
                    Snackbar.make(rootView, R.string.text_size_decrease_error, Snackbar.LENGTH_LONG).show();
                }

            }
        });
        textSizeIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float textSize;
                textSize = yearTv.getTextSize();
                if(textSize < 56.0) {
                    textSize = (textSize/2) + 2;
                    Log.d("text size", ""+textSize);
                    yearTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
                    contentTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, contentTv.getTextSize()/2 + 2);
                    titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleTv.getTextSize()/2 + 2);
                }
                else{
                    Log.d("text size", ""+textSize);
                    Snackbar.make(rootView, R.string.text_size_increase_error, Snackbar.LENGTH_LONG).show();
                }

            }
        });

        bookmarkIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bookmark bookmark = new Bookmark(mItem.id);
                mbookmarkViewModel.delete(bookmark);
            }
        });
    }
}