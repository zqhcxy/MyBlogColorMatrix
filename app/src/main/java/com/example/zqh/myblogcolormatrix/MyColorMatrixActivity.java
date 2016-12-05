package com.example.zqh.myblogcolormatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

/**
 * 使用ColorMatrix实现图片的多种效果
 */
public class MyColorMatrixActivity extends AppCompatActivity {


    private static final int TYPE1 = 1;
    private static final int TYPE2 = 2;
    private static final int TYPE3 = 3;

    private int currentType = TYPE1;
    private ImageView corMatrix_old_pic_iv;
    private ImageView corMatrix_new_pic_iv;

    private RadioGroup corMatrix_ragroup;
    private RadioButton corMatrix_saturation;
    private RadioButton corMatrix_lighteness;
    private RadioButton corMatrix_rotate;
    private SeekBar corMatrix_seekbar;

    private Bitmap mOriginBmp, mTempBmp;
    private Canvas mCanvas;
    private Paint mPaint;
    private ColorMatrix mSaturationMatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_color_matrix);

        initView();
        initData();
    }

    private void initView() {
        corMatrix_old_pic_iv = (ImageView) findViewById(R.id.corMatrix_old_pic_iv);
        corMatrix_new_pic_iv = (ImageView) findViewById(R.id.corMatrix_new_pic_iv);

        corMatrix_ragroup = (RadioGroup) findViewById(R.id.corMatrix_ragroup);
        corMatrix_saturation = (RadioButton) findViewById(R.id.corMatrix_saturation);
        corMatrix_lighteness = (RadioButton) findViewById(R.id.corMatrix_lighteness);
        corMatrix_rotate = (RadioButton) findViewById(R.id.corMatrix_rotate);
        corMatrix_seekbar = (SeekBar) findViewById(R.id.corMatrix_seekbar);
        corMatrix_saturation.setChecked(true);
    }

    private void initData() {

        mOriginBmp = BitmapFactory.decodeResource(getResources(), R.drawable.tesetpic1);
        mTempBmp = Bitmap.createBitmap(mOriginBmp.getWidth(), mOriginBmp.getHeight(),
                Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mTempBmp); // 得到画笔对象
        mPaint = new Paint(); // 新建paint
        mPaint.setAntiAlias(true); // 设置抗锯齿,也即是边缘做平滑处理
        mSaturationMatrix = new ColorMatrix();

        corMatrix_ragroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.corMatrix_saturation:
                        currentType = TYPE1;
                        break;
                    case R.id.corMatrix_lighteness:
                        currentType = TYPE2;
                        break;
                    case R.id.corMatrix_rotate:
                        currentType = TYPE3;
                        break;
                }
                initSeekBar();
            }
        });
    }

    /**
     * 初始化Seekbar，因为这里是三个模式公用一个seekbar所以要区分下他们的最大值
     */
    private void initSeekBar() {
        int maxValues = 20;
        int currentpro = 1;
        if (currentType == TYPE1) {
            maxValues = 20;
            currentpro = 1;
        } else if (currentType == TYPE2) {
            maxValues = 10;
            currentpro = 0;
        } else if (currentType == TYPE3) {
            maxValues = 360;
            currentpro = 180;
        }

        corMatrix_seekbar.setMax(maxValues);
        corMatrix_seekbar.setProgress(currentpro);

        corMatrix_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Bitmap bitmap = null;
                if (currentType == TYPE1) {
                    bitmap = handleStaturationColorMatrixBmp(progress);
                } else if (currentType == TYPE2) {
                    bitmap = handlelightenessColorMatrixBmp(progress);
                } else if (currentType == TYPE3) {
                    bitmap = handleRotateColorMatrixBmp(progress);
                }
                if (bitmap != null) {
                    corMatrix_new_pic_iv.setImageBitmap(bitmap);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    /**
     * 变化图片的饱和度
     *
     * @param progress
     * @return
     */
    private Bitmap handleStaturationColorMatrixBmp(int progress) {
        mSaturationMatrix.reset();
        mSaturationMatrix.setSaturation(progress);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mSaturationMatrix));// 设置颜色变换效果
        mCanvas.drawBitmap(mOriginBmp, 0, 0, mPaint); // 将颜色变化后的图片输出到新创建的位图区
        // 返回新的位图，也即调色处理后的图片
        return mTempBmp;
    }

    /**
     * 变化图片的亮度，平移
     *
     * @param progress
     * @return
     */
    private Bitmap handlelightenessColorMatrixBmp(int progress) {

        float value = 1 + progress % 10;
        mSaturationMatrix.reset();
        mSaturationMatrix.setScale(value, value, value, 1);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mSaturationMatrix));// 设置颜色变换效果
        mCanvas.drawBitmap(mOriginBmp, 0, 0, mPaint); // 将颜色变化后的图片输出到新创建的位图区
        // 返回新的位图，也即调色处理后的图片
        return mTempBmp;
    }

    /**
     * 变化图片的色相，旋转
     *
     * @param progress
     * @return
     */
    private Bitmap handleRotateColorMatrixBmp(int progress) {

        mSaturationMatrix.reset();
        /**
         * 将旋转围绕某一个颜色轴旋转
         * axis=0 围绕红色轴旋转
         * axis=1 围绕绿色轴旋转
         * axis=2 围绕蓝色轴旋转
         */
        mSaturationMatrix.setRotate(0, progress - 180);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mSaturationMatrix));// 设置颜色变换效果
        mCanvas.drawBitmap(mOriginBmp, 0, 0, mPaint); // 将颜色变化后的图片输出到新创建的位图区
        // 返回新的位图，也即调色处理后的图片
        return mTempBmp;
    }
}
