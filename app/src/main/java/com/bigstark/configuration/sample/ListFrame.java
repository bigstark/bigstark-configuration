package com.bigstark.configuration.sample;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bigstark.configuration.CoreFrame;

import java.util.List;

/**
 * Created by gangdaegyu on 2017. 12. 8..
 */

public class ListFrame extends CoreFrame {
    public static final String TAG = ListFrame.class.getName();

    private RecyclerView rvSample;
    private ListAdapter adapter;


    public ListFrame(View contentView, Lifecycle lifecycle) {
        super(contentView, lifecycle);

        rvSample = contentView.findViewById(R.id.rv_sample);
        rvSample.setLayoutManager(new LinearLayoutManager(contentView.getContext()));

        adapter = new ListAdapter();
        adapter.subscribeItemClick(item -> Log.v(TAG, "item : " + item));
        rvSample.setAdapter(adapter);
    }


    public Observer<List<Integer>> getItemChangeObserver() {
        return (items) -> {
            if (adapter == null) {
                return;
            }

            adapter.setItems(items);
        };
    }

}
