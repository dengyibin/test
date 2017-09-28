package com.example.sy001.myapplication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.sy001.myapplication.floatbutton.FloatingButton;
import com.example.sy001.myapplication.flowchart.CircleFlowChartView;
import com.example.sy001.myapplication.flowchart.FlowChartData;
import com.example.sy001.myapplication.flowchart.FlowPhaseData;
import com.example.sy001.myapplication.flowchart.LineData;
import com.example.sy001.myapplication.flowchart.NodeData;
import com.example.sy001.myapplication.flowchart.NodeStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by sy001 on 2015/10/9.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName().toString();
    private static final int CAMERA = 100;
    private Context mContext;
    Integer[] arrays = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    String[] apps = {"faf", "gaghhhh", "dd", "asda", "fagagh", "aghhhhhh"};
    @Inject
    DaggerTest mDaggerTest;
    private ImageView mImageView;
    String tempFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp1.jpg";
    private String mPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp.jpg";
    private int CAMERA_RQ = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        FloatingButton child = new FloatingButton(this);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(150, 150);
        FrameLayout.LayoutParams layoutparams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(layoutparams);
        child.setLayoutParams(params);
        relativeLayout.addView(child);

        FrameLayout decorView = (FrameLayout) getWindow().getDecorView();
        decorView.addView(relativeLayout);

        mContext = this;
        String json = "{\"items\":[{\"id\":\"20170725153815004510\",\"deptid\":\"007541934\",\"limitunit\":\"G\",\"sxid\":\"11020001700754193414440306\",\"left\":\"40\",\"limitdays\":\"20\",\"type\":\"1\",\"deptname\":\"经促局\",\"top\":\"40\",\"sxmc\":\"商业企业办理投资者股权质押审批\"},{\"id\":\"20170725153815004810\",\"deptid\":\"007550531\",\"limitunit\":\"G\",\"sxid\":\"11050100200755053114440306\",\"left\":\"320\",\"limitdays\":\"20\",\"type\":\"1\",\"deptname\":\"民政局\",\"top\":\"220\",\"sxmc\":\"区管权限内民办非企业单位变更登记\"},{\"id\":\"20170725153815005110\",\"deptid\":\"007543235\",\"limitunit\":\"G\",\"sxid\":\"11102100100754323514440306\",\"left\":\"40\",\"limitdays\":\"5\",\"type\":\"1\",\"deptname\":\"文体旅游局\",\"top\":\"220\",\"sxmc\":\"非出版物印刷企业设立许可\"}],\"lines\":[{\"to\":\"20170725153815005110\",\"from\":\"20170725153815004510\"},{\"to\":\"20170725153815004810\",\"from\":\"20170725153815004510\"}],\"vtype\":\"tb\"}";
        JsonObject object = new Gson().fromJson(json, JsonObject.class);
        System.out.println(object.toString());
//        final Observable<Long>  justObserver = JustObserver();
//        final Observable<Long>  deferObserver = DeferObserver();
//        final Observable<Integer> from = FromArray();
//        final Observable<Long> test = test();
        mImageView = (ImageView) findViewById(R.id.image);
