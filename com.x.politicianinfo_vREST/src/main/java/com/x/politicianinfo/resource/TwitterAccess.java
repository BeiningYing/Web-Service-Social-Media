package com.x.politicianinfo.resource;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;


public class TwitterAccess {

    private static final String BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAKYUtQEAAAAAWBg8MmqmnGW%2Fgke%2Bk%2BipyX1lDFI%3DqcOI2VohLsDiTbNAGFH5vaCiz6BIUq95FtNgSwJ0TV2CMRit53"; 

    public static String searchTweets(String query, int count) {
        HttpURLConnection conn = null;
        StringBuilder result = new StringBuilder();
        
        try {
            String encodedQuery = URLEncoder.encode(query, "UTF-8");
            String searchURL = "https://api.twitter.com/2/tweets/search/recent?query=" + encodedQuery + "&max_results=" + count;
            
            URL url = new URL(searchURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + BEARER_TOKEN);
            conn.setRequestProperty("Content-Type", "application/json");
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        
        return result.toString();
    }
    
    public static String getUserByUsername(String username) {
        HttpURLConnection conn = null;
        StringBuilder result = new StringBuilder();

        try {
            String encodedUsername = URLEncoder.encode(username, "UTF-8");
            String userURL = "https://api.twitter.com/2/users/by/username/" + encodedUsername;

            URL url = new URL(userURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + BEARER_TOKEN);
            conn.setRequestProperty("Content-Type", "application/json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return result.toString();
    }

    public static String getTweetsByUser(String userId, int count) {
        HttpURLConnection conn = null;
        StringBuilder result = new StringBuilder();

        try {
            String userTweetsURL = "https://api.twitter.com/2/users/" + userId + "/tweets?max_results=" + count;

            URL url = new URL(userTweetsURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + BEARER_TOKEN);
            conn.setRequestProperty("Content-Type", "application/json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return result.toString();
    }
}
