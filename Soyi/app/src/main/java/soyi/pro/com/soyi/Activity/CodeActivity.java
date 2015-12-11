package soyi.pro.com.soyi.Activity;

import android.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;

import soyi.pro.com.soyi.Logic.Adapter.CodeAdapter;
import soyi.pro.com.soyi.Logic.LogicJumpTo;
import soyi.pro.com.soyi.R;
import soyi.pro.com.soyi.Tools.ToastUtils;

public class CodeActivity extends Son implements AdapterView.OnItemClickListener {
    private ListView mListView;

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
        switch (position) {
            case 0:
                logicJumpTo.noValueJump(CodeActivity.this, EditTextActivity.class);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;

        }
    }
}
