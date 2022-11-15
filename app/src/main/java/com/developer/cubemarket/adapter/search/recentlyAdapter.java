package com.developer.cubemarket.adapter.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.cubemarket.R;
import com.developer.cubemarket.object.search.recently;
import java.util.List;

public class recentlyAdapter extends RecyclerView.Adapter<recentlyAdapter.recentlyViewHolder>{

    private List<recently> recentlyList;

    public recentlyAdapter(List<recently> recentlyList) {
        this.recentlyList = recentlyList;
    }

    @NonNull
    @Override
    public recentlyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new recentlyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recentlyViewHolder holder, int position) {
        recently recently = recentlyList.get(position);
        if (recently==null){
            return;
        }

        holder.image.setImageResource(recently.getImage());
        holder.txtName.setText(recently.getName());
        holder.txtPrice.setText(recently.getPrice());
    }

    @Override
    public int getItemCount() {
        if (recentlyList !=null){
            return recentlyList.size();
        }
        return 0;
    }

    public class recentlyViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView txtName;
        private TextView txtPrice;

    public recentlyViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        txtName = itemView.findViewById(R.id.name);
        txtPrice = itemView.findViewById(R.id.price);
    }
    }
}
