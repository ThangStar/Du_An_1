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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.HttpsTrustManager;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.Link;
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;


public class DaoSanPham {
    Context context;
    String TAG="-TAG";
    public DaoSanPham(Context context) {
        this.context = context;
    }

    public  void intsert_sanpham( Sanpham sanpham){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.intsert_sanpham, new Response.Listener<String>() {
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

                stringStringMap.put("madanhmuc", String.valueOf(sanpham.getMadanhmuc()));
                stringStringMap.put("tensanpham",sanpham.getTensanpham());
                stringStringMap.put("hinhanh",sanpham.getImg() );// chuyển hình thành base 64

                stringStringMap.put("nhasanxuat", sanpham.getNhasanxuat());
                stringStringMap.put("soluong", String.valueOf(sanpham.getSoluong()));

                stringStringMap.put("giaban", String.valueOf(sanpham.getGiaban()));
                stringStringMap.put("chitiet",sanpham.getChitiet());
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
    public  void delete_sanpham( int masanpham){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.delete_sanpham, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "xóa thành công");
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

                stringStringMap.put("masanpham", String.valueOf(masanpham));

                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void update_sanpham( Sanpham sanpham){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.update_sanpham, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "câp nhập thành công");
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

                stringStringMap.put("masanpham", String.valueOf(sanpham.getMasanpham()));
                stringStringMap.put("madanhmuc", String.valueOf(sanpham.getMadanhmuc()));
                stringStringMap.put("tensanpham",sanpham.getTensanpham());
                stringStringMap.put("hinhanh",sanpham.getImg() );// chuyển hình thành base 64

                stringStringMap.put("nhasanxuat", sanpham.getNhasanxuat());
                stringStringMap.put("soluong", String.valueOf(sanpham.getSoluong()));

                stringStringMap.put("giaban", String.valueOf(sanpham.getGiaban()));
                stringStringMap.put("chitiet",sanpham.getChitiet());
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void getdata_sanpham(){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_sanpham, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>> "+response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        try {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            int masanpham=jsonObject.getInt("masanpham");
                            int madanhmuc= jsonObject.getInt("madanhmuc");
                            String tensanpham= jsonObject.getString("tensanpham");
                            String img = jsonObject.getString("img");
                            String nhasanxuat=jsonObject.getString("nhasanxuat");
                            int soluong=jsonObject.getInt("soluong");
                            int giaban= jsonObject.getInt("giaban");
                            String chitiet= jsonObject.getString("chitiet");
                            Log.d(TAG, "msp> "+masanpham+" msd >"+masanpham +" ten > "+ tensanpham
                                    +" img > "+img+" nsx > "+nhasanxuat+
                                    " soluong > "+ soluong+" hinhdang > "+
                                    " giaban > "+giaban+" chitiet > "+chitiet);

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
                stringStringMap.put("YEUCAUGEDATAALL", "YEUCAUGEDATAALL");
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
    public  void getdata_sanpham_saphet(){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_sanpham, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>> "+response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        try {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            int masanpham=jsonObject.getInt("masanpham");
                            int madanhmuc= jsonObject.getInt("madanhmuc");
                            String tensanpham= jsonObject.getString("tensanpham");
                            String img = jsonObject.getString("img");
                            String nhasanxuat=jsonObject.getString("nhasanxuat");
                            int soluong=jsonObject.getInt("soluong");
                            int giaban= jsonObject.getInt("giaban");
                            String chitiet= jsonObject.getString("chitiet");
                            Log.d(TAG, "msp> "+masanpham+" msd >"+masanpham +" ten > "+ tensanpham
                                    +" img > "+img+" nsx > "+nhasanxuat+
                                    " soluong > "+ soluong+" hinhdang > "+
                                    " giaban > "+giaban+" chitiet > "+chitiet);

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
                stringStringMap.put("YEUCAUGEDATAALL", "YEUCAUGEDATASAPHET");
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
    public  void search_sanpham(String noidungsearch){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.search_sanpham, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>> "+response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        try {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            int masanpham=jsonObject.getInt("masanpham");
                            int madanhmuc= jsonObject.getInt("madanhmuc");
                            String tensanpham= jsonObject.getString("tensanpham");
                            String img = jsonObject.getString("img");
                            String nhasanxuat=jsonObject.getString("nhasanxuat");
                            int soluong=jsonObject.getInt("soluong");
                            int giaban= jsonObject.getInt("giaban");
                            String chitiet= jsonObject.getString("chitiet");
                            Log.d(TAG, "msp> "+masanpham+" msd >"+masanpham +" ten > "+ tensanpham
                                    +" img > "+img+" nsx > "+nhasanxuat+
                                    " soluong > "+ soluong+" hinhdang > "+
                                    " giaban > "+giaban+" chitiet > "+chitiet);

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
                stringStringMap.put("noidungsearch", noidungsearch);
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
    public  void update_soluong_sanpham(int masanpham,int soluong){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.update_soluong_sanpham, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "câp nhập thành công");
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

                stringStringMap.put("masanpham", String.valueOf(masanpham));
                stringStringMap.put("soluong", String.valueOf(soluong));
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
}
