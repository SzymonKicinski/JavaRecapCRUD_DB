package com.utils;


public class Utils {
    // HTTP Action
    public static String GET = "GET";
    public static String POST = "POST";
    public static String DELETE = "DELETE";
    public static String PUT = "PUT";

    public static String DATABASE = "database";


    //  Errors
    public static String _500 = "Something went wrong with query";
    public static String _501 = "Empty resource";


    // Query
    public static String CREATE_TABLE_IF_NOT_EXISTS = " CREATE TABLE IF NOT EXISTS ";
    public static String CREATE_VIEW_IF_NOT_EXISTS = " CREATE VIEW IF NOT EXISTS ";
    public static String SELECT = " SELECT ";
    public static String FROM = " FROM ";
    public static String SET = " SET ";
    public static String WHERE = " WHERE ";
    public static String ORDER_BY = " ORDER BY ";
    public static String NONE = " NONE ";
    public static String ASC = " ASC ";
    public static String DESC = " DESC ";
    public static String UPDATE = " UPDATE ";
    public static String INNER_JOIN = " INNER JOIN ";
    public static String ON = " ON ";
    public static String INSERT_INTO = " INSERT INTO ";
    public static String EQUAL = " = ";
    public static String STAR = " * ";
    public static String DOT = ".";
    public static String COMMA = ", ";
    public static String AS = " AS ";
    private static final String OR = "OR";
    public static String COLLATE_NOCASE = " COLLATE NOCASE ";


    // Data entity models
    // ALBUMS
    public static final String TABLE_ALBUMS = "albums";
    public static final String TABLE_ALBUMS_COLUMN_ID = "_id ";
    public static final String TABLE_ALBUMS_COLUMN_NAME = "name ";
    public static final String TABLE_ALBUMS_COLUMN_ARTIST = "artist ";
    // INDEX
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    // ARTISTS
    public static final String TABLE_ARTISTS = "artists";
    public static final String TABLE_ARTISTS_COLUMN_ID = "_id ";
    public static final String TABLE_ARTISTS_COLUMN_NAME = "name ";
    // INDEX
    public static final int INDEX_ARTISTS_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    // SONGS
    public static final String TABLE_SONGS = "songs";
    public static final String TABLE_SONGS_COLUMN_ID = "_id ";
    public static final String TABLE_SONGS_COLUMN_TRACK = "track ";
    public static final String TABLE_SONGS_COLUMN_TITLE = "title ";
    public static final String TABLE_SONGS_COLUMN_ALBUM = "album ";
    // INDEX
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;


    public static final String QUERY_ALBUMS_BY_ARTISTS_START =
            Utils.SELECT + Utils.TABLE_ALBUMS + "." + Utils.TABLE_ALBUMS_COLUMN_NAME + Utils.FROM + Utils.TABLE_ALBUMS
                    + Utils.INNER_JOIN + Utils.TABLE_ARTISTS + Utils.ON + Utils.TABLE_ALBUMS + "."
                    + Utils.TABLE_ALBUMS_COLUMN_ARTIST + Utils.EQUAL + Utils.TABLE_ARTISTS +"."
                    + Utils.TABLE_ARTISTS_COLUMN_ID + Utils.WHERE + Utils.TABLE_ARTISTS + "."
                    + Utils.TABLE_ARTISTS_COLUMN_NAME + Utils.EQUAL + "\"";
    public static final String QUERY_ALBUMS_BY_ARTIST_SORT = "\"" + Utils.ORDER_BY + Utils.TABLE_ALBUMS + "."
            + Utils.TABLE_ALBUMS_COLUMN_NAME + Utils.COLLATE_NOCASE;

    public static final String QUERY_ARTIST_ALBUM_TRACK_BY_TITLES_START =
            Utils.SELECT +
                    Utils.TABLE_ARTISTS + Utils.DOT + Utils.TABLE_ARTISTS_COLUMN_NAME +  Utils.COMMA +
                    Utils.TABLE_ALBUMS + Utils.DOT  + Utils.TABLE_ALBUMS_COLUMN_NAME + Utils.COMMA +
                    Utils.TABLE_SONGS + Utils.DOT + Utils.TABLE_SONGS_COLUMN_TRACK +
                    Utils.FROM + Utils.TABLE_SONGS +
                    Utils.INNER_JOIN + Utils.TABLE_ALBUMS + Utils.ON
                        + Utils.TABLE_SONGS + Utils.DOT + Utils.TABLE_SONGS_COLUMN_ALBUM + Utils.EQUAL
                        + Utils.TABLE_ALBUMS + Utils.DOT + Utils.TABLE_ARTISTS_COLUMN_ID +
                    Utils.INNER_JOIN + Utils.TABLE_ARTISTS + Utils.ON
                        + Utils.TABLE_ALBUMS + Utils.DOT + Utils.TABLE_ALBUMS_COLUMN_ARTIST + Utils.EQUAL
                        + Utils.TABLE_ARTISTS + Utils.DOT + Utils.TABLE_ARTISTS_COLUMN_ID +
                    Utils.WHERE + Utils.TABLE_SONGS + Utils.DOT + Utils.TABLE_SONGS_COLUMN_TITLE + Utils.EQUAL +
                    "\"";



