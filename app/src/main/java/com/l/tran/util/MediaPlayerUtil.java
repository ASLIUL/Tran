package com.l.tran.util;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;

public class MediaPlayerUtil {


    MediaPlayer player = new MediaPlayer();


    public void player(String key){

        SharedPreferences sharedPreferences = CustomContent.getContext().getSharedPreferences("config",0);

        String url = sharedPreferences.getString(key,null);
        if (TextUtils.isEmpty(url)){
            return;
        }
        try {
            player.reset();
            player.setDataSource(url);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(CustomContent.getContext(), "解析失败，请检查网络或权限", Toast.LENGTH_SHORT).show();
        }

    }


    public void playerRel(){
        if (player != null){
            player.stop();
            player.release();
        }
    }
}
