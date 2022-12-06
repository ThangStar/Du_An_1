package com.developer.cubemarket.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.cubemarket.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import es.dmoral.toasty.Toasty;

public class ForgotPassFragment extends Fragment {
    TextView otp;
    EditText otp_box_1,otp_box_2,otp_box_3,otp_box_4,otp_box_5,otp_box_6;
    MaterialButton btnCheckCode;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_forgot_pass, container, false);

        otp = v.findViewById(R.id.otp);
        otp_box_1 = v.findViewById(R.id.otp_box_1);
        otp_box_2 = v.findViewById(R.id.otp_box_2);
        otp_box_3 = v.findViewById(R.id.otp_box_3);
        otp_box_4 = v.findViewById(R.id.otp_box_4);
        otp_box_5 = v.findViewById(R.id.otp_box_5);
        otp_box_6 = v.findViewById(R.id.otp_box_6);

        btnCheckCode = v.findViewById(R.id.btnCheckCode);
        btnCheckCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String otpUser = otp_box_1.getText().toString()+otp_box_2.getText().toString()+
                            otp_box_3.getText().toString()+otp_box_4.getText().toString()+
                            otp_box_5.getText().toString()+otp_box_6.getText().toString();
                    assert getArguments() != null;
                    String valueTrue = getArguments().getString("CODE");
                    String email = getArguments().getString("EMAIL");

                    Log.d("SSS", "code: "+valueTrue);
                    if(otpUser.equals(valueTrue)){
                        Toasty.success(v.getContext(), "Xác minh thành công!", Toast.LENGTH_SHORT, true).show();

                        Bundle bd = new Bundle();
                        bd.putString("CODE", valueTrue);
                        bd.putString("EMAIL", email);

                        Navigation.findNavController(v).navigate(R.id.action_forgotPassFragment_to_newPassFragment, bd);
//                        MotionToast.Companion.createColorToast(EnterCode.this,"",
//                                MotionToast.Companion.getTOAST_SUCCESS(),
//                                MotionToast.Companion.getGRAVITY_BOTTOM(),
//                                MotionToast.Companion.getSHORT_DURATION(),
//                                ResourcesCompat.getFont(EnterCode.this, www.sanju.motiontoast.R.font.helveticabold));

//                        Intent it = new Intent(v.getContext(), NewPass.class);
//                        it.putExtra("EmailUser", getIntent().getExtras().getString("EmailUser"));
//                        startActivity(it);
                    }else{
                        Toasty.error(v.getContext(), "Mã xác minh không chính xác!", Toast.LENGTH_SHORT, true).show();

//                        MotionToast.Companion.createColorToast(EnterCode.this,"",
//                                MotionToast.Companion.getTOAST_ERROR(),
//                                MotionToast.Companion.getGRAVITY_BOTTOM(),
//                                MotionToast.Companion.getSHORT_DURATION(),
//                                ResourcesCompat.getFont(EnterCode.this, www.sanju.motiontoast.R.font.helveticabold));
                    }
                }catch (Exception ex) {
                    ex.printStackTrace();
                    Toasty.error(v.getContext(), "ĐÃ XẢY RA LỖI, KIỂM TRA LẠI BẠN ĐÃ NHẬP ĐẦY ĐỦ CHƯA!", Toast.LENGTH_SHORT, true).show();

//                    MotionToast.Companion.createColorToast(EnterCode.this,"",
//                            MotionToast.Companion.getTOAST_ERROR(),
//                            MotionToast.Companion.getGRAVITY_BOTTOM(),
//                            MotionToast.Companion.getSHORT_DURATION(),
//                            ResourcesCompat.getFont(EnterCode.this, www.sanju.motiontoast.R.font.helveticabold));

                }

            }
        });
        otp_box_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_2.requestFocus();
                }
            }
        });
        otp_box_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_3.requestFocus();
                }
            }
        });
        otp_box_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_4.requestFocus();
                }
            }
        });
        otp_box_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_5.requestFocus();
                }
            }
        });
        otp_box_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_6.requestFocus();
                }
            }
        });

        return v;
    }
}