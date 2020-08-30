package com.sindhu.myapplication.bookmark_room_data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BookmarkViewModel extends AndroidViewModel {
    private BookmarkRepository mRepository;
   // private List<Bookmark> mAllBookmarks;
    private LiveData<List<EventItem>> mBookmarks;


    public BookmarkViewModel (Application application) {
        super(application);
        mRepository = new BookmarkRepository(application);
       // mAllBookmarks = mRepository.getAllBookmarks();
        mBookmarks = mRepository.getBookmarks();
    }

   // public List<Bookmark> getAllBookmarks() {
     //   return (List<Bookmark>) mAllBookmarks;
    //}

    public LiveData<List<EventItem>> getBookmarks() {
        return mBookmarks;
    }


    public void insert(EventItem eventItem) { mRepository.insert(eventItem); }
    public EventItem getBookmarked(String id){
        return  mRepository.mbookmarksDao.getBookmarked(id);
    }
    public void delete(EventItem eventItem) { mRepository.delete(eventItem); }
    /*This is for Update Method*/
    public void update(EventItem eventItem){
        mRepository.update(eventItem);
    }


}

