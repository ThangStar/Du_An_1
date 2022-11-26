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
import com.developer.cubemarket.connection.MODEL.OOP.Danhmuc;
import com.developer.cubemarket.connection.MODEL.OOP.Option;
import com.developer.cubemarket.connection.callback.CallBackDataOption;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DaoOption {
    Context context;
    String TAG="TAG";


    public DaoOption(Context context) {
        this.context = context;
    }
    public  void insert_option( int id_product,String option){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_option, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String a[]=response.toString().split(":");
                if(a[0].trim().equals("s")){
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
                Danhmuc danhmuc = new Danhmuc();
                stringStringMap.put("id_product", String.valueOf(id_product));
                stringStringMap.put("option",option);

                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void delete_option( int id_option){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.delete_option, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String a[]=response.toString().split(":");
                if(a[0].trim().equals("s")){
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
                Danhmuc danhmuc = new Danhmuc();
                stringStringMap.put("id_option", String.valueOf(id_option));


                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void getdata_option(CallBackDataOption callBackDataOption, int id_product){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_option, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>> "+response);
                List<Option> ee= new ArrayList();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        try {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            int id_option=jsonObject.getInt("option_id");
                            int masanpham= jsonObject.getInt("masanpham");
                            String product_name= jsonObject.getString("name_product");
                            String color_name= jsonObject.getString("color_name");
                            String size_name = jsonObject.getString("size_name");
                            int price=jsonObject.getInt("price");
                            int number= jsonObject.getInt("number");
                            callBackDataOption.onSuccess(new Option(id_option,masanpham,product_name,color_name,size_name,
                                    price,number));
                            //---------------------------------------viets code ở dưới này---------------------------------------
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "đã xảy ra lỗi : gggg"+e);
                        }

                    }
                } catch (JSONException e) {
                    callBackDataOption.onFail("Lỗi: "+e);
                    Log.d(TAG, "đã xảy ra lỗi : llllll"+e);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackDataOption.onFail("Lỗi: "+error);
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();

                stringStringMap.put("Id_product", String.valueOf(id_product));

                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}
