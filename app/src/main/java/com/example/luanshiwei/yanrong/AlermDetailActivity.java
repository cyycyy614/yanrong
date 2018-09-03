package com.example.luanshiwei.yanrong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlermDetailActivity extends AppCompatActivity {
    @BindView(R.id.textView_fqname)
    TextView textViewFqname;
    @BindView(R.id.textView_mapname)
    TextView textViewMapname;
    @BindView(R.id.textView_eventtype)
    TextView textViewEventtype;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String mapname = intent.getStringExtra("mapname");
        String fqname = intent.getStringExtra("fqname");
        String eventtype = intent.getStringExtra("eventtype");

        mapname = mapname.equals("null") ? "" : mapname;
        fqname = fqname.equals("null") ? "" : fqname;
        eventtype = eventtype.equals("null") ? "" : eventtype;

        textViewFqname.setText(fqname);
        textViewMapname.setText(mapname);
        textViewEventtype.setText(eventtype);
    }
}
