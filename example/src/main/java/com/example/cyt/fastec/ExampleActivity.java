package com.example.cyt.fastec;

import android.support.v4.app.SupportActivity;

import com.example.latte.activities.ProxyActivity;
import com.example.latte.delegates.LatteDelegate;

/**
 * Created by CYT on 2018/4/3.
 */

public class ExampleActivity extends ProxyActivity{
    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
