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

import MODEL.IResult.IResult_user;
import MODEL.KET_NOI_SEVER.HttpsTrustManager;
import MODEL.KET_NOI_SEVER.Link;
import MODEL.OOP.User;

public class DaoUser {
    Context context;
    String TAG="TAG";

    IResult_user mResultCallback = null;

    public DaoUser(Context context) {
        this.context = context;
    }

    public  void insert_user( User user){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_user, new Response.Listener<String>() {
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
    public  void update_user( int id ,String ten, String phone){
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
                stringStringMap.put("ten",ten);

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
    public  void update_pass_user( int id,String pass,String pass_new){
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
                stringStringMap.put("pass",pass);
                stringStringMap.put("pass_new", pass_new);
                stringStringMap.put("update","UPDATEPASS");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void update_chucvu_user( int id , int chucvu){
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

                stringStringMap.put("chucvu", String.valueOf(chucvu));

                stringStringMap.put("update","UPDATECHUCVU");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public void dangnhap(String user,String pass){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_dangnhap, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               if(response.toString().trim().equals("ErrorLogin")){
                   Log.d(TAG, "Tài khoản hoặc mật khẩu không chính sác");
               }else if(response.toString().trim().equals("LOCKUSER")){
                   Log.d(TAG, "Tài khoản đã bị khóa");
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

                           } catch (JSONException e) {
                               e.printStackTrace();
                               Log.d(TAG, "đã xảy ra lỗi : gggg"+e);
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
    public  void khoa_taikhoan( int id_khoa , int chucvu){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.kichhoat_user, new Response.Listener<String>() {
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
                stringStringMap.put("id_user", String.valueOf(id_khoa));

                stringStringMap.put("chucvu", String.valueOf(chucvu));

                stringStringMap.put("check","1");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void mo_taikhoan( int id_mo_khoa , int chucvu){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.kichhoat_user, new Response.Listener<String>() {
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
                stringStringMap.put("id_user", String.valueOf(id_mo_khoa));

                stringStringMap.put("chucvu", String.valueOf(chucvu));

                stringStringMap.put("check","0");
                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public void get_all_user(IResult_user mResultCallback,int chucvu) {
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
                                int tinhtrang=jsonObject.getInt("condition");

                                ee.add(new User(id,ten,"******",chuvu,phone,gmail,tinhtrang));
                                     //--------------------------------------------------------code them doạn này------------------------------------

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.d(TAG, "đã xảy ra lỗi : gggg"+e);
                            }
                        }
                    } catch (JSONException e) {
                        Log.d(TAG, "-----llll--->>"+e);
                        e.printStackTrace();
                    }
                if(mResultCallback != null){

                    mResultCallback.notifySuccess("usre", ee);
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
                stringStringMap.put("chucvu", String.valueOf(chucvu));
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void  layma(String gmail_nhan) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.get_code_change_pass, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(Integer.parseInt(response)==-1){
                    Log.d(TAG, "vui lòng đợi 3 phút để gủi mã tiếp theo");
                }else{
                    Log.d(TAG, "mã code dooir mật khẩu: .....>"+response);
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
                stringStringMap.put("gmail", gmail_nhan);
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void  nhapma(String gmail, int nhapma,String passnew) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.input_code_change_pass, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(Integer.parseInt(response)==-1){
                    Log.d(TAG, "Mã đã hết hạn hoặc nhập không đúng");
                }else{
                    Log.d(TAG, "đoi mat khau thnah cong");
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
                stringStringMap.put("gmail", gmail);
                stringStringMap.put("nhapma", String.valueOf(nhapma));
                stringStringMap.put("passnew", passnew);
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}
