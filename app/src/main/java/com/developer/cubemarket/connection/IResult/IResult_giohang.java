package com.developer.cubemarket.connection.IResult;

import com.android.volley.VolleyError;
import com.developer.cubemarket.connection.MODEL.OOP.GioHang;

import java.util.List;


public interface IResult_giohang {
    public void notifySuccess(String requestType, List<GioHang> response);
    public void notifyError(String requestType, VolleyError error);
}
