package com.alma.atmmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alma.common.BackEndATM;

public class FinishActivity extends AppCompatActivity {

    Intent intent;
    TextView id_show;
    String ID;
    Button updated_another;
    BackEndATM backEndATM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        id_show = findViewById(R.id.id_show);
        updated_another = findViewById(R.id.updated_another);

        intent = getIntent();
        ID = intent.getStringExtra("id");
        id_show.setText(ID);

        updated_another.setOnClickListener(v -> {
            Intent in = new Intent(FinishActivity.this, BackEndMainActivity.class);
            startActivity(in);
            finish();
        });


    }
}