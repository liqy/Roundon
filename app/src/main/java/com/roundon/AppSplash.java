package com.roundon;

import com.activeandroid.ActiveAndroid;
import com.orhanobut.logger.Logger;
import com.roundon.service.SplashService;
import com.roundon.util.SplashClient;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheContextUtils;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheLogUtils;


/**
 * Created by liqy on 15/12/17.
 */
public class AppSplash extends com.activeandroid.app.Application {

    private static SplashClient splashClient;
    public static final String YOUR_TAG = "com.roundon";

    @Override
    public void onCreate() {
        super.onCreate();

        splashClient = new SplashClient();

        Logger.init(YOUR_TAG)
                .hideThreadInfo()
                .methodOffset(2);

        ActiveAndroid.initialize(this);

        DualCacheLogUtils.enableLog();
        DualCacheContextUtils.setContext(getApplicationContext());

    }

    public static SplashService getSplashService() {
        return splashClient.getSplashService();
    }
}
