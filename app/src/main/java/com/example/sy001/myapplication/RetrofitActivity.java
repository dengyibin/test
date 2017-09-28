package com.example.sy001.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dengyibin on 2016/3/17.
 */
public class RetrofitActivity extends AppCompatActivity {

    @BindView(R.id.btn_click_me)
    Button btnClickMe;
    @BindView(R.id.tv_result)
    TextView tvResult;


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
        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);

//        call.enqueue(new Callback<MovieEntity>() {
//            @Override
//            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
//                tvResult.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<MovieEntity> call, Throwable t) {
//                tvResult.setText(t.getMessage());
//            }
//        });

    }
}
