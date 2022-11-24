package com.developer.cubemarket.adapter.billdetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.cubemarket.R;
import com.developer.cubemarket.fragment.bill_detail.BillDetailFragment;
import com.developer.cubemarket.object.billdetail.BillDetail;

import java.util.ArrayList;
import java.util.List;

public class BillDetailAdapter extends RecyclerView.Adapter<BillDetailAdapter.ViewHoder> {
        List<BillDetail> billDetails = new ArrayList<>();
        BillDetailFragment context;

        public BillDetailAdapter(BillDetailFragment context, List<BillDetail> billDetails) {
            this.billDetails = billDetails;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.hdct_item, parent, false);
            ViewHoder viewHoder = new ViewHoder(view);
            return viewHoder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
            BillDetail current = billDetails.get(position);
            holder.imv_product.setImageBitmap(current.getImg());
            holder.tv_name.setText(current.getName());
            holder.tv_sl.setText(current.getSl());
            holder.tv_color.setText(current.getColor());
            holder.tv_price.setText(current.getPrice());
        }

        @Override
        public int getItemCount() {
            return billDetails.size();
        }

        public class ViewHoder extends RecyclerView.ViewHolder {
            ImageView imv_product;
            TextView tv_name, tv_sl, tv_size, tv_color, tv_price;
            public ViewHoder(@NonNull View itemView) {
                super(itemView);
                imv_product = itemView.findViewById(R.id.imv_product);
                tv_name = itemView.findViewById(R.id.tv_name);
                tv_sl = itemView.findViewById(R.id.tv_sl);
                tv_size = itemView.findViewById(R.id.tv_size);
                tv_price = itemView.findViewById(R.id.tv_price);
                tv_color = itemView.findViewById(R.id.tv_color);
//            txtDanhMuc = itemView.findViewById(R.id.item_danhmucSP);
            }
        }
    }

