package com.l.tran;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.l.tran.fragment.AmInitFragment;
import com.l.tran.fragment.BdInitFragment;
import com.l.tran.fragment.EnInitFragment;
import com.l.tran.util.MediaPlayerUtil;
import com.l.tran.voice.VoiceDeal;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText et;
    public Button btn;
    public ImageView iv;
    public String str;
    public  String from = null;
    public String to = null;
    private InputMethodManager imm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imm = (InputMethodManager) getSystemService(MainActivity.INPUT_METHOD_SERVICE);
        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);
        iv = findViewById(R.id.et_iv);
        btn.setOnClickListener(this);
        iv.setOnClickListener(this);
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String bd_et = et.getText().toString().trim();
                if (TextUtils.isEmpty(bd_et)){
                    Toast.makeText(MainActivity.this, "请输入要翻译的内容", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if(isEnglish(bd_et)){
                    imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
                    BdInitFragment fragment = BdInitFragment.newInstance(bd_et,"auto","zh");
                    replaceFragment(fragment);
                }else {
                    imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
                    BdInitFragment f = BdInitFragment.newInstance(bd_et,"auto","en");
                    replaceFragment(f);
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn:
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
                initMainView();
                break;
            case R.id.et_iv:
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
                etIV();
                break;
        }
    }


    public boolean isEnglish(String str){
        return str.matches("^[a-zA-Z ]*");
    }

    public void initMainView(){
        str = et.getText().toString().trim().toLowerCase();
        et.setText(str);
        EnInitFragment en =  new EnInitFragment();
        AmInitFragment am =  new AmInitFragment();
        if(TextUtils.isEmpty(str)){
            Toast.makeText(this, "请输入要翻译的内容", Toast.LENGTH_SHORT).show();
        }else{
            if(isEnglish(str)){
               replaceFragment(en);
            }else {
                replaceFragment(am);
            }
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout,fragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayerUtil playerUtil = new MediaPlayerUtil();
        playerUtil.playerRel();

    }
    public void etIV(){
        str = et.getText().toString().trim().toLowerCase();
        et.setText(str);
        VoiceDeal deal = new VoiceDeal(MainActivity.this);
        deal.speakStart(str);
    }

}
