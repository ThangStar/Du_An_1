package JAVA.VIEW;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.developer.cubemarket.R;

import java.util.List;

import JAVA.ADAPTRER.adapter_list_cthd;
import MODEL.DAO.DaoChiTietHoaDon;
import MODEL.IResult.IResult_chitiethoadon;
import MODEL.OOP.ChiTietHoaDon;

public class LayoutChitiethoadonActivity extends AppCompatActivity {
    String id_hoadon,day_buy,price,adress=null;
    ListView list_cthd;
    TextView txt_mhd,txt_ngaymua,txt_diachi,txt_gia;
    public  static adapter_list_cthd adapter_list_cthds;
    IResult_chitiethoadon mResultCallback_chitiethoadon = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitiethoadon);
        list_cthd=(ListView) findViewById( R.id.list_cthd);
        txt_mhd=(TextView)findViewById(R.id.txt_cthd_mahoadon);
        txt_ngaymua=(TextView)findViewById(R.id.txt_cthd_ngaytao);
        txt_diachi=(TextView)findViewById(R.id.txt_cthd_diachi);
        txt_gia=(TextView)findViewById(R.id.txt_cthd_thanhtien);

        Intent intent = getIntent();
        if(intent!=null){
            id_hoadon=intent.getStringExtra("id_hoadon").toString().trim();
            day_buy=intent.getStringExtra("day_buy").toString().trim();
            price=intent.getStringExtra("price").toString().trim();
            adress=intent.getStringExtra("adress").toString().trim();
        }
        if(id_hoadon!=null){
            txt_mhd.setText("Mã hóa đơn : "+id_hoadon);
            txt_ngaymua.setText("Ngày mua : "+day_buy);
            txt_gia.setText("Thành tiền : "+price);
            txt_diachi.setText("Địa chỉ : "+adress);
            mResultCallback_chitiethoadon= new IResult_chitiethoadon() {
                @Override
                public void notifySuccess(String requestType, List<ChiTietHoaDon> response) {
                     adapter_list_cthds= new adapter_list_cthd(LayoutChitiethoadonActivity.this,R.layout.list_chitiethoadon,response);
                    list_cthd.setAdapter(adapter_list_cthds);
                }

                @Override
                public void notifyError(String requestType, VolleyError error) {
                    Log.d("TAG", "loix >>>>>>>: "+requestType+">>>>>>>>>>"+error);
                }
            };

            DaoChiTietHoaDon daoChiTietHoaDon= new DaoChiTietHoaDon(mResultCallback_chitiethoadon,LayoutChitiethoadonActivity.this);
            daoChiTietHoaDon.getdata_chitiet_hoadon(Integer.parseInt(id_hoadon));
        }
    }
      





}
