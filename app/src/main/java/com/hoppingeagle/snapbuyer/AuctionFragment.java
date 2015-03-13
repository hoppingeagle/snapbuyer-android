package com.hoppingeagle.snapbuyer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class AuctionFragment extends Fragment {
    private static final String AUCTION_ARG = "auction";
    private Auction mAuction;

    ImageView mImageView;

    public static AuctionFragment newInstance(Auction auction) {
        AuctionFragment fragmentFirst = new AuctionFragment();
        Bundle args = new Bundle();
        args.putSerializable(AUCTION_ARG, auction);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuction = (Auction) getArguments().getSerializable(AUCTION_ARG);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.auction_fragment, container, false);
        mImageView = (ImageView) view.findViewById(R.id.af_image_id);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(mAuction.getAuctionUrl(), mImageView);

        return view;
    }
}
