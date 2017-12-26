package com.bigstark.configuration;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by gangdaegyu on 2017. 12. 8..
 */

public abstract class CoreAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int DEFAULT_ITEM_VIEW_TYPE = 1000;

    private List<T> items = new ArrayList<>();
    private Consumer<T> itemClickConsumer;
    private Consumer<T> itemClickConsumerInternal = (t) -> {
        if (itemClickConsumer == null) {
            return;
        }

        itemClickConsumer.accept(t);
    };

    public void addItem(T item) {
        this.items.add(item);
        notifyDataSetChanged();
    }

    public void addItem(int index, T item) {
        this.items.add(index, item);
        notifyDataSetChanged();
    }

    public void addItems(List<T> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addItems(int index, List<T> items) {
        this.items.addAll(index, items);
        notifyDataSetChanged();
    }

    public void removeItem(T item) {
        this.items.remove(item);
        notifyDataSetChanged();
    }

    public void removeItem(int index) {
        this.items.remove(index);
        notifyDataSetChanged();
    }

    public void removeItems(List<T> items) {
        this.items.removeAll(items);
        notifyDataSetChanged();
    }

    public void setItems(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void clear(List<T> items) {
        this.items.clear();
        notifyDataSetChanged();
    }

    public void subscribeItemClick(Consumer<T> consumer) {
        this.itemClickConsumer = consumer;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == DEFAULT_ITEM_VIEW_TYPE) {
            return onCreateItemViewHolder(parent);
        }

        return null;
    }


    public CoreViewHolder<T> onCreateItemViewHolder(ViewGroup parent) {
        return null;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < getHeaderCount()) {
            onBindHeaderViewHolder(holder, position);
            return;
        }

        if (position < items.size() + getHeaderCount()) {
            onBindItemViewHolder((CoreViewHolder<T>) holder, position - getHeaderCount());
            return;
        }

        onBindFooterViewHolder(holder, position - getHeaderCount() - items.size());
    }


    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int headerPosition) {}


    protected void onBindItemViewHolder(CoreViewHolder<T> holder, int itemPosition) {
        holder.bindItem(items.get(itemPosition), itemClickConsumer);
    }


    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int footerPosition) {}


    @Override
    public int getItemCount() {
        return getHeaderCount() + items.size() + getFooterCount();
    }


    protected int getHeaderCount() {
        return 0;
    }


    protected int getFooterCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getHeaderCount()) {
            return getHeaderItemViewType(position);
        }

        if (position < items.size() + getHeaderCount()) {
            return DEFAULT_ITEM_VIEW_TYPE;
        }


        return getFooterItemViewType(position - getHeaderCount() - items.size());
    }

    public int getHeaderItemViewType(int headerPosition) {
        return 0;
    }


    public int getFooterItemViewType(int footerPosition) {
        return 0;
    }
}
