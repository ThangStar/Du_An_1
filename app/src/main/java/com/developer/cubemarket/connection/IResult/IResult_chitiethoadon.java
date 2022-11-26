package com.developer.cubemarket.connection.IResult;

import com.android.volley.VolleyError;
import com.developer.cubemarket.connection.MODEL.OOP.ChiTietHoaDon;

import java.util.List;


public interface IResult_chitiethoadon {
    public void notifySuccess(String requestType, List<ChiTietHoaDon> response);
    public void notifyError(String requestType, VolleyError error);
}
