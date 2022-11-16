package MODEL.DAO;


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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import MODEL.IResult.IResult_danhmuc;
import MODEL.KET_NOI_SEVER.HttpsTrustManager;
import MODEL.KET_NOI_SEVER.Link;
import MODEL.OOP.Danhmuc;

public class DaoDanhMuc {
    Context context;
    String TAG="TAG";
    IResult_danhmuc mResultCallback = null;

    public DaoDanhMuc(IResult_danhmuc resultCallback, Context context) {
        mResultCallback = resultCallback;
        this.context = context;
    }

    public  void insert_danhmuc( Danhmuc danhmuc){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_danhmuc, new Response.Listener<String>() {
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
    public  void delete_danhmuc( int madanhmuc){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.delete_danhmuc, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>>" +response);
                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "xóa thành công");
                }else{
                    Log.d(TAG, "xóa không thành công");
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
                stringStringMap.put("madanhmuc", String.valueOf(madanhmuc));
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
    public  void update_danhmuc(Danhmuc danhmuc){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.update_danhmuc, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>>" +response);
                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "cập nhập thành công");
                }else{
                    Log.d(TAG, "cập nhập không thành công");
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
                stringStringMap.put("madanhmuc", String.valueOf(danhmuc.getMadanhmuc()));
                stringStringMap.put("tendanhmuc",danhmuc.getTendanhmuc());
                stringStringMap.put("khuvuc",danhmuc.getKhuvuc());
                stringStringMap.put("img",danhmuc.getImg());
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
    public  void getdata_danhmuc(){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, Link.getdata_danhmuc,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Danhmuc> list= new ArrayList();
                for (int i = 0 ; i<response.length();i++){
                    try {
                        JSONObject jsonObject= response.getJSONObject(i);
                     int   madanhmuc= jsonObject.getInt("madanhmuc");
                      String  tendanhmuc= jsonObject.getString("tendanhmuc");
                        String  khuvuc= jsonObject.getString("khuvuc");
                        String img=jsonObject.getString("img") ;
                        Log.d(TAG, "ma >> "+madanhmuc +" tên >>" +tendanhmuc +" khuvuwc  >> "+ khuvuc+" img >>>> "+img);
                        //---------------------------------------viets code ở dưới này---------------------------------------

                        list.add(new Danhmuc(madanhmuc,tendanhmuc,khuvuc,img));



                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d(TAG, "đã xảy ra lỗi : gggg"+e);
                    }

                }
                if(mResultCallback != null){

                    mResultCallback.notifySuccess("danhmuc", list);
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
