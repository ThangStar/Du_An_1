package MODEL.OOP.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.Mausac;

public interface IResult_mausac {
    public void notifySuccess(String requestType, List<Mausac> response);
    public void notifyError(String requestType, VolleyError error);
}
