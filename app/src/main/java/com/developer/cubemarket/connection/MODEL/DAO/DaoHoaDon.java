package com.developer.cubemarket.connection.MODEL.DAO;

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
import com.developer.cubemarket.connection.IResult.IResult_hoadon;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.HttpsTrustManager;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.Link;
import com.developer.cubemarket.connection.MODEL.OOP.Hoadon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DaoHoaDon {
    Context context;
    String TAG="TAG";
    IResult_hoadon mResultCallback = null;

    public DaoHoaDon( Context context) {

        this.context = context;
    }
    public  void insert_hoadon( int id,String tendiachi,String nhapmagiamgia ){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_hoadon, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, ">>>>>>>>>>>>>>>>>>>>>> " +response) ;
                String a=response;
                String b[]=a.split(":");
                if(b[0].toString().trim().equals("sssss")){
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

                stringStringMap.put("id", String.valueOf(id));
                stringStringMap.put("tendiachi",tendiachi);
                stringStringMap.put("nhapma",nhapmagiamgia);
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void getdata_hoadon_admin(IResult_hoadon mResultCallback, int id ){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_hoadon, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Hoadon> ee = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        try {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            int mahoadon=jsonObject.getInt("mahoadon");
                            int id= jsonObject.getInt("id");
                            String phantramkhuyenmai= jsonObject.getString("phantramkhuyenmai");
                            String tendiachi= jsonObject.getString("tendiachi");
                            String ngaymua=jsonObject.getString("ngaymua");
                            int tongtien=jsonObject.getInt("tongtien");
                            int sotiengiam=jsonObject.getInt("sotiengiam");
                            int sotienphaitra=jsonObject.getInt("phaitra");
                            int soluonghoadon= jsonObject.getInt("soluong");

                            ee.add(new Hoadon(mahoadon,id,phantramkhuyenmai,tendiachi,ngaymua,tongtien,sotiengiam,sotienphaitra,soluonghoadon));
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

                    mResultCallback.notifySuccess("hoadon", ee);
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

                stringStringMap.put("id", String.valueOf(id));
                stringStringMap.put("check","0");

                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void getdata_hoadon_user( IResult_hoadon mResultCallback,int id ){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_hoadon, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);
                List<Hoadon> ee = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        try {
                            Log.d(TAG, "onResponse: ");
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            int mahoadon=jsonObject.getInt("mahoadon");
                            int id= jsonObject.getInt("id");
                            String phantramkhuyenmai= jsonObject.getString("phantramkhuyenmai");
                            String tendiachi= jsonObject.getString("tendiachi");
                            String ngaymua=jsonObject.getString("ngaymua");
                            int tongtien=jsonObject.getInt("tongtien");
                            int sotiengiam=jsonObject.getInt("sotiengiam");
                            int sotienphaitra=jsonObject.getInt("phaitra");
                            int soluonghoadon= jsonObject.getInt("soluong");

                            ee.add(new Hoadon(mahoadon,id,phantramkhuyenmai,tendiachi,ngaymua,tongtien,sotiengiam,sotienphaitra,soluonghoadon));
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

                    mResultCallback.notifySuccess("hoadon", ee);
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

                stringStringMap.put("id", String.valueOf(id));
                stringStringMap.put("check","123");

                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
}
