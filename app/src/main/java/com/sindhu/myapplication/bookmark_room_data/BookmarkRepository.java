package com.sindhu.myapplication.bookmark_room_data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookmarkRepository {
    private BookmarksDao mbookmarksDao;
    private LiveData<List<Bookmark>> mAllBookmarks;

    BookmarkRepository(Application application) {
        BookmarkDatabase db = BookmarkDatabase.getDatabase(application);
        mbookmarksDao = db.bookmarksDao();
        mAllBookmarks = mbookmarksDao.getAllBookmarks();
    }

    LiveData<List<Bookmark>> getAllBookmarks() {
        return mAllBookmarks;
    }

    public void insert (Bookmark bookmark) {
        new insertAsyncTask(mbookmarksDao).execute(bookmark);
    }
    public void delete(Bookmark id){
        new deleteAsyncTask(mbookmarksDao).execute(id);
    }

    private static class insertAsyncTask extends AsyncTask<Bookmark, Void, Void> {

        private BookmarksDao mAsyncTaskDao;

        insertAsyncTask(BookmarksDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Bookmark... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class deleteAsyncTask extends AsyncTask<Bookmark, Void, Void> {

        private BookmarksDao mAsyncTaskDao;

        deleteAsyncTask(BookmarksDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Bookmark... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
