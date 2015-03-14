package com.hoppingeagle.snapbuyer;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Auction implements Serializable, SliderItem {
    private long mId;

    @JsonProperty("image_url")
    private String mImageUrl;

    @JsonProperty("offer_url")
    private String mPageUrl;
    private String mName;

    @JsonProperty("category_id")
    private long mCategoryId;

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

    public long getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(long categoryId) {
        mCategoryId = categoryId;
    }

    @Override
    public int getLayoutId() {
        return R.layout.auction_card_layout;
    }

    @Override
    public void handleView(View view, final Context context) {
        ImageView imageView = (ImageView) view.findViewById(R.id.auction_image_id);

        if (getImageUrl() != null) {
            Picasso.with(context).load(getImageUrl()).into(imageView);
        } else {
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.no_photo));
        }

        TextView name = (TextView) view.findViewById(R.id.auction_name_id);
        name.setText(getName());

        TextView buy = (TextView) view.findViewById(R.id.buy_button_id);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.openAuction(Auction.this, context);
            }
        });

    }
}
