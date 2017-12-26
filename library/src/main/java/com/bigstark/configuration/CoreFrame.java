package com.bigstark.configuration;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.HashSet;
import java.util.Set;

import io.reactivex.functions.Action;

/**
 * Created by gangdaegyu on 2017. 12. 8..
 */

public class CoreFrame implements LifecycleObserver, LifecycleOwner {

    private Set<CoreFrame> frames = new HashSet<>();

    private LifecycleRegistry registry;
    private Lifecycle lifecycle;

    public View contentView;

    public CoreFrame(View contentView, Lifecycle lifecycle) {
        this.contentView = contentView;
        this.lifecycle = lifecycle;
        this.registry = new LifecycleRegistry(this);
    }

    protected void addFrame(CoreFrame frame) {
        getLifecycle().addObserver(frame);
        frames.add(frame);
    }

    protected void removeFrame(CoreFrame frame) {
        getLifecycle().removeObserver(frame);
        frames.remove(frame);
    }

    protected Context getContext() {
        return contentView == null ? null : contentView.getContext();
    }

    protected void startActivity(Intent intent) {
        getContext().startActivity(intent);
    }

    protected void startActivityForResult(Intent intent, int requestCode) {
        ((Activity) getContext()).startActivityForResult(intent, requestCode);
    }


    public void subscribeItemClick(int viewId, Action action) {
        RxView.clicks(contentView.findViewById(viewId))
                .subscribe(v -> {
                    try {
                        action.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }


    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onLifecycleCreate() {
        registry.markState(Lifecycle.State.CREATED);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onLifecycleStart() {
        registry.markState(Lifecycle.State.STARTED);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onLifecycleResume() {
        registry.markState(Lifecycle.State.RESUMED);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onLifecyclePause() {}


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onLifecycleStop() {}


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onLifecycleDestroy() {
        for (CoreFrame frame : frames) {
            getLifecycle().removeObserver(frame);
        }
        frames.clear();
        registry.markState(Lifecycle.State.DESTROYED);

        contentView = null;
    }

}
