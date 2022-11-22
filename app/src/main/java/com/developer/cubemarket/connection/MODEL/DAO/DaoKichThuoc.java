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
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.HttpsTrustManager;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.Link;
import com.developer.cubemarket.connection.MODEL.OOP.Kichthuoc;
import com.developer.cubemarket.callback.CallBackDeleteSize;
import com.developer.cubemarket.callback.CallBackInsertSize;
import com.developer.cubemarket.callback.CallBackSizeProduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class DaoKichThuoc {
    Context context;
    String TAG="TAG";
    public DaoKichThuoc(Context context) {
        this.context = context;
    }
    public  void insert_kichthuoc(CallBackInsertSize callBackInsertSize, Kichthuoc kichthuoc){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_kichthuoc, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    callBackInsertSize.onSuccess("Thành công");
                    Log.d(TAG, "thành công");
                }else{
                    callBackInsertSize.onFail("Lỗi: "+response);
                    Log.d(TAG, "lỗi>>"+response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackInsertSize.onFail("Đã xảy ra lỗi: "+error);
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("masanpham", String.valueOf(kichthuoc.getMasanpham()));
                stringStringMap.put("tenkichthuoc",kichthuoc.getTenkichthuoc());

                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
    public  void delete_kichthuoc(CallBackDeleteSize callBackDeleteSize, int makichthuoc){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.delete_kichthuoc, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    callBackDeleteSize.onSuccess("Thành công");
                    Log.d(TAG, "thành công");
                }else{
                    callBackDeleteSize.onFail("Lỗi: "+response);
                    Log.d(TAG, "lỗi>>"+response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackDeleteSize.onFail("Đã xảy ra lỗi: "+error);
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("makichthuoc", String.valueOf(makichthuoc));
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
    public  void getdata_kichthuoc(CallBackSizeProduct callBackSize, int masanpham){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_kichthuoc, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>> "+response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        try {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            int makichThuoc= jsonObject.getInt("makichthuoc");
                            int maSanPham=jsonObject.getInt("masanpham");
                            String tenKichThuoc= jsonObject.getString("tenkichthuoc");

//                            Log.d(TAG, "mamau > "+mamausac+" msp >"+masanpham +" ten > "+ tenmau);

                            //---------------------------------------viets code ở dưới này---------------------------------------
                            callBackSize.onSuccess(new Kichthuoc(makichThuoc,maSanPham,tenKichThuoc));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "đã xảy ra lỗi : gggg"+e);
                        }

                    }
                } catch (JSONException e) {
                    callBackSize.onFail(e.getMessage());
                    Log.d(TAG, "đã xảy ra lỗi : llllll"+e);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackSize.onError(error.getMessage());
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("masanpham", String.valueOf(masanpham));
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
}
