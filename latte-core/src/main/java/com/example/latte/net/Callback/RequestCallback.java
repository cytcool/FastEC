package com.example.latte.net.Callback;

import android.os.Handler;

import com.example.latte.ui.LatteLoader;
import com.example.latte.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by CYT on 2018/4/4.
 */

public class RequestCallback implements Callback<String>{
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADERSTYLE;
    private static final Handler HANDLER = new Handler();

    public RequestCallback(IRequest request, ISuccess success, IFailure failure, IError error,LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADERSTYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS!=null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if (ERROR!=null){
                ERROR.onError(response.code(),response.message());
            }
        }
        if (LOADERSTYLE!=null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();

                }
            },1000);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE!=null){
            FAILURE.onFailure();
        }

        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }
    }
}
