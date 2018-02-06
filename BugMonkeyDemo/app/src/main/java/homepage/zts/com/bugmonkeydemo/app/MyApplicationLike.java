package homepage.zts.com.bugmonkeydemo.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> parent of e95047b... Revert "logger引入和网络框架搭建"
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
=======
>>>>>>> parent of 5c7fbe4... logger引入和网络框架搭建
=======
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
>>>>>>> parent of e95047b... Revert "logger引入和网络框架搭建"
=======
>>>>>>> parent of 5c7fbe4... logger引入和网络框架搭建
import com.tencent.tinker.lib.tinker.TinkerInstaller;
>>>>>>> parent of e95047b... Revert "logger引入和网络框架搭建"
import com.tencent.tinker.loader.app.DefaultApplicationLike;

/**
 * Created by BugMonkey on 2018/2/2.
 */

public class MyApplicationLike extends DefaultApplicationLike {
    public static final String TAG = "MyApplicationLike";

    public MyApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        //Bugly.init(getApplication(), "900029763", false);
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        //MultiDex.install(base);

        // 安装tinker
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
         TinkerManager.installTinker(this);
=======
         TinkerInstaller.install(this);
>>>>>>> parent of 5c7fbe4... logger引入和网络框架搭建
=======
         TinkerInstaller.install(this);
>>>>>>> parent of 5c7fbe4... logger引入和网络框架搭建
       // Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
<<<<<<< HEAD
<<<<<<< HEAD
=======
        TinkerInstaller.install(this);
        // Beta.installTinker(this);
>>>>>>> parent of e95047b... Revert "logger引入和网络框架搭建"
=======
        TinkerInstaller.install(this);
        // Beta.installTinker(this);
>>>>>>> parent of e95047b... Revert "logger引入和网络框架搭建"
=======
>>>>>>> parent of 5c7fbe4... logger引入和网络框架搭建
=======
        TinkerInstaller.install(this);
        // Beta.installTinker(this);
>>>>>>> parent of e95047b... Revert "logger引入和网络框架搭建"
=======
>>>>>>> parent of 5c7fbe4... logger引入和网络框架搭建
    }




}
