package MODEL.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.ChiTietHoaDon;

public interface IResult_chitiethoadon {
    public void notifySuccess(String requestType, List<ChiTietHoaDon> response);
    public void notifyError(String requestType, VolleyError error);
}
