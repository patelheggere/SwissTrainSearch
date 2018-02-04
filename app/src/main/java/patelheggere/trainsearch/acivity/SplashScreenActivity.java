package patelheggere.trainsearch.acivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import patelheggere.trainsearch.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/zag_bold.otf");
        TextView tv1 = findViewById(R.id.tv_sts);
        tv1.setTypeface(face);

        goToNextActivity();
    }

    private void goToNextActivity() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                {
                    Log.d("isFirstTimeUser", "true:");

                    startActivity(new Intent(SplashScreenActivity.this, FaceBookLoginActivity.class));
                    finish();

                }
            }
        }, 3000);
    }

}
