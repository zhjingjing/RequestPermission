package com.zh.request;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zh.util.IPermissionListener;
import com.zh.util.RequestUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestUtils.getInstance().requestPermission(this, new IPermissionListener() {
            @Override
            public void success() {
                Toast.makeText(MainActivity.this,"授权成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cancel() {
                Toast.makeText(MainActivity.this,"拒绝还怎么玩啊",Toast.LENGTH_SHORT).show();
            }
        }, Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RequestUtils.getInstance().onRequestResult(requestCode,permissions,grantResults);
    }
}
