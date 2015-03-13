package com.hoppingeagle.snapbuyer;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NoTitle;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;
import org.androidannotations.annotations.rest.RestService;

import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    @ViewById(R.id.view_pager_id)
    ViewPager mViewPager;

    @RestService
    AuctionClient mAuctionClient;

    List<Auction> mAuctions;

    DynamicAuctionAdapter mPagerAdapter;

    @AfterInject
    void afterInject() {
        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .build();
        ImageLoader.getInstance().init(config);
    }

    @Background
    void loadData() {
        mAuctions = mAuctionClient.getAuctions();
        updateAdapter();
    }

    @UiThread
    void updateAdapter() {
        mPagerAdapter.setNewData(mAuctions);
        mViewPager.setAdapter(mPagerAdapter);
    }

    @AfterViews
    void afterViews() {
        mPagerAdapter = new DynamicAuctionAdapter(getSupportFragmentManager());
        loadData();
    }
}
