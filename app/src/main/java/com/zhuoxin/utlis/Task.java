package com.zhuoxin.utlis;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by admin on 2016/10/31.
 */

public class Task extends AsyncTask<String, Void, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        InputStream inputStream=null;
        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                byte[] bytes = new byte[1024];
                int len = 0;
                StringBuilder builder = new StringBuilder();
                while (((len = inputStream.read(bytes)) != -1)) {
                    builder.append(new String(bytes, 0, len));
                }
                return builder.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
        if ((str != null && onLoadBitmapLister != null)) {
            onLoadBitmapLister.onLoadBitmap(str);

        }
    }

    OnLoadBitmapLister onLoadBitmapLister;

    public void setListener(OnLoadBitmapLister onLoadBitmapLister) {
        this.onLoadBitmapLister = onLoadBitmapLister;
    }
}
