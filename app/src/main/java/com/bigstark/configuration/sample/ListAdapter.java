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
    public CoreViewHolder<Integer> onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item, parent, false);
        return new ListViewHolder(itemView);
    }


    public class ListViewHolder extends CoreViewHolder<Integer> {

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
