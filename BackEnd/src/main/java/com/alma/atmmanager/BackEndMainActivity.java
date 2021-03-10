package com.alma.atmmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alma.common.BackEndATM;
import com.alma.common.Bills_name;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class BackEndMainActivity extends AppCompatActivity {

    EditText et_ID, et_20bill, et_50bill, et_100bill, et_200bill;
    Button btn_updated;
    Long idL, bill20L, bill50L, bill100L, bill200L;

    HashMap<String, Long> newBills = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_end_main);

        et_ID = findViewById(R.id.editTextID);
        et_20bill = findViewById(R.id.editText20bill);
        et_50bill = findViewById(R.id.editText50bill);
        et_100bill = findViewById(R.id.editText100bill);
        et_200bill = findViewById(R.id.editText200bill);
        btn_updated = findViewById(R.id.btn_updated);

        btn_updated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idL = Long.parseLong(et_ID.getText().toString());
                bill20L = Long.parseLong(et_20bill.getText().toString());
                bill50L = Long.parseLong(et_50bill.getText().toString());
                bill100L = Long.parseLong(et_100bill.getText().toString());
                bill200L = Long.parseLong(et_200bill.getText().toString());

                if (idL != null || bill20L != null || bill50L != null || bill100L != null || bill200L != null) {
                    newBills.put(Bills_name.Bill_20.getName(), bill20L);
                    newBills.put(Bills_name.Bill_50.getName(), bill50L);
                    newBills.put(Bills_name.Bill_100.getName(), bill100L);
                    newBills.put(Bills_name.Bill_200.getName(), bill200L);

                } else {
                    Toast.makeText(BackEndMainActivity.this, "One of the fields is incomplete",
                            Toast.LENGTH_LONG).show();
                }


                BackEndATM backEndATM = new BackEndATM(idL, newBills);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                reference.child("ATM information").child(idL.toString()).setValue(backEndATM);


                Intent intent = new Intent(BackEndMainActivity.this, FinishActivity.class);
                intent.putExtra("id", idL.toString());
                startActivity(intent);
                finish();
            }
        });


    }
}