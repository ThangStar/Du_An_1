package JAVA.VIEW;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.developer.cubemarket.R;

public class ScreenOptionThongkeActivity extends AppCompatActivity {
    Button bnt1,bnt2,bnt3;
    // chuyền id_user và chức vụ vào đây
    int CHUCVU = 2;
    int ID = 1;


    String TAG = "TAG123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_option_thongke);
        bnt1=(Button) findViewById(R.id.bnt_inten_day);
        bnt2=(Button) findViewById(R.id.bnt_inten_month);
        bnt3=(Button) findViewById(R.id.bnt_inten_year);
        bnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // chuyền id_user và chức vụ vào đây
                Intent intent= new Intent(ScreenOptionThongkeActivity.this,ThongkeActivity.class);
                intent.putExtra("ID_URSE" ,String.valueOf(ID));
                intent.putExtra("CHUCVU" ,String.valueOf(CHUCVU));
                intent.putExtra("CHON_THONG_KE" ,"THEONGAY");
                startActivity(intent);
            }
        });
        bnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // chuyền id_user và chức vụ vào đây
                Intent intent= new Intent(ScreenOptionThongkeActivity.this,ThongkeActivity.class);
                intent.putExtra("ID_URSE" ,String.valueOf(ID));
                intent.putExtra("CHUCVU" ,String.valueOf(CHUCVU));
                intent.putExtra("CHON_THONG_KE" ,"THEOTHANG");
                startActivity(intent);
            }
        });
        bnt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // chuyền id_user và chức vụ vào đây
                Intent intent= new Intent(ScreenOptionThongkeActivity.this,ThongkeActivity.class);
                intent.putExtra("ID_URSE" ,String.valueOf(ID));
                intent.putExtra("CHUCVU" ,String.valueOf(CHUCVU));
                intent.putExtra("CHON_THONG_KE" ,"THEONAM");
                startActivity(intent);
            }
        });

    }
}