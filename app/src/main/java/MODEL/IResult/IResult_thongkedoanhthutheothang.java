package MODEL.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.ThongKeDoanhThuTheoThang;

public interface IResult_thongkedoanhthutheothang {
    public void notifySuccess(String requestType, List<ThongKeDoanhThuTheoThang> response);
    public void notifyError(String requestType, VolleyError error);
}
