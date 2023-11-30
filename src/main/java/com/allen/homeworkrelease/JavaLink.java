package com.allen.homeworkrelease;

import com.alibaba.fastjson.JSON;
import com.aliyun.alink.dm.api.DeviceInfo;

import com.aliyun.alink.dm.api.IMqttClient;
import com.aliyun.alink.dm.api.InitResult;
import com.aliyun.alink.linkkit.api.*;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.MqttConfigure;

import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttSubscribeRequest;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.allen.homeworkrelease.pojo.Homework;
import com.allen.homeworkrelease.pojo.Payload;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class JavaLink {
    //public static String productKey = "k0f62rWdZFV";
    //public static String deviceName = "client";
    //public static String deviceSecret = "8353d48c970d757799424e4832abd7c1";
    public static String productKey = "k0f62rWdZFV";
    public static String deviceName = "server";
    public static String deviceSecret = "3dac22ba3e4e4f3860c670ca7025ed67";

    /**
     * 初始化连接
     */
    public static void initLink(){

        LinkKitInitParams params = new LinkKitInitParams();
        final String TAG = "HelloWorld";

        /**
         * step 1: 设置MQTT初始化参数
         */
        IoTMqttClientConfig config = new IoTMqttClientConfig();
        MqttConfigure.mqttHost = "iot-060a88dy.mqtt.iothub.aliyuncs.com:443";

        /*
         *是否接受离线消息
         *对应MQTT的cleanSession字段
         */
        config.receiveOfflineMsg = false;
        params.mqttClientConfig = config;

        /**
         * step 2: 设置初始化设备认证信息
         */
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.productKey = productKey;
        deviceInfo.deviceName = deviceName;
        deviceInfo.deviceSecret = deviceSecret;
        params.deviceInfo = deviceInfo;

        /**
         * step 3: 设置设备的username, token和clientId
         * 仅用于一型一密免预注册
         * 默认关闭
         */
        // MqttConfigure.deviceToken="${YourDeviceToken}";
        // MqttConfigure.clientId="${YourClientId}";
        LinkKit.getInstance().init(params, new ILinkKitConnectListener() {
            public void onError(AError aError) {
                ALog.e(TAG, "Init Error error= "+aError);
            }
            public void onInitDone(InitResult initResult) {
                ALog.i(TAG, "onInitDone result=" + initResult);
            }
        });

        IConnectNotifyListener notifyListener = new IConnectNotifyListener() {
            @Override
            public void onNotify(String connectId, String topic, AMessage aMessage) {
                // 物联网平台下行数据回调，包括connectId、连接类型、下行Topic和aMessage下行数据
                //String pushData = new String((byte[]) aMessage.data);
                // pushData示例  {"method":"thing.service.test_service","id":"123374967","params":{"vv":60},"version":"1.0.0"}
                // 上一行代码表示method服务类型以及params下推数据内容
                log.info(aMessage.data.toString());
            }
            @Override
            public boolean shouldHandle(String connectId, String topic) {
                // 选择是否不处理某个Topic的下行数据
                // 如果不处理某个Topic，则onNotify不会收到对应Topic的下行数据
                return true; //TODO 根据实际情况，编写要处理的监听逻辑。
            }
            @Override
            public void onConnectStateChange(String connectId, ConnectState connectState) {
                // 对应连接类型的连接状态变化回调，具体连接状态参考SDK ConnectState
                //当SDK因网络波动断开连接时，会自动尝试重连，重试的间隔是1s、2s、4s、8s...128s...128s, 到了最大间隔128s后，会一直以128s为间隔重连直到连云成功。
            }
        };
        // 注册下行监听，包括长连接的状态和下行的数据
        LinkKit.getInstance().registerOnNotifyListener(notifyListener);

        // 订阅
        MqttSubscribeRequest subscribeRequest = new MqttSubscribeRequest();
        // subTopic 替换成您需要订阅的 topic
        subscribeRequest.topic = "/k0f62rWdZFV/sever/user/homework";
        subscribeRequest.isSubscribe = true;
        LinkKit.getInstance().subscribe(subscribeRequest, new IConnectSubscribeListener() {
            @Override
            public void onSuccess() {
                // 订阅成功
            }
            @Override
            public void onFailure(AError aError) {
                // 订阅失败
            }
        });

    }

    public static void release(Object o){
        System.out.println("release");
        // 发布
        MqttPublishRequest request = new MqttPublishRequest();
        // 设置是否需要应答。
        request.isRPC = false;
        // 设置topic，设备通过该Topic向物联网平台发送消息。
        request.topic = "/k0f62rWdZFV/sever/user/homework";
        // 设置qos
        request.qos = 0;
        Payload payload = new Payload("M750", (List<Homework>) o);
        String jsonString = JSON.toJSONString(payload);
        System.out.println(jsonString);
        request.payloadObj = jsonString;
        IMqttClient mqttClient = LinkKit.getInstance().getMqttClient();
        mqttClient.publish(request, new IConnectSendListener() {
            @Override
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                // 消息成功提交给操作系统的发送缓冲区。
                // 在网络波动等异常情况下，消息可能无法到达云端。
                // 如果上行的消息有对应的下行的reply, 建议通过reply报文来确认上行消息的到达。
                System.out.println("response");
                System.out.println(aResponse.getData());
            }
            @Override
            public void onFailure(ARequest aRequest, AError aError) {
                // 发布失败
                System.out.println("error");
                System.out.println(aError.toString());
            }
        });
    }

}
