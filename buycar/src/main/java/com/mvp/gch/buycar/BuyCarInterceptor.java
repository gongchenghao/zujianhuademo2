package com.mvp.gch.buycar;

import android.content.Context;
import android.support.annotation.MainThread;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * Created by gongchenghao on 2019/10/28 0028.
 * describe:
 */
@Interceptor(priority = 1, name = "购物车组件拦截器")
public class BuyCarInterceptor implements IInterceptor
{
    public static boolean isRegister;
    Context context;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback)
    {
        if (isRegister) {
            callback.onContinue(postcard);
        } else if (postcard.getPath().contains("buycar/")) {
            Log.i("test111","购物车组件已卸载");
        }
    }

    @Override
    public void init(Context context)
    {
        this.context = context;
    }
}
