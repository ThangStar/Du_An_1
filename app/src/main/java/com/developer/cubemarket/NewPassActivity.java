package com.developer.cubemarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.developer.cubemarket.MODEL.DAO.DaoUser;
import com.developer.cubemarket.MODEL.OOP.User;
import com.shashank.sony.fancytoastlib.FancyToast;

public class NewPassActivity extends AppCompatActivity {
    EditText etNewPass;
    Button passbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pass);

        etNewPass = findViewById(R.id.et_newpass);
        passbtn = findViewById(R.id.passbtn);


        passbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String email = intent.getStringExtra("email");
                String user = intent.getStringExtra("user");
                String pass = etNewPass.getText().toString();
                if (pass.trim().equals("")) {
                    FancyToast.makeText(NewPassActivity.this, "HÃY ĐIỀN PASSWORD!", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                }else {
                    DaoUser dao = new DaoUser(getBaseContext());
                    User users = new User();
//                    users.setEmail(email); chỗ này thêm gì tùy
                    users.setPassword(pass);
//                    boolean mkmoi = dao.newpass(users);  sài cái nào để set mật khẩu mới cho người dùng vô data base thì dùng
//                    if(mkmoi) {
//                        FancyToast.makeText(getBaseContext(), "Cập Nhập mật khẩu thành công", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
//                        Intent intent1 = new Intent(getApplicationContext(), Login.class);
//                        startActivity(intent1);
//                    } else {
//                        FancyToast.makeText(getBaseContext(), "Cập nhập mật khẩu lỗi", FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
//                    }
                }
            }
        });
    }
}