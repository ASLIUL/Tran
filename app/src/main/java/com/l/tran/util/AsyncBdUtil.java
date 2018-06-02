package com.l.tran.util;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.l.tran.bdtran.TransApi;


public class AsyncBdUtil extends AsyncTask<String,Integer,String> {

    private static final String APP_ID = "20171120000098110";
    private static final String SECURITY_KEY = "Fluh6cXzO96hnnTfdH81";

    private String from;
    private String to;
    TextView textView;
    public AsyncBdUtil(String from, String to, TextView tv){
        this.from = from;
        this.to = to;
        this.textView =tv;
    }

    @Override
    protected String doInBackground(String... strings) {
        TransApi api = new TransApi(APP_ID,SECURITY_KEY);
        String Val = api.getTransResult(strings[0],from,to);
        return Val;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try{
            textView.setText(new ParseUtil().dealBdFastjson(s));
        }catch (Exception e){
            Toast.makeText(CustomContent.getContext(), "翻译出现错误，请检查翻译内容或网络", Toast.LENGTH_SHORT).show();
        }
        
    }
}
