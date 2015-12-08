package soyi.pro.com.soyi.Logic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import soyi.pro.com.soyi.R;

/**
 * Created by Ezreal on 2015/12/3.
 */
public class MenuAdapter extends BaseAdapter {
    Context context;
    String[] values;

    int[] imageArray={R.drawable.embed2,R.drawable.photo_images,R.drawable.star_empty,R.drawable.qrcode,R.drawable.coin_yen,R.drawable.share2,R.drawable.github5,R.drawable.cog,R.drawable.exit};

    public MenuAdapter(Context context,String[] values) {
        this.context = context;
        this.values=values;
    }

    public final class ViewHolder {
        public ImageView imageView;
        public TextView text;
    }




    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return values[position];
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
            LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.listview_item, null);
            convertView = view;
            holder.text = (TextView) convertView.findViewById(R.id.itemText);
            holder.imageView = (ImageView) convertView.findViewById(R.id.itemImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText(values[position]);
        holder.imageView.setImageResource(imageArray[position]);
        return convertView;
    }

}

