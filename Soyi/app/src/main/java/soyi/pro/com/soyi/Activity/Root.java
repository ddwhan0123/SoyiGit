package soyi.pro.com.soyi.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import soyi.pro.com.soyi.R;
import soyi.pro.com.soyi.Tools.DialogUtils;
import soyi.pro.com.soyi.Tools.ToastUtils;

public class Root extends Activity implements View.OnClickListener{
    Button showTitleDialog,showTitleAndMsgDialog,showErrorDialog,showWarningDialog,
            showSuccessDialog,showCustomIconDialog,showWarningListenerDialog,showWarningAllDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        findId();
        setClickener();
    }

    public void findId(){
        showTitleDialog=(Button)findViewById(R.id.showTitleDialog);
        showTitleAndMsgDialog=(Button)findViewById(R.id.showTitleAndMsgDialog);
        showErrorDialog=(Button)findViewById(R.id.showErrorDialog);
        showWarningDialog=(Button)findViewById(R.id.showWarningDialog);
        showSuccessDialog=(Button)findViewById(R.id.showSuccessDialog);
        showCustomIconDialog=(Button)findViewById(R.id.showCustomIconDialog);
        showWarningListenerDialog=(Button)findViewById(R.id.showWarningListenerDialog);
        showWarningAllDialog=(Button)findViewById(R.id.showWarningAllDialog);
    }

    public  void setClickener(){
        showTitleDialog.setOnClickListener(this);
        showTitleAndMsgDialog.setOnClickListener(this);
        showErrorDialog.setOnClickListener(this);
        showWarningDialog.setOnClickListener(this);
        showSuccessDialog.setOnClickListener(this);
        showCustomIconDialog.setOnClickListener(this);
        showWarningListenerDialog.setOnClickListener(this);
        showWarningAllDialog.setOnClickListener(this);
    }

    protected void showToast(Context context,String msg,boolean isLong){
        ToastUtils.show(context,msg,isLong);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.showTitleDialog:
                DialogUtils.showTitleDialog(this, "標題Dialog");
                break;
            case R.id.showTitleAndMsgDialog:
                DialogUtils.showTitleAndMsgDialog(this, "標題內容", "具體的提示信息");
                break;
            case R.id.showErrorDialog:
                DialogUtils.showErrorDialog(this,"錯誤的標題","錯誤的文字內容");
                break;
            case R.id.showWarningDialog:
                DialogUtils.showWarningDialog(this,"提示的標題","提示的具體內容","確認按鈕");
                break;
            case  R.id.showSuccessDialog:
                DialogUtils.showSuccessDialog(this,"標題","文字內容");
                break;
            case R.id.showCustomIconDialog:
                DialogUtils.showCustomIconDialog(this,"自定義標題","自定義文字內容，字數要多",R.drawable.icon_zbx);
                break;
            case R.id.showWarningListenerDialog:
                DialogUtils.showWarningListenerDialog(this,"標題","提示內容","按鈕文字");
                break;
            case R.id.showWarningAllDialog:
                DialogUtils.showWarningAllDialog(this,"標題","對話框中的內容","取消","確認");
                break;
        }
    }
}
