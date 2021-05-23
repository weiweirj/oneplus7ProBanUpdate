package com.oneplus7Pro.banUpdate;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Main implements IXposedHookLoadPackage{
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.oneplus.opbackup")){
            XposedHelpers.findAndHookMethod(loadPackageParam.classLoader.loadClass("org.json.JSONObject"), "getString",String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    if (param.args[0]=="new_version")
                        param.setResult("");
                    super.afterHookedMethod(param);
                }
            });
        }
    }
}
