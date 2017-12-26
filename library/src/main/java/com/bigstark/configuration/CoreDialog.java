package com.bigstark.configuration;

import android.app.Dialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gangdaegyu on 2017. 12. 15..
 */

public class CoreDialog extends Dialog implements LifecycleObserver, LifecycleOwner {

  private Set<CoreFrame> frames = new HashSet<>();

  private LifecycleRegistry registry;
  private Lifecycle lifecycle;

  public CoreDialog(@NonNull Context context, Lifecycle lifecycle) {
    super(context);
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
  }
}
