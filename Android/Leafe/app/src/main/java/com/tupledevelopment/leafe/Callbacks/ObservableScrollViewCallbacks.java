package com.tupledevelopment.leafe.Callbacks;


import com.tupledevelopment.leafe.Enums.ScrollState;

public interface ObservableScrollViewCallbacks {
    void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging);
    void onDownMotionEvent();
    void onUpOrCancelMotionEvent(ScrollState scrollState);
}
