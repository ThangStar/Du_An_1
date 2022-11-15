package com.developer.cubemarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.cubemarket.adapter.search.historyAdapter;
import com.developer.cubemarket.adapter.search.ketquaAdapter;
import com.developer.cubemarket.adapter.search.recentlyAdapter;
import com.developer.cubemarket.object.search.historysearch;
import com.developer.cubemarket.object.search.ketquaObject;
import com.developer.cubemarket.object.search.recently;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView rcvrecently, searchHistoryRecycleView, resultRecycleView;
    private recentlyAdapter recentlyAdapter;
    private historyAdapter historyAdapter;
    private ketquaAdapter ketquaAdapter;
    private TextView clear_Text, refresh_Text;
    private ImageView camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        clear_Text = findViewById(R.id.clear_Text);
        refresh_Text = findViewById(R.id.refresh_Text);
        camera = findViewById(R.id.camera);
        //gần đây
        getListRecently();

        //lịch sử tìm kiếm
        getListHistory();
        //kết quả tìm kiếm
        getListResult();

        //camera
        camera();

        //clear
        clear();

        //
        refresh();
    }

    private void refresh() {
    }

    private void clear() {
    }

    private void camera() {
    }

    private List<ketquaObject> getListResult() {
        resultRecycleView = findViewById(R.id.resultRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        resultRecycleView.setLayoutManager(linearLayoutManager);

        List<ketquaObject> list = new ArrayList<>();
        list.add(new ketquaObject(R.drawable.giay, "Giày nike", "đ 690.000"));
        list.add(new ketquaObject(R.drawable.sonmoi, "Son Môi Micracle", "đ 250.00"));

        ketquaAdapter = new ketquaAdapter(list);
        resultRecycleView.setAdapter(ketquaAdapter);
        return list;
    }

    private List<historysearch> getListHistory() {

        searchHistoryRecycleView = findViewById(R.id.searchHistoryRecycleView);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        searchHistoryRecycleView.setLayoutManager(linearLayoutManager1);

        List<historysearch> list = new ArrayList<>();
        list.add(new historysearch("M.O.I"));
        list.add(new historysearch("Mirac"));
        list.add(new historysearch("Son"));
        list.add(new historysearch("Flawsome"));
        list.add(new historysearch("Glowy"));
        list.add(new historysearch("Lip"));
        list.add(new historysearch("Apo"));

        historyAdapter = new historyAdapter(list);
        searchHistoryRecycleView.setAdapter(historyAdapter);
        return list;
    }

    private List<recently> getListRecently() {
        rcvrecently = findViewById(R.id.recentlyRecycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvrecently.setLayoutManager(linearLayoutManager);

        List<recently> list = new ArrayList<>();
        list.add(new recently(R.drawable.sonmoi, "Son lì Shu Uem", "đ 360.00"));
        list.add(new recently(R.drawable.sonmoi, "Son Môi Micracle", "đ 250.00"));

        recentlyAdapter = new recentlyAdapter(list);
        rcvrecently.setAdapter(recentlyAdapter);
        return list;
    }

}