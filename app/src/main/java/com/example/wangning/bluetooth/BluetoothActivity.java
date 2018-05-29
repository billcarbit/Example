package com.example.wangning.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangning.R;
import com.example.wangning.swiperefreshlayout.Constant;

import java.util.Set;

/**
 * Created by Administrator on 2018/5/23.
 */
public class BluetoothActivity extends Activity {

    private TextView mTextView;
    private BluetoothAdapter mBluetoothAdapter;
    private BroadcastReceiver mReceiver = new BleSearchReceiver();
    private final static int CODE_REQ = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        if (checkBluetoothExist()) {
            Toast.makeText(this, "当前设备不支持蓝牙", Toast.LENGTH_SHORT).show();
            return;
        }

        mTextView = (TextView) findViewById(R.id.tvDevices);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        checkBltOpen();
        registerBltReceiver();
    }

    private void registerBltReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);  // 注册搜索完时的receiver
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND); // 注册用以接收到已搜索到的蓝牙设备的receiver
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(mReceiver, intentFilter);

    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //解除注册
        unregisterReceiver(mReceiver);
    }


    public void onClick_Search(View v) {
        // 如果正在搜索，就先取消搜索
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
        // 开始搜索蓝牙设备,搜索到的蓝牙设备通过广播返回
        mBluetoothAdapter.startDiscovery();
    }


    /**
     * 检查蓝牙是否打开
     *
     * @return
     */
    private void checkBltOpen() {
        // 判断是否打开蓝牙
        if (!mBluetoothAdapter.isEnabled()) {
            //弹出对话框提示用户是后打开
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, CODE_REQ);
        } else {
            // 不做提示，强行打开
            mBluetoothAdapter.enable();
        }
    }

    /**
     * 检查是否支持蓝牙
     *
     * @return
     */
    private boolean checkBluetoothExist() {
        BluetoothAdapter bltAdapter = BluetoothAdapter.getDefaultAdapter();
        return bltAdapter == null || !getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }

    public class BleSearchReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                mTextView.append("正在搜索..." + "\n");
            } else if (action.equals(BluetoothDevice.ACTION_FOUND)) {// 获得已经搜索到的蓝牙设备
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mTextView.append(device.getName() + ":"
                        + device.getAddress() + "\n");
          /*      // 搜索到的不是已经绑定的蓝牙设备
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    // 显示在TextView上
                    mTextView.append(device.getName() + ":"
                            + device.getAddress()+"\n");
                }*/
                // 搜索完成
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                mTextView.append("搜索完成" + "\n");
            }
        }
    }
}
