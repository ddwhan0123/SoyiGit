package soyi.pro.com.soyi.Activity.CodeActivityPro;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.felipecsl.gifimageview.library.GifImageView;


import soyi.pro.com.soyi.Activity.Son;
import soyi.pro.com.soyi.Custom.Gif.Blur;
import soyi.pro.com.soyi.Custom.Gif.GifDataDownloader;
import soyi.pro.com.soyi.R;

public class ShowCodeViewActivity extends Son {
    private ActionBar actionBar;
    private GifImageView showCodeViewImage;
    private Blur blur;
    private TextView textView;
    private boolean shouldBlur = false;


    @Override
    public int getLayout() {
        return R.layout.activity_show_code_view;
    }

    @Override
    public void bindID() {
        LogUtils.d("--->ShowCodeViewActivity bindID");

        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getIntent().getStringExtra("CodeActivityToShowCodeActivity"));

        blur = Blur.newInstance(this);
        showCodeViewImage = (GifImageView) findViewById(R.id.showCodeViewImage);
        textView = (TextView) findViewById(R.id.jumpText);
    }

    @Override
    public void init() {
    }

    @Override
    public void setOnclick() {
        showCodeViewImage.setOnFrameAvailable(new GifImageView.OnFrameAvailable() {
            @Override
            public Bitmap onFrameAvailable(Bitmap bitmap) {
                if (shouldBlur) {
                    return blur.blur(bitmap);
                }
                return bitmap;
            }
        });
    }

    @Override
    public void logic() {
        new GifDataDownloader() {
            @Override
            protected void onPostExecute(final byte[] bytes) {
                showCodeViewImage.setBytes(bytes);
                showCodeViewImage.startAnimation();
                LogUtils.d("GIF width is " + showCodeViewImage.getGifWidth());
                LogUtils.d("GIF height is " + showCodeViewImage.getGifHeight());
                textView.setText(Html.fromHtml(
                        getIntent().getStringExtra("CodeActivityToShowCodeActivityMSG")  +
                                "<br><a href=\""+getIntent().getStringExtra("CodeActivityToShowCodeActivityGitUrl")+"\">点击链接可访问项目地址</a>"));
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }.execute(getIntent().getStringExtra("CodeActivityToShowCodeActivityImageUrl"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        showCodeViewImage.stopAnimation();
        showCodeViewImage.clear();
    }


}
