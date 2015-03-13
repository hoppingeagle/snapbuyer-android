package com.hoppingeagle.snapbuyer;

import java.io.Serializable;

public class Auction implements Serializable {
    private long mId;
    private String mUrl;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
