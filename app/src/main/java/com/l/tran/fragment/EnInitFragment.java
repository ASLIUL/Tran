package com.l.tran.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.l.tran.MainActivity;
import com.l.tran.R;
import com.l.tran.util.AsyncAmUtil;
import com.l.tran.util.MediaPlayerUtil;
import com.l.tran.util.ParseUtil;

import java.util.ArrayList;

public class EnInitFragment extends Fragment {

    public TextView py_main_1,py_main_2;
    public  TextView nexus;
    public TextView word_past,word_done,word_ing,word_third,word_er,word_est,word_p1;
    public TextView part_adj,part_n,part_adv,part_pron,part_num;
    public   TextView part_v,part_art,part_prep,part_conj,part_interj,part_participle,part_aux,part_vt;
    public   LinearLayout py_1,py_2;
    public ImageView py_music_1,py_music_2;

    public ArrayList<TextView> py_mains = new ArrayList<>();
    public ArrayList<TextView> py_words = new ArrayList<>();
    public ArrayList<TextView> py_parts = new ArrayList<>();
    public ArrayList<LinearLayout> layouts  = new ArrayList<>();
    private View view;

    ParseUtil parseUtil = new ParseUtil();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.en_init_main,null);
        InitView();
        AddList();
        CustomClickEn click = new CustomClickEn();
        py_music_1.setOnClickListener(click);
        py_music_2.setOnClickListener(click);
        MainActivity activity = (MainActivity) getActivity();
        final String str = activity.str;
        String url = "http://dict-co.iciba.com/api/dictionary.php?w="+str+"&type=json&key=12DDC3A3F4A855A3CABED6EB18AB833F";
        new AsyncAmUtil(py_mains,py_words,py_parts,layouts).execute(url);
        return view;
    }

    public void InitView(){
        py_music_1 = view.findViewById(R.id.py_music_1);
        py_music_2 = view.findViewById(R.id.py_music_2);
        py_main_1  = view.findViewById(R.id.py_main_1);
        py_main_2  = view.findViewById(R.id.py_main_2);
        nexus = view.findViewById(R.id.nexus);
        word_p1 = view.findViewById(R.id.word_p1);
        word_past = view.findViewById(R.id.word_past);
        word_ing = view.findViewById(R.id.word_ing);
        word_done = view.findViewById(R.id.word_done);
        word_third = view.findViewById(R.id.word_third);
        word_er = view.findViewById(R.id.word_er);
        word_est = view.findViewById(R.id.word_est);
        part_adj = view.findViewById(R.id.part_adj);
        part_n = view.findViewById(R.id.part_n);
        part_adv = view.findViewById(R.id.part_adv);
        part_pron = view.findViewById(R.id.part_pron);
        part_num = view.findViewById(R.id.part_num);
        part_v = view.findViewById(R.id.part_v);
        part_art = view.findViewById(R.id.part_art);
        part_prep = view.findViewById(R.id.part_prep);
        part_conj = view.findViewById(R.id.part_conj);
        part_interj = view.findViewById(R.id.part_interj);
        part_participle = view.findViewById(R.id.part_participle);
        part_aux = view.findViewById(R.id.part_aux);
        part_vt = view.findViewById(R.id.part_vt);
        py_1 = view.findViewById(R.id.py_1);
        py_2 = view.findViewById(R.id.py_2);
    }

    public void AddList(){
        layouts.add(py_1);
        layouts.add(py_2);

        py_mains.add(py_main_1);
        py_mains.add(py_main_2);

        py_words.add(word_p1);
        py_words.add(word_past);
        py_words.add(word_done);
        py_words.add(word_ing);
        py_words.add(word_third);
        py_words.add(word_er);
        py_words.add(word_est);

        py_parts.add(part_adj);
        py_parts.add(part_n);
        py_parts.add(part_adv);
        py_parts.add(part_pron);
        py_parts.add(part_num);
        py_parts.add(part_v);
        py_parts.add(part_art);
        py_parts.add(part_prep);
        py_parts.add(part_conj);
        py_parts.add(part_interj);
        py_parts.add(part_participle);
        py_parts.add(part_aux);
        py_parts.add(part_vt);
    }


    class CustomClickEn implements View.OnClickListener{
        public MediaPlayerUtil playerUtil = new MediaPlayerUtil();
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.py_music_1:
                    playerUtil.player("ph_am_mp3");
                    break;
                case R.id.py_music_2:
                    playerUtil.player("ph_en_mp3");
                    break;
            }
        }
    }
}
