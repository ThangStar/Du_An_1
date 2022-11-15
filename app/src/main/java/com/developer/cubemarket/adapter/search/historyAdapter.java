package com.developer.cubemarket.adapter.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.cubemarket.R;
import com.developer.cubemarket.object.search.historysearch;


import java.util.List;

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.historyViewHolder>{

    private List<historysearch> historysearchList;

    public historyAdapter(List<historysearch> historysearchList){
        this.historysearchList = historysearchList;
    }

    @NonNull
    @Override
    public historyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.historysearch, parent, false);
        return new historyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull historyViewHolder holder, int position) {
        historysearch historysearch = historysearchList.get(position);
        if (historysearch==null){
            return;
        }
        holder.title.setText(historysearch.getTitle());

    }

    @Override
    public int getItemCount() {
        if (historysearchList !=null){
            return historysearchList.size();
        }
        return 0;
    }

    public class historyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public historyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
