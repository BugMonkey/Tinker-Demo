package homepage.zts.com.bugmonkeydemo.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

<<<<<<< HEAD
=======
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
>>>>>>> parent of e95047b... Revert "logger引入和网络框架搭建"
import com.tencent.tinker.loader.app.DefaultApplicationLike;

import homepage.zts.com.bugmonkeydemo.BuildConfig;

/**
 * Created by BugMonkey on 2018/2/2.
 */

public class MyApplicationLike extends DefaultApplicationLike {
    public static final String TAG = "MyApplicationLike";
    private static Context instance;

    public MyApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
        instance = application;
        //log  配置
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Logger.e(activity.getLocalClassName() + "---------启动-----------");
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    public static Context getInstance() {

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        //MultiDex.install(base);

        // 安装tinker
<<<<<<< HEAD
         TinkerManager.installTinker(this);
       // Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
=======
        TinkerInstaller.install(this);
        // Beta.installTinker(this);
>>>>>>> parent of e95047b... Revert "logger引入和网络框架搭建"
    }


}
