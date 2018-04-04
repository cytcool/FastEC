package com.example.latte.net;

import com.example.latte.net.Callback.IError;
import com.example.latte.net.Callback.IFailure;
import com.example.latte.net.Callback.IRequest;
import com.example.latte.net.Callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by CYT on 2018/4/4.
 */

public class RestClientBuilder {

    private  String mUrl;
    private  static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private  IRequest mIRequest;
    private  ISuccess mISuccess;
    private  IFailure mIFailure;
    private  IError mIError;
    private  RequestBody mBody;

    RestClientBuilder(){

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
       PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key,value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    private Map<String,Object> checkParams(){
        if (PARAMS == null){
            return new WeakHashMap<>();
        }
        return PARAMS;
    }

    public final RestClient build() {
        return new RestClient(mUrl,PARAMS,
                mIRequest, mISuccess, mIFailure,
                mIError, mBody);
    }
}
