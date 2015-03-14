package com.hoppingeagle.snapbuyer;

import android.graphics.drawable.Drawable;

import com.andtinder.model.CardModel;

public class AuctionCard extends CardModel {
    private Auction mAuction;

    public AuctionCard(String title, String description, String url, Auction auction) {
        super(title, description, (Drawable) null);
        mAuction = auction;
    }

    public Auction getAuction() {
        return mAuction;
    }

    public void setAuction(Auction auction) {
        mAuction = auction;
    }
}
