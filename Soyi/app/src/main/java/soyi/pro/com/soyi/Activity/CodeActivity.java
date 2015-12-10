package soyi.pro.com.soyi.Activity;

import android.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;

import soyi.pro.com.soyi.Logic.Adapter.CodeAdapter;
import soyi.pro.com.soyi.R;
import soyi.pro.com.soyi.Tools.ToastUtils;

public class CodeActivity extends Son implements AdapterView.OnItemClickListener {
    private ListView mListView;

    ToastUtils toastUtils;

    @Override
    public int getLayout() {
        LogUtils.d("--->CodeActivity getLayout");
        return R.layout.activity_code;
    }

    @Override
    public void init() {
        toastUtils = ToastUtils.getInstance();

        mListView.setAdapter(new CodeAdapter(CodeActivity.this, getResources().getStringArray(R.array.codeArray)));
    }

    @Override
    public void bindID() {
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("个人开发");
        actionBar.setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.codeListView);

    }

    @Override
    public void setOnclick() {
        mListView.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String[] value = getResources().getStringArray(R.array.codeArray);
        toastUtils.show(this, value[position], false);
    }
}
