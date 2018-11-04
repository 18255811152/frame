package com.example.qwert.view.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class TestRefreshAdapter extends RecyclerView.Adapter<TestRefreshAdapter.RefreshViewHolder> {


    @NonNull
    @Override
    public RefreshViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RefreshViewHolder refreshViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RefreshViewHolder extends RecyclerView.ViewHolder {

        public RefreshViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
