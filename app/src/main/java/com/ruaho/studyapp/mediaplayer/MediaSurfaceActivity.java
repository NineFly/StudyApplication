package com.ruaho.studyapp.mediaplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.ruaho.studyapp.R;
import com.ruaho.studyapp.activity.BaseActivity;
import com.ruaho.studyapp.activity.MainActivity;

import java.io.File;
import java.io.IOException;

public class MediaSurfaceActivity extends BaseActivity {


    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    //读取本地文件
    private File file=new File("/storage/sdcard1/音乐/", "这里就是放本地文件.mp4");
    //访问网络视频
    private String uri= "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_media_surface);
        uri = "android.resource://" + this.getPackageName() + "/" + R.raw.dd;
//        uri = "http://v.baidu.com/watch/5742701164405633660.html?fr=ps_ala11&wd=.mp4";
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mediaPlayer = new MediaPlayer();
        //获取SurfaceHolder 可以通过该接口来操作SurfaceView中的Surface
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        //设置Meiaplayer的准备监听
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //准备完成后播放
                mediaPlayer.start();
            }
        });


        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            //当SurfaceView中Surface创建时回掉
            //该方法表示Surface已经创建完成，可以在该方法中进行绘图操作
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mediaPlayer.reset();
                try {
                    //设置视屏文件图像的显示参数
                    mediaPlayer.setDisplay(holder);

                    //file.getAbsolutePath()本地视频
                    //uri 网络视频
                    mediaPlayer.setDataSource(MediaSurfaceActivity.this, Uri.parse(uri));
                    //prepare();表示准备工作同步进行，（准备工作在UI线程中进行）
                    //当播放网络视频时，如果网络不要 会报ARN 所以不采用该方法
                    //mediaPlayer.prepare();
                    //异步准备 准备工作在子线程中进行 当播放网络视频时候一般采用此方法
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //当SurfaceView的大小发生改变时候触发该方法
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }
            //Surface销毁时回掉
            //当Surface销毁时候，同时把MediaPlayer也销毁
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (mediaPlayer!=null) {
                    mediaPlayer.stop();
                    //释放资源
                    mediaPlayer.release();
                }
            }
        });
        //设置 surfaceView点击监听
        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                        } else {
                            mediaPlayer.start();
                        }
                        break;
                }
                //返回True代表事件已经处理了
                return true;
            }
        });

    }


}
