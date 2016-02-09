package com.reka.tour.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.reka.tour.R;
import com.reka.tour.adapter.MaskapaiAdapter;
import com.reka.tour.model.Maskapai;
import com.reka.tour.utils.Util;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity {
    @Bind(R.id.rg_price)
    RadioGroup rgPrice;
    @Bind(R.id.list_maskapai)
    ListView listMaskapai;

    @Bind(R.id.tv_save)
    TextView tvSave;

    private RadioButton rbPrice;

    private MaskapaiAdapter maskapaiAdapter;
    private ArrayList<Maskapai> maskapais = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        ((Toolbar) findViewById(R.id.toolbar)).setNavigationIcon(R.drawable.back);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        setValue();
        Util.setListview(listMaskapai);
        setCallBack();
    }

    private void setCallBack() {
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rgPrice.getCheckedRadioButtonId();
                rbPrice = (RadioButton) findViewById(selectedId);
                Toast.makeText(FilterActivity.this, rbPrice.getText(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setValue() {
        for (int i = 1; i <= 5; i++) {
            maskapais.add(new Maskapai(
                    "Air " + i,
                   true
            ));
        }

        maskapaiAdapter = new MaskapaiAdapter(this, maskapais);
        listMaskapai.setAdapter(maskapaiAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }



}
