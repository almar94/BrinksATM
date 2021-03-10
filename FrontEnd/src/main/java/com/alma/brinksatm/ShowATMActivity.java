package com.alma.brinksatm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alma.common.ATMManager;
import com.alma.common.BackEndATM;
import com.alma.common.Bills_name;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowATMActivity extends AppCompatActivity {

    Intent intent;
    public static String id;
    Long idLong;

    public TextView showID, bill_20, bill_50, bill_100, bill_200;
    public TextView short_20, short_50, short_100, short_200;
    Button go_back_btn, fill_btn, save_btn;
    String zero = "0";

    ArrayList<BackEndATM> short_ArrayList = new ArrayList<>();

    DatabaseReference databaseReference;
    public static HashMap<String, Long> short_bills = new HashMap<>();
    public static HashMap<String, Long> show_bills = new HashMap<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_a_t_m);

        showID = findViewById(R.id.ShowID);
        go_back_btn = findViewById(R.id.go_back_btn);
        fill_btn = findViewById(R.id.fill_btn);
        save_btn = findViewById(R.id.save_btn);
        bill_20 = findViewById(R.id.bill_20);
        bill_50 = findViewById(R.id.bill_50);
        bill_100 = findViewById(R.id.bill_100);
        bill_200 = findViewById(R.id.bill_200);

        short_20 = findViewById(R.id.short_20);
        short_50 = findViewById(R.id.short_50);
        short_100 = findViewById(R.id.short_100);
        short_200 = findViewById(R.id.short_200);

        go_back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(ShowATMActivity.this, MainList.class);
            startActivity(intent);
            finish();
        });

        intent = getIntent();
        id = intent.getStringExtra("ATMId");
        idLong = Long.parseLong(id);

        showID.setText(id);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("ATM information");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                short_ArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    BackEndATM backEndATM = dataSnapshot.getValue(BackEndATM.class);
                    assert backEndATM != null;
                    if (backEndATM.getId() != null) {
                        if (backEndATM.getId().equals(idLong)) {
                            show_bills = backEndATM.getBills();
                            if (show_bills != null) {
                                bill_20.setText(show_bills.get(Bills_name.Bill_20.getName()).toString());
                                bill_50.setText(show_bills.get(Bills_name.Bill_50.getName()).toString());
                                bill_100.setText(show_bills.get(Bills_name.Bill_100.getName()).toString());
                                bill_200.setText(show_bills.get(Bills_name.Bill_200.getName()).toString());
                            }
                            short_ArrayList.add(backEndATM);
                            short_bills = ATMManager.ATMCheckShort(short_ArrayList);
                            short_20.setText(short_bills.get(Bills_name.Bill_20.getName()).toString());
                            short_50.setText(short_bills.get(Bills_name.Bill_50.getName()).toString());
                            short_100.setText(short_bills.get(Bills_name.Bill_100.getName()).toString());
                            short_200.setText(short_bills.get(Bills_name.Bill_200.getName()).toString());
                        }
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        fill_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ATMManager.fill(show_bills);
                bill_20.setText(show_bills.get(Bills_name.Bill_20.getName()).toString());
                bill_50.setText(show_bills.get(Bills_name.Bill_50.getName()).toString());
                bill_100.setText(show_bills.get(Bills_name.Bill_100.getName()).toString());
                bill_200.setText(show_bills.get(Bills_name.Bill_200.getName()).toString());
                short_20.setText(zero);
                short_50.setText(zero);
                short_100.setText(zero);
                short_200.setText(zero);
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackEndATM backEndATM = new BackEndATM(idLong, show_bills);
                databaseReference.child(idLong.toString()).setValue(backEndATM);
                Intent intent = new Intent(ShowATMActivity.this, MainList.class);
                startActivity(intent);
                finish();
            }
        });

    }

}