package com.developer.cubemarket.connection.MODEL.IResult;

import com.android.volley.VolleyError;
import com.developer.cubemarket.connection.MODEL.OOP.Kichthuoc;

import java.util.List;

public interface IResult_kichthuoc {
    public void notifySuccess(String requestType, List<Kichthuoc> response);
    public void notifyError(String requestType, VolleyError error);
}
