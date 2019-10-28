package com.mvp.gch.base;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 *
 * @ClassName:RxBus

 * @PackageName:com.wuxiaolong.androidmvpsample.rxbus

 * @Create On 2018/5/18   16:20

 * @author:gongchenghao

 * @Copyrights 2018/5/18 宫成浩 All rights reserved.
 */


//有背压处理（Backpressure）的 Rxbus

//RxBus的核心功能是基于Rxjava的，在RxJava中有个Subject类，它继承Observable类，
// 同时实现了Observer接口，因此Subject可以同时担当订阅者和被订阅者的角色，
// 这里我们使用Subject的子类PublishSubject来创建一个Subject对象
// （PublishSubject只有被订阅后才会把接收到的事件立刻发送给订阅者），
// 在需要接收事件的地方，订阅该Subject对象，之后如果Subject对象接收到事件，
// 则会发射给该订阅者，此时Subject对象充当被订阅者的角色。完成了订阅，
// 在需要发送事件的地方将事件发送给之前被订阅的Subject对象，则此时Subject对象做为订阅者接收事件，
// 然后会立刻将事件转发给订阅该Subject对象的订阅者，以便订阅者处理相应事件，
// 到这里就完成了事件的发送与处理。
// 最后就是取消订阅的操作了，Rxjava中，订阅操作会返回一个Subscription对象，
// 以便在合适的时机取消订阅，防止内存泄漏，如果一个类产生多个Subscription对象，
// 我们可以用一个CompositeSubscription存储起来，以进行批量的取消订阅。
public class RxBus {
    private final  FlowableProcessor<Object> mBus;
	private static ArrayList<String>         mArrayList = new ArrayList<>();

    private RxBus() {
        // toSerialized method made bus thread safe
        mBus = PublishProcessor.create().toSerialized();
    }

    public static RxBus get() {
        return Holder.BUS;
    }

    public void post(Object obj) {
        mBus.onNext(obj);
    }

    public <T> Flowable<T> toFlowable(Class<T> tClass) {
		mArrayList.add(tClass.toString());
        return mBus.ofType(tClass);
    }

    //用来判断rxbus是否重复注册，比如已经注册过DeleteGongGaoBean.class了，
	// 就不能再注册了，否则会出现发送一个消息，接收到好几个消息的情况
    public static boolean isAdded(Class tClass)
	{
		boolean isAdd = false;
		for (int i = 0; i <mArrayList.size() ; i++) {
			if (tClass.toString().equals(mArrayList.get(i)))
			{
				isAdd = true;
			}
		}
		return isAdd;
	}

    public Flowable<Object> toFlowable() {
        return mBus;
    }

	public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }

    private static class Holder {
        private static final RxBus BUS = new RxBus();
    }
}
