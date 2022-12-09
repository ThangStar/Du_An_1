package JAVA.VIEW;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.android.volley.VolleyError;
import com.developer.cubemarket.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import JAVA.listviewitems.BarChartItem;
import JAVA.listviewitems.ChartItem;
import JAVA.listviewitems.LineChartItem;
import JAVA.listviewitems.PieChartItem;
import JAVA.notimportant.DemoBase;
import MODEL.DAO.DaoThongKeDoanhThuTheoNam;
import MODEL.DAO.DaoThongKeDoanhThuTheoNgay;
import MODEL.DAO.DaoThongKeDoanhThuTheothang;
import MODEL.IResult.IResult_thongkedoanhthutheonam;
import MODEL.IResult.IResult_thongkedoanhthutheongay;
import MODEL.IResult.IResult_thongkedoanhthutheothang;
import MODEL.OOP.Array_list_thongke;
import MODEL.OOP.ThongKeDoanhThuTheoNam;
import MODEL.OOP.ThongKeDoanhThuTheoNgay;
import MODEL.OOP.ThongKeDoanhThuTheoThang;

public class ThongkeActivity extends DemoBase {

    int CHUCVU = -9999999;// neeus banwgf -9999999 thif chưa có id người dùng và chức vụ
    int ID = -9999999;
    int USD=23700;
    String CHON_THONG_KE=null;
    int STT=7;
    BarChart barChart, barChart1, barChart2;
    IResult_thongkedoanhthutheongay iResult_thongkedoanhthutheongay = null;
    IResult_thongkedoanhthutheonam iResult_thongkedoanhthutheonam = null;
    IResult_thongkedoanhthutheothang iResult_thongkedoanhthutheothang = null;
    String TAG = "TAG123";
    ArrayList<Integer> aaaaa= new ArrayList<>();
    ArrayList<Array_list_thongke> array_list_thongkes=new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_thongke);

        ListView lv = findViewById(R.id.listView1);

        // Math.round(c*100.0)/100.0)
        /// viết code ở khu vực này///////
        Intent intent= getIntent();
        if(intent==null){
        }else {

            try {
                ID = Integer.parseInt(intent.getStringExtra("ID_URSE").toString());
                CHUCVU = Integer.parseInt(intent.getStringExtra("CHUCVU").toString());
                CHON_THONG_KE = intent.getStringExtra("CHON_THONG_KE").toString().trim();

            } catch (Exception e) {
                Log.d(TAG, "ERR > " + e);
            }

        }



        if(CHON_THONG_KE.trim().equals("THEONGAY")){
        iResult_thongkedoanhthutheongay= new IResult_thongkedoanhthutheongay() {
            @Override
            public void notifySuccess(String requestType, List<ThongKeDoanhThuTheoNgay> response) {
               
                int i=0;
                for (ThongKeDoanhThuTheoNgay o: response
                ) {
                    //Log.d(TAG, "notifySuccess: "+o.getTongtien());
                    array_list_thongkes.add(new Array_list_thongke(i,o.getNgaymua(),o.getTongtien()));
                    i++;

                }

            }
            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d(TAG, "notifyError: ");
            }
        };

        DaoThongKeDoanhThuTheoNgay daoThongKeDoanhThuTheoNgay= new DaoThongKeDoanhThuTheoNgay(ThongkeActivity.this);
        daoThongKeDoanhThuTheoNgay.getdata_doanhthu_ngay(iResult_thongkedoanhthutheongay,ID,CHUCVU);

        }else  if(CHON_THONG_KE.trim().equals("THEOTHANG")){
            iResult_thongkedoanhthutheothang= new IResult_thongkedoanhthutheothang() {
                @Override
                public void notifySuccess(String requestType, List<ThongKeDoanhThuTheoThang> response) {

                    int i=0;
                    for (ThongKeDoanhThuTheoThang o: response
                    ) {
                      //  Log.d(TAG, "notifySuccess: "+o.getTongtien());
                        array_list_thongkes.add(new Array_list_thongke(i,o.getThangmua(),o.getTongtien()));
                        i++;

                    }

                }
                @Override
                public void notifyError(String requestType, VolleyError error) {
                    Log.d(TAG, "notifyError: ");
                }
            };

            DaoThongKeDoanhThuTheothang daoThongKeDoanhThuTheothang= new DaoThongKeDoanhThuTheothang(ThongkeActivity.this);
            daoThongKeDoanhThuTheothang.getdata_doanhthu_thang(iResult_thongkedoanhthutheothang,ID,CHUCVU);
        }else{
            iResult_thongkedoanhthutheonam= new IResult_thongkedoanhthutheonam() {
                @Override
                public void notifySuccess(String requestType, List<ThongKeDoanhThuTheoNam> response) {

                    int i=0;
                    for (ThongKeDoanhThuTheoNam o: response
                    ) {
                       // Log.d(TAG, "notifySuccess: "+o.getTongtien());
                        array_list_thongkes.add(new Array_list_thongke(i,o.getNammua(),o.getTongtien()));
                        i++;

                    }

                }
                @Override
                public void notifyError(String requestType, VolleyError error) {
                    Log.d(TAG, "notifyError: ");
                }
            };

            DaoThongKeDoanhThuTheoNam daoThongKeDoanhThuTheoNam= new DaoThongKeDoanhThuTheoNam(ThongkeActivity.this);
            daoThongKeDoanhThuTheoNam.getdata_doanhthu_nam(iResult_thongkedoanhthutheonam,ID,CHUCVU);
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Log.d(TAG, "onCreate: >>>>2>>>>1"+  array_list_thongkes.size());
                ArrayList<ChartItem> list = new ArrayList<>();

                // 30 items
                for (int i = 0; i < 3; i++) {

                    if (i % 3 == 0) {
                        list.add(new LineChartItem(generateDataLine(i + 1), getApplicationContext()));
                    } else if (i % 3 == 1) {
                        list.add(new BarChartItem(generateDataBar(i + 1), getApplicationContext()));
                    } else if (i % 3 == 2) {
                        list.add(new PieChartItem(generateDataPie(), getApplicationContext()));
                    }
                }

                ChartDataAdapter cda = new ChartDataAdapter(getApplicationContext(), list);
                lv.setAdapter(cda);
            }
        }, 1500 );//time in milisecond
        
        
        
        
        
        
        
        






