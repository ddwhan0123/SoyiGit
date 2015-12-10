package soyi.pro.com.soyi.Activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Ezreal on 2015/12/10.
 */
public abstract class Son extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        bindID();
        init();
        setOnclick();
        logic();
    }

    public abstract int getLayout();
    public abstract void bindID();
    public abstract void init();
    public abstract void setOnclick();
    public abstract  void logic();

}
