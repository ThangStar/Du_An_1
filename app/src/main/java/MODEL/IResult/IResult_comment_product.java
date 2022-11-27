package MODEL.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.CommentProduct;

public interface IResult_comment_product {
    public void notifySuccess(String requestType, List<CommentProduct> response);
    public void notifyError(String requestType, VolleyError error);
}
