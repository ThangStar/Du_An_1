package com.developer.cubemarket.adapter.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.cubemarket.R;
import com.developer.cubemarket.object.search.ketquaObject;

import java.util.List;

public class ketquaAdapter extends RecyclerView.Adapter<ketquaAdapter.ketquaViewHolder>{
    private List<ketquaObject> ketquaObjectList;

    public ketquaAdapter(List<ketquaObject> ketquaObjectList) {
        this.ketquaObjectList = ketquaObjectList;
    }

    @NonNull
    @Override
    public ketquaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ketqua, parent, false);
        return new ketquaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ketquaViewHolder holder, int position) {
        ketquaObject ketquaObject = ketquaObjectList.get(position);
        if (ketquaObject==null){
            return;
        }
        holder.image.setImageResource(ketquaObject.getImage());
        holder.name.setText(ketquaObject.getName());
        holder.price.setText(ketquaObject.getPrice());
    }

    @Override
    public int getItemCount() {
        if (ketquaObjectList !=null){
            return ketquaObjectList.size();
        }
        return 0;
    }

    public class ketquaViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView name;
        private TextView price;

        public ketquaViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
