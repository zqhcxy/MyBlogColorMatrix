package com.example.zqh.myblogcolormatrix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 图片的颜色矩阵对图片进行编辑
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button crmatrix_simple_btn;
    private Button crmatrix_simple2_btn;
    private Button crmatrix_simple3_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        crmatrix_simple_btn = (Button) findViewById(R.id.crmatrix_simple_btn);
        crmatrix_simple2_btn=(Button)findViewById(R.id.crmatrix_simple2_btn);
        crmatrix_simple3_btn=(Button)findViewById(R.id.crmatrix_simple3_btn);

        crmatrix_simple_btn.setOnClickListener(this);
        crmatrix_simple2_btn.setOnClickListener(this);
        crmatrix_simple3_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.crmatrix_simple_btn:
                Intent intent=new Intent(MainActivity.this,SimpleMatrixActivity.class);
                startActivity(intent);
                break;
            case R.id.crmatrix_simple2_btn:
                Intent intent1=new Intent(MainActivity.this,SimpleMaxtrixActivity2.class);
                startActivity(intent1);
                break;
            case R.id.crmatrix_simple3_btn:
                Intent intent2=new Intent(MainActivity.this,MyColorMatrixActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
