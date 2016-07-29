package com.friean.uploadphoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.friean.clipphoto.ClipImageLayout;
import com.friean.clipphoto.ImageTools;


public class MainActivity extends AppCompatActivity {

    private ImageView photo;
    private static final int REQCODE = 100;
    public static final int IMG_CUB = 1;
    public static final int IMG_CIRCLE = 2;
    public static final String IMG_TYPE = "TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        photo = (ImageView) findViewById(R.id.photo_img);
    }

    /*裁剪为方形图片*/
    public void clipCubImg(View view){
        Intent intent = new Intent(MainActivity.this, ClipActivity.class);
        intent.putExtra(IMG_TYPE,IMG_CUB);
        startActivityForResult(intent,REQCODE);
    }

    /*裁剪为圆形图片*/
    public void clipCircleImg(View view){
        Intent intent = new Intent(MainActivity.this, ClipActivity.class);
        intent.putExtra(IMG_TYPE,IMG_CIRCLE);
        startActivityForResult(intent,REQCODE);
    }

    /*显示裁剪后的图片*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQCODE &&resultCode == RESULT_OK){
            String path = data.getStringExtra(ClipActivity.PATH_NAME);
            if (path != null){
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                photo.setImageBitmap(bitmap);
            }
        }
    }
}
