package in.enggoma.models;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import in.enggoma.R;

public class MaterialAdapter extends BaseAdapter {

    private final List<String> data;
    private final LayoutInflater inflater;
    private final short drop_down_margins;
    private final short margin;
    private final float item_text_size;

    public MaterialAdapter(Context context, List<String> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        final int screen_width = context.getResources().getDisplayMetrics().widthPixels;
        this.drop_down_margins = (short) (screen_width / 40);
        this.margin = (short) (screen_width / 75);
        this.item_text_size = screen_width / 24;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, true);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, false);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    private View initView(int position, View convertView, boolean drop_down) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.spinner_item, null, false);
            holder.title = convertView.findViewById(R.id.title);
            if (drop_down) {
                ((LinearLayout.LayoutParams) holder.title.getLayoutParams()).setMargins(drop_down_margins, drop_down_margins, drop_down_margins, drop_down_margins);
            } else {
                ((LinearLayout.LayoutParams) holder.title.getLayoutParams()).setMargins(0, margin, 0, margin);
                convertView.setBackgroundColor(0);
            }
            holder.title.setTextSize(TypedValue.COMPLEX_UNIT_PX, item_text_size);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(data.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView title;
    }
}