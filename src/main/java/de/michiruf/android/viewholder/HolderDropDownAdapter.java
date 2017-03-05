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
public abstract class HolderDropDownAdapter<ItemType> extends ArrayAdapter<ItemType> {

    private static final String TAG = "HolderDropDownAdapter";

    private int itemLayoutRes;
    private int dropDownItemLayoutRes;

    public HolderDropDownAdapter(Context context, int itemLayoutRes, int dropDownItemLayoutRes) {
        super(context, 0);
        this.itemLayoutRes = itemLayoutRes;
        this.dropDownItemLayoutRes = dropDownItemLayoutRes;
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

    @SuppressWarnings("unchecked")
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
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
                    .inflate(getDropDownItemLayoutRes(position), parent, false);
            holder = constructDropDownHolder(convertView);
        }

        holder.apply(getItem(position), position);
        return convertView;
    }

    @SuppressWarnings("UnusedParameters")
    protected int getItemLayoutRes(int position) {
        return itemLayoutRes;
    }

    @SuppressWarnings("UnusedParameters")
    protected int getDropDownItemLayoutRes(int position) {
        return dropDownItemLayoutRes;
    }

    protected abstract ViewHolder<ItemType> constructHolder(View view);

    protected abstract ViewHolder<ItemType> constructDropDownHolder(View view);

    public interface ViewHolder<T> {

        void apply(T item, int position);
    }
}
