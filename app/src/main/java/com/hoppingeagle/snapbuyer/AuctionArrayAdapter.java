package com.hoppingeagle.snapbuyer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class AuctionArrayAdapter extends ArrayAdapter<SliderItem> {
    private LayoutInflater mInflater;

    public AuctionArrayAdapter(Context context) {
        super(context, 0);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SliderItem slider = getItem(position);
        View view = mInflater.inflate(slider.getLayoutId(), parent, false);
        slider.handleView(view, getContext());

        return view;
    }

    public void pop() {
        if (getCount() == 0) {
            return;
        }
        remove(getItem(0));
        notifyDataSetChanged();
    }


}
