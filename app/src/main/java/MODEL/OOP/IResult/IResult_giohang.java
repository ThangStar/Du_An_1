package MODEL.OOP.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.GioHang;

public interface IResult_giohang {
    public void notifySuccess(String requestType, List<GioHang> response);
    public void notifyError(String requestType, VolleyError error);
}
