package com.example.wangning.alipay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.wangning.R;

import java.util.Map;

/**
 * Created by Administrator on 2018/5/8.
 */
public class AlipayActivity extends Activity {
    private Activity mContext;

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2018050402634068";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCFZUe4vnYDf566ZjEaH9j/10/g9tgF4LXbOQSr0h+6Dp+ZvZZIb1l6fUJeQBAWFl1NF8dja6HhiTcuI4EHaCA+CmA+1PQh6pc825/2VqtKeaMQwAfK/OUES4gqHArW0BingmNy/IHS41N7c0rLHh1im677aJ/uLEIeJsDIFk3IKcYGhE03LN84SpgjvAO9VdVzuMmoH/zpsbCdrk03XkiyAbc/dOf98mCOXTYzDoZ4V0oTASCnb3C498NHMuKkMHvwxXZgbMnDgw0g0ytjvwCeulQeTz/iCL0d2yu2VeBWf+IBZHR8LP0jpG7tH00+YGEiRMwhq9PUdWn7ukBlwwXvAgMBAAECggEALhWERqW9/Y1sV7cQRZc1D4Xh+bUtgjJUgUzceUOZZFzC9IkTzyhwwClf8VGufQLy7rxFTOqVPWZ7IrxvShDF+sj44RwOz1zNLOAvDhp5CLynAhVlW9IsiGRTwUB42ieALPaVxtbHPwT8BU0aTLu1qShhtkc54T13BhWbSkSyUHDlBrTdVz/B09p13+TFezh5zUtGU7HoxrTIuAuE7jq7OP9oN6dfwlIt0UW7/jpKgOVEzR/p75KaxYvf/2JYlvx3D6AMzjMGe9Uco8pTgzxbxCZr82LeGw082Ifsy04HdK9Nhp8krZ8BfuyNSC7N8hTrthxq0mQCiWBThGmMsMZhAQKBgQDVzCDlIKllQrrjs+n0zoDhfBbnTLZsU31lClOPcnmXHMBKmsdv0GWvWX2t5YFZn/aaw9j4F6Paf0dqTS6SZnbFYp8KbPLz5jYqDNF2tnDZpyDywlwySPrkTxzQaa8E9FypuWBvgt7I7iv0RANXr3hhoktwSZJPqtQZEsgi62vJbwKBgQCfujERUT2ZSKcUTd6vFZHhTVxOQmYWluM+DfD/zeuKYo88drscuyKzFvXK8kR5htLMNIspQdm9O4ZKfVH7IVJj0k6UOTgLRMZIun16rWt4lgp4kw+4zuTNzv6zXc5vE9zMR0jUeEZD4YzUSyXRQMP/yD8W3N+DOENJ9Yp5iQFLgQKBgBgoKWtaRmmfoZhK4tt7Xcqch8X4v//7awa4pPpnV9/AKuYDGRfEN0kZ1n6ImRX0YhvYlzJpEWWFzh7vu5xlIXRqEUhPSSBP2bzkl+JO+O9O2AU1+s7fuiGD4uCVoGXx6mpu/Lt80QlpB+RgrvQAapy1hsqYNYT062Qt4Bk5+oLXAoGAOoMyxm1Ea4q56i48jVcRgb+dOO4KB+xugqUIzCzbRG6mmjfEg9np9EFM+XOWsg452qTE9fAxm6jaoR8XkS+7xuWb86Cibz5ahPoMTYyJ+jq8yz7UW5cvKlXR6FNM9FQKZqsWev80fll6Bashx246XtE0i2OOXbuWqtlQSQVvfAECgYEAx8/1iXDkdXT7dSxLRsXM6his6GEnOmZQA9/qwZr6tgtpQTl6p0VLrk3NeOxqk3MKIwlu1/7iDkFZ2CgWSZ/kzxAPmIMeMYBv8vZPlDpOKmvPya2J3vUwB/mA1yzgO8fyuPydKb6enSMKqpiYxUDozg9zzkC8Fg70jdk/Lgg05W8=";
    public static final String RSA_PRIVATE = "";


    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);
        mContext = this;
        Button btn_pay = (Button) findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payV2(v);
            }
        });
    }

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(mContext,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(mContext,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(mContext);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
