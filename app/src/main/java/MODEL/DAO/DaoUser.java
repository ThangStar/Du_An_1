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

import MODEL.KET_NOI_SEVER.HttpsTrustManager;
import MODEL.KET_NOI_SEVER.Link;
import MODEL.OOP.User;

public class DaoUser {
    Context context;
    String TAG="TAG";
    String tenchucvu="Người dùng";

    public DaoUser(Context context) {
        this.context = context;
    }

    public  void insert_user( User user){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.intsert_user, new Response.Listener<String>() {
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
    public void dangnhap(String user,String pass){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_dangnhap, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               if(response.toString().trim().equals("ErrorLogin")){
                   Log.d(TAG, "Tài khoản hoặc mật khẩu không chính sác");
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
}
