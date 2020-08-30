package com.sindhu.myapplication.bookmark_room_data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sindhu.myapplication.event_static_data.EventContent;


@Database(entities = {EventItem.class}, version = 2, exportSchema = false)
public abstract class BookmarkDatabase extends RoomDatabase {
    public abstract BookmarksDao bookmarksDao();

    private static BookmarkDatabase INSTANCE;

    public static BookmarkDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookmarkDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookmarkDatabase.class, "bookmark_table")
                            .fallbackToDestructiveMigration()
                            .build();
                  RoomDatabase.Callback rdc = new RoomDatabase.Callback(){
                        public void onOpen (SupportSQLiteDatabase db) {
                            // do something after database has been created
                            new PopulateDbAsync(INSTANCE).execute();
                        }

                    };
                }
            }
        }
        return INSTANCE;
    }
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final BookmarksDao mDao;

        PopulateDbAsync(@NonNull BookmarkDatabase db) {
            mDao = db.bookmarksDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            //mDao.deleteAll();
            for (int i = 0; i < EventContent.ITEMS.size(); i++) {
                //Bookmark word = new Bookmark(EventContent.ITEMS.get(i).id, year, eventTitle, eventContent, eventImageId);
                mDao.insert(EventContent.ITEMS.get(i));
            }
            return null;
        }
    }
}
