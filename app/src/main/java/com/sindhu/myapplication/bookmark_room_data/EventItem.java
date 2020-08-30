package com.sindhu.myapplication.bookmark_room_data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmark_table")
public class EventItem {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String mId;
    private final int year;
    private final int eventTitle;

    @NonNull
    public String getmId() {
        return mId;
    }

    public int getYear() {
        return year;
    }

    public int getEventTitle() {
        return eventTitle;
    }

    public int getEventContent() {
        return eventContent;
    }

    public int getEventImageId() {
        return eventImageId;
    }

    public Boolean getEventBookmarked() {
        return eventBookmarked;
    }

    public void setEventBookmarked(Boolean eventBookmarked) {
        this.eventBookmarked = eventBookmarked;
    }

    private final int eventContent;
    private final int eventImageId;
    private Boolean eventBookmarked;
    public EventItem(@NonNull String id, int year, int eventTitle, int eventContent, int eventImageId) {
        this.mId = id;
        //this.eventBookmarked = false;

        this.year = year;
        this.eventTitle = eventTitle;
        this.eventContent = eventContent;
        this.eventImageId = eventImageId;
        this.eventBookmarked = false;
    }


    public String getId(){return this.mId;}


}
