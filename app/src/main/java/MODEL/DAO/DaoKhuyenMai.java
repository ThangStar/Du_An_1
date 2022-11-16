package MODEL.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import MODEL.IResult.IResult_khuyenmai;
import MODEL.KET_NOI_SEVER.HttpsTrustManager;
import MODEL.KET_NOI_SEVER.Link;
import MODEL.OOP.KhuyenMai;

public class DaoKhuyenMai {
    Context context;
    String TAG="TAG";
    IResult_khuyenmai mResultCallback = null;

    public DaoKhuyenMai(IResult_khuyenmai resultCallback, Context context) {
        mResultCallback = resultCallback;
        this.context = context;
    }
    public  void insert_khuyenmai(KhuyenMai khuyenMai){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_khuyenmai, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "thêm thành công");
                }else{
                    Log.d(TAG, "lỗi>>"+response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "xảy ra lỗi >>>>" +error);


            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();

                stringStringMap.put("magiamgia", khuyenMai.getMagiamgia());
                stringStringMap.put("phantramgiam", String.valueOf(khuyenMai.getPhantramgiam()));
                stringStringMap.put("ngaybatdau", khuyenMai.getNgaybatdau());
                stringStringMap.put("ngayketthuc", khuyenMai.getNgayketthuc());
                stringStringMap.put("sotienapdung", String.valueOf(khuyenMai.getSotiengiam()));

                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void update_khuyenmai(KhuyenMai khuyenMai){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.update_khuyenmai, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "thêm thành công");
                }else{
                    Log.d(TAG, "lỗi>>"+response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "xảy ra lỗi >>>>" +error);


            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();

                stringStringMap.put("magiamgia", khuyenMai.getMagiamgia());
                stringStringMap.put("phantramgiam", String.valueOf(khuyenMai.getPhantramgiam()));
                stringStringMap.put("ngaybatdau", khuyenMai.getNgaybatdau());
                stringStringMap.put("ngayketthuc", khuyenMai.getNgayketthuc());
                stringStringMap.put("sotienapdung", String.valueOf(khuyenMai.getSotiengiam()));

                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void delete_khuyenmai(String makhuyenmai){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.delete_khuyenmai, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "thêm thành công");
                }else{
                    Log.d(TAG, "lỗi>>"+response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "xảy ra lỗi >>>>" +error);


            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();

                stringStringMap.put("magiamgia",makhuyenmai);


                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void getdata_all(){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_khuyenmai, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>> "+response);
                List<KhuyenMai> ee= new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        try {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            String magiamgia=jsonObject.getString("magiamgia");
                            int phantramgiamgia=jsonObject.getInt("phantramgiamgia");
                            String ngaybatdau=jsonObject.getString("ngaybatdau");
                            String ngayketthuc=jsonObject.getString("ngayketthuc");
                            int sotienapdung=jsonObject.getInt("sotienapdung");
                          ee.add(new KhuyenMai(magiamgia,phantramgiamgia,ngaybatdau,ngayketthuc,sotienapdung));
                            //---------------------------------------viets code ở dưới này---------------------------------------




                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "đã xảy ra lỗi : gggg"+e);
                        }

                    }
                } catch (JSONException e) {
                    Log.d(TAG, "đã xảy ra lỗi : llllll"+e);
                    e.printStackTrace();
                }
                if(mResultCallback != null){

                    mResultCallback.notifySuccess("khuyenmai", ee);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("check", "ALL");

                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
    public  void nhapma_giamgia(String magiamgia){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_khuyenmai, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<KhuyenMai> ee= new ArrayList<>();
              if(response.toString().trim().equals("khongtontai")){
                  Log.d(TAG, "ma giảm giá đã hét hạn hoặc không tồn tại");
              }else{

                  try {
                      JSONArray jsonArray = new JSONArray(response);
                      for (int i = 0 ; i<jsonArray.length();i++){
                          try {
                              JSONObject jsonObject= jsonArray.getJSONObject(i);
                              String magiamgia=jsonObject.getString("magiamgia");
                              int phantramgiamgia=jsonObject.getInt("phantramgiamgia");
                              String ngaybatdau=jsonObject.getString("ngaybatdau");
                              String ngayketthuc=jsonObject.getString("ngayketthuc");
                              int sotienapdung=jsonObject.getInt("sotienapdung");
                              ee.add(new KhuyenMai(magiamgia,phantramgiamgia,ngaybatdau,ngayketthuc,sotienapdung));
                              //---------------------------------------viets code ở dưới này---------------------------------------




                          } catch (JSONException e) {
                              e.printStackTrace();
                              Log.d(TAG, "đã xảy ra lỗi : gggg"+e);
                          }

                      }
                  } catch (JSONException e) {
                      Log.d(TAG, "đã xảy ra lỗi : llllll"+e);
                      e.printStackTrace();
                  }
              }
                if(mResultCallback != null){

                    mResultCallback.notifySuccess("khuyenmai", ee);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("check", "CHECK_MA");
                stringStringMap.put("nhapma", magiamgia);

                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}
