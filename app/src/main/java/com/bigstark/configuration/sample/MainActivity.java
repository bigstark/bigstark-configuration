package com.bigstark.configuration.sample;

import android.os.Bundle;

import com.bigstark.configuration.CoreActivity;
import com.bigstark.configuration.CoreFrame;

public class MainActivity extends CoreActivity {

    private ListViewModel listViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CoreFrame actionFrame = new ActionFrame(findViewById(R.id.action_contents), getLifecycle());
        ListFrame listFrame = new ListFrame(findViewById(R.id.list_contents), getLifecycle());

        addFrame(actionFrame);
        addFrame(listFrame);

        listViewModel = new ListViewModel();
        listViewModel.getItems().observe(this, listFrame.getItemChangeObserver());

        actionFrame.subscribeItemClick(R.id.btn_add, listViewModel::addItem);
        actionFrame.subscribeItemClick(R.id.btn_add_10, listViewModel::addItem10);
        actionFrame.subscribeItemClick(R.id.btn_remove, listViewModel::removeItem);
        actionFrame.subscribeItemClick(R.id.btn_remove_10, listViewModel::removeItem10);
        actionFrame.subscribeItemClick(R.id.btn_clear, listViewModel::clear);
    }


}
