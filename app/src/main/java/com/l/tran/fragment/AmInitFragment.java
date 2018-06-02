package com.l.tran.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.l.tran.MainActivity;
import com.l.tran.R;
import com.l.tran.util.AsyncEnUtil;
import com.l.tran.util.CustomContent;
import com.l.tran.util.MediaPlayerUtil;
import com.l.tran.util.ParseUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class AmInitFragment extends Fragment {

    private TextView py_main_1,py_main_2;
    private ImageView py_mp3_1,py_mp3_2;
    private TextView py_main_3;
    private ImageView py_mp3_3;
    private LinearLayout py_2,py_1,py_3;
    private LinearLayout  means_1,means_2,means_3,means_4;
    private TextView means_1_title,means_1_body;
    private TextView means_2_title,means_2_body;
    private TextView means_3_title,means_3_body;
    private TextView means_4_title,means_4_body;

    private View view;
    private ArrayList<TextView> py_main;
    private ArrayList<LinearLayout> layouts;
    private ArrayList<TextView> means_title;
    private ArrayList<TextView> means_body;
    MediaPlayerUtil playerUtil = new MediaPlayerUtil();
    ParseUtil parseUtil = new ParseUtil();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.am_init_main,null);
        InitView(view);
        addList();
       CustomCLickAM click = new CustomCLickAM();
       py_mp3_1.setOnClickListener(click);
       py_mp3_2.setOnClickListener(click);
       py_mp3_3.setOnClickListener(click);
        MainActivity activity = (MainActivity) getActivity();
        final String str = activity.str;
        String url = "http://dict-co.iciba.com/api/dictionary.php?w="+str+"&type=json&key=12DDC3A3F4A855A3CABED6EB18AB833F";
        new AsyncEnUtil(py_main,layouts,means_title,means_body).execute(url);
        return view;
    }


    public void InitView(View view){
        py_main_1 = view.findViewById(R.id.py_main_1);
        py_main_2 = view.findViewById(R.id.py_main_2);
        py_main_3 = view.findViewById(R.id.py_main_3);
        py_mp3_1 = view.findViewById(R.id.py_mp3_1);
        py_mp3_2 = view.findViewById(R.id.py_mp3_2);
        py_mp3_3 = view.findViewById(R.id.py_mp3_3);
        py_2 = view.findViewById(R.id.py_2);
        py_1 = view.findViewById(R.id.py_1);
        py_3 = view.findViewById(R.id.py_3);
        means_1 = view.findViewById(R.id.means_1);
        means_2 = view.findViewById(R.id.means_2);
        means_3 = view.findViewById(R.id.means_3);
        means_4 = view.findViewById(R.id.means_4);
        means_1_title = view.findViewById(R.id.means_1_title);
        means_2_title = view.findViewById(R.id.means_2_title);
        means_3_title = view.findViewById(R.id.means_3_title);
        means_4_title = view.findViewById(R.id.means_4_title);
        means_1_body = view.findViewById(R.id.means_1_body);
        means_2_body = view.findViewById(R.id.means_2_body);
        means_3_body = view.findViewById(R.id.means_3_body);
        means_4_body = view.findViewById(R.id.means_4_body);

    }

    public void addList(){
        py_main = new ArrayList<>();
        layouts = new ArrayList<>();
        means_body = new ArrayList<>();
        means_title = new ArrayList<>();

        py_main.add(py_main_1);
        py_main.add(py_main_2);
        py_main.add(py_main_3);

        means_title.add(means_1_title);
        means_title.add(means_2_title);
        means_title.add(means_3_title);
        means_title.add(means_4_title);
        means_body.add(means_1_body);
        means_body.add(means_2_body);
        means_body.add(means_3_body);
        means_body.add(means_4_body);

        layouts.add(py_1);
        layouts.add(py_2);
        layouts.add(py_3);

    }

}
class CustomCLickAM implements View.OnClickListener{
    public MediaPlayerUtil playerUtil = new MediaPlayerUtil();
    String [] py_music_en = new String[]{"ph_mp3_1","ph_mp3_2","ph_mp3_3","ph_mp3_4","ph_mp3_5"};
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.py_mp3_1:
                playerUtil.player(py_music_en[0]);
                break;
            case R.id.py_mp3_2:
                playerUtil.player(py_music_en[1]);
                break;
            case R.id.py_mp3_3:
                playerUtil.player(py_music_en[3]);
                break;
        }

    }
}
