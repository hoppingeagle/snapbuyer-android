package com.hoppingeagle.snapbuyer;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Auction implements Serializable {
    private long mId;

    @JsonProperty("image_url")
    private String mImageUrl;

    @JsonProperty("offer_url")
    private String mPageUrl;
    private String mName;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getPageUrl() {
        return mPageUrl;
    }

    public void setPageUrl(String pageUrl) {
        mPageUrl = pageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
