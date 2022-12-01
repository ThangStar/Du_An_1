package com.developer.cubemarket.connection.IResult;

import com.android.volley.VolleyError;
import com.developer.cubemarket.connection.MODEL.OOP.ThongKeDoanhThuTheoNam;

import java.util.List;


public interface IResult_thongkedoanhthutheonam {
    public void notifySuccess(String requestType, List<ThongKeDoanhThuTheoNam> response);
    public void notifyError(String requestType, VolleyError error);
}
