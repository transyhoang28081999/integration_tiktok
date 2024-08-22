package com.monte.integration.tiktok.processor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monte.integration.tiktok.commons.Common;

import java.util.*;

public class GetAccessTokenResponseProcessor implements Processor {
    protected final static Logger logger = LoggerFactory.getLogger(GetAccessTokenResponseProcessor.class);

    public void process(Exchange exchange) throws Exception {
        Message response = exchange.getIn();
        String responseBody = response.getBody(String.class);
        JsonObject jobj = new Gson().fromJson(responseBody, JsonObject.class);

        Common common = new Common();

        Map<String, Object> paramsMap = common.convertJsonToMap(jobj);

        exchange.getOut().setBody(paramsMap);
    }
}