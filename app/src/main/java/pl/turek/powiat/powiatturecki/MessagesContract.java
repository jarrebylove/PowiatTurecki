package pl.turek.powiat.powiatturecki;

import android.provider.BaseColumns;

public final class MessagesContract {

    private MessagesContract() {}

    public static class MessageEntry implements BaseColumns {
        public static final String TABLE_NAME = "messages";
        public static final String COLUMN_NAME_SOURCE_ID = "source_id";
        public static final String COLUMN_NAME_CATEGORY_ID = "category_id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_BEGIN = "begin";
        public static final String COLUMN_NAME_END = "end";
        public static final String COLUMN_NAME_PAGE_ID = "page_id";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MessageEntry.TABLE_NAME + " (" +
                    MessageEntry._ID + " INTEGER PRIMARY KEY," +
                    MessageEntry.COLUMN_NAME_SOURCE_ID + " TEXT," +
                    MessageEntry.COLUMN_NAME_CATEGORY_ID + " TEXT," +
                    MessageEntry.COLUMN_NAME_TITLE + " TEXT," +
                    MessageEntry.COLUMN_NAME_BEGIN + " TEXT," +
                    MessageEntry.COLUMN_NAME_END + " TEXT," +
                    MessageEntry.COLUMN_NAME_PAGE_ID + " TEXT)";

    public static final String SQL_DELETE_ENTRIES = "";
}
