package com.hoppingeagle.snapbuyer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    @ViewById(R.id.view_pager_id)
    ViewPager mViewPager;

    @AfterInject
    void afterInject() {
        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .build();
        ImageLoader.getInstance().init(config);
    }

    @AfterViews
    void afterViews() {
        DynamicAuctionAdapter pagerAdapter = new DynamicAuctionAdapter(getSupportFragmentManager());
        List<Auction> auctionsMock = new ArrayList<>(4);
        {
            Auction auction = new Auction();
            auction.setId(1);
            auction.setAuctionUrl("http://img16.allegroimg.pl/photos/oryginal/50/93/09/85/5093098540");
            auctionsMock.add(auction);
        }
        {
            Auction auction = new Auction();
            auction.setId(2);
            auction.setAuctionUrl("http://img09.allegroimg.pl/photos/oryginal/50/98/00/72/5098007215");
            auctionsMock.add(auction);
        }
        {
            Auction auction = new Auction();
            auction.setId(3);
            auction.setAuctionUrl("http://img03.allegroimg.pl/photos/oryginal/51/63/32/64/5163326440");
            auctionsMock.add(auction);
        }
        pagerAdapter.setNewData(auctionsMock);
        mViewPager.setAdapter(pagerAdapter);
    }
}
