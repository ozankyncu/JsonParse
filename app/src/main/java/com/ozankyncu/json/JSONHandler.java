package com.ozankyncu.json;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by ozankoyuncu on 7.3.2016.
 */
public class JSONHandler {
    private String isim;
    private String arkadasIsmi;
    private String yas;
    private String arkadasYas;
    private String urlString=null;

    public volatile boolean parseEtmeTamamlandi=true;

    public JSONHandler(String url)
    {
        this.urlString=url;
    }
    public String getIsim() {
        return isim;
    }

    public String getArkadasIsmi() {
        return arkadasIsmi;
    }

    public String getYas() {
        return yas;
    }

    public String getArkadasYas() {
        return arkadasYas;
    }
    public  void ParseJSON(String json)
    {
        try {
            JSONObject okuyucu=new JSONObject(json);
            isim=okuyucu.getString("name");
            yas=okuyucu.getString("age");

            JSONObject arkadasJson= okuyucu.getJSONObject("friend");
            arkadasIsmi=arkadasJson.getString("name");
            arkadasYas=arkadasJson.getString("age");

            parseEtmeTamamlandi=false;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void JSONAl()
    {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(urlString);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setReadTimeout(10000);
                    connection.setConnectTimeout(1500);
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.connect();

                    InputStream stream=connection.getInputStream();
                    String data=StreamStringeCevir(stream);

                    ParseJSON(data);
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    private String StreamStringeCevir(java.io.InputStream stream) {
        java.util.Scanner scanner=new java.util.Scanner(stream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() :"";
    }
}
