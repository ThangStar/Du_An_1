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

import java.util.HashMap;
import java.util.Map;

import com.developer.cubemarket.MODEL.KET_NOI_SEVER.HttpsTrustManager;
import MODEL.KET_NOI_SEVER.Link;
import MODEL.OOP.GioHang;

public class DaoGioHang {
    Context context;
    String TAG="TAG";
    public DaoGioHang(Context context) {
        this.context = context;
    }
    public  void insert_giohang(GioHang gioHang){
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
                stringStringMap.put("id", String.valueOf(gioHang.getId()));
                stringStringMap.put("masanpham",String.valueOf(gioHang.getMasanpham()));
                stringStringMap.put("mamausac",String.valueOf(gioHang.getMamausac()));
                stringStringMap.put("makichthuoc",String.valueOf(gioHang.getMakichthuoc()));

                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
    public  void getdata_giohang(int id){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_giohang, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>> "+response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        try {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            int magiohang=jsonObject.getInt("magiohang");
                            int mamausac= jsonObject.getInt("mamausac");
                            int makichthuoc= jsonObject.getInt("makichthuoc");
                            int giasanpham= jsonObject.getInt("giaban");
                            String tensanpham=jsonObject.getString("tensanpham");
                            String tenmausac=jsonObject.getString("tenmau");
                            String tenkichthuoc=jsonObject.getString("tenkichthuoc");
                            String img=jsonObject.getString("img");

                            Log.d(TAG, "magiohang-> "+magiohang+" mamasac -> "+mamausac +" makichthuoc -> "+makichthuoc+ " giasanpham -> "+giasanpham

                            +" tensanpham -> "+ tensanpham +" tenmausac -> "+tenmausac + " tenkichthuoc ->? "+tenkichthuoc+" img -> "+img
                            );

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
    public  void delete_giohang_all(int id){
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

    }
    public  void update_soluong_giohang( int magiohang,int soluong){
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
                stringStringMap.put("soluong", String.valueOf(soluong));
                stringStringMap.put("update","UDATESOLUONG");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void update_mausac_giohang( int magiohang,int id,int masanpham,int makichthuoc,int mamausac){
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
                stringStringMap.put("masanpham", String.valueOf(masanpham));
                stringStringMap.put("makichthuoc", String.valueOf(makichthuoc));
                stringStringMap.put("mamausac", String.valueOf(mamausac));

                stringStringMap.put("update","UDATEMAUSAC");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void update_kichthuoc_giohang( int magiohang,int id,int masanpham,int makichthuoc,int mamausac){
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
                stringStringMap.put("masanpham", String.valueOf(masanpham));
                stringStringMap.put("makichthuoc", String.valueOf(makichthuoc));
                stringStringMap.put("mamausac", String.valueOf(mamausac));

                stringStringMap.put("update","UDATEKICHTHUOC");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
}
