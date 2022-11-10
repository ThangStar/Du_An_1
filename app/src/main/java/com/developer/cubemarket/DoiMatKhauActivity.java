package com.developer.cubemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.developer.cubemarket.MODEL.DAO.DaoUser;
import com.google.android.material.textfield.TextInputEditText;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoiMatKhauActivity extends AppCompatActivity {

    TextInputEditText mk1,mk2;
    TextView tv_mk1,tv_mk2;
    Button btn;
    DaoUser daoUser;
    String mk,re_mk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        ///// Ánh Xạ

        daoUser = new DaoUser(DoiMatKhauActivity.this);

        mk1 = findViewById(R.id.edt_a);
        mk2 = findViewById(R.id.edt_b);

        tv_mk1 = findViewById(R.id.tv_pass);
        tv_mk2 = findViewById(R.id.tv_repass);

        btn = findViewById(R.id.btn_capnhat);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
                if (validate() == false) {

                    FancyToast.makeText(DoiMatKhauActivity.this,"Hãy kiểm tra lại thông tin", FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                }else {
                    FancyToast.makeText(DoiMatKhauActivity.this,"Đổi Mật Khẩu Thành Công", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                }
            }
        });

    }

    public static boolean isValidPassword(String password,String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean validate(){
        mk = mk1.getText().toString();
        re_mk = mk2.getText().toString();
        String regex = "^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mk);
        int a, b;

        boolean validPassword = isValidPassword(mk,regex);

        if (mk.length() == 0) {
            tv_mk1.setText("Không được để trống mật khẩu");
            a = 1;
        }else
        if ((!validPassword == matcher.matches())) {
            tv_mk1.setText("Mật Khẩu phải có độ dài từ 8 đến 20 ký tựL");
            a = 1;
        }else {
            tv_mk1.setText("");
            a = 0;
        }
        if (!mk.equals(re_mk)) {
           tv_mk2.setText("Không trùng khớp");
            b = 1;
        }else {
            tv_mk2.setText("");
            b = 0;
        }
        if ( b == 0 && a == 0) {
            return true;
        }
        return false;
    }
}