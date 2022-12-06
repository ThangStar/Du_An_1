package com.developer.cubemarket.connection.MODEL.DAO;

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
import com.developer.cubemarket.config.share_references.DataShareReferences;
import com.developer.cubemarket.config.user.DataUser;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.HttpsTrustManager;
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.Link;
import com.developer.cubemarket.connection.MODEL.OOP.User;
import com.developer.cubemarket.connection.callback.CallBackChangePass;
import com.developer.cubemarket.connection.callback.CallBackGetCode;
import com.developer.cubemarket.connection.callback.CallBackGetDataUser;
import com.developer.cubemarket.connection.callback.CallBackInsertUser;
import com.developer.cubemarket.connection.callback.CallBackLockAccount;
import com.developer.cubemarket.connection.callback.CallBackLogin;
import com.developer.cubemarket.connection.callback.CallBackUpdatePass;
import com.developer.cubemarket.connection.callback.CallBackUpdatePermissionUser;
import com.developer.cubemarket.connection.callback.CallBackUpdateUser;
import com.developer.cubemarket.fragment.bottom_sheet.BtsChangePermissionUserFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class DaoUser {
    Context context;
    String TAG="TAG";
    String tenchucvu="User";

    public DaoUser(Context context) {
        this.context = context;
    }
    public  void update_chucvu_user(BtsChangePermissionUserFragment btsChangePermissionUserFragment, CallBackUpdatePermissionUser callBackUpdatePermissionUser, int id, int chucvu){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.update_user, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    callBackUpdatePermissionUser.onSuccess(btsChangePermissionUserFragment, "Thành công");
                    Log.d(TAG, "thành công");
                }else{
                    callBackUpdatePermissionUser.onFail("Cập nhật thất bại");
                    Log.d(TAG, "lỗi>>"+response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackUpdatePermissionUser.onFail("Đã xảy ra lỗi"+error);
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("id", String.valueOf(id));

                stringStringMap.put("chucvu", String.valueOf(chucvu));

                stringStringMap.put("update","UPDATECHUCVU");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void insert_user(CallBackInsertUser callBackInsertUser, User user){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_user, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    callBackInsertUser.onSuccess("Tạo tài khoản thành công!");
                }else{
                    callBackInsertUser.onFail("Thất bại: "+response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackInsertUser.onError("Thất bại: "+error);
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
    public void update_user(CallBackUpdateUser callBackUpdateUser, int id , String ten, int chucvu, String phone){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.update_user, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    callBackUpdateUser.onSuccess("thành công");
                }else{
                    callBackUpdateUser.onFail("Lỗi"+response.toString());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackUpdateUser.onError("Lỗi: "+error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("id", String.valueOf(id));
                stringStringMap.put("ten",ten);
                stringStringMap.put("chucvu", String.valueOf(chucvu));
                stringStringMap.put("phone",phone);
                stringStringMap.put("update","UPDATETHONGTIN");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void update_gmail_user( int id,String gmail){
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
                stringStringMap.put("id", String.valueOf(id));
                stringStringMap.put("gmail",gmail);
                stringStringMap.put("update","UPDATEGMAIL");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void update_pass_user(CallBackChangePass callBackChangePass, int id, String pass, String pass_new){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.update_user, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    callBackChangePass.onSuccess("Đổi mật khẩu thành công");
                }else{
                    callBackChangePass.onFail(response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackChangePass.onError("Đã xảy ra lỗi");
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("id", String.valueOf(id));
                stringStringMap.put("pass",pass);
                stringStringMap.put("pass_new", pass_new);
                stringStringMap.put("update","UPDATEPASS");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public void dangnhap(CallBackLogin callBackLogin, String user, String pass){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_dangnhap, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("ErrorLogin")){
                    callBackLogin.onFail("Tài khoản hoặc mật khẩu không chính xác");
                }else if(response.toString().trim().equals("LOCKUSER")){
                    Log.d(TAG, "Tài khoản đã bị khóa");
                    callBackLogin.onError("Tài khoản này đã bị khóa!");
                }
                else{
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
                                String tenchucvu="Người dùng";
                                if(chuvu==1){
                                    tenchucvu="Người bán";
                                }else if (chuvu==2) {
                                    tenchucvu="Admin";
                                }
                                Log.d(TAG, "  d=>  "+id+"  ten=> "+ten+"   chvu=> "+tenchucvu+"   phone=> "+phone+"  gmal=> "+gmail);
                                //--------------------------------------------------------code them doạn này------------------------------------
                                Toasty.success(context, "đăng nhập thành công!", Toasty.LENGTH_SHORT).show();
                                DataUser.Companion.setId(id);
                                DataUser.Companion.setName(ten);
                                DataUser.Companion.setEmail(gmail);
                                DataUser.Companion.setOccupation(chuvu);
                                DataUser.Companion.setNumberPhone(phone);
                                DataUser.Companion.setPass(pass);
                                //put data in share references
                                DataShareReferences.Companion.putEmailAndPass(context, user, pass);
                                //go to home
                                callBackLogin.onSuccess("Đăng nhập thành công");
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.d(TAG, "đã xảy ra lỗi : gggg"+e);
                            }
                        }
                    } catch (JSONException e) {
                        callBackLogin.onError("LỖI"+e);
                        Log.d(TAG, "-----llll--->>"+e);
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackLogin.onError("LỖI"+error);
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
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
    public void get_all_user(CallBackGetDataUser callBackGetDataUser, int chucvu) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_all, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: .....>"+response);
                List<User> ee= new ArrayList<>();
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
                            callBackGetDataUser.onSuccess(new User(id,ten,"******",chuvu,phone,gmail));
                            ee.add(new User(id,ten,"******",chuvu,phone,gmail));
                            //--------------------------------------------------------code them doạn này------------------------------------

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "đã xảy ra lỗi : gggg"+e);
                        }
                    }
                } catch (JSONException e) {
                    callBackGetDataUser.onFail(e.toString());
                    Log.d(TAG, "-----llll--->>"+e);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackGetDataUser.onError(error.toString());
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("chucvu", String.valueOf(chucvu));
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void layma(CallBackGetCode callBackGetCode, String gmail_nhan) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.get_code_change_pass, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(Integer.parseInt(response)==-1){
                    callBackGetCode.onFail("vui lòng đợi 3 phút để gủi mã tiếp theo");
                    Log.d(TAG, "vui lòng đợi 3 phút để gủi mã tiếp theo");
                }else{
                    callBackGetCode.onSuccess(response.toString());
                    Log.d(TAG, "mã code dooir mật khẩu: .....>"+response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackGetCode.onError("Lỗi: "+error);
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("gmail", gmail_nhan);
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void nhapma(CallBackUpdatePass callBackUpdatePass, String gmail, int nhapma, String passnew) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.input_code_change_pass, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(Integer.parseInt(response)==-1){
                    callBackUpdatePass.onFail("Mã đã hết hạn hoặc nhập không đúng");
                    Log.d(TAG, "Mã đã hết hạn hoặc nhập không đúng");
                }else{
                    callBackUpdatePass.onSuccess("Đổi mật khẩu thành công");
                    Log.d(TAG, "Đổi mật khẩu thành công");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackUpdatePass.onError("Lỗi: "+error);
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("gmail", gmail);
                stringStringMap.put("nhapma", String.valueOf(nhapma));
                stringStringMap.put("passnew", passnew);
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
    public  void khoa_taikhoan(CallBackLockAccount callBack, int id_khoa , int chucvu){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.kichhoat_user, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "thành công");
                    callBack.onSuccess("Thành công");
                }else{
                    callBack.onFail("Thất bại"+response);
                    Log.d(TAG, "lỗi>>"+response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError("Lỗi"+error);
                Log.d(TAG, "xảy ra lỗi >>>>" +error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap= new HashMap<>();
                stringStringMap.put("id_user", String.valueOf(id_khoa));

                stringStringMap.put("chucvu", String.valueOf(chucvu));

                stringStringMap.put("check","1");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
}
