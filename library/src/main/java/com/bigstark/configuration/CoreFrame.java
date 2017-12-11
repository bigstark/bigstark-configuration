package com.bigstark.configuration;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import io.reactivex.functions.Action;

/**
 * Created by gangdaegyu on 2017. 12. 8..
 */

public class CoreFrame implements LifecycleObserver, LifecycleOwner {

    private LifecycleRegistry registry;
    private Lifecycle lifecycle;

    public View contentView;

    public CoreFrame(View contentView, Lifecycle lifecycle) {
        this.contentView = contentView;
        this.lifecycle = lifecycle;
        this.registry = new LifecycleRegistry(this);
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
        registry.markState(Lifecycle.State.DESTROYED);

        contentView = null;
    }

}
