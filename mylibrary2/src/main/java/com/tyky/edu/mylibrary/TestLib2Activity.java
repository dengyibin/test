package com.tyky.edu.mylibrary;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tyky.edu.mylibrary.databinding.TestBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dengyibin on 2017/8/11.
 *
 * @description
 */

public class TestLib2Activity extends AppCompatActivity {
    @BindView(R2.id.test_tv)
    TextView testView;
    @BindView(R2.id.test_btn)
    Button btn;
    Test test;
    private TestBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        binding = DataBindingUtil.setContentView(this, R.layout.test);
        ButterKnife.bind(this);
        btn.setText("按钮");

        test = new Test();
        test.name ="测试2";
        binding.setTest(test);
//        findViewById(R.id.test_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                test.name = "测试"+System.currentTimeMillis();
////                binding.setTest(test);
//                Toast.makeText(TestLibActivity.this,"ce="+(R.id.test_tv==R2.id.test_tv),Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @OnClick(R2.id.test_btn)
    void onClickShow(View v) {
        Toast.makeText(TestLibActivity.this,"测试="+testView.getText(),Toast.LENGTH_SHORT).show();
        test.name = "测试"+System.currentTimeMillis();
                binding.setTest(test);
    }
}
