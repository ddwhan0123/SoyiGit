package soyi.pro.com.soyi.Custom.CuteEditTextPro;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.apkfuns.logutils.LogUtils;

import soyi.pro.com.soyi.R;

/**
 * Created by Ezreal on 2015/12/8.
 */
public class CuteEditText extends LinearLayout {
    private int Default_MinLength;
    private int Default_MaxLength;
    private String Default_Anim_Type;
    private String Default_Hint;
    //被操作的EidtText
    private EditText editText;
    //被操作的ImageView
    private ImageView imageView;

    //上下文对象
    private Context context;


    public CuteEditText(Context context) {
        super(context);
        this.context = context;
    }

    public CuteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initValue(context, attrs);
    }

    public CuteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initValue(context, attrs);
    }

    //初始化
    private void initValue(final Context context, AttributeSet attrs) {
        //获取标签数组
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.cuteEditText);
        try {
            //获取最短长度
            Default_MinLength = typedArray.getInteger(R.styleable.cuteEditText_text_minLength, 0);
            //获取最大长度
            Default_MaxLength = typedArray.getInteger(R.styleable.cuteEditText_text_maxLength, 12);
            //获取动画种类
            Default_Anim_Type = typedArray.getString(R.styleable.cuteEditText_text_anim_Type);
            //获取默认参数
            Default_Hint = typedArray.getString(R.styleable.cuteEditText_text_hint);
            LogUtils.d("--->cuteedittext Default_Hint  " + Default_Hint+"\n"+"--->cuteedittext Default_Hint  " + Default_Anim_Type+"\n"
                    +"--->cuteedittext Default_MaxLength  " + Default_MaxLength+"\n"+"--->cuteedittext Default_MinLength  " + Default_MinLength);
        } catch (Exception ex) {
            LogUtils.e("Unable to parse attributes due to: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            typedArray.recycle();
        }


        if (Default_Anim_Type == null) {
            Default_Anim_Type = "Swing";
        }
        if (Default_Hint == null) {
            Default_Hint = "";
        }
        //设置水平
        this.setOrientation(LinearLayout.HORIZONTAL);
        //创建edittext
        if (editText == null) {
            editText = new EditText(this.getContext());
            editText.setLayoutParams(
                    new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0.8f));
            editText.setHint(Default_Hint);
            this.addView(editText);
        }


        //添加button
        if (imageView == null) {
            imageView = new ImageView(this.getContext());
            imageView.setLayoutParams(
                    new LayoutParams(0, LayoutParams.MATCH_PARENT, 0.2f)
            );
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setVisibility(View.INVISIBLE);
            imageView.setImageResource(R.drawable.cross);

            editText.addTextChangedListener(new TextWatcher() {
                // s:之前的文字内容
                // start:添加文字的位置(从0开始)
                // count:不知道 一直是0
                // after:添加的文字总数
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                // s:之后的文字内容
                // start:添加文字的位置(从0开始)
                // before:不知道 一直是0
                // before:添加的文字总数
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int valueLen = s.length();
                    if (valueLen < Default_MinLength || valueLen > Default_MaxLength) {
                        editText.setTextColor(getResources().getColor(R.color.OrangeRed));
                        imageView.setClickable(true);
                    } else {
                        editText.setTextColor(getResources().getColor(R.color.CornflowerBlue));
                        imageView.setImageResource(R.drawable.checkmark);
                        imageView.setClickable(false);
                    }
                }

                // s:之后的文字内容
                @Override
                public void afterTextChanged(Editable s) {
                    int valueLen = s.length();
                    if (valueLen < Default_MinLength || valueLen > Default_MaxLength) {
                        editText.setTextColor(getResources().getColor(R.color.OrangeRed));
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setImageResource(R.drawable.cross);
                        imageView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editText.setText("");
                                imageView.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                }
            });
            this.addView(imageView);
        }
    }

}
