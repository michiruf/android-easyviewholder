package de.michiruf.android.viewholder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Michael Ruf
 * @since 2017-03-05
 */
public abstract class RecyclerHolderArrayAdapter<ItemType> extends RecyclerView.Adapter {

    private final int itemLayoutRes;
    private final List<ItemType> items;

    private OnItemClickListener onItemClickListener;

    public RecyclerHolderArrayAdapter(int itemLayoutRes) {
        this.itemLayoutRes = itemLayoutRes;
        items = new ArrayList<>();
    }

    public RecyclerHolderArrayAdapter(int itemLayoutRes, List<ItemType> dataList) {
        this.itemLayoutRes = itemLayoutRes;
        items = dataList;
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

        int adapterPosition = holder.getAdapterPosition();
        // Cancel binding and stuff if the element was removed
        if (adapterPosition == RecyclerView.NO_POSITION) {
            return;
        }

        myHolder.apply(items.get(adapterPosition), adapterPosition);

        if (onItemClickListener != null) {
            if (myHolder.internalListener == null) {
                myHolder.internalListener = new InternalOnItemClickListener(onItemClickListener);
                myHolder.itemView.setOnClickListener(myHolder.internalListener);
            }
            myHolder.internalListener.position = adapterPosition;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ItemType getItem(int position) {
        return items.get(position);
    }

    public List<ItemType> getDataList() {
        return items;
    }

    public void add(@NonNull ItemType entry) {
        items.add(entry);
    }

    public void add(int position, @NonNull ItemType itemType) {
        items.add(position, itemType);
    }

    public boolean addAll(@NonNull Collection<? extends ItemType> collection) {
        return items.addAll(collection);
    }

    public boolean addAll(int i, @NonNull Collection<? extends ItemType> collection) {
        return items.addAll(i, collection);
    }

    public ItemType remove(int position) {
        return items.remove(position);
    }

    public void clear() {
        items.clear();
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    protected abstract ViewHolder<ItemType> constructHolder(View view);

    public static abstract class ViewHolder<ItemType> extends RecyclerView.ViewHolder {

        private InternalOnItemClickListener internalListener;

        public ViewHolder(View view) {
            super(view);
        }

        public abstract void apply(ItemType item, int position);
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);
    }

    private static class InternalOnItemClickListener implements View.OnClickListener {

        private final OnItemClickListener onItemClickListener;
        private int position;

        private InternalOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, position);
        }
    }
}