//        mImageView.setImageResource(R.drawable.img003001);
//        HelloWorld.main(null);
//        if (!OpenCVLoader.initDebug()) {
//            Log.e(this.getClass().getSimpleName(), "  OpenCVLoader.initDebug(), not working.");
//        } else {
//            Log.d(this.getClass().getSimpleName(), "  OpenCVLoader.initDebug(), working.");
//        }

        CircleFlowChartView flowChartView = (CircleFlowChartView) findViewById(R.id.flowChart);
        List<FlowPhaseData> flowPhaseDatas = new ArrayList<>();
        FlowPhaseData phaseData = new FlowPhaseData();
        List<NodeData> nodeDatas = new ArrayList<>();
        nodeDatas.add(new NodeData("人防审批", 1, NodeStatus.COMPLETE));
        nodeDatas.add(new NodeData("测试2", 2, NodeStatus.NOT_REQUIRED));
        phaseData.setPhaseName("第一阶段");
        phaseData.setNodeDatas(nodeDatas);

        FlowPhaseData phaseData2 = new FlowPhaseData();
        List<NodeData> nodeDatas2 = new ArrayList<>();
        nodeDatas2.add(new NodeData("人防审批", 3, NodeStatus.ERROR));
        nodeDatas2.add(new NodeData("测试2", 4, NodeStatus.IN_PROGRESS));
        nodeDatas2.add(new NodeData("测试3", 5, NodeStatus.COMPLETE));
        phaseData2.setPhaseName("第二阶段");
        phaseData2.setNodeDatas(nodeDatas2);

        FlowPhaseData phaseData3 = new FlowPhaseData();
        List<NodeData> nodeDatas3 = new ArrayList<>();
        nodeDatas3.add(new NodeData("人防审批", 6, NodeStatus.COMPLETE));
        phaseData3.setPhaseName("第三阶段");
        phaseData3.setNodeDatas(nodeDatas3);

        FlowPhaseData phaseData4 = new FlowPhaseData();
        List<NodeData> nodeDatas4 = new ArrayList<>();
        nodeDatas4.add(new NodeData("测试2", 7, NodeStatus.IN_PROGRESS));
        nodeDatas4.add(new NodeData("测试3", 8, NodeStatus.IN_PROGRESS));
        nodeDatas4.add(new NodeData("测试3", 9, NodeStatus.NOT_START));
        nodeDatas4.add(new NodeData("测试3", 10, NodeStatus.NOT_START));
        nodeDatas4.add(new NodeData("测试3", 11, NodeStatus.NOT_START));
        nodeDatas4.add(new NodeData("测试3", 12, NodeStatus.NOT_START));
        phaseData4.setPhaseName("第四阶段");
        phaseData4.setNodeDatas(nodeDatas4);

        FlowPhaseData phaseData5 = new FlowPhaseData();
        List<NodeData> nodeDatas5 = new ArrayList<>();
        nodeDatas5.add(new NodeData("人防审批", 13, NodeStatus.NOT_START));
        phaseData5.setPhaseName("第五阶段");
        phaseData5.setNodeDatas(nodeDatas5);

        flowPhaseDatas.add(phaseData);
        flowPhaseDatas.add(phaseData2);
        flowPhaseDatas.add(phaseData3);
        flowPhaseDatas.add(phaseData4);
        flowPhaseDatas.add(phaseData5);
        List<LineData> lineData = new ArrayList<>();
        lineData.add(new LineData(1, 3));
        lineData.add(new LineData(2, 3));
        lineData.add(new LineData(1, 4));
        lineData.add(new LineData(1, 5));
        lineData.add(new LineData(5, 6));
        lineData.add(new LineData(3, 6));
        lineData.add(new LineData(6, 9));
        lineData.add(new LineData(6, 10));
        lineData.add(new LineData(6, 11));
        lineData.add(new LineData(6, 12));
        lineData.add(new LineData(6, 7));
        lineData.add(new LineData(7, 13));
        lineData.add(new LineData(8, 13));
        lineData.add(new LineData(9, 13));
        lineData.add(new LineData(10, 13));
        FlowChartData chartData = new FlowChartData(flowPhaseDatas, lineData);
        flowChartView.setChartData(chartData);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                downLoadTest();
//                playAacTest();

//                RxJavaTest();
                startActivity(new Intent(mContext, LifecircleActivity.class));


            }
        });
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        DaggerAppComponent.builder().build().in(this);
//        System.out.println(""+mDaggerTest.getName());
        findViewById(R.id.btn_design_support).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, DesignSupportActivity.class));
//                View mCustomView = findViewById(R.id.customview);
//                mCustomView.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.translate));
//                mCustomView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.translate));
//                ObjectAnimator.ofFloat(mCustomView,"translationX",0,100,200,300).setDuration(10000).start();

//                File saveDir = null;
//
//                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        == PackageManager.PERMISSION_GRANTED) {
//                    // Only use external storage directory if permission is granted, otherwise cache directory is used by default
//                    saveDir = new File(Environment.getExternalStorageDirectory(), "MaterialCamera");
//                    saveDir.mkdirs();
//                }
////
//                MaterialCamera materialCamera =
//                        new MaterialCamera(MainActivity.this)
//                                .saveDir(saveDir)
//                                .showPortraitWarning(true)
//                                .allowRetry(true)
//                                .defaultToFrontFacing(true);
//
//
//                    materialCamera.stillShot(); // launches the Camera in stillshot mode
//
//                materialCamera.start(CAMERA_RQ);

            }
        });
        findViewById(R.id.btn_RxJava2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(mContext, TestLibActivity.class));
            }
        });
        findViewById(R.id.btn_webView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, Main3Activity.class));
            }
        });
        findViewById(R.id.btn_ijk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MainActivity2.class));
            }
        });
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).
                doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integers) throws Exception {
                        System.out.println(integers);
                    }
                }).
                contains(4).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println("" + aBoolean.booleanValue());
            }

        });

    }


    private Observable<Long> JustObserver() {
        return Observable.just(System.currentTimeMillis());
    }


    private Observable<Long> test() {
//        return Observable.from(arrays);
        return Observable.just(1).timer(2, TimeUnit.SECONDS).repeat();
    }

    private Observable<List<Integer>> bufferObserver() {
        return Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).buffer(3, 4);
    }

//    private Observable<List<Long>> bufferTimeObserver() {
//        return Observable.interval(1, TimeUnit.SECONDS).buffer(3, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread());
//    }


}
