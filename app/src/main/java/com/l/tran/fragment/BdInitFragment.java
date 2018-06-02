package com.l.tran.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.l.tran.R;
import com.l.tran.util.AsyncBdUtil;
import com.l.tran.util.CustomContent;
import com.l.tran.voice.VoiceDeal;

public class BdInitFragment extends Fragment {

    ImageView iv;
    TextView tv;

    public static BdInitFragment newInstance(String val,String from,String to){
        BdInitFragment f = new BdInitFragment();
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        bundle.putString("to", to);
        bundle.putString("val", val);
        f.setArguments(bundle);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        View view = inflater.inflate(R.layout.bd_init_main,null);
        iv = view.findViewById(R.id.bd_iv);
        tv = view.findViewById(R.id.bd_tv);
        new AsyncBdUtil(args.getString("from"),args.getString("to"),tv).execute(args.getString("val"));
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoiceDeal deal = new VoiceDeal(CustomContent.getContext());
                deal.speakStart(tv.getText().toString());
            }
        });
        return view;
    }
}
