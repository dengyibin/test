package com.example.sy001.myapplication;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.DownloadRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by sy001 on 2015/10/9.
 */
public class MainActivity extends Activity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                downLoadTest();
//                playAacTest();

                RxJavaTest();

            }
        });
    }

    private Observable<List<String>> query(String s) {
        final List<String> urls = new ArrayList<>();
        urls.add("aa1");
        urls.add(null);
        urls.add("aa2");

        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                subscriber.onNext(urls);
            }
        });
    }

    Observable<String> getTitle(final String URL) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(URL.substring(1));
            }
        });
    }

    private void RxJavaTest() {


        query("ss").flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> urls) {
                return Observable.from(urls);
            }
        }).take(2).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return s != null;
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return getTitle(s);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });


    }

    private void playAacTest() {
        MediaPlayer mediaPlayer = null;
        try {
            File sdDir = Environment.getExternalStorageDirectory();//
            String path = sdDir.getAbsolutePath() + "/怒放的生命.aac";
            mediaPlayer = AudioEngineer.createAudioPlayer(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();
    }

    private void downLoadTest() {
        String url = "http://192.9.8.156:8080/cdisk/ws/bigfileservice/download/561875c769a249921d0a85b2";
        String path = Environment.getExternalStorageDirectory().getPath() + "/设计模式 - 可复用面向对象软件的基础（高清版）.pdf";
//                DownloadRequest request = new DownloadRequest(url, path, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.i("MainActivity==",response);
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//                request.setOnProgressListener(new Response.ProgressListener() {
//                    @Override
//                    public void onProgress(long transferredBytes, long totalSize) {
//                        Log.i("MainActivity==",""+transferredBytes);
//                    }
//                });
        DownloadRequest downloadRequest = new DownloadRequest(url, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                        fileBean.setFileStatus(-3);//下载成功
//                        fileBean.setFileProgress(100);
//                        bean.setFileBean(fileBean);
//                Intent intent = new Intent();
//                intent.setAction(MessageActionContants.sendFileTransferAction);
//                intent.addCategory(MessageActionContants.fileTransferCategory);
//                intent.putExtra(ImCommonConstants.CZINGMESSAGE, bean);
//                EventBus.getDefault().post(intent);
                Log.e("onProgress", "onResponse:" + response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                        fileBean.setFileStatus(-1);//下载失败
                error.printStackTrace();
            }
        });

        downloadRequest.setOnProgressListener(new Response.ProgressListener() {
            @Override
            public void onProgress(long transferredBytes, long totalSize) {
//                fileBean.setFileProgress((int) ((float) transferredBytes / totalSize * 100));//文件下载进度赋值
//                Log.e(TAG,  "totalSize:" + totalSize + ",transferredBytes:" + transferredBytes + ",progress:" + (int) ((float) transferredBytes / totalSize * 100) + ",flieSize:" + fileBean.getFileSize());
//                        for (int i = 0; i < listeners.size(); i++) {
//                            listeners.get(i).onUpdate((int) (transferredBytes * 100 / totalSize), bean);
//                        }
                if (transferredBytes % 1024 == 0) {
                    Log.e("onProgress", "totalSize:" + totalSize + ",transferredBytes:" + transferredBytes + ",flieSize:");
                }
            }
        });

//                EleduApplication.getInstance().addToRequestQueue(downloadRequest);
//            }
        Volley.newRequestQueue(getApplicationContext()).add(downloadRequest);
    }
}
