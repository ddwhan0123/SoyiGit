package soyi.pro.com.soyi.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.CBViewHolderCreator;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.dodola.listview.extlib.ListViewExt;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;
import com.apkfuns.logutils.LogUtils;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import net.frakbot.jumpingbeans.JumpingBeans;

import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.support.v4.view.ViewPager;

import cn.pedant.SweetAlert.SweetAlertDialog;
import soyi.pro.com.soyi.ContentConfig;
import soyi.pro.com.soyi.Custom.ImageHolderView.NetworkImageHolderView;
import soyi.pro.com.soyi.Logic.Adapter.MenuAdapter;
import soyi.pro.com.soyi.Logic.LogicJumpTo;
import soyi.pro.com.soyi.R;
import soyi.pro.com.soyi.Tools.DialogUtils;
import soyi.pro.com.soyi.Tools.SpUtils;
import soyi.pro.com.soyi.Tools.ToastUtils;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.bigkoo.convenientbanner.OnItemClickListener;


public class HomeActivity extends Activity implements ViewPager.OnPageChangeListener, OnItemClickListener {
    //计时器的线程池
    ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

    //返回键计时
    private long exitTime = 0;
    //传来的用户名
    private String userName;
    //一系列工具
    SpUtils spUtils;
    LogicJumpTo logicJumpTo;
    ToastUtils toastUtils;
    DialogUtils dialogUtils;

    private DrawerLayout mDrawerLayout;
    private ListViewExt mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;

    //加载条
    private CircleProgressBar circleProgressBar;
    private RelativeLayout loadingLayout;
    private TextView jumpTextView;
    private JumpingBeans jumpingBeans;

    //滚动栏相关
    private ConvenientBanner convenientBanner;
    private List<String> networkImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        LogUtils.d("--->HomeActivity onCreate");
        init();
        findViewID();
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
        //初始化图片加载
        initImageLoader();
        //滚动广告
        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);

        //获得图片URL
        networkImages = Arrays.asList(getResources().getStringArray(R.array.imagesArray));
        //网络加载
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, networkImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                        //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                        //设置翻页的效果，不需要翻页效果可用不设
                .setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer)
                .setOnItemClickListener(this);
//                .setOnPageChangeListener(this);监听翻页事件

    }

    public void findViewID() {
        loadingLayout = (RelativeLayout) findViewById(R.id.loadingLayout);
        circleProgressBar = (CircleProgressBar) findViewById(R.id.circleProgressBar);
        circleProgressBar.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light, R.color.MediumAquamarine, R.color.blue_btn_bg_pressed_color, R.color.red_btn_bg_color);
        circleProgressBar.setShowProgressText(false);

        //动画TextView
        jumpTextView = (TextView) findViewById(R.id.jumpTextView);
        jumpTextView.setText(userName + " 努力加载中");
        jumpingBeans = JumpingBeans.with(jumpTextView)
                .makeTextJump(0, jumpTextView.getText().toString().indexOf(' '))
                .setIsWave(true)
                .setLoopDuration(800)  // ms
                .build();

        //侧拉菜单
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

        //菜单数据源
        MenuAdapter adapter = new MenuAdapter(HomeActivity.this, getResources().getStringArray(R.array.menuArray));
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        toastUtils.show(HomeActivity.this, "个人研发", false);
                        break;
                    case 1:
                        toastUtils.show(HomeActivity.this, "我的照片墙", false);
                        break;
                    case 2:
                        toastUtils.show(HomeActivity.this, "给软件评分", false);
//                        String appUrl = "https://play.google.com/store/apps/details?id=" + getPackageName();
//                        Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appUrl));
//                        startActivity(rateIntent);
                        break;
                    case 3:
                        toastUtils.show(HomeActivity.this, "扫描二维码", false);
                        break;
                    case 4:
                        toastUtils.show(HomeActivity.this, "赞助作者", false);
                        break;
                    case 5:
                        toastUtils.show(HomeActivity.this, "项目地址", false);
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ddwhan0123/SoyiGit"));
                        startActivity(browserIntent);
                        break;
                    case 6:
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
                    case 7:
                        toastUtils.show(HomeActivity.this, "设置", false);
                        break;
                    case 8:
                        showLogOutDialog();
                        break;
                }

            }
        });
    }

    private void hideLoadingLayout() {
        loadingLayout.setVisibility(View.GONE);
        //停止跳动回收资源
        jumpingBeans.stopJumping();
    }

    private void showLoadingLayout() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pool.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
        //开始自动翻页
        convenientBanner.startTurning(3000);//此值不能小于1200（即ViewPagerScroller的mScrollDuration的值），否则最后一页翻页效果会出问题。如果硬要兼容1200以下，那么请修改ViewPagerScroller的mScrollDuration的值，不过修改后，3d效果就没那么明显了。
        LogUtils.d("--->HomeActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDrawerLayout.closeDrawer(mDrawerList);
        //停止翻页
        convenientBanner.stopTurning();
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

    TimerTask task = new TimerTask() {
        public void run() {
            ContentConfig.RECLEN--;
            //修改界面的相关设置只能在UI线程中执行
            runOnUiThread(new Runnable() {
                public void run() {
                    if (ContentConfig.RECLEN < 3) {
                        hideLoadingLayout();
                    }
                }
            });
        }
    };

    @Override
    public void onItemClick(int position) {
        toastUtils.show(this, "点击了第" + position + "个", false);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        toastUtils.show(this, "监听到翻到第" + position + "了", false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //初始化网络图片缓存库
    private void initImageLoader() {
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.ic_default_adimage)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
}
