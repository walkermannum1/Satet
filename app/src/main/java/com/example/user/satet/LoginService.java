package com.example.user.satet;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Guang on 2016/12/9.
 */

public class LoginService implements Runnable {
    public static boolean check(String name, String password) {
        String path = "http://116.236.224.54:21219/TestTaskManagerDemo/Login";
        Map<String,String> user = new HashMap<String, String>();
        user.put("name", name);
        user.put("password", password);
        try {
            return sendGETRequest(path, user, "utf-8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean sendGETRequest(String path, Map<String, String> user, String encode) throws MalformedURLException, IOException {
        StringBuilder url = new StringBuilder(path);
        url.append("?");
        for (Map.Entry<String, String> entry:user.entrySet()) {
            url.append(entry.getKey()).append("=");
            url.append(URLEncoder.encode(entry.getValue(), encode));
            url.append("&");
        }
        url.deleteCharAt(url.length()-1);
        HttpURLConnection conn = (HttpURLConnection)new URL(url.toString()).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run() {

    }
}
