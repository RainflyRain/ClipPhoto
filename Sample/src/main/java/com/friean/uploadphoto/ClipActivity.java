package com.friean.uploadphoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.friean.clipphoto.ClipImageLayout;
import com.friean.clipphoto.ImageTools;

import java.io.File;


@SuppressWarnings("ConstantConditions")
public class ClipActivity extends AppCompatActivity{

    private ClipImageLayout mClipImageLayout;
    public static final String PATH_NAME = "path";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip);
        getSupportActionBar().hide();

        /*初始化裁剪图片资源和裁剪形状*/
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.photo);
        mClipImageLayout = (ClipImageLayout) findViewById(R.id.id_clipImageLayout);
        mClipImageLayout.setBitmap(bitmap);
        int imgType = getIntent().getIntExtra(MainActivity.IMG_TYPE,-1);
        if (MainActivity.IMG_CIRCLE == imgType){
            mClipImageLayout.setIsCircle(true);
        }else{
            mClipImageLayout.setIsCircle(false);
        }

        /*返回按钮*/
        TextView back = (TextView) findViewById(R.id.id_action_back);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });

        /*裁剪图片并保存的缓存目录*/
        Button clipBtn = (Button) findViewById(R.id.id_action_clip);
        clipBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = mClipImageLayout.clip();
                        String path= getCacheDir() + File.separator+"nethelp.png";
                        ImageTools.savePhotoToSDCard(bitmap,path);
                        Intent intent = new Intent();
                        intent.putExtra(PATH_NAME,path);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }).start();
            }
        });
    }



}
