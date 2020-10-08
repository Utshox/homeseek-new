package com.zanjou.http.response;


public interface ResponseListener {

    void onResponse(int httpCode, String content);
}
