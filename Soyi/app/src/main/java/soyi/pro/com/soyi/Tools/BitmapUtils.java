package soyi.pro.com.soyi.Tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;

import java.io.ByteArrayOutputStream;

/**
 * Created by Ezreal on 2015/12/11.
 */
public class BitmapUtils {
    private static BitmapUtils instance;

    public static BitmapUtils getInstance() {
        if (instance == null) {
            instance = new BitmapUtils();
        }
        return instance;
    }

    private BitmapUtils() {
    }

    /**
     * 计算所需图片的缩放比例
     *
     * @param height  高度
     * @param width   宽度
     * @param options options选项
     * @return
     */
    private int calculateSampleSize(int height, int width, BitmapFactory.Options options) {
        int realHeight = options.outHeight;
        int realWidth = options.outWidth;
        int heigthScale = realHeight / height;
        int widthScale = realWidth / width;
        if (widthScale > heigthScale) {
            return widthScale;
        } else {
            return heigthScale;
        }
    }

    /**
     * 根据资源id获取指定大小的Bitmap对象
     *
     * @param context 应用程序上下文
     * @param id      资源id
     * @param height  高度
     * @param width   宽度
     * @return
     */
    public Bitmap getBitmapFromResource(Context context, int id, int height, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), id, options);
        options.inSampleSize = calculateSampleSize(height, width, options);
        options.inJustDecodeBounds = false;
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(context.getResources(), id, options);
        return bitmap;
    }

    /**
     * 将Bitmap对象转换为byte[]数组
     * @param bitmap	Bitmap对象
     * @return		返回转换后的数组
     */
    public  byte[] bitmapToByte(Bitmap bitmap){
        if(bitmap == null){
            throw new IllegalArgumentException("Bitmap is null, please check you param");
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 将Drawable对象转换成Bitmap对象
     * @param drawable	Drawable对象
     * @return	返回转换后的Bitmap对象
     */
    public  Bitmap drawableToBitmap(Drawable drawable) {
        if(drawable == null){
            throw new IllegalArgumentException("Drawable is null, please check you param");
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 获取指定大小的Bitmap对象
     * @param bitmap	Bitmap对象
     * @param height	高度
     * @param width		宽度
     * @return
     */
    public  Bitmap getThumbnailsBitmap(Bitmap bitmap, int height, int width){
        if(bitmap == null){
            throw new IllegalArgumentException("Bitmap is null, please check you param");
        }
        return ThumbnailUtils.extractThumbnail(bitmap, width, height);
    }
}
