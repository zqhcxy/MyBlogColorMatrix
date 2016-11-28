package com.example.zqh.myblogcolormatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 改变图片的透明度和单通道颜色
 */
public class SimpleMatrixActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView old_pic_iv;
    private ImageView new_pic_iv;
    private Button alpha_btn;
    private Button single_clr_btn;
    private Button saturation_clr_btn;
    private Button nativity_clr_btn;

    private Paint paint;
    private Canvas canvas;
    private int singtype = 0;
    private int saturationtype=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_matrix);

        initView();
    }

    private void initView() {
        old_pic_iv = (ImageView) findViewById(R.id.old_pic_iv);
        new_pic_iv = (ImageView) findViewById(R.id.new_pic_iv);
        alpha_btn = (Button) findViewById(R.id.alpha_btn);
        single_clr_btn = (Button) findViewById(R.id.single_clr_btn);
        saturation_clr_btn = (Button) findViewById(R.id.saturation_clr_btn);
        nativity_clr_btn=(Button)findViewById(R.id.nativity_clr_btn);

        alpha_btn.setOnClickListener(this);
        single_clr_btn.setOnClickListener(this);
        saturation_clr_btn.setOnClickListener(this);
        nativity_clr_btn.setOnClickListener(this);

        paint = new Paint();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alpha_btn:
                new_pic_iv.setImageBitmap(getAlphaPic());
                break;
            case R.id.single_clr_btn:
                new_pic_iv.setImageBitmap(getSingleColorPic(singtype));
                if (singtype < 3) {
                    singtype++;
                } else {
                    singtype = 0;
                }
                break;
            case R.id.saturation_clr_btn:
                new_pic_iv.setImageBitmap(getSaturationPic(50,saturationtype));
                if (saturationtype < 3) {
                    saturationtype++;
                } else {
                    saturationtype = 0;
                }
                break;
            case R.id.nativity_clr_btn:
                new_pic_iv.setImageBitmap(getNegativePic());
                break;

        }
    }

    /**
     * 获得50%透明度的图片
     *
     * @return
     */
    private Bitmap getAlphaPic() {
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tesetpic1);
        Bitmap bmp = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
        //生成颜色矩阵，百分之50透明度
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 0.5f, 0,
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(bmp, 0, 0, paint);
        return bmp;
    }

    /**
     * 单颜色通道显示图片
     *
     * @param type
     * @return
     */
    private Bitmap getSingleColorPic(int type) {
        int r, g, b;
        if (type == 0) {
            single_clr_btn.setText("点击变化单通道颜色(当前：红色)");
            r = 1;
            g = 0;
            b = 0;
        } else if (type == 1) {
            single_clr_btn.setText("点击变化单通道颜色(当前：绿色)");
            r = 0;
            g = 1;
            b = 0;
        } else if (type == 2) {
            single_clr_btn.setText("点击变化单通道颜色(当前：蓝色)");
            r = 0;
            g = 0;
            b = 1;
        } else {
            single_clr_btn.setText("点击变化单通道颜色(当前：原图)");
            r = 1;
            g = 1;
            b = 1;
        }

        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tesetpic1);
        Bitmap bmp = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                r, 0, 0, 0, 0,
                0, g, 0, 0, 0,
                0, 0, b, 0, 0,
                0, 0, 0, 1, 0,
        });
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas = new Canvas(bmp);
        canvas.drawBitmap(bmp, 0, 0, paint);
        return bmp;
    }


    /**
     * 饱和度
     *
     * @param value
     * @return
     */
    private Bitmap getSaturationPic(int value,int type) {
        int r, g, b;
        if (type == 0) {
            saturation_clr_btn.setText("点击变化添加饱和度颜色(当前：红色)");
            r = value;
            g = 0;
            b = 0;
        } else if (type == 1) {
            saturation_clr_btn.setText("点击变化添加饱和度颜色(当前：绿色)");
            r = 0;
            g = value;
            b = 0;
        } else if (type == 2) {
            saturation_clr_btn.setText("点击变化添加饱和度颜色(当前：蓝色)");
            r = 0;
            g = 0;
            b = value;
        } else {
            saturation_clr_btn.setText("点击变化添加饱和度颜色(当前：全部)");
            r = value;
            g = value;
            b = value;
        }

        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tesetpic1);
        Bitmap bmp = mBitmap.copy(Bitmap.Config.ARGB_8888, true);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, r,
                0, 1, 0, 0, g,
                0, 0, 1, 0, b,
                0, 0, 0, 1, 0,
        });

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas = new Canvas(bmp);
        canvas.drawBitmap(bmp, 0, 0, paint);
        return bmp;
    }


    private Bitmap getNegativePic() {
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tesetpic1);
        Bitmap bmp = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
        //生成颜色矩阵，百分之50透明度
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0,
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(bmp, 0, 0, paint);
        return bmp;
    }
}
