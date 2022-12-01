package com.developer.cubemarket.activity.VIEW;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.VolleyError;
import com.developer.cubemarket.R;
import com.developer.cubemarket.config.user.DataUser;
import com.developer.cubemarket.connection.IResult.IResult_thongkedoanhthutheonam;
import com.developer.cubemarket.connection.IResult.IResult_thongkedoanhthutheongay;
import com.developer.cubemarket.connection.IResult.IResult_thongkedoanhthutheothang;
import com.developer.cubemarket.connection.MODEL.DAO.DaoThongKeDoanhThuTheoNam;
import com.developer.cubemarket.connection.MODEL.DAO.DaoThongKeDoanhThuTheoNgay;
import com.developer.cubemarket.connection.MODEL.DAO.DaoThongKeDoanhThuTheothang;
import com.developer.cubemarket.connection.MODEL.OOP.ThongKeDoanhThuTheoNam;
import com.developer.cubemarket.connection.MODEL.OOP.ThongKeDoanhThuTheoNgay;
import com.developer.cubemarket.connection.MODEL.OOP.ThongKeDoanhThuTheoThang;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;


public class ThongkeActivity extends AppCompatActivity {
 int[] colors= {Color.rgb(102,255,0),
         Color.rgb(255,255,51),
         Color.rgb(255,204,51),
         Color.rgb(153,102,255),
         Color.rgb(255,51,102),
         Color.rgb(255,153,102),
         Color.rgb(102,255,0),
         Color.rgb(255,255,51),
         Color.rgb(255,204,51),
         Color.rgb(153,102,255),
         Color.rgb(255,51,102),
         Color.rgb(255,153,102)
        };
    int CHUCVU= DataUser.Companion.getOccupation();
    int ID=DataUser.Companion.getId();
    BarChart barChart,barChart1,barChart2;
    IResult_thongkedoanhthutheongay iResult_thongkedoanhthutheongay=null;
    IResult_thongkedoanhthutheonam iResult_thongkedoanhthutheonam=null;
    IResult_thongkedoanhthutheothang iResult_thongkedoanhthutheothang=null;
    String TAG="TAG123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);
        barChart = (BarChart) findViewById(R.id.barchart);
        barChart1 = (BarChart) findViewById(R.id.barchart3);
        barChart2 = (BarChart) findViewById(R.id.barchart4);

        iResult_thongkedoanhthutheongay= new IResult_thongkedoanhthutheongay() {
            @Override
            public void notifySuccess(String requestType, List<ThongKeDoanhThuTheoNgay> response) {
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                 int i=0;
                for (ThongKeDoanhThuTheoNgay o: response
                ) {
                    barEntries.add(new BarEntry(i, o.getTongtien()));
                    i++;
                }

                BarDataSet barDataSet = new BarDataSet(barEntries, "Thống kê doanh thu theo ngày");
                barDataSet.setColors(colors);
                ArrayList<String> theDates = new ArrayList<>();

                for (ThongKeDoanhThuTheoNgay o: response
                ) {
                    theDates.add("Ngày "+o.getNgaymua());
                }

                barChart.notifyDataSetChanged();
                barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(theDates));
               // barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);// ddooi chu xuong been duoi
                BarData theData = new BarData(barDataSet);//----Line of error
                barChart.animateXY(2000, 2000);
                barChart.setData(theData);
                barChart.setTouchEnabled(true);
                barChart.setDragEnabled(true);
                barChart.setScaleEnabled(true);
                barChart.setBorderColor(R.color.teal_200);



            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d(TAG, "notifyError: ");
            }
        };
        iResult_thongkedoanhthutheothang= new IResult_thongkedoanhthutheothang() {
            @Override
            public void notifySuccess(String requestType, List<ThongKeDoanhThuTheoThang> response) {
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                int i=0;
                for (ThongKeDoanhThuTheoThang o: response
                ) {
                    barEntries.add(new BarEntry(i, o.getTongtien()));
                    i++;
                }

                BarDataSet barDataSet = new BarDataSet(barEntries, "Thống kê doanh thu theo Tháng");
                barDataSet.setColors(colors);
                ArrayList<String> theDates = new ArrayList<>();

                for (ThongKeDoanhThuTheoThang o: response
                ) {
                    theDates.add("Tháng "+o.getThangmua());
                }

                barChart1.notifyDataSetChanged();
                barChart1.getXAxis().setValueFormatter(new IndexAxisValueFormatter(theDates));
                BarData theData = new BarData(barDataSet);//----Line of error
                barChart1.setData(theData);
                barChart1.animateXY(4000, 4000);
                barChart1.setTouchEnabled(true);
                barChart1.setDragEnabled(true);
                barChart1.setScaleEnabled(true);
                barChart1.setBorderColor(R.color.teal_200);


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d(TAG, "notifyError: ");
            }
        };
        iResult_thongkedoanhthutheonam= new IResult_thongkedoanhthutheonam() {
            @Override
            public void notifySuccess(String requestType, List<ThongKeDoanhThuTheoNam> response) {
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                int i=0;
                for (ThongKeDoanhThuTheoNam o: response
                ) {
                    barEntries.add(new BarEntry(i, o.getTongtien()));
                    i++;
                }

                BarDataSet barDataSet = new BarDataSet(barEntries, "Thống kê doanh thu theo năm");
                barDataSet.setColors(colors);
                ArrayList<String> theDates = new ArrayList<>();

                for (ThongKeDoanhThuTheoNam o: response
                ) {
                    theDates.add("Năm "+o.getNammua());

                }

                barChart2.notifyDataSetChanged();
                barChart2.getXAxis().setValueFormatter(new IndexAxisValueFormatter(theDates));
                BarData theData = new BarData(barDataSet);//----Line of error
                barChart2.animateXY(6000, 6000);
                barChart2.setData(theData);
                barChart2.setTouchEnabled(true);
                barChart2.setDragEnabled(true);
                barChart2.setScaleEnabled(true);
                barChart2.setBorderColor(R.color.teal_200);


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d(TAG, "notifyError: ");
            }
        };
        DaoThongKeDoanhThuTheoNgay daoThongKeDoanhThuTheoNgay= new DaoThongKeDoanhThuTheoNgay(ThongkeActivity.this);
        daoThongKeDoanhThuTheoNgay.getdata_doanhthu_ngay(iResult_thongkedoanhthutheongay,ID,CHUCVU);
        DaoThongKeDoanhThuTheothang daoThongKeDoanhThuTheothang= new DaoThongKeDoanhThuTheothang(ThongkeActivity.this);
        daoThongKeDoanhThuTheothang.getdata_doanhthu_thang(iResult_thongkedoanhthutheothang,ID,CHUCVU);
        DaoThongKeDoanhThuTheoNam daoThongKeDoanhThuTheoNam= new DaoThongKeDoanhThuTheoNam(ThongkeActivity.this);
        daoThongKeDoanhThuTheoNam.getdata_doanhthu_nam(iResult_thongkedoanhthutheonam,ID,CHUCVU);











    }
}