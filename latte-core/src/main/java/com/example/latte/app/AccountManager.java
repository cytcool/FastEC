package com.example.latte.app;

import com.example.latte.utils.storage.LattePerference;

/**
 * Created by CYT on 2018/4/14.
 */

public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state){
        LattePerference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    public static boolean isSignIn(){
        return LattePerference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }
}
