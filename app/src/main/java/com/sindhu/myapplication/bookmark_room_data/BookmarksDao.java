package com.sindhu.myapplication.bookmark_room_data;

import android.content.ClipData;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface BookmarksDao {
    @Insert
    void insert(EventItem eventItem);

    @Query("DELETE FROM bookmark_table")
    void deleteAll();
    /*
    @Query("SELECT * from bookmark_table ORDER BY id ASC")
    List<Bookmark> getAllBookmarks();*/

    @Query("SELECT * from bookmark_table WHERE eventBookmarked")
    LiveData<List<EventItem>> getBookmarks();

    @Delete
    void delete(EventItem eventItem);

    @Query("SELECT * from bookmark_table WHERE id=:mid")
    EventItem getBookmarked(String mid);

    @Update
    public void update(EventItem eventItem);


}
