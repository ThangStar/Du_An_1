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

import MODEL.IResult.IResult_thongkedoanhthutheothang;
import MODEL.KET_NOI_SEVER.HttpsTrustManager;
import MODEL.KET_NOI_SEVER.Link;
import MODEL.OOP.ThongKeDoanhThuTheoThang;

public class DaoThongKeDoanhThuTheothang {
    Context context;
    String TAG="TAG";
    IResult_thongkedoanhthutheothang mResultCallback = null;

    public DaoThongKeDoanhThuTheothang(Context context) {
        this.context = context;
    }
    public  void getdata_doanhthu_thang(IResult_thongkedoanhthutheothang mResultCallback,int id,int chucvu){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Link.getdata_thongketheothang, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: >>> "+response);
                List<ThongKeDoanhThuTheoThang> ee = new ArrayList<>();
                try {

                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length();i++){

                        try {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            String thang= jsonObject.getString("thang");
                            int tongtien= jsonObject.getInt("tongtien");
                            ee.add(new ThongKeDoanhThuTheoThang(thang,tongtien));

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

                    mResultCallback.notifySuccess("thongkethang",ee);
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
                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);

    }
}
