package com.bigstark.configuration;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by gangdaegyu on 2017. 12. 8..
 */

public abstract class CoreAdapter<T> extends RecyclerView.Adapter<CoreViewHolder<T>> {

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
    public void onBindViewHolder(CoreViewHolder<T> holder, int position) {
        holder.bindItem(items.get(position), itemClickConsumerInternal);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


}
