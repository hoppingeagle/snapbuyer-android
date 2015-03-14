package com.hoppingeagle.snapbuyer;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.MultiValueMap;

import java.util.List;


@Rest(rootUrl = "http://api.snapbuyer.sparklab.pl", converters = {MappingJacksonHttpMessageConverter.class})
public interface AuctionClient {

    @Get("/allegro/offers")
    List<Auction> getAuctions();

    @Post("/allegro/categories/preferences/store")
    void storePrefrences(CategoryPreferences preferences);

}