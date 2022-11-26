package com.developer.cubemarket.connection.IResult;

import com.android.volley.VolleyError;
import com.developer.cubemarket.connection.MODEL.OOP.Option;

import java.util.List;



public interface IResult_option {
    public void notifySuccess(String requestType, List<Option> response);
    public void notifyError(String requestType, VolleyError error);
}
