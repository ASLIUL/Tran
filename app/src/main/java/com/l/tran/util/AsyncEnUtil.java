package com.l.tran.util;

import android.os.AsyncTask;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncEnUtil extends AsyncTask<String,Integer,String>{

    public String responeData;
    public ArrayList<TextView> py_main;
    public ArrayList<LinearLayout> layouts;
    public ArrayList<TextView> means_title;
    public ArrayList<TextView> means_body;
    public AsyncEnUtil(ArrayList<TextView> py_main, ArrayList<LinearLayout> layouts, ArrayList<TextView> means_title, ArrayList<TextView> means_body){
      this.py_main = py_main;
      this.layouts = layouts;
      this.means_title = means_title;
      this.means_body = means_body;
    }

    @Override
    protected String doInBackground(String... strings) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(strings[0]).build();
        try {
            Response response = client.newCall(request).execute();
            responeData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responeData;
    }

    @Override
    protected void onPostExecute(String s) {
       ParseUtil parseUtil = new ParseUtil(py_main,means_title,means_body,layouts);
       parseUtil.amtoEnWithFastJson(s);
    }
}