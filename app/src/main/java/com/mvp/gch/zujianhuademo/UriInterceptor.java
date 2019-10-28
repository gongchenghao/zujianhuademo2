//package com.mvp.gch.zujianhuademo;
//
//import android.content.Context;
//import android.text.TextUtils;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.alibaba.android.arouter.facade.Postcard;
//import com.alibaba.android.arouter.facade.annotation.Interceptor;
//import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
//import com.alibaba.android.arouter.facade.template.IInterceptor;
//
///**
// * Created by gongchenghao on 2019/10/17 0017.
// * describe:
// */
//@Interceptor(priority = 4) //括号中的是优先级,
//public class UriInterceptor implements IInterceptor
//{
//    @Override
//    public void process(Postcard postcard, InterceptorCallback callback)
//    {
//        //检测所有跳转中的uri是否为空,为空则进行提示 这里的main是跳转到购物车模块时的参数名称
//        String main = postcard.getExtras().getString("main");
//        if (TextUtils.isEmpty(main))
//        {
//            Log.i("test111","跳转到购物车模块时的参数main为空");
//            callback.onInterrupt(null);
//        }else {
//            callback.onContinue(postcard);
//        }
//    }
//
//    @Override
//    public void init(Context context)
//    {
//
//    }
//}
