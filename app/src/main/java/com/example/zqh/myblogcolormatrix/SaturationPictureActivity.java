package com.example.zqh.myblogcolormatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * ColorMatrixde 色相改变
 */
public class SaturationPictureActivity extends AppCompatActivity {

    private Button saturation_change_btn;//改变饱和度的颜色通道
    private SeekBar saturation_seeckbar;//改变的饱和度值
    private ImageView saturation_pic_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saturation_picture);


        initView();
    }

    private void initView(){
        saturation_change_btn=(Button)findViewById(R.id.saturation_change_btn);
        saturation_seeckbar=(SeekBar)findViewById(R.id.saturation_seeckbar);
        saturation_pic_iv=(ImageView)findViewById(R.id.saturation_pic_iv);

        saturation_seeckbar.setMax(360);
        saturation_seeckbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    private Bitmap getSaturationPic(int value) {

        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tesetpic1);
        Bitmap bmp = mBitmap.copy(Bitmap.Config.ARGB_8888, true);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, value,
                0, 1, 0, 0, value,
                0, 0, 1, 0, value,
                0, 0, 0, 1, 0,
        });

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas();
        canvas.drawBitmap(bmp, 0, 0, paint);
        return bmp;
    }
}
