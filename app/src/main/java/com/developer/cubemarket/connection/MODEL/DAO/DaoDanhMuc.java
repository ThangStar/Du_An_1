package com.developer.cubemarket.connection.MODEL.DAO;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.HttpsTrustManager;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.Link;
import com.developer.cubemarket.connection.MODEL.OOP.Danhmuc;
import com.developer.cubemarket.connection.callback.CallBackDelDirectory;
import com.developer.cubemarket.connection.callback.CallbackUpdateDirectory;
import com.developer.cubemarket.connection.callback.VolleyCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;


public class DaoDanhMuc {
    Context context;
    String TAG="TAG";


    public DaoDanhMuc(Context context) {
        this.context = context;
    }

    public  void insert_danhmuc(Context ctx, Danhmuc danhmuc){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_danhmuc, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "Thành công");
                    Toasty.success(ctx, "OK", 500).show();
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

                stringStringMap.put("tendanhmuc",danhmuc.getTendanhmuc());
                stringStringMap.put("khuvuc",danhmuc.getKhuvuc());
                stringStringMap.put("img",danhmuc.getImg());// phải ep thành base 64
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    // hàm chuyển ảnh thành base 64
    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return imgString;
    }
    public void delete_danhmuc(CallBackDelDirectory callBackDelDirectory, int madanhmuc){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.delete_danhmuc, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>>" +response);
                if(response.toString().trim().equals("success")){
                    callBackDelDirectory.onUpdateScreen();
                    Log.d(TAG, "xóa thành công");
                }else{
                    callBackDelDirectory.onFail("Xóa thất bại");
                    Log.d(TAG, "xóa không thành công");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackDelDirectory.onError("Xảy ra lỗi: "+error);
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("madanhmuc", String.valueOf(madanhmuc));
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
    public  void update_danhmuc(CallbackUpdateDirectory callbackUpdateDirectory, Danhmuc danhmuc){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.update_danhmuc, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>>" +response);
                if(response.toString().trim().equals("success")){
                    callbackUpdateDirectory.onSuccess();
                }else{
                    callbackUpdateDirectory.onFail();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
                callbackUpdateDirectory.onError();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("madanhmuc", String.valueOf(danhmuc.getMadanhmuc()));
                stringStringMap.put("tendanhmuc",danhmuc.getTendanhmuc());
                stringStringMap.put("khuvuc",danhmuc.getKhuvuc());
                stringStringMap.put("img",danhmuc.getImg());
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
    public  void getdata_danhmuc(final VolleyCallBack callBack){
        Log.d(TAG, "laydulieudanhmuc: ");
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, Link.getdata_danhmuc,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "onResponse: 1");
                Log.d(TAG, "onResponse: >>"+response.toString());
                ArrayList<Danhmuc> arr = new ArrayList<Danhmuc>();
                for (int i = 0 ; i<response.length();i++){
                    try {
                        JSONObject jsonObject= response.getJSONObject(i);
                     int   madanhmuc= jsonObject.getInt("madanhmuc");
                      String  tendanhmuc= jsonObject.getString("tendanhmuc");
                        String  khuvuc= jsonObject.getString("khuvuc");
                        String img=jsonObject.getString("img") ;
                        Log.d(TAG, "ma >> "+madanhmuc +" tên >>" +tendanhmuc +" khuvuwc  >> "+ khuvuc+" img >>>> "+img);
                        //---------------------------------------viets code ở dưới này---------------------------------------
                        callBack.onSuccess(new Danhmuc(madanhmuc, tendanhmuc, khuvuc, img));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d(TAG, "đã xảy ra lỗi : gggg"+e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "đã xảy ra lỗi : "+error);
            }
        }

        );

        requestQueue.add(stringRequest);
    }

}
