package com.developer.cubemarket;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OPT extends AppCompatActivity {

    int code;
    EditText passcode1;
    EditText passcode2;
    EditText passcode3;
    EditText passcode4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opt);
        passcode1=findViewById(R.id.digit1);
        passcode2=findViewById(R.id.digit2);
        passcode3=findViewById(R.id.digit3);
        passcode4=findViewById(R.id.digit4);

        passcode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    passcode2.requestFocus();
                }
            }
        });
        passcode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    passcode2.requestFocus();
                }
            }
        });
        passcode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    passcode4.requestFocus();
                }
            }
        });

    }

    public void sendVerifyEmail(View view) {
        Random random = new Random();
        code = random.nextInt(8999)+1000;
        EditText emailTXT = findViewById(R.id.email);
        String url ="https://rahilacademy.com/otp/sendEmail.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(OPT.this, response, Toast.LENGTH_SHORT).show();
                findViewById(R.id.box1).setVisibility(View.GONE);
                findViewById(R.id.box2).setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OPT.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("email", emailTXT.getText().toString());
                params.put("code", String.valueOf(code));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void checkCode(View view) {
        String inputCode;
        inputCode =passcode1.getText().toString()+passcode2.getText()+passcode3.getText()+passcode4.getText();

        if (inputCode.equals(String.valueOf(code))){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }
}