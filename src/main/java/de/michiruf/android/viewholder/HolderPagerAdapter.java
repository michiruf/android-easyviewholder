package de.michiruf.android.viewholder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * @author Michael Ruf
 * @since 2015-08-08
 */
public abstract class HolderPagerAdapter<ItemType> extends ArrayAdapter<ItemType> {

    private static final String TAG = "HolderPagerAdapter";

    private int itemLayoutRes;

    public HolderPagerAdapter(Context context, int itemLayoutRes) {
        super(context, 0);
        this.itemLayoutRes = itemLayoutRes;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder<ItemType> holder = null;

        if (convertView != null && convertView.getTag() instanceof ViewHolder) {
            try {
                holder = (ViewHolder<ItemType>) convertView.getTag();
            } catch (ClassCastException e) {
                Log.e(TAG, Log.getStackTraceString(e));
            }
        }

        if (holder == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(getItemLayoutRes(position), parent, false);
            holder = constructHolder(convertView);
        }

        holder.apply(getItem(position), position);
        return convertView;
    }

    @SuppressWarnings("UnusedParameters")
    protected int getItemLayoutRes(int position) {
        return itemLayoutRes;
    }

    protected abstract ViewHolder<ItemType> constructHolder(View view);

    public interface ViewHolder<ItemType> {

        void apply(ItemType item, int position);
    }
}