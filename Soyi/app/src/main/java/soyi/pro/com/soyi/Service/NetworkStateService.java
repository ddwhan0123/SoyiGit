package soyi.pro.com.soyi.Service;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;

import soyi.pro.com.soyi.Tools.ToastUtils;

/**
 * Created by Ezreal on 2015/10/16.
 * 网络判断
 */
public class NetworkStateService extends Service {
    //吐司的實例
    ToastUtils toastUtils;
    final static String TAG = NetworkStateService.class.getName() + ".TAG";

    class NetworkState {
        final static int TYPE_WIFI = 1;
        final static int TYPE_MOBILE = 2;
        final static int TYPE_NO_NETWORK = 3;
    }

    private ConnectivityManager connectivityManager;
    private NetworkInfo netInfo;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NetworkState.TYPE_WIFI:
                    //Toast.makeText(getApplicationContext(),"WIFI状态",Toast.LENGTH_SHORT).show();
                    break;
                case NetworkState.TYPE_MOBILE:
                    //Toast.makeText(getApplicationContext(),"移动网络",Toast.LENGTH_SHORT).show();
                    break;
                case NetworkState.TYPE_NO_NETWORK:
                    toastUtils.show(getApplicationContext(), "~。~没有网络了", true);
                    break;
            }
        }
    };

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                netInfo = connectivityManager.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isAvailable()) {
                    //网络连接
                    String name = netInfo.getTypeName();

                    if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                        //WiFi网络
                        LogUtils.d("Wifi :" + name);
                        mHandler.sendEmptyMessageAtTime(NetworkState.TYPE_WIFI, 1000);
                    } else if (netInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {
                        //有线网络
                        LogUtils.d("有线网络 :" + name);
                    } else if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                        //手机网络
                        LogUtils.d("手机网络 :" + name);
                        mHandler.sendEmptyMessageAtTime(NetworkState.TYPE_MOBILE, 1000);
                    }
                } else {
                    //网络断开
                    LogUtils.d("网络断开");
                    mHandler.sendEmptyMessage(NetworkState.TYPE_NO_NETWORK);

                }
            }
        }

    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        toastUtils = ToastUtils.getInstance();

        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d("--->onDestroy " + TAG);
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

}
