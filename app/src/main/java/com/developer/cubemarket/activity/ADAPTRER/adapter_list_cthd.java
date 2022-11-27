package com.developer.cubemarket.activity.ADAPTRER;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.developer.cubemarket.R;
import com.developer.cubemarket.config.utils.Utils;
import com.developer.cubemarket.connection.MODEL.OOP.ChiTietHoaDon;

import java.util.List;


public class adapter_list_cthd extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<ChiTietHoaDon> list;

    public adapter_list_cthd(Context context, int layout, List<ChiTietHoaDon> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {

            view = LayoutInflater.from(context).inflate(R.layout.list_chitiethoadon,null);
        }
        ImageView imv_product;
        TextView tv_name, tv_sl, tv_size, tv_color, tv_price,tv_sum_price;
        imv_product = view.findViewById(R.id.imv_product);
        tv_name = view.findViewById(R.id.tv_name);
        tv_sl = view.findViewById(R.id.tv_sl);
        tv_size = view.findViewById(R.id.tv_size);
        tv_sum_price = view.findViewById(R.id.tv_tonggia);
        tv_color = view.findViewById(R.id.tv_color);

        ChiTietHoaDon o = list.get(i);
        tv_name.setText("Tên : "+o.getTensanpham());
        tv_sl.setText("Số lương : "+o.getSoluong() );
        tv_size.setText("Kích thước : "+o.getTenkichthuoc());
        tv_color.setText("Màu : "+o.getTenmau());
        tv_sum_price.setText(Utils.Companion.formaterVND(o.getTongtien()));
        Log.d("TAG", "getView: "+o.getImg());
        Glide.with(context).load(o.getImg()).into(imv_product);

        return view;
    }
}
