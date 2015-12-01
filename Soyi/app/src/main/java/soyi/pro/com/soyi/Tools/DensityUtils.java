package soyi.pro.com.soyi.Tools;

import android.content.Context;
import android.util.TypedValue;

/**
 * 常用单位转换的辅助类
 */
public class DensityUtils {
    private static DensityUtils instance;

    public static DensityUtils getInstance() {
        if (instance == null) {
            instance = new DensityUtils();
        }
        return instance;
    }

    private DensityUtils() {
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

}  