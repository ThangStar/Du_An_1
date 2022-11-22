package MODEL.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.User;

public interface IResult_user {
    public void notifySuccess(String requestType, List<User> response);
    public void notifyError(String requestType, VolleyError error);
}
