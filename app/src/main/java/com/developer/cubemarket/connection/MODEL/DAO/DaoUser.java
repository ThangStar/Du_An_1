package com.developer.cubemarket.connection.MODEL.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developer.cubemarket.R;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.HttpsTrustManager;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.Link;
import com.developer.cubemarket.connection.MODEL.OOP.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;


public class DaoUser {
    Context context;
    String TAG="TAG";
    String tenchucvu="Người dùng";

    public DaoUser(Context context) {
        this.context = context;
    }

    public void insert_user(User user){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.intsert_user, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){

                    Toasty.success(context, "Tạo tài khoản thành công!", Toasty.LENGTH_SHORT).show();
                }else{
                    Toasty.error(context, "Lỗi: "+response.toString(), Toasty.LENGTH_SHORT).show();
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
                stringStringMap.put("ten",user.getTen());
                stringStringMap.put("pass",user.getPassword());
                stringStringMap.put("chucvu", String.valueOf(user.getChucvu()));
                stringStringMap.put("gmail",user.getGmail());
                stringStringMap.put("phone",user.getPhone());
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
    public  void updete_user( User user){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.update_user, new Response.Listener<String>() {
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
                stringStringMap.put("id", String.valueOf(user.getId()));
                stringStringMap.put("ten",user.getTen());
                stringStringMap.put("pass",user.getPassword());
                stringStringMap.put("chucvu", String.valueOf(user.getChucvu()));
                stringStringMap.put("gmail",user.getGmail());
                stringStringMap.put("phone",user.getPhone());
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public void dangnhap(LinearLayout root, String user, String pass){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_dangnhap, new Response.Listener<String>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onResponse(String response) {
               if(response.toString().trim().equals("ErrorLogin")){
                   Toasty.warning(context, "Tài khoản hoặc mật khẩu không chính xác", Toasty.LENGTH_SHORT).show();
               }else{
                   try {
                       JSONArray jsonArray= new JSONArray(response);
                       for (int i = 0 ; i<jsonArray.length();i++){
                           try {
                               JSONObject jsonObject= jsonArray.getJSONObject(i);
                               int id= jsonObject.getInt("id");
                               String ten = jsonObject.getString("ten");
                               int chuvu=jsonObject.getInt("chucvu");
                               String phone=jsonObject.getString("phone");
                               String gmail=jsonObject.getString("gmail");
                               if(chuvu==1){
                                   tenchucvu="Người bán";
                               }
                               Log.d(TAG, "  d=>  "+id+"  ten=> "+ten+"   chvu=> "+tenchucvu+"   phone=> "+phone+"  gmal=> "+gmail);
                               Toasty.success(context, "đăng nhập thành công!", Toasty.LENGTH_SHORT).show();
                               //--------------------------------------------------------code them doạn này------------------------------------
                               //start home when login success!
                               Navigation.findNavController(root)
                                       .navigate(R.id.action_loginFragment_to_productFragment);
                           } catch (JSONException e) {
                               e.printStackTrace();
                               Toasty.error(context, "đã xảy ra lỗi "+e, Toasty.LENGTH_SHORT).show();
                           }
                       }
                   } catch (JSONException e) {
                       Log.d(TAG, "-----llll--->>"+e);
                       e.printStackTrace();
                   }
               }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toasty.error(context, "đã xảy ra lỗi "+error, Toasty.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("gmail", user);
                stringStringMap.put("pass", pass);
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}
