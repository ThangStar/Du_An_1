package com.developer.cubemarket.connection.IResult;

import com.android.volley.VolleyError;
import com.developer.cubemarket.connection.MODEL.OOP.ThongKeDoanhThuTheoThang;

import java.util.List;


public interface IResult_thongkedoanhthutheothang {
    public void notifySuccess(String requestType, List<ThongKeDoanhThuTheoThang> response);
    public void notifyError(String requestType, VolleyError error);
}
