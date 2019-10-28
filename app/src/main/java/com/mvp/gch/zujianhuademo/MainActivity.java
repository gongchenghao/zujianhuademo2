package com.mvp.gch.zujianhuademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mvp.gch.base.RxBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.tv_buy_for_resule)
    TextView tvBuyForResule;
    @BindView(R.id.tv_buy_data)
    TextView tvBuyData;

    private int BUY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvBuy.setOnClickListener(this);
        tvBuyForResule.setOnClickListener(this);
        tvBuyData.setOnClickListener(this);
        initRxbus();
    }

    private void initRxbus()
    {
        RxBus.get().toFlowable(String.class)
                .map(new Function<Object, String>() {
                    @Override
                    public String apply(@io.reactivex.annotations.NonNull Object o) throws Exception {
                        return (String) o;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull String string) throws Exception {
                        Log.i("test111","MainActivity_收到的消息:"+string);
                    }
                });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.tv_buy: //跳转到购物车模块
                toBuy();
                break;
            case R.id.tv_buy_for_resule: //跳转到购物车模块,并带数据返回
                toBuyForResule();
                break;
            case R.id.tv_buy_data: //带参跳转到购物车模块
                toBuyWithData();
                break;

        }
    }

    //直接跳转
    private void toBuy()
    {
        ARouter.getInstance().build("/buycar/BuyCar_BuyCarActivity").navigation();
    }

    //startActivityForResult
    private void toBuyForResule()
    {
        ARouter.getInstance().build("/buycar/BuyCar_BuyCarActivity").navigation(this, BUY_REQUEST_CODE);
    }

    //传递参数
    private void toBuyWithData()
    {
        ARouter.getInstance().build("/buycar/BuyCar_BuyCarActivity")
                .withString("main","main")
                .navigation();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BUY_REQUEST_CODE && data != null) //从购物车模块返回的数据
        {
            String buy = data.getStringExtra("buy");
            if (TextUtils.isEmpty(buy) == false)
            {
                Toast.makeText(this, buy, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
