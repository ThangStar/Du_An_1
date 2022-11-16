package MODEL.OOP.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.Diachi;

public interface IResult_diachi {
    public void notifySuccess(String requestType, List<Diachi> response);
    public void notifyError(String requestType, VolleyError error);
}
