package com.monte.integration.tiktok.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class GetAccessTokenProcessor implements Processor {
    protected final static Logger logger = LoggerFactory.getLogger(GetAccessTokenProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Map inputMap = exchange.getIn().getBody(Map.class);
        String appKey = exchange.getContext().resolvePropertyPlaceholders("{{tiktok.graph_api.app_key}}");
        String appSecret = exchange.getContext().resolvePropertyPlaceholders("{{tiktok.graph_api.app_secret}}");

        String refreshToken = (String) inputMap.get("refreshToken");

        // TODO Auto-generated method stub
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("app_key",appKey));
        nvps.add(new BasicNameValuePair("app_secret",appSecret));
        nvps.add(new BasicNameValuePair("refresh_token",refreshToken));
        nvps.add(new BasicNameValuePair("grant_type", "refresh_token"));

        exchange.getOut().setHeader(Exchange.HTTP_METHOD, "GET");
        exchange.getOut().setHeader(Exchange.HTTP_URI, "https://auth.tiktok-shops.com/api/v2/token/refresh?"+ URLEncodedUtils.format(nvps,"UTF-8"));
    }
}