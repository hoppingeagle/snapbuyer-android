package com.hoppingeagle.snapbuyer;

import org.androidannotations.annotations.EBean;

import java.util.HashMap;

@EBean(scope = EBean.Scope.Singleton)
public class CategoryBean {

    private HashMap<Long, Long> mPreferencesMap = new HashMap<>();

    public void like(Auction auction) {
        Long val = mPreferencesMap.get(auction.getCategoryId());
        if (val == null) {
            val = 0L;
        }
        mPreferencesMap.put(auction.getCategoryId(), val + 1);
    }

    public void dislike(Auction auction) {
        Long val = mPreferencesMap.get(auction.getCategoryId());
        if (val == null) {
            val = 0L;
        }
        mPreferencesMap.put(auction.getCategoryId(), val - 1);
    }

    public CategoryPreferences toPreferences() {
        CategoryPreferences prefs = new CategoryPreferences();
        prefs.setPreferences(mPreferencesMap);
        return prefs;
    }

}
