package com.sindhu.myapplication.event_static_data;

import com.sindhu.myapplication.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class EventContent {

    /**
     * An array of sample (dummy) items.
     */
    /**
     * An array of sample (dummy) items.
     */
    public static final List<EventItem> ITEMS = Arrays.asList(
            new EventItem("eic", R.string.event_year_eic, R.string.event_title_eic,R.string.event_content_eic, R.drawable.ic_eastindia),
            new EventItem("revolt", R.string.event_year_revolt, R.string.event_title_revolt,R.string.event_content_revolt, R.drawable.ic_revolt),
            new EventItem("inc", R.string.event_year_inc, R.string.event_title_inc,R.string.event_content_inc, R.drawable.ic_incv),
            new EventItem("bengal",R.string.event_year_bengal , R.string.event_title_bengal,R.string.event_content_bengal, R.drawable.ic_bengal),
            new EventItem("ml", R.string.event_year_ml , R.string.event_title_ml, R.string.event_content_ml, R.drawable.ic_ml),
            new EventItem("calcutta", R.string.event_year_calcutta, R.string.event_title_calcutta,R.string.event_content_calcutta, R.drawable.ic_calcutta),
            new EventItem("surat",R.string.event_year_surat , R.string.event_title_surat,R.string.event_content_surat, R.drawable.ic_surat),
            new EventItem("calpih", R.string.event_year_caliph, R.string.event_title_caliph,R.string.event_content_caliph,  R.drawable.ic_bengal),
            new EventItem("rowlatt",R.string.event_year_rowlatt , R.string.event_title_rowlatt,R.string.event_content_rowlatt,  R.drawable.ic_rowlatt),
            new EventItem("bagh",R.string.event_year_bagh , R.string.event_title_bagh,R.string.event_content_bagh,  R.drawable.ic_bagh),
            new EventItem("ncm",R.string.event_year_ncm , R.string.event_title_ncm,R.string.event_content_ncm,  R.drawable.ic_ncm),
            new EventItem("swaraj",R.string.event_year_swaraj , R.string.event_title_swaraj,R.string.event_content_swaraj,  R.drawable.ic_swaraj),
            new EventItem("cdm",R.string.event_year_cdm , R.string.event_title_cdm,R.string.event_content_cdm,  R.drawable.ic_dandi_march),
            new EventItem("ina", R.string.event_year_ina, R.string.event_title_ina,R.string.event_content_ina,  R.drawable.ic_ina),
            new EventItem("qim",  R.string.event_year_qim, R.string.event_title_qim, R.string.event_content_qim,  R.drawable.ic_quitindia),
            new EventItem("14",R.string.event_year_15 , R.string.event_title_15,R.string.event_content_15,  R.drawable.ic_lahorev),
            new EventItem("ind",  R.string.event_year_ind, R.string.event_title_ind,  R.string.event_content_ind,  R.drawable.ic_paertion)

    );
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, EventItem> ITEM_MAP = new HashMap<String, EventItem>();

    private static final int COUNT = ITEMS.size();

    static {
        for(int i = 0; i<COUNT;i++){
            EventItem eventItem = ITEMS.get(i);
            ITEM_MAP.put(eventItem.id, eventItem);
        }
    }


    /**
     * A Event item which contains represents the data within a event
     */
    public static class EventItem {
        public final String id;
        public final int year;
        public final int eventTitle;
        public final int eventContent;
        public final int eventImageId;

        public EventItem(String id, int year, int eventTitle, int eventContent, int eventImageId) {
            this.id = id;
            this.year = year;
            this.eventTitle = eventTitle;
            this.eventContent = eventContent;
            this.eventImageId = eventImageId;
        }


    }
}