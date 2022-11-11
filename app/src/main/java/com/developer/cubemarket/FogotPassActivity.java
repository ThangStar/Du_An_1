package com.developer.cubemarket;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.developer.cubemarket.MailConfig.SendMail;
import com.shashank.sony.fancytoastlib.FancyToast;

public class FogotPassActivity extends AppCompatActivity {
    private String verificode ;
    private int random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        EditText email = (EditText) findViewById(R.id.et_newpass);
        EditText vricode = (EditText) findViewById(R.id.verify);
        Button resend = (Button) findViewById(R.id.passbtn);
        Button code = (Button) findViewById(R.id.verifybtn) ;

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificode = String.valueOf(Random(random));
                Log.d("TAG", "onClick: " + verificode);
                String Email = email.getText().toString();
                if (email.getText().toString().length()<=0){
                    FancyToast.makeText(FogotPassActivity.this,"THIS FIELD IS REQUIERD !",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }else {
                    /// mấy cái này để gửi code xác nhận về mail
                    SendMail javaMailAPI = new SendMail(getBaseContext(), Email,
                            "GET PASS", "CODE: " + verificode);
                    javaMailAPI.execute();
                    FancyToast.makeText(FogotPassActivity.this, "ĐÃ GỬI VERIFY CODE HÃY CHECK MAIL !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                }

            }
        });

        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String code = vricode.getText().toString();
                if (email.getText().toString().length()<=0){
                    FancyToast.makeText(FogotPassActivity.this,"THIS FIELD IS REQUIERD !",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                }else {
                    if (code.equals(verificode)){
                        String user;
//                        user = DB.getUSER(Email); chủ yếu để lấy mấy thông tin người dùng rồi gửi qua bên đổi pass mới thích làm gì chỗ này thì làm
                        Intent intent = new Intent(getApplicationContext(), NewPassActivity.class);
                        intent.putExtra("email", Email);
//                        intent.putExtra("user",user);
                        startActivity(intent);

                        FancyToast.makeText(FogotPassActivity.this, "ĐÃ GỬI VERIFY CODE HÃY CHECK MAIL !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                    }else {
                        FancyToast.makeText(FogotPassActivity.this,"WRONG VERIFY CODE !",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    }
                }
            }
        });
    }

    public int Random(int randomInt){
        double random = Math.random();
        random = random * 9999 + 1000;
        randomInt = (int) random;
        return randomInt;

    }
}

