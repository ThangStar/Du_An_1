package com.developer.cubemarket.connection.IResult;

import com.android.volley.VolleyError;
import com.developer.cubemarket.connection.MODEL.OOP.User;

import java.util.List;


public interface IResult_user {
    public void notifySuccess(String requestType, List<User> response);
    public void notifyError(String requestType, VolleyError error);
}
