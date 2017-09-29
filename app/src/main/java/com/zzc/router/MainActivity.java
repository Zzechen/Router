package com.zzc.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zzc.library.Path;
import com.zzc.library.Router;

@Path(path = "/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Router.init(this);
        setContentView(R.layout.activity_main);
    }

    public void toSecond(View view) {
        Bundle args = new Bundle();
        args.putString("hahaha", "hahaha");
        Router.start(this, args, "second");
    }

    public void toSecondHaha(View view) {
        Bundle args = new Bundle();
        args.putString("hahaha", "second/hahaha");
        Router.start(this, args, "second/haha?time=11112&userId=24gdsghsh");
    }
}
