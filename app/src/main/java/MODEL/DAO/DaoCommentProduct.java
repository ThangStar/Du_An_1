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

import MODEL.IResult.IResult_comment_product;
import MODEL.KET_NOI_SEVER.HttpsTrustManager;
import MODEL.KET_NOI_SEVER.Link;
import MODEL.OOP.CommentProduct;

public class DaoCommentProduct {
    Context context;
    String TAG="TAG";
    IResult_comment_product mResultCallback = null;

    public DaoCommentProduct( Context context) {
        this.context = context;
    }
    public  void insert_comment(int masanpham,int id_user_nguoi_dang,String noidung,int sosao){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_comment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("success")){
                    Log.d(TAG, "thành công");
                }else if(response.toString().trim().equals("Error")){
                    Log.d(TAG, "Bạn chưa mua sản phẩm nay ");
                }else if(response.toString().trim().equals("Error1")){
                    Log.d(TAG, "mỗi người chỉ được bình luận 1 lần ");
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
    public  void getdata_comment( IResult_comment_product mResultCallback,int masanpham ){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_comment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().trim().equals("NO")){
                    Log.d(TAG, "Chưa có bimnhf luận nào");
                }else{
                    List<CommentProduct> ee = new ArrayList<>();
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

                                ee.add(new CommentProduct(tennguoidang,phone,noidung,sosao,ngaydang));

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

                        mResultCallback.notifySuccess("comment", ee);
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

                stringStringMap.put("id_product", String.valueOf(masanpham));


                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public  void check_user(int masanpham,int id ){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.check_comment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               int a= Integer.parseInt(response);
               if(a==2){
                   Log.d(TAG, "bạn chỉ có thế sửa bình luận");

               }else if(a==1){
                   Log.d(TAG, "bạn chỉ có thế  bình luận");
               }else{
                   Log.d(TAG, "bạn không thể làm gì vì bạn chưa mua hàng");
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
                stringStringMap.put("id_user", String.valueOf(id));

                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
}
