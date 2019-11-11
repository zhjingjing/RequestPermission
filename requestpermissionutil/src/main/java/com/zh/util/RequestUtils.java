package com.zh.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import java.util.ArrayList;
import java.util.List;

/**
 * create by zj on 2019/10/23
 */
public class RequestUtils {


    private final static int REQUEST_CODE=1101;

    IPermissionListener iPermissionListener;

    Activity activity;

    private final static RequestUtils REQUEST_UTILS=new RequestUtils();

    public static RequestUtils getInstance(){
        return REQUEST_UTILS;
    }

    public  void requestPermission(Activity activity,IPermissionListener iPermissionListener,String...permissions){
        this.iPermissionListener=iPermissionListener;
        this.activity=activity;

        List<String> permissionLists=new ArrayList<>();

        for (String permission:permissions){
            if ( ContextCompat.checkSelfPermission(activity,permission)!= PackageManager.PERMISSION_GRANTED){
                permissionLists.add(permission);
            }
        }

        if (!permissionLists.isEmpty()){
            ActivityCompat.requestPermissions(activity,permissionLists.toArray(new String[permissionLists.size()]),REQUEST_CODE);
        }else{
            iPermissionListener.success();
        }
    }

    public void onRequestResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode){
            case REQUEST_CODE:
                //存在未授权的
                if (grantResults.length>0){
                    List<String> list=new ArrayList<>();
                     for (int i=0;i<permissions.length;i++){
                         int result=grantResults[i];
                         String permission=permissions[i];
                         if (result!=PackageManager.PERMISSION_GRANTED){
                             list.add(permission);
                         }
                     }
                    if (iPermissionListener!=null)
                     if (list.size()>0){
                         iPermissionListener.cancel();
                     }else {
                         iPermissionListener.success();
                     }
                }
                break;
        }
    }
}
