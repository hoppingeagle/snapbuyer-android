package com.hoppingeagle.snapbuyer;

import java.io.Serializable;

public class Auction implements Serializable {
    private int mId;
    private String mAuctionUrl;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getAuctionUrl() {
        return mAuctionUrl;
    }

    public void setAuctionUrl(String auctionUrl) {
        mAuctionUrl = auctionUrl;
    }
}
