package de.michiruf.android.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Ruf
 * @since 2017-03-05
 */
public abstract class RecyclerHolderArrayAdapter<ItemType> extends RecyclerView.Adapter {

    private final int itemLayoutRes;
    private final List<ItemType> items;

    public RecyclerHolderArrayAdapter(int itemLayoutRes) {
        this.itemLayoutRes = itemLayoutRes;
        items = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayoutRes, parent, false);
        return constructHolder(view);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof ViewHolder)) {
            throw new RuntimeException("Recycler view's view-holder must be instance of " +
                    "RecyclerHolderAdapter.ViewHolder!");
        }

        ViewHolder<ItemType> myHolder = (ViewHolder<ItemType>) holder;
        myHolder.apply(items.get(position), position);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void add(ItemType entry) {
        items.add(entry);
    }

    public void add(int position, ItemType itemType) {
        items.add(position, itemType);
    }

    public ItemType remove(int position) {
        return items.remove(position);
    }

    public void clear() {
        items.clear();
    }

    protected abstract ViewHolder<ItemType> constructHolder(View view);

    public static abstract class ViewHolder<ItemType> extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
        }

        public abstract void apply(ItemType item, int position);
    }
}
