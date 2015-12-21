package soyi.pro.com.soyi.Logic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;

import net.frakbot.jumpingbeans.JumpingBeans;

import soyi.pro.com.soyi.Activity.CodeActivityPro.ShowCodeTypeActivity;
import soyi.pro.com.soyi.Activity.CodeActivityPro.ShowCodeViewActivity;
import soyi.pro.com.soyi.ContentConfig;
import soyi.pro.com.soyi.R;
import soyi.pro.com.soyi.Tools.SpUtils;

/**
 * Created by Ezreal on 2015/12/3.
 */
public class LogicJumpTo {
    private static LogicJumpTo instance;
    JumpingBeans jumpingBeans;

    public static LogicJumpTo getInstance() {
        if (instance == null) {
            instance = new LogicJumpTo();
        }
        return instance;
    }

    private LogicJumpTo() {
    }

    SpUtils spUtils = SpUtils.getInstance();

    public void noValueJump(Activity activity1, Class activity2) {
        Intent intent = new Intent(activity1, activity2);
        activity1.startActivity(intent);
    }

    public void RootToOtherActivity(Activity activity1, Class activity2, Class activity3, String value) {
        Intent intent;
        if (value == null) {

            intent = new Intent(activity1, activity2);

        } else {

            intent = new Intent(activity1, activity3);

        }
        activity1.startActivity(intent);
    }

    public void LoginToHomeActivity(Activity activity1, Class activity2, String param) {
        Intent intent = new Intent(activity1, activity2);
        intent.putExtra("userName", param);
        spUtils.putBoolean(activity1, ContentConfig.IS_LOGIN, true);
        activity1.startActivity(intent);
    }

    public void CodeActivityToShowCodeActivity(Activity activity1, Class activity2, String param) {
        Intent intent = new Intent(activity1, activity2);
        intent.putExtra("CodeActivityToShowCodeActivity", param);
        activity1.startActivity(intent);
    }

    public void CodeActivityToShowCodeViewActivity(Activity activity1, Class activity2, String param1,String param2,String param3,String param4) {
        Intent intent = new Intent(activity1, activity2);
        intent.putExtra("CodeActivityToShowCodeActivity", param1);
        intent.putExtra("CodeActivityToShowCodeActivityImageUrl", param2);
        intent.putExtra("CodeActivityToShowCodeActivityMSG", param3);
        intent.putExtra("CodeActivityToShowCodeActivityGitUrl", param4);
        activity1.startActivity(intent);
    }

    public String[] makeArrayDate(String value, Context context) {
        switch (value) {
            case "For EditText":
                return context.getResources().getStringArray(R.array.forEditText);

            case "For TextView":
                return context.getResources().getStringArray(R.array.forTextView);

            case "For Button/CheckBox/Switch/ProgressBar/Spinner":
                return context.getResources().getStringArray(R.array.forButton);

            case "For ListView/GridView/TabHost":
                return context.getResources().getStringArray(R.array.forListView);

            case "For Dialog":
                return context.getResources().getStringArray(R.array.forDialog);

            case "For CustomView":
                return context.getResources().getStringArray(R.array.forCustomView);

            case "For ImageView":
                return context.getResources().getStringArray(R.array.forImageView);

            case "For WebView":
                return context.getResources().getStringArray(R.array.forWebView);

            case "For Animation":
                return context.getResources().getStringArray(R.array.forAnimation);

            case "For Layout":
                return context.getResources().getStringArray(R.array.forLayout);

            case "For Menu":
                return context.getResources().getStringArray(R.array.forMenu);

            case "For NetWork":
                return context.getResources().getStringArray(R.array.forNetWork);

            case "Others":
                return context.getResources().getStringArray(R.array.forOthers);

            default:
                return null;
        }
    }

    public void ShowCodeTypeActivityOnItemClickListenerSwitch(String data,Activity a1){
        switch (data) {
            case "CuteEditText":
                CodeActivityToShowCodeViewActivity(a1, ShowCodeViewActivity.class,
                        "CuteEditText", "https://github.com/ddwhan0123/CuteEditTextGit/raw/master/CuteEditTextPro/show.gif",
                        ContentConfig.CuteEditTextMSG, "https://github.com/ddwhan0123/CuteEditTextGit");
                LogUtils.d("--->ShowCodeTypeActivity init setOnItemClickListener   --->CuteEditText");
                break;
            case "LabelImageView":
                CodeActivityToShowCodeViewActivity(a1, ShowCodeViewActivity.class,
                        "LabelImageView", "http://img.blog.csdn.net/20151221150506738",
                        ContentConfig.LabelImageViewMSG, "https://github.com/ddwhan0123/GitLabelImageView");
                LogUtils.d("--->ShowCodeTypeActivity init setOnItemClickListener   --->CuteEditText");
            case "FlexibleListView":
                CodeActivityToShowCodeViewActivity(a1, ShowCodeViewActivity.class,
                        "FlexibleListView", "https://github.com/ddwhan0123/GitFlexibleListView/raw/master/FlexibleListView/coco1.gif",
                        ContentConfig.LabelImageViewMSG, "https://github.com/ddwhan0123/GitFlexibleListView");
                LogUtils.d("--->ShowCodeTypeActivity init setOnItemClickListener   --->FlexibleListView");
        }
    }

    public void makeTextJump(TextView textView){

        jumpingBeans = JumpingBeans.with(textView)
                .makeTextJump(0, textView.getText().toString().indexOf(' '))
                .setIsWave(true)
                .setLoopDuration(800)  // ms
                .build();
    }

    public void stopTextJump(){
        jumpingBeans.stopJumping();
    }
}
