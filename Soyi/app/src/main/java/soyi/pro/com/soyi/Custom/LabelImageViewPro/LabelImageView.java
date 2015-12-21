package soyi.pro.com.soyi.Custom.LabelImageViewPro;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;

import soyi.pro.com.soyi.R;
import soyi.pro.com.soyi.Tools.BitmapUtils;

/**
 * Created by Ezreal on 2015/12/16.
 */
public class LabelImageView extends View {
    final static String LeftTop = "LeftTop";
    final static String LeftBottom = "LeftBottom";
    final static String RightTop = "RightTop";
    final static String RightBottom = "RightBottom";

    private Context context;
    private int textColor;
    private String contentStr;
    private int imageSrc;
    private Bitmap bitmap;
    private Paint paint;
    private float bitmapWidth;
    private float bitmapHeight;
    private int textSize;
    BitmapUtils bitmapUtils;
    private String textLocation;

    public LabelImageView(Context context) {
        super(context);
    }

    public LabelImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        bitmapUtils = BitmapUtils.getInstance();
        init(context, attrs);
    }

    public LabelImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        bitmapUtils = BitmapUtils.getInstance();
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.labelImageView);
        try {
            //获取颜色，默认白色字体
            textColor = typedArray.getColor(R.styleable.labelImageView_text_color, getResources().getColor(R.color.Black));
            contentStr = typedArray.getString(R.styleable.labelImageView_text_content);
            imageSrc = typedArray.getResourceId(R.styleable.labelImageView_image_src, R.drawable.icon_zbx);
            textSize = typedArray.getInteger(R.styleable.labelImageView_text_size, 30);
            textLocation = typedArray.getString(R.styleable.labelImageView_text_location);
            if (textLocation == null || textLocation.length() <= 0) {
                textLocation = RightBottom;
            }
            //初始化画布
            bitmap = BitmapFactory.decodeResource(getResources(), imageSrc);
            bitmapWidth = typedArray.getDimension(R.styleable.labelImageView_image_width, bitmap.getWidth());
            bitmapHeight = typedArray.getDimension(R.styleable.labelImageView_image_height, bitmap.getHeight());

            LogUtils.d("--->LabelImageView init bitmapHeight " + bitmapHeight + "  bitmapWidth  " + bitmapWidth + "\n" + "--->LabelImageView init imageSrc " + imageSrc + "  R.drawable.icon_zbx= " + R.drawable.icon_zbx
                    + "\n" + "--->LabelImageView init contentStr " + contentStr + "\n" + "--->LabelImageView init textColor " + textColor
                    + "--->LabelImageView init  textSize  " + textSize + "\n" + "--->LabelImageView init  textLocation  " + textLocation);

            paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        } catch (Exception ex) {
            LogUtils.e("Unable to parse attributes due to: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtils.d("--->LabelImageView onDraw");
        //画画操作
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        switch (textLocation) {
            case RightBottom:
                //右下
                canvas.drawText(contentStr, (int) bitmapWidth - paint.measureText(contentStr), (int) bitmapHeight-fontMetrics.bottom, paint);
                break;
            case RightTop:
                //右上
                canvas.drawText(contentStr, (int) bitmapWidth - paint.measureText(contentStr), 0+textSize, paint);
                break;
            case LeftTop:
                //左上
                canvas.drawText(contentStr, 0, 0+textSize, paint);
                break;
            case LeftBottom:
                //左下
                canvas.drawText(contentStr, 0, (int) bitmapHeight-fontMetrics.bottom, paint);
                break;
        }
    }

    //定制大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) bitmapWidth, (int) bitmapHeight);
    }
}
