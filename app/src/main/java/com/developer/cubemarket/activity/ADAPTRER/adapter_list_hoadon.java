package com.developer.cubemarket.activity.ADAPTRER;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.developer.cubemarket.R;
import com.developer.cubemarket.activity.VIEW.LayoutChitiethoadonActivity;
import com.developer.cubemarket.connection.MODEL.OOP.Hoadon;

import java.util.List;


public class adapter_list_hoadon extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Hoadon> list;

    public adapter_list_hoadon(Context context, int layout, List<Hoadon> list) {
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

            view = LayoutInflater.from(context).inflate(R.layout.list_hoadon,null);
        }
        TextView txt_ngaymua=(TextView) view.findViewById(R.id.txt_hoadon_ngaymua);
        TextView txt_ma=(TextView) view.findViewById(R.id.txt_hoadon_mahoadon);
        TextView txt_diachi=(TextView) view.findViewById(R.id.txt_hoadon_diachi);
        TextView txt_soluong=(TextView) view.findViewById(R.id.txt_hoadon_soluong);
        TextView txt_khuyenmai=(TextView) view.findViewById(R.id.txt_hoadon_khuyenmai);
        TextView txt_gia=(TextView) view.findViewById(R.id.txt_hoadon_gia);
        Button bnt=(Button)view.findViewById(R.id.bnt_hoadon_chitiet);

        Hoadon o = list.get(i);
        txt_ngaymua.setText("Ngày mua :"+o.getNgaymua());
        txt_ma.setText("Mã :"+o.getMahoadon());
        txt_diachi.setText("Địa chỉ :"+o.getTendiachi());
        txt_soluong.setText("Số lượng :"+o.getSoluonghoadon());
        txt_khuyenmai.setText("Khuễn mãi :"+o.getPhantramkhuyenmai()+"");
        txt_gia.setText("đ "+o.getTongtien());
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, LayoutChitiethoadonActivity.class);
                intent.putExtra("id_hoadon",String.valueOf(o.getMahoadon()));
                intent.putExtra("day_buy",String.valueOf(o.getNgaymua()));
                intent.putExtra("adress",String.valueOf(o.getTendiachi()));
                intent.putExtra("price",String.valueOf(o.getSotienphaitra()));
                context.startActivity(intent);
            }
        });

        return view;
    }
}
