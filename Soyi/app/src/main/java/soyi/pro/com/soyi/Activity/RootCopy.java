//package soyi.pro.com.soyi.Activity;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import soyi.pro.com.soyi.R;
//import soyi.pro.com.soyi.Tools.AppUtils;
//import soyi.pro.com.soyi.Tools.DialogUtils;
//import soyi.pro.com.soyi.Tools.NetUtils;
//import soyi.pro.com.soyi.Tools.SpUtils;
//import soyi.pro.com.soyi.Tools.ToastUtils;
//
//public class RootCopy extends Activity implements View.OnClickListener {
//    Button showTitleDialog, showTitleAndMsgDialog, showErrorDialog, showWarningDialog,
//            showSuccessDialog, showCustomIconDialog, showWarningListenerDialog, showWarningAllDialog,
//            getAppName, SpUtilsGet,isWifi;
//    //對話框實例
//    DialogUtils dialogUtils;
//    //AppUtils事例
//    AppUtils appUtils;
//    //SpUtils的實例
//    SpUtils spUtils;
//    //吐司的實例
//    ToastUtils toastUtils;
//    //網絡操作
//    NetUtils netUtils;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_root);
//        dialogUtils = DialogUtils.getInstance();
//        appUtils = AppUtils.getInstance();
//        spUtils = SpUtils.getInstance();
//        toastUtils=ToastUtils.getInstance();
//        netUtils=NetUtils.getInstance();
//        findId();
//        setClickener();
//    }
//
//    public void findId() {
//        showTitleDialog = (Button) findViewById(R.id.showTitleDialog);
//        showTitleAndMsgDialog = (Button) findViewById(R.id.showTitleAndMsgDialog);
//        showErrorDialog = (Button) findViewById(R.id.showErrorDialog);
//        showWarningDialog = (Button) findViewById(R.id.showWarningDialog);
//        showSuccessDialog = (Button) findViewById(R.id.showSuccessDialog);
//        showCustomIconDialog = (Button) findViewById(R.id.showCustomIconDialog);
//        showWarningListenerDialog = (Button) findViewById(R.id.showWarningListenerDialog);
//        showWarningAllDialog = (Button) findViewById(R.id.showWarningAllDialog);
//        getAppName = (Button) findViewById(R.id.getAppName);
//        SpUtilsGet = (Button) findViewById(R.id.SpUtilsGet);
//        isWifi=(Button)findViewById(R.id.isWifi);
//    }
//
//    public void setClickener() {
//        showTitleDialog.setOnClickListener(this);
//        showTitleAndMsgDialog.setOnClickListener(this);
//        showErrorDialog.setOnClickListener(this);
//        showWarningDialog.setOnClickListener(this);
//        showSuccessDialog.setOnClickListener(this);
//        showCustomIconDialog.setOnClickListener(this);
//        showWarningListenerDialog.setOnClickListener(this);
//        showWarningAllDialog.setOnClickListener(this);
//        getAppName.setOnClickListener(this);
//        SpUtilsGet.setOnClickListener(this);
//        isWifi.setOnClickListener(this);
//    }
//
//    protected void showToast(Context context, String msg, boolean isLong) {
//        toastUtils.show(context, msg, isLong);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.showTitleDialog:
//                dialogUtils.showTitleDialog(this, "標題Dialog");
//                break;
//            case R.id.showTitleAndMsgDialog:
//                dialogUtils.showTitleAndMsgDialog(this, "標題內容", "具體的提示信息");
//                break;
//            case R.id.showErrorDialog:
//                dialogUtils.showErrorDialog(this, "錯誤的標題", "錯誤的文字內容");
//                break;
//            case R.id.showWarningDialog:
//                dialogUtils.showWarningDialog(this, "提示的標題", "提示的具體內容", "確認按鈕");
//                break;
//            case R.id.showSuccessDialog:
//                dialogUtils.showSuccessDialog(this, "標題", "文字內容");
//                break;
//            case R.id.showCustomIconDialog:
//                dialogUtils.showCustomIconDialog(this, "自定義標題", "自定義文字內容，字數要多", R.drawable.icon_zbx);
//                break;
//            case R.id.showWarningListenerDialog:
//                dialogUtils.showWarningListenerDialog(this, "標題", "提示內容", "按鈕文字");
//                break;
//            case R.id.showWarningAllDialog:
//                dialogUtils.showWarningAllDialog(this, "標題", "對話框中的內容", "取消", "確認");
//                break;
//            case R.id.getAppName:
//                Toast.makeText(this, appUtils.getAppName(this), Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.SpUtilsGet:
//                spUtils.putString(this, "wjj", "wjj123");
//                Toast.makeText(this, spUtils.getString(this, "aa") + "  已經操作", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.isWifi:
//                boolean flag=netUtils.isWifi(this);
//                showToast(this,"isWifi判斷結果是： "+flag,true);
//                break;
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//}
