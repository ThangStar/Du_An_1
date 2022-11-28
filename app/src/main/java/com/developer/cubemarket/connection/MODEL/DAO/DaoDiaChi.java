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
import com.developer.cubemarket.connection.MODEL.IResult.IResult_diachi;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.HttpsTrustManager;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.Link;
import com.developer.cubemarket.connection.MODEL.OOP.Diachi;
import com.developer.cubemarket.connection.callback.CallBackGetAddress;
import com.developer.cubemarket.connection.callback.CallBackInsertAddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DaoDiaChi {
    Context context;
    String TAG="TAG";
    public DaoDiaChi(Context context) {
        this.context = context;
    }
    public  void insert_diachi(CallBackInsertAddress callBackInsertAddress, int id, String tendiachi){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_diachi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "thêm thành công");
                    callBackInsertAddress.onSuccess("Đã thêm");
                }else{
                    callBackInsertAddress.onFail("Thêm địa chỉ thất bại");
                    Log.d(TAG, "lỗi>>"+response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackInsertAddress.onSuccess("Xảy ra lỗi: "+error);
                Log.d(TAG, "xảy ra lỗi >>>>" +error);


            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();

                stringStringMap.put("id", String.valueOf(id));
                stringStringMap.put("tendiachi", tendiachi);

                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void update_diachi( int madiachi, String tendiachi){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.update_diachi, new Response.Listener<String>() {
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

                stringStringMap.put("madiachi", String.valueOf(madiachi));
                stringStringMap.put("tendiachi", tendiachi);

                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void delete_diachi( int madiachi){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.delete_diachi, new Response.Listener<String>() {
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

                stringStringMap.put("madiachi", String.valueOf(madiachi));

                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void getdata_diachi(CallBackGetAddress callBackGetAddress, int id){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_diachi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>> "+response);
                List<Diachi> ee= new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        try {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            int madiachi= jsonObject.getInt("madiachi");
                            int id= jsonObject.getInt("id");
                            String tendiachi= jsonObject.getString("tendiachi");
                            ee.add(new Diachi(madiachi,id,tendiachi));

                            //---------------------------------------viets code ở dưới này---------------------------------------
                            callBackGetAddress.onSuccess(new Diachi(madiachi,id,tendiachi));


                        } catch (JSONException e) {
                            callBackGetAddress.onFail("Lấy dữ liệu thất bại");
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
                callBackGetAddress.onError("Lỗi kết nối: "+error);
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
}