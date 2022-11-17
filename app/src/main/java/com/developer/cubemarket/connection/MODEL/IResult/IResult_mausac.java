package com.developer.cubemarket.connection.MODEL.IResult;

import com.android.volley.VolleyError;
import com.developer.cubemarket.connection.MODEL.OOP.Mausac;

import java.util.List;

public interface IResult_mausac {
    public void notifySuccess(String requestType, List<Mausac> response);
    public void notifyError(String requestType, VolleyError error);
}
