package com.bigstark.configuration;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gangdaegyu on 2017. 12. 11..
 */

public class CoreListViewModel<T> extends ViewModel {

  private MutableLiveData<List<T>> items;

  public CoreListViewModel() {
    items = new MutableLiveData<>();
    items.setValue(new ArrayList<>());
  }

  public LiveData<List<T>> getItems() {
    return items;
  }

  protected void setValue(List<T> values) {
    items.setValue(values);
  }

  protected void postValue(List<T> values) {
    items.postValue(values);
  }

  protected List<T> getValue() {
    return items.getValue();
  }

}
