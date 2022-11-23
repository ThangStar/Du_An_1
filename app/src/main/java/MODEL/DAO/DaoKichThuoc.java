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

import MODEL.IResult.IResult_kichthuoc;
import MODEL.KET_NOI_SEVER.HttpsTrustManager;
import MODEL.KET_NOI_SEVER.Link;
import MODEL.OOP.Kichthuoc;

public class DaoKichThuoc {
    Context context;
    String TAG="TAG";
    IResult_kichthuoc mResultCallback = null;

    public DaoKichThuoc( Context context) {

        this.context = context;
    }
    public  void insert_kichthuoc(int masanpham,String tenkichthuoc){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.insert_kichthuoc, new Response.Listener<String>() {
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
                stringStringMap.put("masanpham", String.valueOf(masanpham));
                stringStringMap.put("tenkichthuoc",tenkichthuoc);


                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
    public  void delete_kichthuoc(int makichthuoc){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.delete_kichthuoc, new Response.Listener<String>() {
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
                stringStringMap.put("makichthuoc", String.valueOf(makichthuoc));
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
    public  void getdata_kichthuoc(IResult_kichthuoc mResultCallback){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_kichthuoc, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>> "+response);
               List<Kichthuoc> ee = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){
                        try {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            int makichthuoc= jsonObject.getInt("makichthuoc");
                            String tenkichthuoc= jsonObject.getString("tenkichthuoc");


                            ee.add(new Kichthuoc(makichthuoc,tenkichthuoc));

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

                    mResultCallback.notifySuccess("kichthuoc", ee);
                }else{
                    Log.d(TAG, "onResponse: qqqqq");

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

                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
}
