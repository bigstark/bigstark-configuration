package com.bigstark.configuration;

import android.support.v7.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by gangdaegyu on 2017. 12. 8..
 */

public abstract class CoreActivity extends AppCompatActivity {

    private Set<CoreFrame> frames = new HashSet<>();

    protected void addFrame(CoreFrame frame) {
        getLifecycle().addObserver(frame);
        frames.add(frame);
    }


    protected void removeFrame(CoreFrame frame) {
        getLifecycle().removeObserver(frame);
        frames.remove(frame);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (CoreFrame frame : frames) {
            getLifecycle().removeObserver(frame);
        }
        frames.clear();
    }
}
