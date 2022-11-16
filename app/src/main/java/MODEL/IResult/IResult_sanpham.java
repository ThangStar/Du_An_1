package MODEL.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.Sanpham;

public interface IResult_sanpham {
    public void notifySuccess(String requestType, List<Sanpham> response);
    public void notifyError(String requestType, VolleyError error);
}
