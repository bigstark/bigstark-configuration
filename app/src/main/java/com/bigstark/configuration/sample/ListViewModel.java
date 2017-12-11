package com.bigstark.configuration.sample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gangdaegyu on 2017. 12. 11..
 */

public class ListViewModel extends ViewModel {

    private MutableLiveData<List<Integer>> items;

    public LiveData<List<Integer>> getItems() {
        if (items == null) {
            items = new MutableLiveData<>();

            List<Integer> value = new ArrayList<>();
            items.setValue(value);
        }


        return items;
    }

    public void addItem() {
        List<Integer> value = items.getValue();
        int lastValue = value.size() == 0 ? -1 : value.get(value.size() - 1);
        value.add(lastValue + 1);

        items.postValue(value);
    }

    public void addItem10() {
        List<Integer> value = items.getValue();
        int lastValue = value.size() == 0 ? -1 : value.get(value.size() - 1);

        for (int i = 0; i < 10; i++) {
            value.add((lastValue + 1) + i);
        }

        items.postValue(value);
    }

    public void removeItem() {
        List<Integer> value = items.getValue();
        if (value.size() == 0) {
            return;
        }
        value.remove(0);

        items.postValue(value);
    }

    public void removeItem10() {
        List<Integer> value = items.getValue();
        for (int i = 0; i < 10; i++) {
            if (value.size() == 0) {
                break;
            }
            value.remove(0);
        }

        items.postValue(value);
    }

    public void clear() {
        List<Integer> value = items.getValue();
        value.clear();
        items.postValue(value);
    }

}
