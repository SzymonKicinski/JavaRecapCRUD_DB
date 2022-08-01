package com.datasource;

import com.model.Artist;
import com.model.SongArtist;
import com.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Datasource {

    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\sqlite\\sqlite-tools-win32-x86-3390200\\";

    //    public static final String TABLE_CONTACTS = "contacts";


//    public static final String QUERY_SONG_INFO_VIEW =
//            Utils.SELECT + Utils.TABLE_ARTISTS_COLUMN_NAME + Utils.COMMA +
//                    Utils.TABLE_SONGS_COLUMN_ALBUM + Utils.COMMA +
//                    Utils.TABLE_SONGS_COLUMN_TRACK + Utils.FROM +
//                    Utils.TABLE_ARTIST_SONG_VIEW + Utils.WHERE +
//                    Utils.TABLE_SONGS_COLUMN_TITLE + Utils.EQUAL + "?";


    private Connection conn;
    private PreparedStatement querySongInfoView;

    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;

    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;


    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            querySongInfoView = conn.prepareStatement(Utils.QUERY_VIEW_ARTIST_LIST_INFO_BY_TITLE);
            insertIntoArtists = conn.prepareStatement(Utils.INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(Utils.INSERT_ALBUM, Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(Utils.INSERT_SONG);
            queryArtist = conn.prepareStatement(Utils.QUERY_ARTIST_EXISTS);
            queryAlbum = conn.prepareStatement(Utils.QUERY_ALBUM_EXISTS);
//            conn.setAutoCommit(false);

            return true;
        } catch (SQLException e) {
            System.out.println("Something went wrong with establishing connection to database.");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            conn.setAutoCommit(true);

            if (querySongInfoView != null) {
                querySongInfoView.close();
            }
            if (insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }
            if (insertIntoArtists != null) {
                insertIntoArtists.close();
            }
            if (insertIntoSongs != null) {
                insertIntoSongs.close();
            }
            if (queryAlbum != null) {
                queryAlbum.close();
            }
            if (queryArtist != null) {
                queryArtist.close();
            }
            if (conn != null ) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close the connection.");
            System.out.println(e.getMessage());
        }
    }

    public List<Artist> queryArtists(String sortOrder) {

        StringBuilder stringBuilder = new StringBuilder(Utils.SELECT + Utils.STAR + Utils.FROM);
        stringBuilder.append(Utils.TABLE_ARTISTS);

        if (sortOrder != null) {
            stringBuilder.append(Utils.ORDER_BY);
            stringBuilder.append(Utils.TABLE_ARTISTS_COLUMN_NAME);
            switch (sortOrder) {
                case "NONE ":
                    stringBuilder.append(Utils.NONE);
                    break;
                case "ASC ":
                    stringBuilder.append(Utils.ASC);
                    break;
                case "DESC ":
                    stringBuilder.append(Utils.DESC);
                    break;
                default:
                    stringBuilder.append(Utils.NONE);
            }
        }
        stringBuilder.append(";");
       List<Artist> artists = null;
        System.out.println(stringBuilder.toString());
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(stringBuilder.toString())){
            artists = new ArrayList<>();
            while (resultSet.next()) {
                Artist artist = new Artist();
                artist.setId(resultSet.getInt(Utils.INDEX_ARTISTS_ID));
                artist.setName(resultSet.getString(Utils.INDEX_ARTIST_NAME));
                artists.add(artist);
            }
            return artists;

        } catch(SQLException e){
            System.out.println("Something went wrong with query " + Utils.GET + " " + Utils.TABLE_ARTISTS);
            System.out.println(e.getMessage());
        }
        return artists;
    }


    public List<String> queryAlbumsForArtist(String artistName, String sortOrder) {

        StringBuilder stringBuilder = new StringBuilder(Utils.QUERY_ALBUMS_BY_ARTISTS_START);
        stringBuilder.append(artistName);
        if (sortOrder != Utils.NONE) {
            stringBuilder.append(Utils.QUERY_ALBUMS_BY_ARTIST_SORT);
            switch (sortOrder) {
                case "ASC ":
                    stringBuilder.append(Utils.ASC);
                    break;
                case "DESC ":
                    stringBuilder.append(Utils.DESC);
                    break;
                default:
                    stringBuilder.append(Utils.ASC);
            }
        }
        stringBuilder.append(";");
        List<String> albums = null;
        // Print SQL Statement
        System.out.println(stringBuilder.toString());

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(stringBuilder.toString())) {
            albums = new ArrayList<>();
            while (resultSet.next()) {
                albums.add(resultSet.getString(Utils.INDEX_ALBUM_ID));
            }
            return albums;

        } catch (SQLException e) {
            System.out.println("Something went wrong with query " + Utils.GET + " " + Utils.TABLE_ALBUMS);
            System.out.println(e.getMessage());
        }
        return albums;
    }

    public List<SongArtist> queryArtistAlbumTrackByTitles(String songTitle, String sortOrder) {
        StringBuilder stringBuilder = new StringBuilder(Utils.QUERY_ARTIST_ALBUM_TRACK_BY_TITLES_START);
        stringBuilder.append(songTitle);
        if (sortOrder != Utils.NONE) {
            stringBuilder.append(Utils.QUERY_ARTIST_ALBUM_TRACK_BY_TITLES_SORT);
            switch (sortOrder) {
                case "ASC ":
                    stringBuilder.append(Utils.ASC);
                    break;
                case "DESC ":
                    stringBuilder.append(Utils.DESC);
                    break;
                default:
                    stringBuilder.append(Utils.ASC);
            }
        }
        stringBuilder.append(";");
        List<SongArtist> songArtists = null;
        // Print SQL Statement
        System.out.println(stringBuilder.toString());

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(stringBuilder.toString())) {
            songArtists = new ArrayList<>();
            while (resultSet.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(resultSet.getString(Utils.INDEX_ALBUM_ID));
                songArtist.setAlbumName(resultSet.getString(Utils.INDEX_ALBUM_NAME));
                songArtist.setTrack(resultSet.getInt(3));
                songArtists.add(songArtist);
            }
            return songArtists;

        } catch (SQLException e) {
            System.out.println("Something went wrong with query " + Utils.GET + " " + Utils.TABLE_SONGS);
            System.out.println(e.getMessage());
        }
        return songArtists;
    }


    public void querySongsMetadata() {
        String sql = "SELECT * FROM " + Utils.TABLE_SONGS;

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numColumns = metaData.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                System.out.format("Column %d in the songs table is name %s \n",
                        i,
                        metaData.getColumnName(i));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong with query " + Utils.GET + " " + Utils.TABLE_SONGS
                    + " querySongsMetadata");
            System.out.println(e.getMessage());
        }
    }


    public int getCount(String tableName) {
        String sql =  Utils.SELECT +
                " COUNT(*) " + Utils.FROM + " " +tableName;

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            int index = resultSet.getInt(1);
            return index;
        } catch (SQLException e) {
            System.out.println("Something went wrong with query COUNT of " + Utils.GET + " " + Utils.TABLE_SONGS
                    + " getCount");
            System.out.println(e.getMessage());
            return -1;
        }
    }


    public int getMinCount(String tableName) {
        String sql =  Utils.SELECT +
                " COUNT(*) AS count, MIN(_id) AS min_id" + Utils.FROM + " " +tableName;

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            int index = resultSet.getInt("count");
            int min = resultSet.getInt("min_id");
            System.out.format("Count = %d, Min = %d \n", index, min);
            return index;
        } catch (SQLException e) {
            System.out.println("Something went wrong with query COUNT of " + Utils.GET + " " + Utils.TABLE_SONGS
                    + " getCount");
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public boolean createViewIfNotExist() {
        System.out.println(Utils.CREATE_ARTIST_FOR_SONG_VIEW_START);
        try (Statement statement = conn.createStatement()){
            statement.execute(
                     Utils.CREATE_ARTIST_FOR_SONG_VIEW_START);
            return true;
        } catch (SQLException e) {
            System.out.println("Creating view failed of " + Utils.CREATE_VIEW_IF_NOT_EXISTS
                    + " " + Utils.TABLE_SONGS
                    + " createViewIfNotExist");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return false;
        }
    }

    public List<SongArtist> querySongInfoView(String title) {
//        StringBuilder sb = new StringBuilder(Utils.QUERY_VIEW_ARTIST_LIST_INFO_BY_TITLE)
//                .append(title)
//                .append("\"");
//        System.out.println(sb);
        try (Statement statement = conn.createStatement()) {
            System.out.println(querySongInfoView);
            querySongInfoView.setString(1, title);

            ResultSet resultSet = querySongInfoView.executeQuery();

            List<SongArtist> songArtists = new ArrayList<>();
            while (resultSet.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(resultSet.getString(1));
                songArtist.setAlbumName(resultSet.getString(2));
                songArtist.setTrack(resultSet.getInt(3));
                songArtists.add(songArtist);
            }
            return songArtists;
        } catch (SQLException e) {
            System.out.println("Creating view failed of " + Utils.QUERY_VIEW_ARTIST_LIST_INFO_BY_TITLE
                    + " " + Utils.TABLE_ARTIST_SONG_VIEW
                    + " querySongInfoView");
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return null;
        }


    }


    public int insertArtist(String name) throws SQLException {
        try {
            queryArtist.setString(1, name);
            ResultSet resultSet = queryArtist.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                // Insert the artist
                insertIntoArtists.setString(1, name);
                int affectedRows = insertIntoArtists.executeUpdate();

                if (affectedRows != 1) {
                    throw new SQLException("Error while adding new artist " + queryArtist.toString());
                }

                ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Cloud not get _id of artist vol.2 - Don't find the id for artist"
                            + queryArtist.toString());
                }
            }

        } catch (SQLException e){
            System.out.println("Inserting failed of " + insertIntoArtists
                    + " insertArtist");
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return -1;
            }
    }

    public int insertAlbum(String name, int artistId) throws SQLException {
        try {
            queryAlbum.setString(1, name);
            queryAlbum.setInt(2, artistId);
            ResultSet resultSet = queryAlbum.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                // Insert the album
                insertIntoAlbums.setString(1, name);
                insertIntoAlbums.setInt(2, artistId);
                int affectedRows = insertIntoAlbums.executeUpdate();

                if (affectedRows != 1) {
                    throw new SQLException("Error while adding new album " + queryAlbum.toString());
                }

                ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Cloud not get _id of album vol.2 - Don't find the id for albums"
                            + queryArtist.toString());
                }
            }

        } catch (SQLException e) {
            System.out.println("Inserting failed of " + insertIntoAlbums
                    + " insertAlbum");
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return -1;
        }
    }

    public int insertSong(String title, String artist,String album, int trackId) {
        try {
            conn.setAutoCommit(false);
            // Check if exist the artists
            int artistId = insertArtist(artist);
            // Check if the albums exists too
            int albumId = insertAlbum(album, artistId);

            insertIntoSongs.setInt(1, trackId);
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumId);

            int affectedRows = insertIntoSongs.executeUpdate();
            if (affectedRows == 1) {
                conn.commit();
                ResultSet generatedKeys = insertIntoSongs.getGeneratedKeys();
                if (generatedKeys.next())
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Error while adding new album " + queryAlbum.toString());
            }
        } catch (SQLException e) {
            System.out.println("Inserting failed of " + insertIntoSongs
                    + " insertSong");
            System.out.println("Performing rollback! #AHTUNG#");
            try {
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
                System.out.println("Winter is coming! You have failed on us, my Padawan!");
                System.out.println(Arrays.toString(e2.getStackTrace()));
                return -1;
            }
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return -1;
        } finally {
            try {
                System.out.println("Resetting default commit behaviour");
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("You F##K Up This Time!");
                System.out.println("Call for Thanos");
            }
        }
        return -1;
    }

 }
