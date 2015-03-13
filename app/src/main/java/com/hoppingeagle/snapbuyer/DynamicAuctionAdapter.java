package com.hoppingeagle.snapbuyer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.LinkedList;
import java.util.List;

public class DynamicAuctionAdapter extends FragmentPagerAdapter {
    private LinkedList<Auction> mAuctionList = new LinkedList<>();

    public DynamicAuctionAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return mAuctionList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return AuctionFragment.newInstance(mAuctionList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    public void setNewData(List<Auction> data) {
        mAuctionList.clear();
        mAuctionList.addAll(data);
        notifyDataSetChanged();
    }

}

