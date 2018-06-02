package com.l.tran.util;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class ParseUtil {

    public ParseUtil() {
        super();
    }



    public ArrayList<TextView> py_main;
    public ArrayList<TextView> means_title;
    public ArrayList<TextView> means_body;

    public ArrayList<LinearLayout> layouts = new ArrayList<>();
    public ArrayList<TextView> py_mains = new ArrayList<>();

    public ParseUtil(ArrayList<TextView> py_main, ArrayList<TextView> means_title,ArrayList<TextView> means_body,ArrayList<LinearLayout> layouts){
    this.py_main = py_main;
    this.means_title = means_title;
    this.means_body = means_body;
    this.layouts = layouts;
}

    public void amtoEnWithFastJson(String s){
        JSONArray parts = null;
        JSONArray means = null;
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONArray symbols = jsonObject.getJSONArray("symbols");
        String [] py_music_en = new String[]{"ph_mp3_1","ph_mp3_2","ph_mp3_3","ph_mp3_4","ph_mp3_5"};
        try {
            int i = 0;
            for (Object o:symbols){
                JSONObject jsonresulat = (JSONObject) o;
                py_main.get(i).setText(jsonresulat.getString("word_symbol"));
                parts = jsonresulat.getJSONArray("parts");
                shareXml(py_music_en[i],jsonresulat.getString("symbol_mp3"));
                i++;
            }

            i=0;
            for (Object object : parts) {
                JSONObject p = (JSONObject) object;
                means_title.get(i).setText(p.getString("part_name"));
                means = p.getJSONArray("means");
                for (Object ob : means){
                    JSONObject mOb = (JSONObject) ob;
                    means_body.get(i).append(mOb.getString("word_mean")+";");
                }
                i++;
            }
            for (i=0;i<py_main.size();i++){
                if (TextUtils.isEmpty(py_main.get(i).getText().toString())){
                    layouts.get(i).getLayoutParams().height=0;
                }
            }
            for (i=0;i<means_title.size();i++){
                if (TextUtils.isEmpty(means_title.get(i).getText().toString())){
                    means_title.get(i).getLayoutParams().height = 0;
                }
            }
            for (i=0;i<means_body.size();i++){
                if (TextUtils.isEmpty(means_body.get(i).getText().toString())){
                    means_body.get(i).getLayoutParams().height = 0;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(CustomContent.getContext(), "解析出错,请检查单词", Toast.LENGTH_SHORT).show();
        }
    }


    public void enToAMWithfastjson(String str){


        try{
            JSONArray partsAm = null;
            JSONObject jsonObject = JSONObject.parseObject(str);
            JSONObject exchange = jsonObject.getJSONObject("exchange");
            if (TextUtils.isEmpty(exchange.getString("word_past"))){
                means_title.get(0).getLayoutParams().height = 0;
            }else {
                means_title.get(0).setText("过去式   "+dealAm(exchange.getString("word_past")));
            }
            if (TextUtils.isEmpty(exchange.getString("word_done"))){
                means_title.get(1).getLayoutParams().height = 0;
            }else {
                means_title.get(1).setText("过去完成时   "+dealAm(exchange.getString("word_done")));
            }
            if (TextUtils.isEmpty(exchange.getString("word_ing"))){
                means_title.get(2).getLayoutParams().height = 0;
            }else {
                means_title.get(2).setText("正在进行时   "+dealAm(exchange.getString("word_ing")));
            }
            if (TextUtils.isEmpty(exchange.getString("word_third"))){
                means_title.get(3).getLayoutParams().height = 0;
            }else {
                means_title.get(3).setText("第三人称单数形式   "+dealAm(exchange.getString("word_third")));
            }
            if (TextUtils.isEmpty(exchange.getString("word_er"))){
                means_title.get(4).getLayoutParams().height = 0;
            }else {
                means_title.get(4).setText("比较级   "+dealAm(exchange.getString("word_er")));
            }
            if (TextUtils.isEmpty(exchange.getString("word_est"))){
                means_title.get(5).getLayoutParams().height = 0;
            }else {
                means_title.get(5).setText("最高级   "+dealAm(exchange.getString("word_est")));
            }
            if (TextUtils.isEmpty(exchange.getString("word_pl"))){
                means_title.get(6).getLayoutParams().height = 0;
            }else {
                means_title.get(6).setText("复数形式   "+dealAm(exchange.getString("word_pl")));
            }
            JSONArray symbols = jsonObject.getJSONArray("symbols");
            for (Object object:symbols){
                JSONObject json = (JSONObject) object;
                py_main.get(0).setText(json.getString("ph_en"));
                py_main.get(1).setText(json.getString("ph_am"));
                shareXml("ph_en_mp3",json.getString("ph_en_mp3"));
                shareXml("ph_am_mp3",json.getString("ph_am_mp3"));
                partsAm = json.getJSONArray("parts");
            }

            int i=0;
            for (Object object : partsAm){
                JSONObject json = (JSONObject) object;
                means_body.get(i).setText(dealAm(json.getString("part")+"\n"+json.getString("means")));
                i++;
            }
            i=0;
            for (i=0;i<py_mains.size();i++){
                if (TextUtils.isEmpty(py_mains.get(i).getText().toString())){
                    layouts.get(i).getLayoutParams().height=0;
                }
            }
            for (i=0;i<means_title.size();i++){
                if (TextUtils.isEmpty(means_title.get(i).getText())){
                    means_title.get(i).getLayoutParams().height = 0;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(CustomContent.getContext(), "解析出现异常，请检查输入的内容", Toast.LENGTH_SHORT).show();
        }
    }

    public String dealAm(String str){
        return str.replace("\"","").replace("[","").replace("]","");
    }

    public void shareXml(String key,String val){
        SharedPreferences.Editor editor = CustomContent.getContext().getSharedPreferences("config",0).edit();
        editor.putString(key,val);
        editor.apply();
    }

    public String dealBdFastjson(String el){

        String dst = "";
        JSONObject jsonObject = JSONObject.parseObject(el);
        JSONArray resulat = jsonObject.getJSONArray("trans_result");
        for (Object object:resulat){
            JSONObject jsonresulat = (JSONObject) object;
            dst = jsonresulat.getString("dst");
        }
        return  dst;
    }
}
