package com.example.orangemodule.communication;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by 杨健 on 2017/6/16.
 * function: USB通信类
 */

public class UsbCommunication {

    private static final String TAG = UsbCommunication.class.getSimpleName();
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";

    private HashMap<String, UsbDevice> deviceList;  //设备列表
    private UsbManager usbManager;  //USB管理器:负责管理USB设备的类
    private UsbDevice usbDevice;   //找到的USB设备
    private UsbInterface usbInterface;  //代表USB设备的一个接口
    private UsbDeviceConnection deviceConnection;  //USB连接的一个类。用此连接可以向USB设备发送和接收数据，这里我们使用这个类下面的块传输方式
    private UsbEndpoint usbEpIn;  //代表一个接口的某个节点的类:读数据节点
    private UsbEndpoint usbEpOut;  //代表一个接口的某个节点的类:写数据节点
    private PendingIntent intent; //意图

    public static UsbCommunication instance;

    //利用单例模式获取对象
    public static UsbCommunication getInstance(Context context) {
        if (null == instance) {
            synchronized (UsbCommunication.class) {
                if (null == instance) {
                    instance = new UsbCommunication(context);
                }
            }
        }
        return instance;
    }

    public UsbCommunication(Context context) {
        intent = PendingIntent.getBroadcast(context,0,new Intent(ACTION_USB_PERMISSION),0);
        context.registerReceiver(broadcastReceiver, new IntentFilter(ACTION_USB_PERMISSION));

        initUsbDevice(context);
    }


    //初始化设备
    private void initUsbDevice(Context context) {
        usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        deviceList = usbManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            //找到指定的设备
            if (device.getVendorId() == 2588 && device.getProductId() == 9030) {
                usbDevice = device;
                Log.e(TAG, "找到设备");
            }
        }
        findInterface();
    }

    //获取设备接口
    private void findInterface() {

        if (usbDevice == null) {
            Log.e(TAG, "没有找到设备");
            return;
        }

        for (int i = 0; i < usbDevice.getInterfaceCount(); i++) {
            //一个设备上面一般只有一个接口，有两个端点，分别接受和发送数据
            UsbInterface uInterface = usbDevice.getInterface(i);
            usbInterface = uInterface;
            Log.e(TAG, usbInterface.toString());
            break;
        }

        getEndpoint(usbInterface);

        if (usbInterface != null) {
            UsbDeviceConnection connection = null;
            //判断是否有权限
            if (usbManager.hasPermission(usbDevice)) {
                Log.e(TAG, "已经获得权限");
                connection = usbManager.openDevice(usbDevice);
                Log.e(TAG, connection == null ? "true" : "false");
                if (connection == null) {
                    Log.e(TAG, "设备连接为空");
                    return;
                }
                if (connection != null && connection.claimInterface(usbInterface, true)) {
                    deviceConnection = connection;
                    Log.e(TAG, deviceConnection == null ? "true" : "false");
                } else {
                    connection.close();
                }

            } else {
                Log.e(TAG, "正在获取权限...");
                usbManager.requestPermission(usbDevice, intent);
                if (usbManager.hasPermission(usbDevice)) {
                    Log.e(TAG, "获取权限");
                } else {
                    Log.e(TAG, "没有权限");
                }

            }
        } else {
            Log.e(TAG, "没有找到接口");
        }
    }

    //获取端点
    private void getEndpoint (UsbInterface usbInterface) {
        for (int i = 0; i < usbInterface.getEndpointCount(); i++) {
            UsbEndpoint ep = usbInterface.getEndpoint(i);
            if (ep.getType() == UsbConstants.USB_ENDPOINT_XFER_BULK) {
                if (ep.getDirection() == UsbConstants.USB_DIR_OUT) {
                    usbEpOut = ep;
                    Log.e(TAG, "获取发送数据的端点");
                } else {
                    usbEpIn = ep;
                    Log.e(TAG, "获取接受数据的端点");
                }
            }
        }
    }

    public int sendMessage(byte[] bytes) {
        int result = deviceConnection.bulkTransfer(usbEpOut, bytes, bytes.length, 3000);
        Log.e(TAG, result + "----------------------");
        return result;
    }

    public byte[] receiveMessage() {
        byte[] bytes = new byte[512];
        int result = deviceConnection.bulkTransfer(usbEpIn, bytes, bytes.length, 3000);
        if (result > 0) {
            return bytes;
        } else {
            return null;
        }
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, intent.getAction());
            if (intent.getAction().equals(ACTION_USB_PERMISSION)) {
                boolean granted = intent.getExtras().getBoolean(UsbManager.EXTRA_PERMISSION_GRANTED);
                Log.e("granted", granted + "");
            }
        }
    };

    public UsbDeviceConnection getDeviceConnection() {
        return deviceConnection;
    }

    public UsbEndpoint getUsbEpIn() {
        return usbEpIn;
    }

    public UsbEndpoint getUsbEpOut() {
        return usbEpOut;
    }
}
