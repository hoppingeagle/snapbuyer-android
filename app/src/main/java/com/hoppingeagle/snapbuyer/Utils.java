package com.hoppingeagle.snapbuyer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public final class Utils {
    private Utils() {

    }

    public static void openAuction(Auction auction, Context context) {
        String pageUrl = auction.getPageUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl));
        context.startActivity(intent);
    }
}
