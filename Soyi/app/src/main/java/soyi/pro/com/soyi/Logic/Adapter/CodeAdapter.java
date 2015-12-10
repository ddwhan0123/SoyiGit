package soyi.pro.com.soyi.Logic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import soyi.pro.com.soyi.R;

/**
 * Created by Ezreal on 2015/12/10.
 */
public class CodeAdapter extends BaseAdapter {
    Context context;
    String[] values;

    public final class ViewHolder {
        public TextView text;
    }

    public CodeAdapter(Context context,String[] values) {
        this.context = context;
        this.values=values;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.code_listview_item, null);
            convertView = view;
            holder.text = (TextView) convertView.findViewById(R.id.codeText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText(values[position]);
        return convertView;
    }
}
