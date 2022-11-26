package com.developer.cubemarket.connection.IResult;

import com.android.volley.VolleyError;
import com.developer.cubemarket.connection.MODEL.OOP.Diachi;

import java.util.List;



public interface IResult_diachi {
    public void notifySuccess(String requestType, List<Diachi> response);
    public void notifyError(String requestType, VolleyError error);
}
