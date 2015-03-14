package com.hoppingeagle.snapbuyer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardStackAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

public class AuctionCardStackAdapter extends CardStackAdapter {

    public AuctionCardStackAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public View getCardView(int position, CardModel model, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            convertView = inflater.inflate(R.layout.auction_card_layout, parent, false);

            assert convertView != null;
        }

        AuctionCard auctionCard = (AuctionCard) model;

        ImageView image = (ImageView) convertView.findViewById(R.id.auction_image_id);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(auctionCard.getAuction().getImageUrl(), image);

        ((TextView)convertView.findViewById(R.id.auction_name_id)).setText(model.getTitle());
        ((TextView)convertView.findViewById(R.id.auction_description_id)).setText(model.getDescription());
        return convertView;
    }
}

