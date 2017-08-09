package com.ruaho.studyapp.other;

/**
 * 安卓服务环境的上下文
 * Created by jinyunyang on 15/3/5.
 */
public class ServiceContext {

    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";
    private static String socketServer = null;
    private static String _uuid = null;

    /**
     * 是否显示ip：不可更改，更改无效
     */
    public static boolean isDebug = true;
    private static String serverAddress = "picc.cochat.cn";

    static {
        if (serverAddress != null) {
            if (!serverAddress.startsWith(HTTP) && !serverAddress.startsWith(HTTPS)) {
                serverAddress = HTTP + serverAddress;
            }
        }
    }

    /**
     * @return 众信消息服务器地址。格式为：cochat.cn:81
     */
    public static String getSocketServer() {
        return socketServer;
    }

    /**
     * @param addr 服务器地址
     */
    public static void setSocketServer(String addr) {
        socketServer = addr;
    }

    /**
     * @return 众信Http服务器URL，url以"/"结尾。格式为：http://cochat.cn:81/
     */
    public static String getHttpServer() {
        if (!serverAddress.endsWith("/")) {
            return serverAddress + "/";
        } else {
            return serverAddress;
        }
    }

    /**
     * @return 设备的UUID
     */
    public static String getUUID() {
        return _uuid;
    }

    /**
     * @param uuid 设备的UUID
     */
    public static void setUUID(String uuid) {
        _uuid = uuid;
    }

}
