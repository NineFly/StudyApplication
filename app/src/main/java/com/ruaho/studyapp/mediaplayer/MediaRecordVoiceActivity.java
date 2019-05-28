package com.ruaho.studyapp.mediaplayer;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ruaho.studyapp.R;
import com.ruaho.studyapp.activity.BaseActivity;

import java.io.File;

/**
 * MediaRecorder录制音频
 *
 * 博客:https://www.cnblogs.com/plokmju/p/android_MediaRecorder.html
 *
 */

public class MediaRecordVoiceActivity extends BaseActivity {

    private Button btn_RecordStart, btn_RecordStop;
    private MediaRecorder mediaRecorder;
    private boolean isRecording;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_record_voice);

        btn_RecordStart = (Button) findViewById(R.id.btn_RecordStart);
        btn_RecordStop = (Button) findViewById(R.id.btn_RecordStop);

        btn_RecordStop.setEnabled(false);

        btn_RecordStart.setOnClickListener(click);
        btn_RecordStop.setOnClickListener(click);
    }

    private View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_RecordStart:
                    start();
                    break;
                case R.id.btn_RecordStop:
                    stop();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 开始录音
     */
    protected void start() {
        try {
            File file = new File("/sdcard/mediarecorder.amr");
            if (file.exists()) {
                // 如果文件存在，删除它，演示代码保证设备上只有一个录音文件
                file.delete();
            }
            mediaRecorder = new MediaRecorder();
            // 设置音频录入源
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            // 设置录制音频的输出格式
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            // 设置音频的编码格式
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            // 设置录制音频文件输出文件路径
            mediaRecorder.setOutputFile(file.getAbsolutePath());

            mediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {

                @Override
                public void onError(MediaRecorder mr, int what, int extra) {
                    // 发生错误，停止录制
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    isRecording=false;
                    btn_RecordStart.setEnabled(true);
                    btn_RecordStop.setEnabled(false);
                    Toast.makeText(MediaRecordVoiceActivity.this, "录音发生错误", 0).show();
                }
            });

            // 准备、开始
            mediaRecorder.prepare();
            mediaRecorder.start();

            isRecording=true;
            btn_RecordStart.setEnabled(false);
            btn_RecordStop.setEnabled(true);
            Toast.makeText(MediaRecordVoiceActivity.this, "开始录音", 0).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 录音结束
     */
    protected void stop() {
        if (isRecording) {
            // 如果正在录音，停止并释放资源
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording=false;
            btn_RecordStart.setEnabled(true);
            btn_RecordStop.setEnabled(false);
            Toast.makeText(MediaRecordVoiceActivity.this, "录音结束", 0).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (isRecording) {
            // 如果正在录音，停止并释放资源
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
        super.onDestroy();
    }

}
