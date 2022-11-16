package MODEL.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.KhuyenMai;

public interface IResult_khuyenmai {
    public void notifySuccess(String requestType, List<KhuyenMai> response);
    public void notifyError(String requestType, VolleyError error);
}
