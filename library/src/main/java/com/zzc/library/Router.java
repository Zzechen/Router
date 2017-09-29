package com.zzc.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.util.Map;
import java.util.Set;

/**
 * Created : zzc
 * Time : 2017/9/29
 * Email : zzc1259@163.com
 * Description : ${desc}
 */
public class Router {
    public static final String TAG = Router.class.getSimpleName();
    public static boolean LOG_ENABLE = true;

    public static void init(Context context) {
        if (context != null) {
            PathParser.init(context.getApplicationContext());
        }
    }

    public static void start(Context context, String path) {
        start(context, null, path);
    }

    public static void start(Context context, Bundle params, String path) {
        Intent intent = createIntent(context, params, path);
        if (intent == null) return;
        context.startActivity(intent);
    }

    public static void startForResult(Activity activity, String path, int reqCode) {
        startForResult(activity, new Bundle(), path, reqCode);
    }

    public static void startForResult(Activity activity, Bundle params, String path, int reqCode) {
        Intent intent = createIntent(activity, params, path);
        if (intent == null) return;
        activity.startActivityForResult(intent, reqCode);
    }

    private static Intent createIntent(Context context, Bundle params, String path) {
        if (context == null) {
            throw new NullPointerException("Path start activity but context is null");
        }
        log(TAG, "bundle :" + (params == null ? "null" : params.toString()));
        String className = PathParser.get(path);
        Intent intent = null;
        if (!TextUtils.isEmpty(className)) {
            intent = new Intent();
            log(TAG, "className:" + className);
            if (params == null) params = new Bundle();
            Map<String, String> startParams = PathParser.getParams(path);
            if (startParams != null && startParams.size() > 0) {
                Set<String> keySet = startParams.keySet();
                for (String key : keySet) {
                    params.putString(key, startParams.get(key));
                }
            }
            params.putString(constants.KEY_PATH, PathParser.getPath(path));
            intent.putExtra(constants.KEY_BUNDLE, params);
            intent.setClassName(context.getPackageName(), className);
        }
        return intent;
    }

    public static class constants {
        private constants() {
        }

        public static final String KEY_BUNDLE = "routerIntentKeyBundle";
        public static final String KEY_PATH = "routerIntentPath";
    }

    private static void log(String tag, String content) {
        if (LOG_ENABLE) {
            Log.d(tag, content);
        }
    }
}
