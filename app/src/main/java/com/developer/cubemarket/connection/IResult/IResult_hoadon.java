package com.developer.cubemarket.connection.IResult;

import com.android.volley.VolleyError;
import com.developer.cubemarket.connection.MODEL.OOP.Hoadon;

import java.util.List;


public interface IResult_hoadon {
    public void notifySuccess(String requestType, List<Hoadon> response);
    public void notifyError(String requestType, VolleyError error);
}
