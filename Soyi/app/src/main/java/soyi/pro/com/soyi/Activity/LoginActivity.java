package soyi.pro.com.soyi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apkfuns.logutils.LogUtils;

import soyi.pro.com.soyi.Logic.LogicJumpTo;
import soyi.pro.com.soyi.R;
import soyi.pro.com.soyi.Tools.SpUtils;
import soyi.pro.com.soyi.Tools.ToastUtils;

public class LoginActivity extends Activity implements View.OnClickListener {
    private long exitTime = 0;
    final static String TAG = LoginActivity.class.getName();
    RequestQueue mQueue;
    ToastUtils toastUtils;
    SpUtils spUtils;
    LogicJumpTo logicJumpTo;
    //找回密码
    LinearLayout findPwdLayout;
    Button loginButton;
    EditText userPassword, userName;
    TextView findPwdTextView,RegTextView;
    ImageView KeyImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LogUtils.d("--->" + TAG + " onCreate");


        findId();
        init();
        setClickListener();
    }

    private void init() {
        //网络初始化
        mQueue = Volley.newRequestQueue(this);

        loginButton.setText("登录账号^.^");
        RegTextView.setText("去注册界面");
        findPwdTextView.setText("找回密码");
        KeyImage.setImageResource(R.drawable.key);
    }

    private void findId() {
        toastUtils = ToastUtils.getInstance();
        spUtils = SpUtils.getInstance();
        logicJumpTo=LogicJumpTo.getInstance();
        RegTextView=(TextView)findViewById(R.id.RegTextView);

        findPwdLayout = (LinearLayout) findViewById(R.id.findPwdLayout);
        loginButton = (Button) findViewById(R.id.loginButton);
        userPassword = (EditText) findViewById(R.id.userPassword);
        userName = (EditText) findViewById(R.id.userName);
        findPwdTextView = (TextView) findViewById(R.id.findPwdTextView);
        KeyImage = (ImageView) findViewById(R.id.KeyImage);
    }

    private void setClickListener() {
        findPwdLayout.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        RegTextView.setOnClickListener(this);
    }

    private boolean checkLogin(EditText editText, String Key) {
        String value = editText.getText().toString().trim();
        if (value.equals(spUtils.getString(this, Key))) {
            return true;
        } else {
            return false;
        }
    }

    //登陆逻辑
    private void loginLogic() {
        if (checkLogin(userName, "userName") && checkLogin(userPassword, "userPassword")) {
            toastUtils.show(this, "登陆成功", true);
            //后面做跳转和异步
            StringRequest stringRequest = new StringRequest("http://www.baidu.com",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            LogUtils.d("--->onResponse"+ response);
                            logicJumpTo.LoginToHomeActivity(LoginActivity.this,HomeActivity.class,userName.getText().toString().trim());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    LogUtils.e("TAG", error.getMessage(), error);
                }
            });
            mQueue.add(stringRequest);
        } else {
            userName.setText("");
            userPassword.setText("");
            toastUtils.show(this, "输入帐号/密码有误", true);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.findPwdLayout:
                toastUtils.show(this, "找回密码", true);
                break;
            case R.id.loginButton:
                loginLogic();
                break;
            case R.id.RegTextView:
                logicJumpTo.noValueJump(LoginActivity.this,RegisteredActivity.class);
                break;
        }
    }
}
