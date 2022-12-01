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
import com.developer.cubemarket.connection.MODEL.OOP.CommentProduct;
import com.developer.cubemarket.connection.callback.CallBackGetComment;
import com.developer.cubemarket.connection.callback.CallBackInsertCmt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DaoCommentProduct {
    Context context;
    String TAG="TAG";

    public DaoCommentProduct(Context context) {
        this.context = context;
    }
    public  void update_comment(int masanpham,int id_user_nguoi_dang,String noidung,int sosao){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.update_comment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "thành công");
                }else if(response.toString().trim().equals("Error1")){
                    Log.d(TAG, "bạn chưa mua sản phẩm này ");
                }
                else{
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
                stringStringMap.put("id_product", String.valueOf(masanpham));
                stringStringMap.put("id_user", String.valueOf(id_user_nguoi_dang));
                stringStringMap.put("conten_comment", noidung);
                stringStringMap.put("star", String.valueOf(sosao));
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
    public  void getdata_comment(CallBackGetComment callBackGetComment, int masanpham ){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_comment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("NO")){
                    Log.d(TAG, "Chưa có bimnhf luận nào");
                }else{
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0 ; i<jsonArray.length();i++){
                            try {
                                JSONObject jsonObject= jsonArray.getJSONObject(i);

                                String tennguoidang= jsonObject.getString("name_user");
                                String phone= jsonObject.getString("phone_user");
                                String noidung= jsonObject.getString("conten_comment");
                                String ngaydang= jsonObject.getString("day_comment");
                                int sosao=jsonObject.getInt("star");

                                callBackGetComment.onSuccess(new CommentProduct(tennguoidang,phone,noidung,sosao,ngaydang));
                                //---------------------------------------viets code ở dưới này---------------------------------------

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.d(TAG, "đã xảy ra lỗi : gggg"+e);
                            }

                        }
                        callBackGetComment.onFinish();
                    } catch (JSONException e) {
                        callBackGetComment.onFail("Lấy dữ liệu cmt thất bại: "+e);
                        Log.d(TAG, "đã xảy ra lỗi : llllll"+e);
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackGetComment.onError("Xảy ra lỗi: "+error);
                Log.d(TAG, "xảy ra lỗi >>>>" +error);


            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();

                stringStringMap.put("id_product", String.valueOf(masanpham));


                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }

    public  void insert_comment(CallBackInsertCmt callBackInsertCmt, int masanpham, int id_user_nguoi_dang, String noidung, float sosao){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_comment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    callBackInsertCmt.onSuccess();
                    Log.d(TAG, "thành công");
                }else if(response.toString().trim().equals("Error")){
                    Log.d(TAG, "Bạn chưa mua sản phẩm nay ");
                }else if(response.toString().trim().equals("Error1")){
                    callBackInsertCmt.onFail("mỗi người chỉ được bình luận 1 lần ");
                    Log.d(TAG, "mỗi người chỉ được bình luận 1 lần ");
                }
                else{
                    Log.d(TAG, "lỗi>>"+response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackInsertCmt.onError("Lỗi: "+error);
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }


        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("id_product", String.valueOf(masanpham));
                stringStringMap.put("id_user", String.valueOf(id_user_nguoi_dang));
                stringStringMap.put("conten_comment", noidung);
                stringStringMap.put("star", String.valueOf(sosao));
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
}