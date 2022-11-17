package com.developer.cubemarket.connection.MODEL.IResult;

import com.android.volley.VolleyError;
import com.developer.cubemarket.connection.MODEL.OOP.KhuyenMai;

import java.util.List;


public interface IResult_khuyenmai {
    public void notifySuccess(String requestType, List<KhuyenMai> response);
    public void notifyError(String requestType, VolleyError error);
}
