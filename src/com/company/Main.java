package com.company;

import com.datasource.Datasource;
import com.model.Artist;
import com.model.SongArtist;
import com.utils.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    private static Connection conn;

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if (!datasource.open()) {
            System.out.println("Can't open the datasource.");
//            conn = datasource.open();
            return;
        }

//        List<Artist> artists = datasource.queryArtists(Utils.ASC);
//        if (artists == null) {
//            System.out.println("No artists in " + Utils.DATABASE);
//            return;
//        } else {
//            for (Artist artist : artists) {
//                System.out.println("ID " + Utils.EQUAL + artist.getId() +
//                        ", Name " + Utils.EQUAL + artist.getName());
//            }
//        }
//        List<String> albumsForArtist = datasource.queryAlbumsForArtist(
//                "Carole King",
//                Utils.DESC );
//        if (albumsForArtist == null) {
//            System.out.println("No artists in " + Utils.DATABASE);
//            return;
//        } else {
//            for (String s : albumsForArtist) {
//                System.out.println(s);
//            }
//        }

//        List<SongArtist> songArtists = datasource.queryArtistAlbumTrackByTitles(
//                "I Can't Quit You Baby",
//                Utils.DESC );
//        if (songArtists == null) {
//            System.out.println("No artists in " + Utils.DATABASE);
//            return;
//        } else {
//            for (SongArtist s : songArtists) {
//                System.out.println(s.getAlbumName());
//                System.out.println(s.getArtistName());
//                System.out.println(s.getTrack());
//            }
//        }

//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Eneter a song title: ");
//        String title = null;

//        title = scanner.nextLine();
        List<SongArtist> songArtists = datasource.querySongInfoView(
                "Go Your Own Way");
        if (songArtists.isEmpty()) {
            System.out.println("No artists in " + Utils.DATABASE);
                return;
        } else {
                for (SongArtist s : songArtists) {
                    System.out.print(s.getAlbumName() + " ");
                    System.out.print(s.getArtistName() + " ");
                    System.out.print(s.getTrack() + "\n ");
                }
            }
//            datasource.querySongsMetadata();

//        int count = datasource.getMinCount(Utils.TABLE_SONGS);
//        System.out.println("Number of songs is: " + count);
//        boolean tu = datasource.createViewIfNotExist();
//        System.out.println("The result of creating view is: " + tu);


//	try (Connection conn = DriverManager.getConnection(
//            CONNECTION_STRING + DB_NAME);
//         Statement statement = conn.createStatement()) {
////        Connection conn = DriverManager.getConnection(
////                "jdbc:sqlite:C:\\sqlite\\sqlite-tools-win32-x86-3390200\\testjava.db");
////        Connection conn = DriverManager.getConnection(
////                "jdbc:sqlite:D:\\JAVA_COURSE\\SQLite\\testjava.db");
//
////        Statement statement = conn.createStatement();
//        statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
//                "(" + COLUMN_NAME + " TEXT, "
//                + COLUMN_PHONE + " INTEGER,"
//                + COLUMN_EMAIL + " TEXT )");
//        insertContact(statement, "Paul", 12345678, "paul@email.com");
//        statement.executeUpdate("UPDATE " + TABLE_CONTACTS + " SET " + COLUMN_PHONE +" =11111111 WHERE " +
//                COLUMN_NAME + " ='Ben'");
////        statement.execute("DELETE FROM contacts WHERE name='Diego'");
//
////        statement.execute("SELECT * FROM contacts");
//        ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
//        while (results.next()) {
//            System.out.println(results.getString(COLUMN_NAME) + " " +
//                                results.getString(COLUMN_PHONE) + " " +
//                                results.getString(COLUMN_EMAIL) + ".");
//        }
//
//        statement.close();
//        conn.close();
//
//    } catch (SQLException e) {
//        System.out.println("Something get wrong");
//        System.out.println(e.getMessage());
//    }
//    }
//
//    private static void insertContact(
//            Statement statement,
//            String name,
//            int phone,
//            String email) {
//        try {
//            System.out.println(" INSERT INTO " + TABLE_CONTACTS + "("
//                    + COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + " )" +
//                    "VALUES( "
//                    + name + ", "
//                    + phone + ", "
//                    + "\"" + email + "\"" + ");");
//            statement.execute(" INSERT INTO " + TABLE_CONTACTS + "("
//                    + COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + " )" +
//                    "VALUES( "
//                    + name + ", "
//                    + phone + ", "
//                    + email + ");");
//        } catch (SQLException e) {
//            System.out.println("Error while adding new contact to the database");
//            e.printStackTrace();
//        }
//    }

    }


}