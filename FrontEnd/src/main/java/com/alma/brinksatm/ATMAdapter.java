package com.alma.brinksatm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alma.common.BackEndATM;

import java.util.List;

public class ATMAdapter extends RecyclerView.Adapter<ATMAdapter.ViewHolder> {

    private Context context;
    private List<BackEndATM> mAtm;

    public ATMAdapter(Context context, List<BackEndATM> mAtm) {
        this.context = context;
        this.mAtm = mAtm;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BackEndATM atmList = mAtm.get(position);
        holder.cell.setList(atmList);



        holder.cell.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShowATMActivity.class);
            intent.putExtra("ATMId", atmList.getId().toString());
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return mAtm.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        SingleCell cell;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cell = itemView.findViewById(R.id.cell);
        }

    }
}
