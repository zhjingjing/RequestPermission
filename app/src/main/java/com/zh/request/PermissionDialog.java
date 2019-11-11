package com.zh.request;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * create by zj on 2019/11/11
 */
public class PermissionDialog extends Dialog {
    private Context context;
    private LinearLayout llCamera;
    private LinearLayout llLocation;
    private LinearLayout llVoice;
    public PermissionDialog( @NonNull Context context) {
        super(context);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_permission);
        setCancelable(false);
        llCamera=findViewById(R.id.ll_camera);
        llLocation=findViewById(R.id.ll_location);
        llVoice=findViewById(R.id.ll_voice);
        initView();

        //设置背景透明
        Window window = getWindow();
        //设置dialog的宽，必须在dialog.show之后
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = context.getResources().getDisplayMetrics().widthPixels / 10 * 8;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

    private void initView() {


        llCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked.setLlCamera();
            }
        });

        llVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked.setLlVoice();
            }
        });

        llLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked.setLlLocation();
            }
        });

    }


    public void setLlCamera(){
        Log.e("xx","setLlCamera");
        if (llCamera!=null)
        llCamera.setBackgroundResource(R.drawable.bg_press);
    }

    public void setLlLocation(){
        Log.e("xx","setLlLocation");
        if (llLocation!=null)
        llLocation.setBackgroundResource(R.drawable.bg_press);
    }

    public void setLlVoice(){
        if (llVoice!=null)
        Log.e("xx","setLlVoice");
        llVoice.setBackgroundResource(R.drawable.bg_press);
    }

    public interface OnItemClicked{
        void setLlCamera();
        void setLlLocation();
        void setLlVoice();
    }

    OnItemClicked onItemClicked;

    public void setOnItemClicked(OnItemClicked onItemClicked) {
        this.onItemClicked = onItemClicked;
    }
}
