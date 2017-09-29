package com.zzc.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zzc.library.Path;
import com.zzc.library.Router;

@Path(path = {"second", "second/haha"})
public class SecondActivity extends AppCompatActivity {
    public static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundleExtra = getIntent().getBundleExtra(Router.constants.KEY_BUNDLE);
        Log.e(TAG, "onCreate," + bundleExtra.getString("time"));
        Log.e(TAG, "onCreate," + bundleExtra.getString("userId"));
        Log.e(TAG, "onCreate," + bundleExtra.getString(Router.constants.KEY_PATH));
    }
}
