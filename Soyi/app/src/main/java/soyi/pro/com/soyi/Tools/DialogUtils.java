package soyi.pro.com.soyi.Tools;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Ezreal on 2015/11/30.
 */
public class DialogUtils {
    /*
    * @param Context context:上下文對象
    * @param String msg:標題的文字
    * */
    public static void showTitleDialog(Context context, String msg) {
        new SweetAlertDialog(context)
                .setTitleText(msg)
                .show();
    }

    /*
    * @param Context context:上下文對象
    * @param String title:標題
    * @param String msg: 具體文字內容
    * */
    public static void showTitleAndMsgDialog(Context context,String title,String msg){
        new SweetAlertDialog(context)
                .setTitleText(title)
                .setContentText(msg)
                .show();
    }

}
