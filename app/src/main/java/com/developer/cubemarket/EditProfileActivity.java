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
        Email = findViewById(R.id.edt_a);
        Ten = findViewById(R.id.edt_b);
        Password = findViewById(R.id.edt_c);
        Sdt = findViewById(R.id.edt_d);
        ChucVu = findViewById(R.id.edt_e);

        tv_Email = findViewById(R.id.tv_email);
        tv_Ten = findViewById(R.id.tv_name);
        tv_Pass = findViewById(R.id.tv_pass);
        tv_Sdt = findViewById(R.id.tv_sdt);
        tv_ChucVu = findViewById(R.id.tv_chucvu);

        String email = Email.getText().toString();
        String ten = Ten.getText().toString();
        String pass = Password.getText().toString();
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
                    user.setGmail(email);
                    user.setPassword(pass);
                    user.setPhone(sdt);
                    user.setChucvu(1);
                    userDao.updete_user(user);
                    FancyToast.makeText(EditProfileActivity.this,"Đã cập nhật", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                }
            }
        });

    }

    public boolean validate(){
        String email = Email.getText().toString();
        String ten = Ten.getText().toString();
        String pass = Password.getText().toString();
        String sdt = Sdt.getText().toString();
        String chucVu = ChucVu.getText().toString();
        int a ,b, c, d ;
        Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher match = ptrn.matcher(sdt);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!email.matches(emailPattern)){
            tv_Email.setText("Email không hợp lệ");
            a = 1;
        }else {
            tv_Email.setText("");
            a = 0;
        }
        if (ten.length() <= 10) {
            tv_Ten.setText("Tên phải trên 10 ký tự");
            b = 1;
        }else {
            tv_Ten.setText("");
            b = 0;
        }
        if (pass.length() <=6) {
            tv_Pass.setText("Password phải chứa ít nhất 6 ký tự");
            c = 1;
        }else {
            tv_Ten.setText("");
            c = 0;
        }
        if (sdt.matches(String.valueOf(match.find() && match.group().equals(sdt)))) {
            tv_Pass.setText("Số Điện Thoại Không Hợp Lệ");
            d = 1;
        }else {
            tv_Pass.setText("");
            d = 0;
        }
        if (a == 0 && b == 0 && c == 0 && d == 0) {
            return true;
        }
        return false;
    }


}