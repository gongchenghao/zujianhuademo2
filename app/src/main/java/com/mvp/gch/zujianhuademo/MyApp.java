package com.mvp.gch.zujianhuademo;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mvp.gch.base.BaseApp;
import com.mvp.gch.base.CodeUtils;
import com.mvp.gch.base.IComponentApplication;

/**
 * Created by gongchenghao on 2019/10/15 0015.
 *
 */
public class MyApp extends BaseApp
{
    private static final String[] MODULESLIST = {"com.mvp.gch.buycar.Buycar_MyApp"};

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i("test111","MyApp_onCreate()");
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (isDebug())
        {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        //Module类的APP初始化
        modulesApplicationInit();
    }

    private void modulesApplicationInit()
    {
        for (String moduleImpl : MODULESLIST){
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof IComponentApplication){
                    Log.i("test111","实现了IComponentApplication");
                    if (obj.getClass().getName().contains("buycar"))
                    {
                        ((IComponentApplication) obj).init(true);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isDebug()
    {
        return BuildConfig.DEBUG;
    }
}
