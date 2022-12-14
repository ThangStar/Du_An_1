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
                    callBackUpdatePermissionUser.onSuccess(btsChangePermissionUserFragment, "Th??nh c??ng");
                    Log.d(TAG, "th??nh c??ng");
                }else{
                    callBackUpdatePermissionUser.onFail("C???p nh???t th???t b???i");
                    Log.d(TAG, "l???i>>"+response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackUpdatePermissionUser.onFail("???? x???y ra l???i"+error);
                Log.d(TAG, "x???y ra l???i >>>>" +error);
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
                    callBackInsertUser.onSuccess("T???o t??i kho???n th??nh c??ng!");
                }else{
                    callBackInsertUser.onFail("Th???t b???i: "+response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackInsertUser.onError("Th???t b???i: "+error);
                Log.d(TAG, "x???y ra l???i >>>>" +error);
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
                    callBackUpdateUser.onSuccess("th??nh c??ng");
                }else{
                    callBackUpdateUser.onFail("L???i"+response.toString());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackUpdateUser.onError("L???i: "+error.toString());
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
                    Log.d(TAG, "th??nh c??ng");
                }else{
                    Log.d(TAG, "l???i>>"+response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "x???y ra l???i >>>>" +error);
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
                    callBackChangePass.onSuccess("?????i m???t kh???u th??nh c??ng");
                    DataShareReferences.Companion.putEmailAndPass(context, DataUser.Companion.getEmail(), pass_new);
                }else{
                    callBackChangePass.onFail(response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackChangePass.onError("???? x???y ra l???i");
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
                    callBackLogin.onFail("T??i kho???n ho???c m???t kh???u kh??ng ch??nh x??c");
                }else if(response.toString().trim().equals("LOCKUSER")){
                    Log.d(TAG, "T??i kho???n ???? b??? kh??a");
                    callBackLogin.onError("T??i kho???n n??y ???? b??? kh??a!");
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
                                String tenchucvu="Ng?????i d??ng";
                                if(chuvu==1){
                                    tenchucvu="Ng?????i b??n";
                                }else if (chuvu==2) {
                                    tenchucvu="Admin";
                                }
                                Log.d(TAG, "  d=>  "+id+"  ten=> "+ten+"   chvu=> "+tenchucvu+"   phone=> "+phone+"  gmal=> "+gmail);
                                //--------------------------------------------------------code them do???n n??y------------------------------------
                                Toasty.success(context, "????ng nh???p th??nh c??ng!", Toasty.LENGTH_SHORT).show();
                                DataUser.Companion.setId(id);
                                DataUser.Companion.setName(ten);
                                DataUser.Companion.setEmail(gmail);
                                DataUser.Companion.setOccupation(chuvu);
                                DataUser.Companion.setNumberPhone(phone);
                                DataUser.Companion.setPass(pass);
                                //put data in share references
                                DataShareReferences.Companion.putEmailAndPass(context, user, pass);
                                //go to home
                                callBackLogin.onSuccess("????ng nh???p th??nh c??ng");
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.d(TAG, "???? x???y ra l???i : gggg"+e);
                            }
                        }
                    } catch (JSONException e) {
                        callBackLogin.onError("L???I"+e);
                        Log.d(TAG, "-----llll--->>"+e);
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackLogin.onError("L???I"+error);
                Log.d(TAG, "x???y ra l???i >>>>" +error);
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
                            int tinhtrang=jsonObject.getInt("condition");
                            callBackGetDataUser.onSuccess(new User(id,ten,"******",chuvu,phone,gmail, tinhtrang));
                            //--------------------------------------------------------code them do???n n??y------------------------------------

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "???? x???y ra l???i : gggg"+e);
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
                Log.d(TAG, "x???y ra l???i >>>>" +error);
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
                    callBackGetCode.onFail("vui l??ng ?????i 3 ph??t ????? g???i m?? ti???p theo");
                    Log.d(TAG, "vui l??ng ?????i 3 ph??t ????? g???i m?? ti???p theo");
                }else{
                    callBackGetCode.onSuccess(response.toString());
                    Log.d(TAG, "m?? code dooir m???t kh???u: .....>"+response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackGetCode.onError("L???i: "+error);
                Log.d(TAG, "x???y ra l???i >>>>" +error);
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
                    callBackUpdatePass.onFail("M?? ???? h???t h???n ho???c nh???p kh??ng ????ng");
                    Log.d(TAG, "M?? ???? h???t h???n ho???c nh???p kh??ng ????ng");
                }else{
                    callBackUpdatePass.onSuccess("?????i m???t kh???u th??nh c??ng");
                    Log.d(TAG, "?????i m???t kh???u th??nh c??ng");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackUpdatePass.onError("L???i: "+error);
                Log.d(TAG, "x???y ra l???i >>>>" +error);
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
                    Log.d(TAG, "th??nh c??ng");
                    callBack.onSuccess("Th??nh c??ng");
                }else{
                    callBack.onFail("Th???t b???i"+response);
                    Log.d(TAG, "l???i>>"+response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError("L???i"+error);
                Log.d(TAG, "x???y ra l???i >>>>" +error);
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
    public  void mo_taikhoan(CallBackLockAccount callBackLockAccount, int id_mo_khoa , int chucvu){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.kichhoat_user, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    callBackLockAccount.onSuccess("???? M???");
                    Log.d(TAG, "th??nh c??ng");
                }else{
                    callBackLockAccount.onFail("Th???t b???i");
                    Log.d(TAG, "l???i>>"+response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackLockAccount.onError("L???i");
                Log.d(TAG, "x???y ra l???i >>>>" +error);
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
}
