package com.zzc.library;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created : zzc
 * Time : 2017/9/29
 * Email : zzc1259@163.com
 * Description : ${desc}
 */

public class PathParser {
    private static final HashMap<String, String> sPathMap = new HashMap<>();

    public static void init(Context application) {
        String pkgName = application.getPackageName();
        try {
            PackageInfo packageInfo = application.getPackageManager().getPackageInfo(application.getPackageName(), PackageManager.GET_ACTIVITIES);
            ActivityInfo[] activities = packageInfo.activities;
            if (activities == null) return;
            for (ActivityInfo activityInfo : activities) {
                String activityName = activityInfo.name;
                if (activityName.startsWith(".")) activityName = pkgName + activityName;
                Class cls = Class.forName(activityName);
                Path router = (Path) cls.getAnnotation(Path.class);
                if (router == null) continue;
                String[] paths = router.path();
                for (String path : paths) {
                    if (TextUtils.isEmpty(path)) continue;
                    sPathMap.put(path, activityName);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 去除参数
     * 例：
     * path = /main?time=1242575832&userId=1
     * return /main
     *
     * @param path
     * @return
     */
    public static String getPath(String path) {
        if (path.contains("?")) {
            String[] split = path.split(Pattern.quote("?"));
            path = split[0];
        }
        return path;
    }

    /**
     * 去除参数
     * 例：
     * path = /main?time=1242575832&userId=1
     * return {("time":"1242575832"),("userId":"1")}
     *
     * @param path
     * @return
     */
    public static Map<String, String> getParams(String path) {
        Map<String, String> map = null;
        if (path.contains("?")) {
            map = new HashMap<>();
            int i = path.indexOf('?');
            if (i >= path.length() - 1) return map;
            String[] split = path.substring(i + 1, path.length()).split("&");
            for (String item : split) {
                String[] keyValue = item.split("=");
                map.put(keyValue[0], keyValue[1]);
            }
        }
        return map;
    }

    public static String get(String path) {
        return sPathMap.get(getPath(path));
    }
}
