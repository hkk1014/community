package com.hk.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hk.community.community.dto.AccessTokenDTO;
import com.hk.community.community.dto.GithubUser;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 作者: 何康先生
 * 时间: 2020-03-15 12:27
 * 描述:
 **/
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        String json = JSONObject.toJSONString(accessTokenDTO);
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token =string.split("&", 0)[0].split("=", 0)[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }


    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();

            GithubUser githubUser = JSON.parseObject(response.body().string(), GithubUser.class);
        return   githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }





}
