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
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.nineoldandroids.animation.Animator;

import java.util.Timer;
import java.util.TimerTask;

import soyi.pro.com.soyi.Anim.Techniques;
import soyi.pro.com.soyi.Anim.YoYo;
import soyi.pro.com.soyi.Custom.QuickReaderView;
import soyi.pro.com.soyi.R;
import soyi.pro.com.soyi.Service.NetworkStateService;
import soyi.pro.com.soyi.Tools.SpUtils;
import soyi.pro.com.soyi.Tools.ToastUtils;

public class Root extends Activity implements View.OnClickListener {
    Intent network;
    //吐司的實例
    ToastUtils toastUtils;
    SpUtils spUtils;
    private QuickReaderView changeTextView;
    private ImageView iconImage;
    private YoYo.YoYoString rope;
    private long exitTime = 0;

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

        iconImage = (ImageView) findViewById(R.id.iconImage);
        changeTextView = (QuickReaderView) findViewById(R.id.changeTextView);
    }

    private void logic() {
        makeAnim(iconImage, 3800);

        String text = getResources().getString(R.string.changeText);
        changeTextView.setWordsPerSecond(400).setRepeat(true).showText(text);
    }


    private void makeAnim(View view, int value) {
        Techniques technique = (Techniques) view.getTag();
        rope = YoYo.with(technique.Shake)
                .duration(value)
                .interpolate(new AccelerateDecelerateInterpolator())
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        LogUtils.d("--->onAnimationEnd");
                        String value = spUtils.getString(Root.this, "userName");
                        Intent intent;
                        if (value == null) {

                            intent = new Intent(Root.this, RegisteredActivity.class);
                        } else {

                            intent = new Intent(Root.this, LoginActivity.class);
                        }
                        startActivity(intent);
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

    protected void showToast(Context context, String msg, boolean isLong) {
        toastUtils.show(context, msg, isLong);
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
