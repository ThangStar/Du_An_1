package com.developer.cubemarket.connection.MODEL.IResult;

import com.android.volley.VolleyError;
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham;

import java.util.List;


public interface IResult_sanpham {
    public void notifySuccess(String requestType, List<Sanpham> response);
    public void notifyError(String requestType, VolleyError error);
}
