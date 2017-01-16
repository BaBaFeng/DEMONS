package babafeng.demons;


import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


/**
 * Created by Vii26 on 2017/1/13.
 */

public class XposedHook implements IXposedHookLoadPackage {
    String TAG = "BABAFENG2017: ";
    String app_pack_name = "babafeng.hooktest";


    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {

        if (!lpparam.packageName.equals(app_pack_name))
            return;

        XposedBridge.log(TAG + "Hooking: " + lpparam.packageName);

        findAndHookMethod("babafeng.hooktest.MainActivity",
                lpparam.classLoader,
                "text",
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        String text = (String)param.args[0];
                        XposedBridge.log("BABAFENG2017: " + text);
                        Log.d(TAG, text);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    }
                });



    }
}
