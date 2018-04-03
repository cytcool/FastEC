package com.example.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * 该类为配置文件存储和读取
 * Created by CYT on 2018/4/3.
 */

public class Configurator {

    //static final 修饰的类名字最好是大写加下划线组成
    private static final HashMap<String,Object> LATTE_CONFIGS = new HashMap<>();
    //封装iconify框架
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    //初始化
    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    //静态内部类实现单例模式，避免线程不安全问题
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure(){
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    //初始化字体图标
    private void initIcons(){
        if (ICONS.size()>0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size() ; i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    //检查配置是否完成
    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady){
            throw new RuntimeException("COnfiguration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

}
