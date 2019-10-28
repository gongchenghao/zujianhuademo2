package com.mvp.gch.buycar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mvp.gch.base.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/buycar/BuyCar_BuyCarActivity")
public class BuyCar_BuyCarActivity extends AppCompatActivity implements View.OnClickListener
{
    @Autowired
    String   main;


    @BindView(R2.id.tv_close)
    TextView tvClose;
    @BindView(R2.id.tv_test_r)
    TextView tvTestR;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_car__buy_car);
        ButterKnife.bind(this);

        tvClose.setOnClickListener(this);
        tvTestR.setText("hahahhahah");

//        getData();
        RxBus.get().post("这是从BuyCar_BuyCarActivity传递过来的消息");
    }


    private void getData()
    {
        //获取传参的方法一:
        String mainYi = getIntent().getStringExtra("main");
        Toast.makeText(this, mainYi, Toast.LENGTH_SHORT).show();

        //获取传参的方法二:
        ARouter.getInstance().inject(this);
        Toast.makeText(this, main, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.tv_close)
        {
            finishForresult();
        }
    }

    private void finishForresult()
    {
        Log.i("test111", "finishForresult()");
        Intent in = new Intent();
        in.putExtra("buy", "这是从购物车模块返回的数据");
        setResult(999, in);
        finish();
    }
}
