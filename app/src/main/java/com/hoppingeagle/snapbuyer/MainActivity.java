package com.hoppingeagle.snapbuyer;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {
    @ViewById(R.id.card_container)
    CardContainer mCardContainer;

    @RestService
    AuctionClient mAuctionClient;


    TextView mTextView;

    List<Auction> mAuctions;

    AuctionCardStackAdapter mAdapter;

    @AfterInject
    void afterInject() {
        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .build();
        ImageLoader.getInstance().init(config);
    }

    @Click(R.id.buy)
    void buy() {
        if (mAdapter.getCount() == 0) {
            return;
        }
        AuctionCard auctionCard = (AuctionCard) mAdapter.getItem(0);
        String pageUrl = auctionCard.getAuction().getPageUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl));
        startActivity(intent);
    }

    @Background
    void loadData() {
        mAuctions = mAuctionClient.getAuctions();
        updateAdapter();
    }

    @UiThread
    void updateAdapter() {
        for (final Auction auction: mAuctions) {
            final CardModel card = new AuctionCard(auction.getName(), "", auction.getPageUrl(), auction);
            card.setOnCardDimissedListener(new CardModel.OnCardDimissedListener() {
                @Override
                public void onLike() {
                    Toast.makeText(MainActivity.this, "Like: " + auction.getPageUrl(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDislike() {
                    Toast.makeText(MainActivity.this, "Dislike: " + auction.getPageUrl(), Toast.LENGTH_SHORT).show();
                }
            });
            mAdapter.add(card);
        }
        mCardContainer.setAdapter(mAdapter);
    }

    @AfterViews
    void afterViews() {
        mAdapter = new AuctionCardStackAdapter(this);

        loadData();
    }
}
