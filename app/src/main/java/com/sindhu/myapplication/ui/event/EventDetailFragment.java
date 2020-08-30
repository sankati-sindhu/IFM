package com.sindhu.myapplication.ui.event;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
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
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.sindhu.myapplication.R;
import com.sindhu.myapplication.bookmark_room_data.EventItem;
import com.sindhu.myapplication.bookmark_room_data.BookmarkViewModel;
import com.sindhu.myapplication.event_static_data.EventContent;

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
   // private EventContent.EventItem mItem;
    private EventItem mItem;
    private ImageView bookmarkIv;
    private BookmarkViewModel mbookmarkViewModel;
    private TextView yearTv;
    private TextView titleTv;
    private TextView contentTv,  textSizeIncrease, textSizeDecrease;
    private View rootView;
    private  SharedPreferences pref;
    SharedPreferences.Editor editor;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
   public EventDetailFragment() {
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() == null) throw new AssertionError();
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            mbookmarkViewModel = ViewModelProviders.of(this).get(BookmarkViewModel.class);
            mItem = EventContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            //mItem = mbookmarkViewModel.getBookmarked(getArguments().getString(ARG_ITEM_ID));
            //assert mItem != null;
            //mItem.setEventBookmarked(mbookmarkViewModel.getBookmarked(mItem.getId()));
            Activity activity = this.getActivity();
            assert activity != null;
            pref = activity.getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            editor = pref.edit();

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
            ((TextView) titleTv).setText(mItem.getEventTitle());
            SetTextSize(pref.getInt("TextSizes", -1));
            Log.d("successful","yay"+pref.getInt("TextSizes", -1));
            ((TextView) contentTv).setText(mItem.getEventContent());
            ((TextView) yearTv).setText(mItem.getYear());
            ((ImageView) rootView.findViewById(R.id.eventImage)).setImageResource(mItem.getEventImageId());

        }

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*
          since these views of handset are in the toolbar of {@Link EventDetailActivity}
          we initialize them after activity creates**/
       if (textSizeDecrease == null) {
            textSizeDecrease = requireActivity().findViewById(R.id.eventTextDecrease);
        }
        if (textSizeIncrease == null) {
            textSizeIncrease = requireActivity().findViewById(R.id.eventTextIncrease);
        }
        if (bookmarkIv == null) {
            bookmarkIv = requireActivity().findViewById(R.id.eventBookmark);
        }
        if (mItem.getEventBookmarked()) {
            bookmarkIv.setImageResource(R.drawable.ic_baseline_bookmark_24);
        } else {
            bookmarkIv.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
        }
        final EventItem eventItem = mItem;
        if(textSizeDecrease != null){
            textSizeDecrease.setOnClickListener(view -> {
                int size = pref.getInt("TextSizes", -1);
                size--;
                if(size < 1){
                    Snackbar.make(rootView, R.string.text_size_decrease_error, Snackbar.LENGTH_LONG).show();
                }else{
                    SetTextSize(size);
                    editor.putInt("TextSizes", size);
                    editor.apply();
                }

            });
        }
        if(textSizeIncrease != null){
            textSizeIncrease.setOnClickListener(view -> {
                int size = pref.getInt("TextSizes", -1);
                size++;
                if(size > 6){
                    Snackbar.make(rootView, R.string.text_size_increase_error, Snackbar.LENGTH_LONG).show();

                }else{
                    SetTextSize(size);
                    editor.putInt("TextSizes", size);
                    editor.apply();

                }

            });
        }


        bookmarkIv.setOnClickListener(view -> {
            if (mItem.getEventBookmarked()) {
                bookmarkIv.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
                mItem.setEventBookmarked(false);
                mbookmarkViewModel.update(eventItem);
                Snackbar.make(rootView, "Page removed from Bookmarks", Snackbar.LENGTH_LONG).show();

            } else {
                bookmarkIv.setImageResource(R.drawable.ic_baseline_bookmark_24);
                Snackbar.make(rootView, "Page added to Bookmarks", Snackbar.LENGTH_LONG).show();
                mItem.setEventBookmarked(true);
                mbookmarkViewModel.update(eventItem);

            }
        });
    }

    public void SetTextSize(int pre){
        switch (pre){
            case 1:{
                set(R.dimen.text_size_1,R.dimen.title_size_1,R.dimen.sub_title_size_1);
                break;
            }
            case 2:{
                set(R.dimen.text_size_2,R.dimen.title_size_2,R.dimen.sub_title_size_2);
                break;
            }
            case 3:{
                set(R.dimen.text_size_3,R.dimen.title_size_3,R.dimen.sub_title_size_3);
                break;
            }
            case 4:{
                set(R.dimen.text_size_4,R.dimen.title_size_4,R.dimen.sub_title_size_4);
                break;
            }
            case 5:{
                set(R.dimen.text_size_5,R.dimen.title_size_5,R.dimen.sub_title_size_5);
                break;
            }
            case 6:{
                set(R.dimen.text_size_6,R.dimen.title_size_6,R.dimen.sub_title_size_6);
                break;
            }
            case 7:{
                set(R.dimen.text_size_7,R.dimen.title_size_7,R.dimen.sub_title_size_7);
                break;
            }
            case 8:{
                set(R.dimen.text_size_8,R.dimen.title_size_8,R.dimen.sub_title_size_8);
                break;
            }
            case 9:{
                set(R.dimen.text_size_9,R.dimen.title_size_9,R.dimen.sub_title_size_9);
                break;
            }


        }

    }
    private void set(int text_size, int title_size, int sub_title_Size) {
        Log.d("successful","yay"+text_size);
        contentTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(text_size));
        titleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(title_size));
        yearTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(sub_title_Size));
    }
}



