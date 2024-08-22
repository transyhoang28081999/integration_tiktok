package com.monte.integration.tiktok.router;

import com.monte.integration.tiktok.processor.GetAccessTokenProcessor;
import com.monte.integration.tiktok.processor.GetAccessTokenResponseProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetAccessTokenRoute extends RouteBuilder {
    protected final static Logger logger = LoggerFactory.getLogger(GetAccessTokenRoute.class);

    @Override
    public void configure() throws Exception {
        logger.info("Config GetAccessTokenRoute.......");
        from("moquiservice://gsources.integration.TiktokServices.getAccessToken")
                .process(new GetAccessTokenProcessor()) // might have to do inline code here to pass the token and field into the url
                .to("https4://auth.tiktok-shops.com/api/v2/token/refresh")
                .process(new GetAccessTokenResponseProcessor())
                .to("moquiservice://gsources.integration.TiktokServices.getResponseAccessToken"); //   log the result
    }
}