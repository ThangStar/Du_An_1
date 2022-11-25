package JAVA.VIEW;



import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.DAO.DaoHoaDon;
import MODEL.IResult.IResult_hoadon;
import MODEL.OOP.Hoadon;

public class LayoutHoadonActivity extends AppCompatActivity {
    ListView list_hoadon;
    public  static  adapter_list_hoadon adapter_list_hoadons;

    IResult_hoadon mResultCallback_hoadon = null;
    int ID=2;
    int CHUCVU=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hoadon);
        list_hoadon=(ListView) findViewById( R.id.list_hoadon);
        hien_thi_list_hoadon();

    }
    private void hien_thi_list_hoadon() {

        mResultCallback_hoadon= new IResult_hoadon() {
            @Override
            public void notifySuccess(String requestType, List<Hoadon> response) {


                adapter_list_hoadons= new adapter_list_hoadon(LayoutHoadonActivity.this,R.layout.list_hoadon,response);
                list_hoadon.setAdapter(adapter_list_hoadons);
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "loix >>>>>>>: "+requestType+">>>>>>>>>>"+error);
            }
        };
        DaoHoaDon daoHoaDo = new DaoHoaDon(LayoutHoadonActivity.this);

        if(CHUCVU==2){
            daoHoaDo.getdata_hoadon_admin(mResultCallback_hoadon,ID);
        }else{
            daoHoaDo.getdata_hoadon_user(mResultCallback_hoadon,ID);
        }










    }

}