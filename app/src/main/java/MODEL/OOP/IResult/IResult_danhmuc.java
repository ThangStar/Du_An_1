package MODEL.OOP.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.Danhmuc;

public interface IResult_danhmuc {
    public void notifySuccess(String requestType, List<Danhmuc> response);
    public void notifyError(String requestType, VolleyError error);
}
