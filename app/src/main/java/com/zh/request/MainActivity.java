package com.zh.request;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.zh.util.IPermissionListener;
import com.zh.util.RequestUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {
     PermissionDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog =new PermissionDialog(this);
        dialog.setOnItemClicked(new PermissionDialog.OnItemClicked() {
            @Override
            public void setLlCamera() {
                showPermission( Manifest.permission.CAMERA);
            }

            @Override
            public void setLlLocation() {
                showPermission( Manifest.permission.ACCESS_FINE_LOCATION);
            }

            @Override
            public void setLlVoice() {
                showPermission( Manifest.permission.RECORD_AUDIO);
            }
        });
        dialog.show();

       }
//    Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO

       public void showPermission(final String permission){
           RequestUtils.getInstance().requestPermission(this, new IPermissionListener() {

               @Override
               public void success(List<String> permissions) {
                   Log.e("xxx",permissions.toString());
                   for (int i=0;i<permissions.size();i++){
                       if (permission.equals(Manifest.permission.CAMERA)){
                           dialog.setLlCamera();
                       }
                       if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)){
                           dialog.setLlLocation();
                       }
                       if (permission.equals(Manifest.permission.RECORD_AUDIO)){
                           dialog.setLlVoice();
                       }

                   }
               }

               @Override
               public void cancel() {
                   Toast.makeText(MainActivity.this,"拒绝还怎么玩啊",Toast.LENGTH_SHORT).show();
               }
           }, permission);

       }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RequestUtils.getInstance().onRequestResult(requestCode,permissions,grantResults);
    }
}
