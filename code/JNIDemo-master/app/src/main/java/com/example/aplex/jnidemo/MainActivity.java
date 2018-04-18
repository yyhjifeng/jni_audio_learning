package com.example.aplex.jnidemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;

import io.kvh.media.amr.AmrDecoder;
import io.kvh.media.amr.AmrEncoder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // 没有权限。
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                // 用户拒绝过这个权限了，应该提示用户，为什么需要这个权限。
            } else {
                // 申请授权。
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.REORDER_TASKS,Manifest.permission.CAPTURE_AUDIO_OUTPUT,Manifest.permission.MODIFY_AUDIO_SETTINGS}, 1009);
                Log.d(TAG,"申请授权");
            }
        }

    }

    public void sayHelloToc(View view){
        JniNative.sayHelloToC("Hello");

        new Thread(new Runnable() {
            @Override
            public void run() {
                JniNative.callMethod1();
                JniNative.callMethod2();
                JniNative.callMethod3();
            }
        }).start();

    }

    public void HelloFromC(View view){
        Log.d(TAG,"audioRecoderHandler...");
        final AudioPlayerHandler audioPlayerHandler=new AudioPlayerHandler();

        audioPlayerHandler.prepare();

        AudioRecoderHandler audioRecoderHandler=new AudioRecoderHandler(this);
        audioRecoderHandler.startRecord(new AudioRecoderHandler.AudioRecordingCallback(){

            @Override
            public void onRecording(byte[] data, int startIndex, int length) {
                  Logger.t(TAG).d("AudioRecoderHandler length-->"+length+",startIndex-->"+startIndex+",data-->"+data);
                  Log.d(TAG,"AudioRecoderHandler length-->"+length+",startIndex-->"+startIndex+",data-->"+data);

                audioPlayerHandler.onPlaying(data,startIndex,length);

            }

            @Override
            public void onStopRecord(String savedPath) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1009: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 权限被用户同意，可以去放肆了。
                    Log.e(TAG,"权限被用户同意，可以去放肆了.");
                } else {
                    // 权限被用户拒绝了，洗洗睡吧。
                    Log.e(TAG,"权限被用户拒绝了，洗洗睡吧。");
                }
                return;
            }
        }
    }
}
