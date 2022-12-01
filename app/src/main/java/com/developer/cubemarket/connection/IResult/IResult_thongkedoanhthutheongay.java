package com.developer.cubemarket.connection.IResult;

import com.android.volley.VolleyError;
import com.developer.cubemarket.connection.MODEL.OOP.ThongKeDoanhThuTheoNgay;

import java.util.List;


public interface IResult_thongkedoanhthutheongay {
    public void notifySuccess(String requestType, List<ThongKeDoanhThuTheoNgay> response);
    public void notifyError(String requestType, VolleyError error);
}
