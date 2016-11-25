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

    private Paint paint;
    private Canvas canvas;
    private int singtype=0;


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

        alpha_btn.setOnClickListener(this);
        single_clr_btn.setOnClickListener(this);

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
                if(singtype<3){
                    singtype++;
                }else{
                    singtype=0;
                }
                break;

        }
    }

    private Bitmap getAlphaPic() {
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tesetpic1);
        Bitmap bmp = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
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

    private Bitmap getSingleColorPic(int type){
        int r,g,b;
        if(type==0){
            r=1;
            g=0;
            b=0;
        }else if(type==1){
            r=0;
            g=1;
            b=0;
        }else if(type==2){
            r=0;
            g=0;
            b=1;
        }else{
            r=1;
            g=1;
            b=1;
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

}
