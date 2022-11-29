package MODEL.IResult;

import com.android.volley.VolleyError;

import java.util.List;

import MODEL.OOP.ThongKeDoanhThuTheoNam;

public interface IResult_thongkedoanhthutheonam {
    public void notifySuccess(String requestType, List<ThongKeDoanhThuTheoNam> response);
    public void notifyError(String requestType, VolleyError error);
}
