package com.alma.brinksatm;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alma.common.ATMManager;
import com.alma.common.BackEndATM;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainList extends AppCompatActivity {

    RecyclerView rv;
    ATMAdapter ATMAdapter;

    ArrayList<BackEndATM> atm_ArrayList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);


        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("ATM information");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                atm_ArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    BackEndATM backEndATM = dataSnapshot.getValue(BackEndATM.class);
                    assert backEndATM != null;
                    if (backEndATM.getId() != null) {
                        boolean needsFill = ATMManager.ATMCheck(backEndATM.getBills());
                        if (needsFill) {
                            atm_ArrayList.add(backEndATM);
                        } else {
                            atm_ArrayList.remove(backEndATM);
                        }

                    }

                }

                ATMAdapter = new ATMAdapter(MainList.this, atm_ArrayList);
                rv.setAdapter(ATMAdapter);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}