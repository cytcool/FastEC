package com.example.latte.net;

import com.example.latte.net.Callback.IError;
import com.example.latte.net.Callback.IFailure;
import com.example.latte.net.Callback.IRequest;
import com.example.latte.net.Callback.ISuccess;
import com.example.latte.net.Callback.RequestCallback;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by CYT on 2018/4/4.
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;

    public RestClient(String url,
                      Map<String, Object> PARAMS,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body) {
        this.URL = url;
        PARAMS.putAll(PARAMS);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST!=null){
            REQUEST.onRequestStart();
        }

        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                break;
            case PUT_RAW:
                break;
            case POST_RAW:
                break;
            default:
                break;
        }
        if (call!=null){
            call.enqueue(getRequestCallback());
        }
    }
    private Callback<String> getRequestCallback(){
        return new RequestCallback(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR
        );
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        request(HttpMethod.POST);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
