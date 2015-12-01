package soyi.pro.com.soyi.Tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneUtil {
    private static PhoneUtil instance;

    public static PhoneUtil getInstance() {
        if (instance == null) {
            instance = new PhoneUtil();
        }
        return instance;
    }

    private PhoneUtil() {
    }

    /*获取手机CPU信息*/
    public String[] getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};  //1-cpu型号  //2-cpu频率    
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }
        Log.i("PhoneUtil", "cpuinfo:" + cpuInfo[0] + " " + cpuInfo[1]);
        return cpuInfo;
    }


    /*获取手机型号*/
    public String getMobileMODEL() {
        return android.os.Build.MODEL;
    }

    /*获取SDK版本*/
    @SuppressWarnings("deprecation")
    public String getSdkVersion() {
        return android.os.Build.VERSION.SDK;
    }

    /*获取系统版本*/
    public String getRELEASE() {
        return android.os.Build.VERSION.RELEASE;
    }
}
