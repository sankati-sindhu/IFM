package com.sindhu.myapplication.bookmark_room_data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface BookmarksDao {
    @Insert
    void insert(Bookmark bookmark);

    @Query("DELETE FROM bookmark_table")
    void deleteAll();

    @Query("SELECT * from bookmark_table ORDER BY id ASC")
    LiveData<List<Bookmark>> getAllBookmarks();

    @Delete
    void delete(Bookmark bookmark);
}
