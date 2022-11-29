package MODEL.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.ThongKeDoanhThuTheoNgay;

public interface IResult_thongkedoanhthutheongay {
    public void notifySuccess(String requestType, List<ThongKeDoanhThuTheoNgay> response);
    public void notifyError(String requestType, VolleyError error);
}
