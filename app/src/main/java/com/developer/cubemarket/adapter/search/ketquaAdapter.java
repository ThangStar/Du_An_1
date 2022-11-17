package com.developer.cubemarket.adapter.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.developer.cubemarket.R;
import com.developer.cubemarket.config.utils.Utils;
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham;
import com.developer.cubemarket.object.search.ketquaObject;

import java.util.List;

public class ketquaAdapter extends RecyclerView.Adapter<ketquaAdapter.ketquaViewHolder>{
    private List<Sanpham> arr;
    private Context ctx;

    public ketquaAdapter(Context ctx, List<Sanpham> arr) {
        this.arr = arr;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ketquaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ketqua, parent, false);
        return new ketquaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ketquaViewHolder holder, int position) {
        Sanpham sanpham = arr.get(position);
        if (sanpham==null){
            return;
        }
        RequestOptions option = Utils.Companion.getOptionLoadImgDirectoryFromUrl();
        Glide.with(ctx).load(sanpham.getImg()).apply(option).into(holder.image);
        holder.name.setText(sanpham.getTensanpham());
        holder.price.setText(sanpham.getGiaban()+"");
    }

    @Override
    public int getItemCount() {
        if (arr !=null){
            return arr.size();
        }
        return 0;
    }

    public class ketquaViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView name;
        private TextView price;

        public ketquaViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imv_product);
            name = itemView.findViewById(R.id.tv_title);
            price = itemView.findViewById(R.id.tv_price);
        }
    }
}