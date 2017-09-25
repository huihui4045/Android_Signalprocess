package com.huihui.signalprocess;

/**
 * Created by gavin
 * Time 2017/9/25  14:40
 * Email:molu_clown@163.com
 */

public class Wathcer {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public native void createWatcher(int userId);
}
