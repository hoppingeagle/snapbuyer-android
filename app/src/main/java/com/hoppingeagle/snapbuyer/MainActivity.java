package com.hoppingeagle.snapbuyer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
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
import java.util.LinkedList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @ViewById(R.id.fling_container_id)
    SwipeFlingAdapterView mFlingContainer;

    @RestService
    AuctionClient mAuctionClient;

    AuctionArrayAdapter mAdapter;

    List<Auction> mAuctions;

    @AfterInject
    void afterInject() {
        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .build();
        ImageLoader.getInstance().init(config);
    }

    @Background
    void loadData() {
        Log.d(LOG_TAG, "Loading started.");
        mAuctions = mAuctionClient.getAuctions();
        Log.d(LOG_TAG, "Loading finished.");
        updateAdapter();
    }

    @UiThread
    void updateAdapter() {
        mAdapter.addAll(mAuctions);
        mAdapter.notifyDataSetChanged();
    }

    @AfterViews
    void afterViews() {
        mAuctions = new LinkedList<>();
        mAdapter = new AuctionArrayAdapter(this);

        mFlingContainer.setAdapter(mAdapter);
        mFlingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                mAdapter.pop();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(MainActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "Right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                loadData();
            }

            @Override
            public void onScroll(float v) {

            }
        });

        loadData();
    }
}
