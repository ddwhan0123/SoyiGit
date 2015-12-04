package soyi.pro.com.soyi.Tools;

import android.content.Context;

import java.io.Serializable;

import cn.pedant.SweetAlert.SweetAlertDialog;
import soyi.pro.com.soyi.Activity.LoginActivity;
import soyi.pro.com.soyi.ContentConfig;
import soyi.pro.com.soyi.Logic.LogicJumpTo;

/**
 * Created by Ezreal on 2015/11/30.
 */
public class DialogUtils implements Serializable {

    private static class SingletonHolder {
        /**
         * 单例对象实例
         */
        static final DialogUtils INSTANCE = new DialogUtils();
    }

    public static DialogUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * private的构造函数用于避免外界直接使用new来实例化对象
     */
    private DialogUtils() {
    }

    /**
     * readResolve方法应对单例对象被序列化时候
     */
    private Object readResolve() {
        return getInstance();
    }

    /*標題對話框
    * @param Context context:上下文對象
    * @param String msg:標題的文字
    * */
    public void showTitleDialog(Context context, String msg) {
        new SweetAlertDialog(context)
                .setTitleText(msg)
                .show();
    }

    /*標題+文字對話框
    * @param Context context:上下文對象
    * @param String title:標題
    * @param String msg: 具體文字內容
    * */
    public void showTitleAndMsgDialog(Context context, String title, String msg) {
        new SweetAlertDialog(context)
                .setTitleText(title)
                .setContentText(msg)
                .show();
    }

    /*錯誤提示對話框
    * @param Context context:上下文對象
    * @param String title:標題
    * @param String msg: 具體文字內容
    * */
    public void showErrorDialog(Context context, String eTitle, String msg) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(eTitle)
                .setContentText(msg)
                .show();
    }

    /*警告對話框
    * @param Context context:上下文對象
    * @param String eTitle: 標題
    * @param String msg:具體的文字內容
    * @param String confimText:按鈕的文字
    * */
    public void showWarningDialog(Context context, String eTitle, String msg, String confirmText) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(eTitle)
                .setContentText(msg)
                .setConfirmText(confirmText)
                .show();
    }

    /*成功對話框
    * @param Context context:上下文對象
    * @param String eTitle: 標題
    * @param String msg:具體的文字內容
    * */
    public void showSuccessDialog(Context context, String title, String msg) {
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setContentText(msg)
                .show();
    }

    /*自定義icon對話框
    * @param Context context:上下文對象
    * @param String eTitle: 標題
    * @param String msg:具體的文字內容
    * @param int icon:圖片素材
    * */
    public void showCustomIconDialog(Context context, String title, String msg, int icon) {
        new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText(title)
                .setContentText(msg)
                .setCustomImage(icon)
                .show();
    }

    /*綁定按鈕事件對話框
    * @param Context context:上下文對象
    * @param String eTitle: 標題
    * @param String msg:具體的文字內容
    * @param String confirmText:按鈕的文字
    * setConfirmClickListener: 監聽事件
    * */
    public void showWarningListenerDialog(final Context context, String title, String msg, String confirmText) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(msg)
                .setConfirmText(confirmText)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }

    /*綁定複雜按鈕事件對話框
    * @param Context context:上下文對象
    * @param String eTitle: 標題
    * @param String msg:具體的文字內容
    * @param String confirmText:按鈕的文字
    * setConfirmClickListener: 確認監聽事件
    * setCancelClickListener: 取消監聽事件
    * */
    public void showWarningAllDialog(final Context context, final String title, String msg, String cancelText, String confirmText) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(msg)
                .setCancelText(cancelText)
                .setConfirmText(confirmText)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .show();
    }

}
