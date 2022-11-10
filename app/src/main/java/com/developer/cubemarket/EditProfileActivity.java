package com.developer.cubemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.developer.cubemarket.MODEL.DAO.DaoUser;
import com.developer.cubemarket.MODEL.OOP.User;
import com.google.android.material.textfield.TextInputEditText;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditProfileActivity extends AppCompatActivity {

    TextInputEditText Email,Ten,Sdt,ChucVu,Password;
    TextView tv_Email,tv_Ten,tv_Sdt,tv_ChucVu,tv_Pass;
    Button btn_capnhat;
    DaoUser userDao;
    List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
// Ánh Xạ
        Ten = findViewById(R.id.edt_b);
        Sdt = findViewById(R.id.edt_d);
        ChucVu = findViewById(R.id.edt_e);

        tv_Ten = findViewById(R.id.tv_name);
        tv_Sdt = findViewById(R.id.tv_sdt);
        tv_ChucVu = findViewById(R.id.tv_chucvu);

        String ten = Ten.getText().toString();
        String sdt = Sdt.getText().toString();
        String chucVu = ChucVu.getText().toString();

        userDao = new DaoUser(EditProfileActivity.this);


        btn_capnhat= findViewById(R.id.btn_capnhat);
// bắt sự kiện ấn nút cập nhật
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
                if (validate() == false) {
                    FancyToast.makeText(EditProfileActivity.this,"Hãy kiểm tra lại thông tin", FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                }else {
                    User user = new User();
                    user.setId(1);
                    user.setTen(ten);
                    user.setPhone(sdt);
                    user.setChucvu(1);
                    userDao.updete_user(user);
                    FancyToast.makeText(EditProfileActivity.this,"Đã cập nhật", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                }
            }
        });

    }

    public boolean validate(){
        String ten = Ten.getText().toString();
        String sdt = Sdt.getText().toString();
        String chucVu = ChucVu.getText().toString();
        int a ,b, c, d ;
        String regex ="^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        Pattern ptrn = Pattern.compile(regex);
        boolean kt = sdt.matches(regex);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (ten.length() <= 10) {
            tv_Ten.setText("Tên phải trên 10 ký tự");
            b = 1;
        }else {
            tv_Ten.setText("");
            b = 0;
        }
        if (kt == false) {
            tv_Sdt.setText("Số Điện Thoại Không Hợp Lệ");
            d = 1;
        }else {
            tv_Sdt.setText("");
            d = 0;
        }
        if ( b == 0 && d == 0) {
            return true;
        }
        return false;
    }


}