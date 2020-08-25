package com.sindhu.myapplication.bookmark_room_data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmark_table")
public class Bookmark {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String mId;
    public Bookmark(@NonNull String id) {this.mId = id;}

    public String getId(){return this.mId;}
}
