package soyi.pro.com.soyi.Activity.CodeActivityPro;

import android.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;

import soyi.pro.com.soyi.Activity.Son;
import soyi.pro.com.soyi.ContentConfig;
import soyi.pro.com.soyi.Logic.LogicJumpTo;
import soyi.pro.com.soyi.R;

public class ShowCodeTypeActivity extends Son implements AdapterView.OnItemClickListener {
    private ActionBar actionBar;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CodeTypeAdapter adapter;

    LogicJumpTo logicJumpTo;

    @Override
    public int getLayout() {
        return R.layout.activity_code;
    }

    @Override
    public void bindID() {
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getIntent().getStringExtra("CodeActivityToShowCodeActivity"));

        mRecyclerView = (RecyclerView) findViewById(R.id.codeListView);

    }

    @Override
    public void init() {
        logicJumpTo = LogicJumpTo.getInstance();
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        LogUtils.d("--->ShowCodeTypeActivity  init   " + getIntent().getStringExtra("CodeActivityToShowCodeActivity"));
        adapter = new CodeTypeAdapter(logicJumpTo.makeArrayDate(getIntent().getStringExtra("CodeActivityToShowCodeActivity"), ShowCodeTypeActivity.this));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new CodeTypeAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                logicJumpTo.ShowCodeTypeActivityOnItemClickListenerSwitch(data, ShowCodeTypeActivity.this);
            }
        });

    }

    @Override
    public void setOnclick() {

    }

    @Override
    public void logic() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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

    public static class CodeTypeAdapter extends RecyclerView.Adapter<CodeTypeAdapter.ViewHolder> implements View.OnClickListener {

        public String[] datas = null;

        public CodeTypeAdapter(String[] datas) {
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
                mOnItemClickListener.onItemClick(v, (String) v.getTag());
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
