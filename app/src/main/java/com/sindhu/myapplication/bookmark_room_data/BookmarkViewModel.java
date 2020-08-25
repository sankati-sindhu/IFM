package com.sindhu.myapplication.bookmark_room_data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BookmarkViewModel extends AndroidViewModel {
    private BookmarkRepository mRepository;

    private LiveData<List<Bookmark>> mAllBookmarks;

    public BookmarkViewModel (Application application) {
        super(application);
        mRepository = new BookmarkRepository(application);
        mAllBookmarks = mRepository.getAllBookmarks();
    }

    public LiveData<List<Bookmark>> getAllBookmarks() {
        return mAllBookmarks;
    }


    public void insert(Bookmark bookmark) { mRepository.insert(bookmark); }

    public void delete(Bookmark bookmark) { mRepository.delete(bookmark); }
}

