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
import com.developer.cubemarket.connection.MODEL.IResult.IResult_giohang;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.HttpsTrustManager;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.Link;
import com.developer.cubemarket.connection.MODEL.OOP.GioHang;
import com.developer.cubemarket.connection.callback.CallBackAddCart;
import com.developer.cubemarket.connection.callback.CallBackDeleteCart;
import com.developer.cubemarket.connection.callback.CallBackSelectCart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DaoGioHang {
    Context context;
    String TAG="TAG";

    public DaoGioHang(Context context) {
        this.context = context;
    }
    public  void insert_giohang(CallBackAddCart callBackAddCart,  int id , int option_id){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_giohang, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "thành công");
                    callBackAddCart.onSuccess(response);
                }else{
                    callBackAddCart.onFail("Lỗi: "+response);
                    Log.d(TAG, "lỗi>>"+response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackAddCart.onError("Lỗi: "+error);
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("id", String.valueOf(id));
                stringStringMap.put("option_id",String.valueOf(option_id));


                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }

    public  void getdata_giohang(CallBackSelectCart callBackSelectCart, int id){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_giohang, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>> "+response);
                List<GioHang>ee= new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        try {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            int id_option=jsonObject.getInt("id_option");
                            int magiohang=jsonObject.getInt("magiohang");
                            int masanpham= jsonObject.getInt("id_products");
                            int id= jsonObject.getInt("id");
                            int gia= jsonObject.getInt("price");
                            int soluong= jsonObject.getInt("number_cart");
                            String tensanpham=jsonObject.getString("name_product");
                            String tenmausac=jsonObject.getString("name_color");
                            String tenkichthuoc=jsonObject.getString("name_size");
                            String img=jsonObject.getString("img");

                            callBackSelectCart.onSuccess(new GioHang(id_option,magiohang,id,masanpham,gia,soluong,
                                    tenmausac,tenkichthuoc,tensanpham,img));
                            //---------------------------------------viets code ở dưới này---------------------------------------

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "đã xảy ra lỗi : gggg"+e);
                        }

                    }
                } catch (JSONException e) {
                    callBackSelectCart.onFail("Lỗi "+e);
                    Log.d(TAG, "đã xảy ra lỗi : llllll"+e);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackSelectCart.onError("Lỗi: "+error);
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("id", String.valueOf(id));
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }



    public  void delete_giohang(CallBackDeleteCart callBackDeleteCart, int magiohang){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.delete_giohang, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "thành công");
                    callBackDeleteCart.onSuccess();
                }else{
                    Log.d(TAG, "lỗi>>"+response.toString());
                    callBackDeleteCart.onFail();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackDeleteCart.onError();
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("magiohang", String.valueOf(magiohang));
                stringStringMap.put("delete", "DELETE_GIOHANG");
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }

    /*  public  void delete_giohang_all(int id){
         RequestQueue requestQueue = Volley.newRequestQueue(context);
         HttpsTrustManager.allowAllSSL();
         StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.delete_giohang, new Response.Listener<String>() {
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
                 stringStringMap.put("id", String.valueOf(id));
                 stringStringMap.put("delete", "DELETE_GIOHANG_ALL");
                 return stringStringMap;
             }
         };
         requestQueue.add(stringRequest);

     }*/
    public  void update_soluong_giohang_tangthem1( int magiohang){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.update_giohang, new Response.Listener<String>() {
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
                stringStringMap.put("magiohang", String.valueOf(magiohang));
                stringStringMap.put("cong", String.valueOf(0));
                stringStringMap.put("update", "UDATESOLUONG");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void update_soluong_giohang_giamthem1( int magiohang){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.update_giohang, new Response.Listener<String>() {
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
                stringStringMap.put("magiohang", String.valueOf(magiohang));
                stringStringMap.put("cong", String.valueOf(1));
                stringStringMap.put("update", "UDATESOLUONG");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void update_option( int magiohang,int id,int id_option){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.update_giohang, new Response.Listener<String>() {
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
                stringStringMap.put("magiohang", String.valueOf(magiohang));
                stringStringMap.put("id", String.valueOf(id));
                stringStringMap.put("id_option", String.valueOf(id_option));
                stringStringMap.put("update","UDATEOPTION");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }

}
