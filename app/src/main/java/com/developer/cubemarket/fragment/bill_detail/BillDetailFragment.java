package com.developer.cubemarket.fragment.bill_detail;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.cubemarket.R;
import com.developer.cubemarket.adapter.billdetail.BillDetailAdapter;
import com.developer.cubemarket.config.utils.Utils;
import com.developer.cubemarket.databinding.FragmentBillDetailBinding;
import com.developer.cubemarket.object.billdetail.BillDetail;

import java.util.ArrayList;
import java.util.List;

public class BillDetailFragment extends Fragment {

    private BillDetailViewModel mViewModel;
    private FragmentBillDetailBinding binding;
    RecyclerView ry;
    TextView tv_maHD, tv_DiaChi, tv_date;
    List<BillDetail> ds = new ArrayList<>();
    BillDetailAdapter adapter = null;

    public static BillDetailFragment newInstance() {
        return new BillDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(BillDetailViewModel.class);
        binding = FragmentBillDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ry = binding.rySanPham;
        tv_maHD = binding.tvMaHD;
        tv_DiaChi =binding.tvDiaChi;
        tv_date = binding.tvDate;
        initDataProduct();

        return root;
    }

    private void initRecyclerBuyProduct(){
        adapter = new BillDetailAdapter(this, initDataProduct());
        binding.rySanPham.setAdapter(adapter);

    }

    private List<BillDetail> initDataProduct() {
        Bitmap bm = Utils.Companion.resourceToBitmap(getResources(), R.drawable.product1);
        int price,sl;
        String name, size,color;
        ds.add(new BillDetail(bm, name = "PRODUCT1",sl = 1,size = "S", color="Xanh", price = 123131));
        ds.add(new BillDetail(bm, name = "PRODUCT1",sl = 1,size = "M", color="Xanh", price = 123131));
        ds.add(new BillDetail(bm, name = "PRODUCT1",sl = 1,size = "M", color="Xanh", price = 123131));
        ds.add(new BillDetail(bm, name = "PRODUCT1",sl = 1,size = "L", color="Xanh", price = 123131));
        ds.add(new BillDetail(bm, name = "PRODUCT1",sl = 1,size = "S", color="Xanh", price = 123131));
        return ds;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BillDetailViewModel.class);
        // TODO: Use the ViewModel
    }

}