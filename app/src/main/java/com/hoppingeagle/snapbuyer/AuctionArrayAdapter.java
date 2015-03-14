package com.hoppingeagle.snapbuyer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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

        if (auction.getImageUrl() != null) {
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(auction.getImageUrl(), imageView);
        } else {
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.mipmap.no_photo));
        }

        TextView name = (TextView) view.findViewById(R.id.auction_name_id);
        name.setText(auction.getName());

        TextView buy = (TextView) view.findViewById(R.id.buy_button_id);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAuction(auction);
            }
        });

        return view;
    }

    public void setNewData(List<Auction> auctionList) {
        clear();
        addAll(auctionList);
        notifyDataSetChanged();
    }

    public void pop() {
        if (getCount() == 0) {
            return;
        }
        remove(getItem(0));
        notifyDataSetChanged();
    }


    private void openAuction(Auction auction) {
        String pageUrl = auction.getPageUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl));
        getContext().startActivity(intent);
    }
}
