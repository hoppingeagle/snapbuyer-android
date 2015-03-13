package com.hoppingeagle.snapbuyer;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import java.util.List;


@Rest(rootUrl = "http://api.snapbuyer.sparklab.pl", converters = {MappingJacksonHttpMessageConverter.class})
public interface AuctionClient {

    @Get("/allegro/list")
    List<Auction> getAuctions();

}