package com.example.cyt.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.latte.delegates.LatteDelegate;

/**
 * Created by CYT on 2018/4/3.
 */

public class ExampleDelegate extends LatteDelegate{

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
