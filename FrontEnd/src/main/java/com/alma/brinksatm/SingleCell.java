package com.alma.brinksatm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alma.common.BackEndATM;

public class SingleCell extends ConstraintLayout {

    TextView textID;

    public SingleCell(@NonNull Context context) {
        super(context);
        init();
    }

    public SingleCell(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SingleCell(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public SingleCell(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.cell_100, this, true);

        textID = findViewById(R.id.atmID);
    }

    @SuppressLint("SetTextI18n")
    public void setList(BackEndATM atmList) {
        textID.setText(atmList.getId().toString());
    }
}
