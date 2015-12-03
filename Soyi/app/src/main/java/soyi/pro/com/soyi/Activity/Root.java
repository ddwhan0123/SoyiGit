package soyi.pro.com.soyi.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.nineoldandroids.animation.Animator;

import java.util.Timer;
import java.util.TimerTask;

import soyi.pro.com.soyi.Anim.Techniques;
import soyi.pro.com.soyi.Anim.YoYo;
import soyi.pro.com.soyi.ContentConfig;
import soyi.pro.com.soyi.Custom.QuickReaderView;
import soyi.pro.com.soyi.Logic.LogicJumpTo;
import soyi.pro.com.soyi.R;
import soyi.pro.com.soyi.Service.NetworkStateService;
import soyi.pro.com.soyi.Tools.AppUtils;
import soyi.pro.com.soyi.Tools.SpUtils;
import soyi.pro.com.soyi.Tools.ToastUtils;

public class Root extends Activity implements View.OnClickListener {
    Intent network;
    //吐司的實例
    ToastUtils toastUtils;
    SpUtils spUtils;
    LogicJumpTo logicJumpTo;
    AppUtils appUtils;

    private QuickReaderView changeTextView;
    private ImageView iconImage;
    private YoYo.YoYoString rope;
    private long exitTime = 0;
    private TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        LogUtils.d("---->Root onCreate");

        //监听网络
        if (network == null) {
            network = new Intent(Root.this, NetworkStateService.class);
        }
        startService(network);

        findView();
    }

    private void findView() {
        toastUtils = ToastUtils.getInstance();
        spUtils = SpUtils.getInstance();
        logicJumpTo = LogicJumpTo.getInstance();
        appUtils=AppUtils.getInstance();

        iconImage = (ImageView) findViewById(R.id.iconImage);
        changeTextView = (QuickReaderView) findViewById(R.id.changeTextView);
        version=(TextView)findViewById(R.id.version);
    }

    private void logic() {
        makeAnim(iconImage, 3000);

        String text = getResources().getString(R.string.changeText);
        changeTextView.setWordsPerSecond(100).setRepeat(true).showText(text);
        version.setText("当前版本："+appUtils.getVersionName(Root.this));
    }


    private void makeAnim(View view, int value) {
        Techniques technique = (Techniques) view.getTag();
        rope = YoYo.with(technique.Shake)
                .duration(value)
                .interpolate(new AccelerateDecelerateInterpolator())
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        LogUtils.d("--->onAnimationStart");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        LogUtils.d("--->onAnimationEnd");
                        String value = spUtils.getString(Root.this, "userName");
                        boolean loginFlag = spUtils.getBoolean(Root.this, ContentConfig.IS_LOGIN);
                        if (loginFlag) {
                            logicJumpTo.LoginToHomeActivity(Root.this, HomeActivity.class, value);
                        }else{
                            spUtils.putBoolean(Root.this,ContentConfig.IS_LOGIN, false);
                            logicJumpTo.RootToOtherActivity(Root.this, RegisteredActivity.class, LoginActivity.class, value);
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("--->Root onResume");
        logic();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                //返回桌面
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 注意本行的FLAG设置
                startActivity(intent);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("---->Root onDestroy");
    }
}
