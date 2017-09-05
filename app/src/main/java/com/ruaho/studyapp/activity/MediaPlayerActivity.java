package com.ruaho.studyapp.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ruaho.studyapp.R;

/**
 * Created by ruaho on 2017/9/5.
 */

public class MediaPlayerActivity extends BaseActivity {

    private MediaPlayer mp;//mediaPlayer对象
    private Button play,pause,stop;//播放 暂停/继续 停止 按钮
    private TextView hint;//显示当前播放状态
    private boolean isPause=false;//是否暂停

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        initView();
        initMedia();
        initEvent();

    }

    private void initView() {
        play=(Button) findViewById(R.id.button1);
        pause=(Button) findViewById(R.id.button2);
        stop=(Button) findViewById(R.id.button3);
        hint=(TextView) findViewById(R.id.hint);
        hint.setTextSize(20);
    }

    private void initMedia() {
        //创建mediaplayer对象
        mp=MediaPlayer.create(MediaPlayerActivity.this, R.raw.sound);
        //MediaPlayer的设置数据内容
//        MediaPlayer的setDataSource一共四个方法：
//        setDataSource (String path)
//        setDataSource (FileDescriptor fd)
//        setDataSource (Context context, Uri uri)
//        setDataSource (FileDescriptor fd, long offset, long length)
    }

    private void initEvent() {
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                play();//重新开始播放
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
                if(isPause){
                    pause.setText("暂停");
                    isPause=false;
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()&&!isPause){
                    mp.pause();
                    isPause=true;
                    pause.setText("继续");
                    hint.setText("暂停播放音频...");
                    play.setEnabled(true);
                }else{
                    mp.start();
                    pause.setText("暂停");
                    hint.setText("继续播放音频...");
                    isPause=false;
                    play.setEnabled(false);
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                hint.setText("停止播放音频...");
                pause.setEnabled(false);
                stop.setEnabled(false);
                play.setEnabled(true);
            }
        });

    }

    private void play(){
        try{
            mp.reset();
            mp=MediaPlayer.create(MediaPlayerActivity.this, R.raw.sound);//重新设置要播放的音频
            mp.start();//开始播放
            hint.setText("正在播放音频...");
            play.setEnabled(false);
            pause.setEnabled(true);
            stop.setEnabled(true);
        }catch(Exception e){
            e.printStackTrace();//输出异常信息
        }
    }

    @Override
    protected void onDestroy() {
        if(mp.isPlaying()){
            mp.stop();
        }
        mp.release();//释放资源
        super.onDestroy();
    }
}
