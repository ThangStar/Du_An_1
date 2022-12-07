package com.developer.cubemarket.adapter.search;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.FragmentNavigatorExtrasKt;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_home_item, parent, false);
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
        holder.rbStar.setRating(Float.parseFloat(sanpham.getSao()));
        // FIXME: 07/12/2022
        holder.tvRating.setText(sanpham.getSao()+"/5");
        holder.tvSold.setText("Đã bán: "+sanpham.getSoluongban());
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
        private RatingBar rbStar;
        TextView tvRating;
        TextView tvSold;
        LinearLayout lnProduct;


        public ketquaViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imv_product);
            name = itemView.findViewById(R.id.tv_title);
            price = itemView.findViewById(R.id.tv_price);
            rbStar = itemView.findViewById(R.id.rb_product);
            tvRating = itemView.findViewById(R.id.rating_text);
            tvSold = itemView.findViewById(R.id.tv_amount_sold);
            lnProduct = itemView.findViewById(R.id.ln_product);

            lnProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("img", arr.get(getAdapterPosition()).getImg());
                    bundle.putString("name", arr.get(getAdapterPosition()).getTensanpham());
                    bundle.putString("detail", arr.get(getAdapterPosition()).getChitiet());
                    bundle.putInt("price", arr.get(getAdapterPosition()).getGiaban());
                    bundle.putString("brand", arr.get(getAdapterPosition()).getNhasanxuat());
                    bundle.putInt("amount", arr.get(getAdapterPosition()).getSoluong());
                    bundle.putString("directory", arr.get(getAdapterPosition()).getDanhmuc().getTendanhmuc());
                    bundle.putString("rating", arr.get(getAdapterPosition()).getSao());

                    Navigation.findNavController(view).navigate(
                            R.id.action_productFragment_to_detailProductFragment, bundle);
                }
            });
        }
    }
}