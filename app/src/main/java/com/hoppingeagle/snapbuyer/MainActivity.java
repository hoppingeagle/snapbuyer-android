package com.hoppingeagle.snapbuyer;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @ViewById(R.id.fling_container_id)
    SwipeFlingAdapterView mFlingContainer;

    @Bean
    CategoryBean mCategoryBean;

    @RestService
    AuctionClient mAuctionClient;

    @Pref
    StoredPreferences_ mPreference;


    private AuctionArrayAdapter mAdapter;

    private List<SliderItem> mItems;

    private AtomicBoolean mLoading = new AtomicBoolean(false);

    @Background
    void loadData() {
        if (mLoading.get()) {
            return;
        }
        mLoading.set(true);
        Log.d(LOG_TAG, "Loading started.");
        if (mPreference.firstTime().getOr(true)) {
            mPreference.firstTime().put(false);
            mItems = new LinkedList<>();
            mItems.add(new IntroItem(R.layout.fragment_onboarding_1));
            mItems.add(new IntroItem(R.layout.fragment_onboarding_2));
            mItems.add(new IntroItem(R.layout.fragment_onboarding_3));
            mItems.addAll(mAuctionClient.getAuctions());

        } else {
            mItems = new LinkedList<>();
            mItems.addAll(mAuctionClient.getPreferredAuctions());
        }

        CategoryPreferences prefs = mCategoryBean.toPreferences();
        mAuctionClient.storePrefrences(prefs);
        Log.d(LOG_TAG, "Loading finished.");
        updateAdapter();
    }

    @UiThread
    void updateAdapter() {
        mAdapter.addAll(mItems);
        mAdapter.notifyDataSetChanged();
        mLoading.set(false);
    }

    @AfterViews
    void afterViews() {
        mItems = new LinkedList<>();
        mAdapter = new AuctionArrayAdapter(this);

        mFlingContainer.setAdapter(mAdapter);
        mFlingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                mAdapter.pop();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                if (dataObject instanceof Auction) {
                    Auction auction = (Auction) dataObject;
                    mCategoryBean.dislike(auction);
                }
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                if (dataObject instanceof Auction) {
                    Auction auction = (Auction) dataObject;
                    mCategoryBean.like(auction);
                }
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

    @Click(R.id.like)
    void likeClick() {
        mFlingContainer.getTopCardListener().selectRight();
    }

    @Click(R.id.dislike)
    void dislikeClick() {
        mFlingContainer.getTopCardListener().selectLeft();
    }
}
