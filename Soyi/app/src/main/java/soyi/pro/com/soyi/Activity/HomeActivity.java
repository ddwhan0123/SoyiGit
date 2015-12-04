package soyi.pro.com.soyi.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dodola.listview.extlib.ListViewExt;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;

import com.apkfuns.logutils.LogUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;
import soyi.pro.com.soyi.ContentConfig;
import soyi.pro.com.soyi.Logic.Adapter.MenuAdapter;
import soyi.pro.com.soyi.Logic.LogicJumpTo;
import soyi.pro.com.soyi.R;
import soyi.pro.com.soyi.Tools.DialogUtils;
import soyi.pro.com.soyi.Tools.SpUtils;
import soyi.pro.com.soyi.Tools.ToastUtils;

public class HomeActivity extends Activity {
    String userName;
    private long exitTime = 0;
    SpUtils spUtils;
    LogicJumpTo logicJumpTo;
    ToastUtils toastUtils;
    DialogUtils dialogUtils;

    private DrawerLayout mDrawerLayout;
    private ListViewExt mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
//    private boolean drawerArrowColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        LogUtils.d("--->HomeActivity onCreate");
        init();
        finViewId();
        setClickListener();
    }

    private void init() {
        spUtils = SpUtils.getInstance();
        logicJumpTo = LogicJumpTo.getInstance();
        toastUtils = ToastUtils.getInstance();
        dialogUtils = DialogUtils.getInstance();

        userName = getIntent().getStringExtra("userName");
        if (userName != null) {
            LogUtils.d("--->HomeActivity init  userName= " + userName);
        }

        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
//        ab.setIcon(R.drawable.icon_touming);

    }

    private void finViewId() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListViewExt) findViewById(R.id.navdrawer);
        mDrawerList.setBackgroundColor(00000000);

        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                drawerArrow, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        MenuAdapter adapter = new MenuAdapter(HomeActivity.this, new String[]{
                "我的照片墙",
                "给软件评分",
                "扫描二维码",
                "赞助作者",
                "项目地址",
                "分享给其他人",
                "设置",
                "登出帐号"
        });
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
//                        关闭动画
//                        mDrawerToggle.setAnimateEnabled(false);
//                        drawerArrow.setProgress(1f);
                        toastUtils.show(HomeActivity.this, "我的照片墙", false);
                        break;
                    case 1:
                        toastUtils.show(HomeActivity.this, "给软件评分", false);
//                        String appUrl = "https://play.google.com/store/apps/details?id=" + getPackageName();
//                        Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appUrl));
//                        startActivity(rateIntent);
                        break;
                    case 2:
                        toastUtils.show(HomeActivity.this, "扫描二维码", false);
//                        打开动画
//                        mDrawerToggle.setAnimateEnabled(true);
//                        mDrawerToggle.syncState();
                        break;
                    case 3:
                        toastUtils.show(HomeActivity.this, "赞助作者", false);
//                        if (drawerArrowColor) {
//                            drawerArrowColor = false;
//                            drawerArrow.setColor(R.color.ldrawer_color);
//                        } else {
//                            drawerArrowColor = true;
//                            drawerArrow.setColor(R.color.drawer_arrow_second_color);
//                        }
//                        mDrawerToggle.syncState();
                        break;
                    case 4:
                        toastUtils.show(HomeActivity.this, "项目地址", false);
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ddwhan0123/SoyiGit"));
                        startActivity(browserIntent);
                        break;
                    case 5:
                        toastUtils.show(HomeActivity.this, "分享给其他人", false);
                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        share.putExtra(Intent.EXTRA_SUBJECT,
                                getString(R.string.app_name));
                        share.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_description) + "\n" +
                                "https://github.com/ddwhan0123/SoyiGit");
                        startActivity(Intent.createChooser(share,
                                getString(R.string.app_name)));
                        break;
                    case 6:
                        toastUtils.show(HomeActivity.this, "设置", false);
//                        关闭动画
//                        mDrawerToggle.setAnimateEnabled(false);
//                        drawerArrow.setProgress(0f);
                        break;
                    case 7:
                        showLogOutDialog();
//                        关闭动画
//                        mDrawerToggle.setAnimateEnabled(false);
//                        drawerArrow.setProgress(0f);
                        break;
                }

            }
        });
    }

    private void setClickListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("--->HomeActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d("--->HomeActivity onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("--->HomeActivity onDestroy");
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void showLogOutDialog() {
        new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("提示")
                .setContentText("是否登出?")
                .setCancelText("取消")
                .setConfirmText("登出")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        //切到未登录状态
                        spUtils.putBoolean(HomeActivity.this, ContentConfig.IS_LOGIN, false);
                        logicJumpTo.noValueJump(HomeActivity.this, LoginActivity.class);
                    }
                })
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .show();
    }

}
