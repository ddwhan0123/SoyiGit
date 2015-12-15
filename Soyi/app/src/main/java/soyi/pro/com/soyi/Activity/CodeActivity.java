package soyi.pro.com.soyi.Activity;

import android.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;

import soyi.pro.com.soyi.Logic.LogicJumpTo;
import soyi.pro.com.soyi.R;
import soyi.pro.com.soyi.Tools.ToastUtils;

public class CodeActivity extends Son {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CodeAdapter adapter;

    ToastUtils toastUtils;
    LogicJumpTo logicJumpTo;

    @Override
    public int getLayout() {
        LogUtils.d("--->CodeActivity getLayout");
        return R.layout.activity_code;
    }

    @Override
    public void init() {
        toastUtils = ToastUtils.getInstance();
        logicJumpTo = LogicJumpTo.getInstance();

        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        adapter = new CodeAdapter(getResources().getStringArray(R.array.codeArray));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new CodeAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                toastUtils.show(CodeActivity.this, data, false);
                switch (data) {
                    case "For EditText":
                        logicJumpTo.noValueJump(CodeActivity.this, EditTextActivity.class);
                        break;
                    case "For TextView":
                        break;
                    case "For Button/CheckBox/Switch/ProgressBar/Spinner":
                        break;
                    case "For ListView/GridView/TabHost":
                        break;
                    case "For Dialog":
                        break;
                    case "For CustomView":
                        break;
                    case "For ImageView":
                        break;
                    case "For WebView":
                        break;
                    case "For Animation":
                        break;
                    case "For Layout":
                        break;
                    case "For Menu":
                        break;
                    case "For NetWork":
                        break;
                    case "Others":
                        break;

                }
            }
        });
    }

    @Override
    public void bindID() {
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("个人开发");
        actionBar.setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.codeListView);

    }

    @Override
    public void setOnclick() {

    }

    @Override
    public void logic() {
        LogUtils.d("--->CodeActivity logic");

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

    public static class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.ViewHolder> implements OnClickListener{

        public String[] datas = null;

        public CodeAdapter(String[] datas) {
            this.datas = datas;
        }

        public static interface OnRecyclerViewItemClickListener {
            void onItemClick(View view, String data);
        }

        private OnRecyclerViewItemClickListener mOnItemClickListener = null;

        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }

        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.code_listview_item, viewGroup, false);
            ViewHolder vh = new ViewHolder(view);
            //将创建的View注册点击事件
            view.setOnClickListener(this);
            return vh;
        }

        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.mTextView.setText(datas[position]);
            //将数据保存在itemView的Tag中，以便点击时进行获取
            viewHolder.itemView.setTag(datas[position]);
        }

        //获取数据的数量
        @Override
        public int getItemCount() {
            return datas.length;
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(v,(String)v.getTag());
            }

        }

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mTextView = (TextView) view.findViewById(R.id.codeText);
            }
        }
    }

}
