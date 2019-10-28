package com.mvp.gch.base;

import android.app.Application;

/**
 * Created by gongchenghao on 2019/10/28 0028.
 * describe:所有组件的MyApp类都继承这个类,给所有组件提供全局上下文
 */
public class BaseApp extends Application
{
    private static BaseApp baseApp;

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    public static synchronized BaseApp getInstance()
    {
        if (baseApp == null)
        {
            baseApp = new BaseApp();
        }
        return baseApp;
    }
}
