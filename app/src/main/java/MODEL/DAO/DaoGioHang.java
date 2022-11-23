
package MODEL.DAO;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import MODEL.IResult.IResult_giohang;
import MODEL.KET_NOI_SEVER.HttpsTrustManager;
import MODEL.KET_NOI_SEVER.Link;
import MODEL.OOP.GioHang;

public class DaoGioHang {
    Context context;
    String TAG="TAG";
    IResult_giohang mResultCallback = null;

    public DaoGioHang(Context context) {
        this.context = context;
    }
    public  void insert_giohang(int id ,int option_id){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_giohang, new Response.Listener<String>() {
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
                stringStringMap.put("option_id",String.valueOf(option_id));


                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }

  public  void getdata_giohang(IResult_giohang mResultCallback,int id){
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

                            ee.add(new GioHang(id_option,magiohang,id,masanpham,gia,soluong,tensanpham,tenmausac,tenkichthuoc,img));

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

                    mResultCallback.notifySuccess("giohanhg", ee);
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
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }



   public  void delete_giohang(int magiohang){
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

