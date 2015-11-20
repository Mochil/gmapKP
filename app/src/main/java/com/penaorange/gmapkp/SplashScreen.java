package com.penaorange.gmapkp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by Pena Orange on 03/09/2015.
 */
public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Thread() {
            @Override
            public void run() {
                Intent mainMenu = new Intent(SplashScreen.this, Dashboard.class);
                SplashScreen.this.startActivity(mainMenu);
                SplashScreen.this.finish();
                overridePendingTransition(R.layout.fadein, R.layout.fadeout);
            }
        }, 3000);
    }
}
