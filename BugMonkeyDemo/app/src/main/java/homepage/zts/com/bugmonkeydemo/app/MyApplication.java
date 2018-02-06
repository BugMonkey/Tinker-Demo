package homepage.zts.com.bugmonkeydemo.app;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by BugMonkey on 2018/2/1.
 */

public class MyApplication extends TinkerApplication {


    public  MyApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "homepage.zts.com.bugmonkeydemo.app.MyApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
    }
}
