package com.developer.cubemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.cubemarket.MODEL.DAO.DaoUser;
import com.developer.cubemarket.MODEL.OOP.User;
import com.google.android.material.textfield.TextInputEditText;
import com.shashank.sony.fancytoastlib.FancyToast;

public class EditProfileActivity extends AppCompatActivity {

    TextInputEditText Email,Ten;
    TextView tv_Email,tv_Ten;
    Button btn_capnhat;
    DaoUser userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
// Ánh Xạ
        Email = findViewById(R.id.edt_a);
        Ten = findViewById(R.id.edt_b);

        tv_Email = findViewById(R.id.tv_email);
        tv_Ten = findViewById(R.id.tv_name);

        String email = Email.getText().toString();
        String ten = Ten.getText().toString();



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
                    user.setTen(ten);
                    user.setGmail(email);
                    userDao.updete_user(user);
                    FancyToast.makeText(EditProfileActivity.this,"Đã cập nhật", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                }
            }
        });

    }

    public boolean validate(){
        String email = Email.getText().toString();
        String ten = Ten.getText().toString();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern)){
            tv_Email.setText("Email không hợp lệ");
            return false;
        }

        if (ten.length() <= 10) {
            tv_Ten.setText("Tên phải trên 10 ký tự");
            return false;
        }
        return true;
    }


}