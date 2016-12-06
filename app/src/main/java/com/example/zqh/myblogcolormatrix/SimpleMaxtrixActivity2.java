package com.example.zqh.myblogcolormatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * ColorMatrixde 的简单应用2--黑白效果、反色、怀旧
 */
public class SimpleMaxtrixActivity2 extends AppCompatActivity implements View.OnClickListener {

    private Button blackWhite_btn;//黑白效果
    private Button nostalgia_clr_btn;//怀旧效果
    private Button inverse_clr_btn;//反色效果
    private ImageView old_pic_iv;
    private ImageView new_pic_iv;
    Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saturation_picture);


        initView();
    }

    private void initView() {
        blackWhite_btn = (Button) findViewById(R.id.blackWhite_btn);
        nostalgia_clr_btn = (Button) findViewById(R.id.nostalgia_clr_btn);
        inverse_clr_btn = (Button) findViewById(R.id.inverse_clr_btn);

        old_pic_iv = (ImageView) findViewById(R.id.old_pic_iv);
        new_pic_iv = (ImageView) findViewById(R.id.new_pic_iv);
        blackWhite_btn.setOnClickListener(this);
        nostalgia_clr_btn.setOnClickListener(this);
        inverse_clr_btn.setOnClickListener(this);

         mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.testpic2);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.blackWhite_btn:
                new_pic_iv.setImageBitmap(getBlackWhitePic());
                break;
            case R.id.nostalgia_clr_btn:
                new_pic_iv.setImageBitmap(getNostalgiaPic());
                break;
            case R.id.inverse_clr_btn:
                new_pic_iv.setImageBitmap(getInverseColorPic());
                break;
        }
    }

    /**
     * 黑白
     *
     * @return
     */
    private Bitmap getBlackWhitePic() {

        Bitmap bmp = mBitmap.copy(Bitmap.Config.ARGB_8888, true);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0, 0, 0, 1, 0,
        });

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(bmp, 0, 0, paint);
        return bmp;
    }

    /**
     * 怀旧
     *
     * @return
     */
    private Bitmap getNostalgiaPic() {

        Bitmap bmp = mBitmap.copy(Bitmap.Config.ARGB_8888, true);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1 / 2f, 1 / 2f, 1 / 2f, 0, 0,
                1 / 3f, 1 / 3f, 1 / 3f, 0, 0,
                1 / 4f, 1 / 4f, 1 / 4f, 0, 0,
                0, 0, 0, 1, 0,
        });

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(bmp, 0, 0, paint);
        return bmp;
    }

    /**
     * 反色
     *
     * @return
     */
    private Bitmap getInverseColorPic() {

        Bitmap bmp = mBitmap.copy(Bitmap.Config.ARGB_8888, true);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0, 1, 0, 0, 0,
                1, 0, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0,
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(bmp, 0, 0, paint);
        return bmp;
    }

    @Override
    protected void onDestroy() {
        if(mBitmap!=null){
            mBitmap.recycle();
        }
        super.onDestroy();
    }
}
