package com.huihui.signalprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by gavin
 * Time 2017/9/25  14:41
 * Email:molu_clown@163.com
 */

public class ProcessService extends Service {

    private static final String TAG="ProcessService";
    int i=0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Wathcer wathcer=new Wathcer();
        wathcer.createWatcher(Process.myUid());

        Timer timer=new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                Log.e(TAG,"父进程："+(++i));


            }
        }, 0, 3000);
    }
}
