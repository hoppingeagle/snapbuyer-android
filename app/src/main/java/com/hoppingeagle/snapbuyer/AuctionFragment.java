package com.hoppingeagle.snapbuyer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

public class AuctionFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;

//    @ViewById(R.id.af_image_id)
    ImageView mImageView;

    public static AuctionFragment newInstance(int page, String title) {
        AuctionFragment fragmentFirst = new AuctionFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.auction_fragment, container, false);
        mImageView = (ImageView) view.findViewById(R.id.af_image_id);

        mImageView.setImageDrawable(getResources().getDrawable(R.drawable.placeholder1));
        return view;
    }
}
