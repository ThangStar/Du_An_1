package MODEL.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.Kichthuoc;

public interface IResult_kichthuoc {
    public void notifySuccess(String requestType, List<Kichthuoc> response);
    public void notifyError(String requestType, VolleyError error);
}
