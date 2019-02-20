package com.example.cgz.bloodsoulnote2.install;


import android.util.Log;

import com.example.cgz.bloodsoulnote2.main.MainApplication;

import java.io.File;

public class AppInstallUtils {

    public static void installApp(final File file) {
        if (!isFileExists(file)) return;
        MainApplication.getInstance().startActivity(IntentUtils.getInstallAppIntent(file, true));
    }

    public static boolean installAppSilent(final String filePath) {
        return installAppSilent(getFileByPath(filePath), null);
    }

    public static boolean installAppSilent(final File file) {
        return installAppSilent(file, null);
    }

    public static boolean installAppSilent(final File file, final String params) {
        return installAppSilent(file, params, isDeviceRooted());
    }

    public static boolean installAppSilent(final File file,
                                           final String params,
                                           final boolean isRooted) {
        if (!isFileExists(file)) return false;
        String filePath = '"' + file.getAbsolutePath() + '"';
        String command = "LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm install " +
                (params == null ? "" : params + " ")
                + filePath;
        ShellUtils.CommandResult commandResult = ShellUtils.execCmd(command, isRooted);
        if (commandResult.successMsg != null
                && commandResult.successMsg.toLowerCase().contains("success")) {
            return true;
        } else {
            Log.e("AppUtils", "installAppSilent successMsg: " + commandResult.successMsg +
                    ", errorMsg: " + commandResult.errorMsg);
            return false;
        }
    }

    private static boolean isFileExists(final File file) {
        return file != null && file.exists();
    }

    private static boolean isDeviceRooted() {
        String su = "su";
        String[] locations = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/",
                "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/"};
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }

    private static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
