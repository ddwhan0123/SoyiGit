package soyi.pro.com.soyi.Logic;

import android.app.Activity;
import android.content.Intent;

import soyi.pro.com.soyi.ContentConfig;
import soyi.pro.com.soyi.Tools.SpUtils;

/**
 * Created by Ezreal on 2015/12/3.
 */
public class LogicJumpTo {
    private static LogicJumpTo instance;

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
}
