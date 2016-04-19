package soyi.pro.com.soyi.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 跟App相关的辅助类
 */
public class AppUtils {
    private static AppUtils instance;

    public static AppUtils getInstance() {
        if (instance == null) {
            instance = new AppUtils();
        }
        return instance;
    }

    private AppUtils() {
    }

    /**
     * 获取PackageManager对象
     *
     * @param context
     * @return
     */
    private PackageManager getPackageManager(Context context) {
        return context.getPackageManager();
    }

    /**
     * 获取应用程序名称
     */
    public String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    public String getPacketName(Context context) {
        return context.getPackageName();
    }
    
    /**
     * 获进程名称
     *
     * @param context
     *  @param pid
     * @return
     */
     @Nullable
    public static String getProcessName(Context context, int pid){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null && !runningApps.isEmpty()) {
            for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
                if (procInfo.pid == pid) {
                    return procInfo.processName;
                }
            }
        }
        return null;
    }

    /**
     * 获取所有安装的应用程序,不包含系统应用
     *
     * @param context
     * @return
     */
    public List<PackageInfo> getInstalledPackages(Context context) {
        PackageManager packageManager = getPackageManager(context);
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        List<PackageInfo> packageInfoList = new ArrayList<PackageInfo>();
        for (int i = 0; i < packageInfos.size(); i++) {
            if ((packageInfos.get(i).applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {//系统应用
                packageInfoList.add(packageInfos.get(i));
            }
        }
        return packageInfoList;
    }

    /**
     * 启动安装应用程序
     * @param activity
     * @param path	应用程序路径
     */
    public  void installApk(Activity activity,String path){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(path)),
                "application/vnd.android.package-archive");
        activity.startActivity(intent);
    }

    /**
     * 获取应用程序的icon图标
     * @param context
     * @return
     * 当包名错误时，返回null
     */
    public Drawable getApplicationIcon(Context context){
        PackageManager packageManager = getPackageManager(context);
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPacketName(context), 0);
            return packageInfo.applicationInfo.loadIcon(packageManager);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}  
