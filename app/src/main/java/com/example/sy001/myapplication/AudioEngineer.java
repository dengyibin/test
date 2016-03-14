package com.example.sy001.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;

import com.czt.mp3recorder.MP3Recorder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AudioEngineer {

    private File mRecAudioFile;
    private File mRecAudioPath;
    private MP3Recorder mRecorder;
    /* MediaRecorder对象 */
//	private MediaRecorder	mMediaRecorder;
    /* 录音文件列表 */
//	private List<String>	mMusicList	= new ArrayList<String>();
    /* 零时文件的前缀 */
    private String strTempFile = "recaudio_";

    public AudioEngineer() {
        mRecAudioPath = Environment.getExternalStorageDirectory();
    }


    public void createMusic(String path) {
        try {
            /* 创建录音文件 */
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            mRecAudioFile = File.createTempFile(strTempFile, ".mp3", file);
            mRecorder = new MP3Recorder(mRecAudioFile);
            mRecorder.start();

//			/* 实例化MediaRecorder对象 */
//			mMediaRecorder = new MediaRecorder();
//			/* 设置麦克风 */
//			mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//			/* 设置输出文件的格式 */
//			mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
//			/* 设置音频文件的编码 */
//			mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
//			/* 设置输出文件的路径 */
//			mMediaRecorder.setOutputFile(mRecAudioFile.getAbsolutePath());
//			/* 准备 */
//			mMediaRecorder.prepare();
//			/* 开始 */
//			mMediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveMusic() {
        // TODO Auto-generated method stub
        if (mRecAudioFile != null) {
            try {
                mRecorder.stop();
            } catch (Exception e) {
            }
//			/* 停止录音 */
//			mMediaRecorder.stop();
//			/* 将录音文件添加到List中 */
//			mMusicList.add(mRecAudioFile.getName());
//			
//			/* 释放MediaRecorder */
//			mMediaRecorder.release();
//			mMediaRecorder = null;
        }
    }


    public String getMusic() {

        if (mRecAudioFile != null) {
//			return Environment.getExternalStorageDirectory()+File.separator+ mRecAudioFile.getName();
            return mRecAudioFile.getAbsolutePath();
        } else {
            return null;
        }

    }

    /* 播放录音文件 */
    public static void playMusic(File file, Context context) {
        try {

            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
			/* 设置文件类型 */
            intent.setDataAndType(Uri.fromFile(file), "audio");
            context.startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static MediaPlayer createAudioPlayer(File audioFile) throws IOException {
        MediaPlayer mPlayer = new MediaPlayer();

        // It appears that for security/permission reasons, it is better to
        // pass
        // a FileDescriptor rather than a direct path to the File.
        // Also I have seen errors such as "PVMFErrNotSupported" and
        // "Prepare failed.: status=0x1" if a file path String is passed to
        // setDataSource(). So unless otherwise noted, we use a
        // FileDescriptor here.
        FileInputStream fis = new FileInputStream(audioFile);
        mPlayer.reset();
        mPlayer.setDataSource(fis.getFD());
        mPlayer.prepare();
        return mPlayer;
    }
}
