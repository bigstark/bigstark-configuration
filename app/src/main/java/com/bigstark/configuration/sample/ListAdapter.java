package com.bigstark.configuration.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigstark.configuration.CoreAdapter;
import com.bigstark.configuration.CoreViewHolder;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

/**
 * Created by gangdaegyu on 2017. 12. 8..
 */

public class ListAdapter extends CoreAdapter<Integer> {


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == -1) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_header, parent, false);
            return new HeaderViewHolder(itemView);
        }

        if (viewType == -2) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_footer, parent, false);
            return new FooterViewHolder(itemView);
        }

        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public CoreViewHolder<Integer> onCreateItemViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    protected int getHeaderCount() {
        return 1;
    }

    @Override
    protected int getFooterCount() {
        return 1;
    }

    @Override
    public int getHeaderItemViewType(int headerPosition) {
        return -1;
    }

    @Override
    public int getFooterItemViewType(int footerPosition) {
        return -2;
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class ListViewHolder extends CoreViewHolder<Integer> {

        private TextView tvPosition;

        public ListViewHolder(View itemView) {
            super(itemView);
            tvPosition = itemView.findViewById(R.id.tv_position);
        }

        @Override
        public void bindItem(Integer item, Consumer<Integer> itemClickConsumer) {
            super.bindItem(item, itemClickConsumer);
            tvPosition.setText(Integer.toString(item));
        }
    }

}
