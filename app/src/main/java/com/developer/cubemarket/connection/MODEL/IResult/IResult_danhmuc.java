package com.developer.cubemarket.connection.MODEL.IResult;

import com.android.volley.VolleyError;
import com.developer.cubemarket.connection.MODEL.OOP.Danhmuc;

import java.util.List;


public interface IResult_danhmuc {
    public void notifySuccess(String requestType, List<Danhmuc> response);
    public void notifyError(String requestType, VolleyError error);
}
