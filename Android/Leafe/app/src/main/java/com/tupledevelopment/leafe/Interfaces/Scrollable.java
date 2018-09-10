package com.tupledevelopment.leafe.Interfaces;

import android.view.ViewGroup;

import com.tupledevelopment.leafe.Callbacks.ObservableScrollViewCallbacks;

public interface Scrollable {
    void setScrollViewCallbacks(ObservableScrollViewCallbacks listener);
    void scrollVerticallyTo(int y);
    int getCurrentScrollY();
    void setTouchInterceptionViewGroup(ViewGroup viewGroup);
}