/*
        ArrayList<ChartItem> list = new ArrayList<>();

        // 30 items
        for (int i = 0; i < 3; i++) {

           if (i % 3 == 0) {
                list.add(new LineChartItem(generateDataLine(i + 1), getApplicationContext()));
            } else if (i % 3 == 1) {
                list.add(new BarChartItem(generateDataBar(i + 1), getApplicationContext()));
            } else if (i % 3 == 2) {
                list.add(new PieChartItem(generateDataPie(), getApplicationContext()));
            }
        }

        ChartDataAdapter cda = new ChartDataAdapter(getApplicationContext(), list);
        lv.setAdapter(cda);*/

    }

    @Override
    protected void saveToGallery() {

    }

    /**
     * adapter that supports 3 different item types
     */
    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {

        ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            //noinspection ConstantConditions
            return getItem(position).getView(position, convertView, getContext());
        }

        @Override
        public int getItemViewType(int position) {
            // return the views type
            ChartItem ci = getItem(position);
            return ci != null ? ci.getItemType() : 0;
        }

        @Override
        public int getViewTypeCount() {
            return 3; // we have 3 different item-types
        }
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Line data
     */
    private LineData generateDataLine(int cnt) {
        Log.d(TAG, "onCreate: >tttttttttttt>>>>>>>1:"+  array_list_thongkes.size());
        ArrayList<Entry> values1 = new ArrayList<>();


               /* for (int i = 0; i < STT; i++) {
                    values1.add(new Entry(i, (int) (Math.random() * 65) + 40));
                }*/
                for (int i = 0; i < array_list_thongkes.size(); i++) {
                    values1.add(new Entry(i,array_list_thongkes.get(i).getMoney()/1000));
                }

                LineDataSet d1 = new LineDataSet(values1, "Tiền VND : Thêm 3 số 0 sau đuôi ");
                d1.setLineWidth(2.5f);
                d1.setCircleRadius(4.5f);
                d1.setHighLightColor(Color.rgb(244, 117, 117));
                d1.setDrawValues(false);


                ArrayList<ILineDataSet> sets = new ArrayList<>();
                sets.add(d1);






        return new LineData(sets);

    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Bar data
     */
    private BarData generateDataBar(int cnt) {

        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i <array_list_thongkes.size(); i++) {
           // Log.d(TAG, "onCreate: >>>>>>>>>>>>>>>>>>>>>>>> :::::: "+ ( Math.round((float)((float)array_list_thongkes.get(i).getMoney() / (float) USD)*100.0)/100.0));
            entries.add(new BarEntry((float) i, (float) ( Math.round((float)((float)array_list_thongkes.get(i).getMoney() / (float) USD)*100.0)/100.0)));
        }

        BarDataSet d = new BarDataSet(entries, "Tiền USD   ");
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setHighLightAlpha(255);

        BarData cd = new BarData(d);
        cd.setBarWidth(0.9f);
        return cd;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Pie data
     */
    private PieData generateDataPie() {

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < array_list_thongkes.size(); i++) {
            entries.add(new PieEntry((float) array_list_thongkes.get(i).getMoney(),""+array_list_thongkes.get(i).getTitle()));
        }

        PieDataSet d = new PieDataSet(entries, "Thông kê theo % ");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setFormSize(10);

        return new PieData(d);
    }
}

        
        
        
        
        
        
        
        
        
        
       /* barChart = (BarChart) findViewById(R.id.barchart);

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

        DaoThongKeDoanhThuTheoNgay daoThongKeDoanhThuTheoNgay= new DaoThongKeDoanhThuTheoNgay(ThongkeActivity.this);
        daoThongKeDoanhThuTheoNgay.getdata_doanhthu_ngay(iResult_thongkedoanhthutheongay,ID,CHUCVU);
   */