    public static final String QUERY_ARTIST_ALBUM_TRACK_BY_TITLES_SORT = "\"" +
     Utils.ORDER_BY + Utils.TABLE_ARTISTS + Utils.DOT + Utils.TABLE_ARTISTS_COLUMN_NAME
             + Utils.COMMA + Utils.TABLE_ALBUMS + Utils.DOT + Utils.TABLE_ALBUMS_COLUMN_NAME +
             Utils.COLLATE_NOCASE ;

    // View
    public static String TABLE_ARTIST_SONG_VIEW = "artist_list";
    public static String CREATE_ARTIST_FOR_SONG_VIEW_START =
        Utils.CREATE_VIEW_IF_NOT_EXISTS + TABLE_ARTIST_SONG_VIEW + Utils.AS + Utils.SELECT +
                Utils.TABLE_ARTISTS + Utils.DOT + Utils.TABLE_ARTISTS_COLUMN_NAME + Utils.COMMA +
                Utils.TABLE_ALBUMS + Utils.DOT + Utils.TABLE_ALBUMS_COLUMN_NAME + Utils.AS +
                    Utils.TABLE_SONGS_COLUMN_ALBUM  + Utils.COMMA +
                Utils.TABLE_SONGS + Utils.DOT + Utils.TABLE_SONGS_COLUMN_TRACK + Utils.COMMA +
                Utils.TABLE_SONGS + Utils.DOT + Utils.TABLE_SONGS_COLUMN_TITLE +
                Utils.FROM + Utils.TABLE_SONGS +
                Utils.INNER_JOIN + Utils.TABLE_ALBUMS + Utils.ON +
                    Utils.TABLE_SONGS + Utils.DOT + Utils.TABLE_SONGS_COLUMN_ALBUM + Utils.EQUAL + Utils.TABLE_ALBUMS +
                    Utils.DOT + Utils.TABLE_ALBUMS_COLUMN_ID +
                Utils.INNER_JOIN + Utils.TABLE_ARTISTS + Utils.ON + Utils.TABLE_ALBUMS + Utils.DOT +
                    Utils.TABLE_ALBUMS_COLUMN_ARTIST + Utils.EQUAL + Utils.TABLE_ARTISTS + Utils.DOT +
                    Utils.TABLE_ARTISTS_COLUMN_ID +
                Utils.ORDER_BY +
                    Utils.TABLE_ARTISTS + Utils.DOT + Utils.TABLE_ARTISTS_COLUMN_NAME + Utils.COMMA +
                    Utils.TABLE_ALBUMS + Utils.DOT + Utils.TABLE_ALBUMS_COLUMN_NAME + Utils.COMMA +
                    Utils.TABLE_SONGS + Utils.DOT + Utils.TABLE_SONGS_COLUMN_TRACK;

    public static String QUERY_VIEW_ARTIST_LIST_INFO_BY_TITLE =
            Utils.SELECT + Utils.TABLE_ALBUMS_COLUMN_NAME + Utils.COMMA +
            Utils.TABLE_SONGS_COLUMN_ALBUM + Utils.COMMA + Utils.TABLE_SONGS_COLUMN_TRACK +
            Utils.FROM + TABLE_ARTIST_SONG_VIEW + Utils.WHERE + Utils.TABLE_SONGS_COLUMN_TITLE +
            Utils.EQUAL + "?";


    public static String QUERY_VIEW_ARTIST_LIST_INFO_BY_TITLE_OR_ARTIST =
            Utils.SELECT + Utils.TABLE_ALBUMS_COLUMN_NAME + Utils.COMMA +
            Utils.TABLE_SONGS_COLUMN_ALBUM + Utils.COMMA + Utils.TABLE_SONGS_COLUMN_TRACK +
            Utils.FROM + TABLE_ARTIST_SONG_VIEW + Utils.WHERE + Utils.TABLE_SONGS_COLUMN_TITLE +
            Utils.EQUAL + "?" + Utils.OR + Utils.EQUAL + "?";

}
