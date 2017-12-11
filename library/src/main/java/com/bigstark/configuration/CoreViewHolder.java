package com.bigstark.configuration;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import io.reactivex.functions.Consumer;

public class CoreViewHolder<T> extends RecyclerView.ViewHolder {
        public CoreViewHolder(View itemView) {
            super(itemView);
        }

        public void bindItem(T item, Consumer<T> itemClickConsumer) {
            RxView.clicks(itemView)
                    .map(aVoid -> item)
                    .subscribe(itemClickConsumer);
        }


    }