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
    Button showTitleDialog,showTitleAndMsgDialog;
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
    }

    public  void setClickener(){
        showTitleDialog.setOnClickListener(this);
        showTitleAndMsgDialog.setOnClickListener(this);
    }

    protected void showToast(Context context,String msg,boolean isLong){
        ToastUtils.show(context,msg,isLong);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.showTitleDialog:
                DialogUtils.showTitleDialog(this,"標題Dialog");
                break;
            case R.id.showTitleAndMsgDialog:
                DialogUtils.showTitleAndMsgDialog(this,"標題內容","具體的提示信息");
                break;
        }
    }
}
