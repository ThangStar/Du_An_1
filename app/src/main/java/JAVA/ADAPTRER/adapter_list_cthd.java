package JAVA.ADAPTRER;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import MODEL.OOP.ChiTietHoaDon;

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
        tv_price = view.findViewById(R.id.tv_gia);
        tv_sum_price = view.findViewById(R.id.tv_tonggia);
        tv_color = view.findViewById(R.id.tv_color);

        ChiTietHoaDon o = list.get(i);
        tv_name.setText("Tên : "+o.getTensanpham());
        tv_sl.setText("Số lương : "+o.getSoluong() );
        tv_size.setText("Size : "+o.getTenkichthuoc());
        tv_color.setText("Color : "+o.getTenmau());
        tv_price.setText("Giá mua : "+o.getGiamua());
        tv_sum_price.setText("đ : "+o.getTongtien());
        Log.d("TAG", "getView: "+o.getImg());
        Glide.with(context).load(o.getImg()).into(imv_product);

        return view;
    }
}
