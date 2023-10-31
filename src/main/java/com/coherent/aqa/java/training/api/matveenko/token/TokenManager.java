package com.coherent.aqa.java.training.api.matveenko.token;

import com.coherent.aqa.java.training.api.matveenko.base.BasicHttpClient;
import com.coherent.aqa.java.training.api.matveenko.config.TestProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TokenManager {

    private TokenResponse writeToken;
    private TokenResponse readToken;

    private static final String URL = TestProperties.get("url");

    public TokenManager(){
        this.writeToken = new TokenResponse();
        this.readToken = new TokenResponse();
    }


    public TokenResponse getWriteToken() throws IOException{
        BasicHttpClient httpClient = BasicHttpClient.getInstance();

        Map<String, String> map = new HashMap<>();
        map.put("grant_type", "client_credentials");
        map.put("scope", "write");


        CloseableHttpResponse response = httpClient.executePostRequest(URL, map);


        HttpEntity entity = response.getEntity();
        String responseBody = EntityUtils.toString(entity);

        ObjectMapper objectMapper = ModuleConfig.getObjectMapper();
        TokenResponse writeToken = objectMapper.readValue(responseBody, TokenResponse.class);

        return writeToken;
    }

    public TokenResponse getReadToken() throws IOException{
        BasicHttpClient httpClient = BasicHttpClient.getInstance();

        Map<String, String> map = new HashMap<>();
        map.put("grant_type", "client_credentials");
        map.put("scope", "read");


        CloseableHttpResponse response = httpClient.executePostRequest(URL, map);


        HttpEntity entity = response.getEntity();
        String responseBody = EntityUtils.toString(entity);

        ObjectMapper objectMapper = ModuleConfig.getObjectMapper();
        TokenResponse readToken = objectMapper.readValue(responseBody, TokenResponse.class);

        return readToken;
    }

}