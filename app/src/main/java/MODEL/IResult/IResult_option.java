package MODEL.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.Option;

public interface IResult_option {
    public void notifySuccess(String requestType, List<Option> response);
    public void notifyError(String requestType, VolleyError error);
}
