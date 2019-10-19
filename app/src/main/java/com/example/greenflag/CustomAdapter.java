package com.example.greenflag;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private List<Contacts> dataSet;

    public CustomAdapter(List<Contacts> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.bindView(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
            return dataSet != null ? dataSet.size() : 0;
    }
}
