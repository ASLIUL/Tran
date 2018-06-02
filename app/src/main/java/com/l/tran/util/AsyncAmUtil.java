package com.l.tran.util;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncAmUtil extends AsyncTask<String,Integer,String > {

    private String data;

    public ArrayList<TextView> py_mains;
    public ArrayList<TextView> py_words;
    public ArrayList<TextView> py_parts;
    public ArrayList<LinearLayout> layouts;
    public AsyncAmUtil(ArrayList<TextView> py_mains,ArrayList<TextView> py_words,ArrayList<TextView> py_parts,ArrayList<LinearLayout> layouts){
            this.py_mains = py_mains;
            this.py_words = py_words;
            this.py_parts = py_parts;
            this.layouts = layouts;
    }

    @Override
    protected String doInBackground(String... strings) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(strings[0]).build();
        try {
            Response response = client.newCall(request).execute();
            data = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        ParseUtil parseUtil = new ParseUtil(py_mains,py_words,py_parts,layouts);
        parseUtil.enToAMWithfastjson(s);
    }
}
