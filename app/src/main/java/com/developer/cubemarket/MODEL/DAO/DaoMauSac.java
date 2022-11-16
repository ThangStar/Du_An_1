package com.developer.cubemarket.MODEL.DAO;

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
import com.developer.cubemarket.MODEL.KET_NOI_SEVER.HttpsTrustManager;
import com.developer.cubemarket.MODEL.KET_NOI_SEVER.Link;
import MODEL.OOP.Mausac;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class DaoMauSac {
    Context context;
    String TAG="TAG";

    public DaoMauSac(Context context) {
        this.context = context;
    }
    public  void insert_mausac(Mausac mausac){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_mausac, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "thành công");
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
                stringStringMap.put("masanpham", String.valueOf(mausac.getMasanpham()));
                stringStringMap.put("tenmausac",mausac.getTenmausac());

                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
    public  void delete_mausac(int mamausac){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.delete_mausac, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "thành công");
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
                stringStringMap.put("mamausac", String.valueOf(mamausac));
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
    public  void getdata_mausac(int masanpham){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_mausac, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>> "+response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        try {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            int mamausac= jsonObject.getInt("mamausac");
                            int masanpham=jsonObject.getInt("masanpham");
                            String tenmau= jsonObject.getString("tenmausac");

                            Log.d(TAG, "mamau > "+mamausac+" msp >"+masanpham +" ten > "+ tenmau);

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
                stringStringMap.put("masanpham", String.valueOf(masanpham));
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
}
