package com.hoppingeagle.snapbuyer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class AuctionArrayAdapter extends ArrayAdapter<Auction> {
    private LayoutInflater mInflater;

    public AuctionArrayAdapter(Context context) {
        super(context, 0);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Auction auction = getItem(position);
        View view = mInflater.inflate(R.layout.auction_card_layout, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.auction_image_id);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(auction.getImageUrl(), imageView);

        TextView name = (TextView) view.findViewById(R.id.auction_name_id);
        name.setText(auction.getName());

        return view;
    }

    public void setNewData(List<Auction> auctionList) {
        clear();
        addAll(auctionList);
        notifyDataSetChanged();
    }

    public void pop() {
        remove(getItem(0));
        notifyDataSetChanged();
    }
}
