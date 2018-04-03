package com.example.latte.app;

/**
 * Created by CYT on 2018/4/3.
 */

/*
 * ConfigType
 * 整个程序中唯一的单例
 * 只能被初始化一次
 * 多线程操作（线程安全的懒汉模式）
 */

public enum ConfigType {
    API_HOST,//配置网络请求的域名
    APPLICATION_CONTEXT,//全局的上下文
    CONFIG_READY,
    ICON
}
