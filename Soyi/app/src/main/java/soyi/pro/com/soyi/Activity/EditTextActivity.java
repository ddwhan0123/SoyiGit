package soyi.pro.com.soyi.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import soyi.pro.com.soyi.Activity.Son;
import soyi.pro.com.soyi.R;

public class EditTextActivity extends Son implements AdapterView.OnItemClickListener {
    ActionBar actionBar;

    @Override
    public int getLayout() {
        return R.layout.activity_edit_text;
    }

    @Override
    public void bindID() {
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void init() {

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
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
