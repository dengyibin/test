package com.example.sy001.myapplication;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by dengyibin on 2016/12/13.
 */
public class RxJava2Activity extends AppCompatActivity{
    @BindView(R.id.btn_click_me)
    Button btnClickMe;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.myImageView)
    ImageView mImageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_click_me)
    public void onClick() {
        getMovie();
    }

    private void getMovie(){

        Flowable flowable=   Flowable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "fegg";
            }
        });
        flowable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                tvResult.setText(s);
            }
        });
        Drawable drawable = mImageView.getDrawable();
        if (drawable instanceof Animatable){
            Animatable drawable1 = (Animatable) drawable;
            drawable1.start();
        }

    }
}
