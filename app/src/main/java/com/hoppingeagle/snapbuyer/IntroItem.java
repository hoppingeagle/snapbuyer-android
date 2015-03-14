package com.hoppingeagle.snapbuyer;

import android.content.Context;
import android.view.View;

public class IntroItem implements SliderItem {

    int layoutId;

    public IntroItem(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    public int getLayoutId() {
        return layoutId;
    }

    @Override
    public void handleView(View view, Context context) {

    }
}
