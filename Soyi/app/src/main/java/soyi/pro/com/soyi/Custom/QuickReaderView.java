package soyi.pro.com.soyi.Custom;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class QuickReaderView extends TextView {
    private boolean repeatFlag = false;
    private static final long MINUTE = 60000;
    private long delay = 1000; // 60 words per second by default

    public QuickReaderView(Context context) {
        super(context);
    }

    public QuickReaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuickReaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // in words per second
    public QuickReaderView setWordsPerSecond(long wordsPerSecond) {
        this.delay = MINUTE / wordsPerSecond;
        return this;
    }

    public void showText(String text) {
        final String words[] = text.split(" ");
        final Activity activity = (Activity)getContext();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(repeatFlag) {
                    for(final String word : words) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setText(word);
                            }
                        });
                        sleep(delay);
                    }
                }

            }
        }).start();
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch(InterruptedException e) {
            Log.e(this.getClass().getName(), "Cannot set sleep", e);
        }
    }

    /**
     * Enables repeat option
     * @param repeatFlag
     * @return
     */
    public QuickReaderView setRepeat(boolean repeatFlag) {
        this.repeatFlag = repeatFlag;
        return this;
    }
}