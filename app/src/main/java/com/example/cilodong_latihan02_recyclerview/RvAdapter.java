package com.example.cilodong_latihan02_recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.CardViewHolder> {
    @NonNull
    @Override
    public RvAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.CardViewHolder cardViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
