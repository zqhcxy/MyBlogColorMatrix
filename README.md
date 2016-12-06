# MyBlogColorMatrix
颜色矩阵ColorMatrix和图片的滤镜效果
=====
## Paint实现图片的滤镜效果
### 透明度
```java
ColorMatrix colorMatrix = new ColorMatrix(new float[]{    
        1, 0, 0, 0, 0,    
        0, 1, 0, 0, 0,    
        0, 0, 1, 0, 0,    
        0, 0, 0, 0.5, 0,    
});    
mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));  
```

### 单通道颜色
```java
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
```
### 平移--饱和度
```java
ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, r,
                0, 1, 0, 0, g,
                0, 0, 1, 0, b,
                0, 0, 0, 1, 0,
        });
```
### 反转--底片效果
```java
ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0,
        });

```

### 缩放--亮度
```java
 ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                value, 0, 0, 0, 0,
                0, value, 0, 0, 0,
                0, 0, value, 0, 0,
                0, 0, 0, value, 0,
        });
```
## ColorMatrix自带方法实现滤镜效果
### 饱和度
```java
private Bitmap handleStaturationColorMatrixBmp(int progress) {
        mSaturationMatrix.reset();
        mSaturationMatrix.setSaturation(progress);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mSaturationMatrix));// 设置颜色变换效果
        mCanvas.drawBitmap(mOriginBmp, 0, 0, mPaint); // 将颜色变化后的图片输出到新创建的位图区
        // 返回新的位图，也即调色处理后的图片
        return mTempBmp;
    }
```
### 亮度
```java
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
```
### 色相
```java
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
```
