package com.oneplus7Pro.banUpdate;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Main implements IXposedHookLoadPackage{
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.oneplus.opbackup")) {
            XposedHelpers.findAndHookMethod(loadPackageParam.classLoader.loadClass("org.json.JSONObject"), "getString", String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    if (param.args[0] == "new_version" || param.args[0] == "down_url")
                        param.setResult("");
                    super.afterHookedMethod(param);
                }
            });
        }
        if (loadPackageParam.packageName.equals("com.android.settings")) {
            XposedHelpers.findAndHookMethod(loadPackageParam.classLoader.loadClass("com.android.settings.homepage.contextualcards.conditional.OpOtaConditionController"), "isDisplayable", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(false);
                }
            });
        }
    }
}
