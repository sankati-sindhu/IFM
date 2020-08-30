package com.sindhu.myapplication.bookmark_room_data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookmarkRepository {
    public BookmarksDao mbookmarksDao;
    //private List<Bookmark> mAllBookmarks;
    private LiveData<List<EventItem>> mGetBookmarks;


    BookmarkRepository(Application application) {
        BookmarkDatabase db = BookmarkDatabase.getDatabase(application);
        mbookmarksDao = db.bookmarksDao();
        //mAllBookmarks = mbookmarksDao.getAllBookmarks();
        mGetBookmarks = mbookmarksDao.getBookmarks();

    }


    LiveData<List<EventItem>> getBookmarks() {
        return mGetBookmarks;
    }

    public void insert (EventItem eventItem) {
        new insertAsyncTask(mbookmarksDao).execute(eventItem);
    }
    public void delete(EventItem id){
        new deleteAsyncTask(mbookmarksDao).execute(id);
    }
    public void update(EventItem eventItem){new updateAsyncTask(mbookmarksDao).execute(eventItem);}


    private static class insertAsyncTask extends AsyncTask<EventItem, Void, Void> {

        private BookmarksDao mAsyncTaskDao;

        insertAsyncTask(BookmarksDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final EventItem... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<EventItem,Void,Void>{
        private BookmarksDao mAsyncTaskDao;

        updateAsyncTask(BookmarksDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final EventItem... eventItems) {
            mAsyncTaskDao.update(eventItems[0]);
            return null;
        }
    }
    private static class deleteAsyncTask extends AsyncTask<EventItem, Void, Void> {

        private BookmarksDao mAsyncTaskDao;

        deleteAsyncTask(BookmarksDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final EventItem... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }




}
