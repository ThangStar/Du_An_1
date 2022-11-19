package MODEL.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.Hoadon;

public interface IResult_hoadon {
    public void notifySuccess(String requestType, List<Hoadon> response);
    public void notifyError(String requestType, VolleyError error);
}
