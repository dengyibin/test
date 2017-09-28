package com.example.sy001.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sy001.myapplication.floatbutton.FloatingButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengyibin on 2016/10/19.
 */
public class DesignSupportActivity extends Activity {
    RecyclerView mRecyclerView;
    int[] imgs = {R.drawable.xx013001,R.drawable.xx014001,R.drawable.xx014002,R.drawable.xx015001};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_support);
        mRecyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            stringList.add("ceshi-----"+i);
        }
        MyAdapter adapter = new MyAdapter();
        adapter.setStrings(stringList);
        mRecyclerView.setAdapter(adapter);
        FloatingButton child = new FloatingButton(this);
        RelativeLayout relativeLayout  = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(150, 150);
        FrameLayout.LayoutParams layoutparams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(layoutparams);
        child.setLayoutParams(params);
        relativeLayout.addView(child);

    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_in_from_bottom, R.anim.activity_out_from_top);
    }

    private class MyAdapter extends RecyclerView.Adapter {
        private List<String> mStrings;

        public void setStrings(List<String> strings) {
            mStrings = strings;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text,parent,false);
            return new MyViewHolder(view);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyViewHolder)holder).mText.setText(mStrings.get(position));
            Glide.with(DesignSupportActivity.this).load(imgs[position%4]).into(((MyViewHolder)holder).image);
        }


        @Override
        public int getItemCount() {
            return mStrings.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView mText;
            public ImageView image;

            public MyViewHolder(View itemView) {
                super(itemView);
                mText = (TextView) itemView.findViewById(R.id.text);
                image = (ImageView) itemView.findViewById(R.id.img);
            }
        }
    }
}
