package com.example.aplex.jnidemo;

public class JniNative {
    static {
        System.loadLibrary("mytcpaudio");
    }

    /**
     * C调用java空方法
     */
    public static void nullMethod() {
        System.out.println("hello from java");
    }
    /**
     * C调用java中的带两个int参数的方法
     *
     * @param x
     * @param y
     * @return
     */
    public static int Add(int x, int y) {
        int result = x + y;
        System.out.println("result in java " + result);
        return result;
    }
    /**
     * C调用java中参数为String的方法
     *
     * @param s
     */
    public static void printString(String s) {
        System.out.println("java " + s);
    }


    public static native void sayHelloToC(String hello);
    public static native String HelloFromC();

    public static native void callMethod1();
    public static native void callMethod2();
    public static native void callMethod3();

    //================
    /**初始化*/
    public static native int init(String ip,int port,String localName);

    /**资源释放*/
    public static native int destory();

    /**呼叫*/
    public static native int call(String farName);

    /**挂断*/
    public static native int hangUp(String farName);

    /**接听*/
    public static native int answer(String farName);

    /**拒绝*/
    public static native int refuse(String farName);

    /**
     * 消息回调
     * farName:远端用户名
     * type:远端请求指令
     * 4，正在通话
     * 5，请求通话
     * 6，拒绝通话
     * 7，同意通话
     * 8，挂断通话
     */
    public static void msgCallBack(String farName,int type) {
        System.out.println("msgCallBack farName-->"+farName+",type-->"+type);

    }

    /**
     * 语音数据回调
     * 功能：服务回调来的语音数据回调
     * farName:远端用户名
     * date:数据
     * len ：长度
     */
    public static void audioDataCallBack(String farName,byte[] amrframe,int len) {
        System.out.println("audioDataCallBack farName-->"+farName+",len-->"+len);

    }

    /**
     * 语音数据回调
     * 功能：发送语音数据给服务器
     * farName:远端用户
     * date:数据
     * len ：长度
     */
    public static void sendAudio(String farName,byte[] amrframe,int len) {
        System.out.println("audioDataCallBack farName-->"+farName+",len-->"+len);

    }



}
