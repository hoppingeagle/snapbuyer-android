package com.hoppingeagle.snapbuyer;

import android.content.Context;
import android.view.View;

public interface SliderItem {
    int getLayoutId();
    void handleView(View view, Context context);
}
