package com.mvp.gch.buycar;

import android.app.Application;
import android.util.Log;

import com.mvp.gch.base.IComponentApplication;

/**
 * Created by gongchenghao on 2019/10/28 0028.
 * describe:
 */
public class Buycar_MyApp extends Application implements IComponentApplication
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i("test111","Buycar_MyApp");
//        init(false);
    }

    @Override
    public void init(Boolean isRegister)
    {
        Log.i("test111","Buycar_MyApp中的init()方法调用成功");
        BuyCarInterceptor.isRegister = isRegister; //表示弃用购物车组件
    }
}
