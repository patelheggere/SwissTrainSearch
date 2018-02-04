package patelheggere.trainsearch.acivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import patelheggere.trainsearch.R;

public class FaceBookLoginActivity extends AppCompatActivity {

    private LoginButton mFBLoginButton;
    private CallbackManager mCallbackManager;
    private static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_face_book_login);
        initializeUIComponents();
    }

    private void initializeUIComponents()
    {
        mFBLoginButton = findViewById(R.id.login_button);
        mFBLoginButton.setReadPermissions(Arrays.asList(EMAIL));
        mCallbackManager = CallbackManager.Factory.create();
        boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
        mFBLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent mainIntent = new Intent(getApplicationContext(), OptionSelectionActivity.class);
                startActivity(mainIntent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        if(!loggedIn)
        {
            Intent mainIntent = new Intent(getApplicationContext(), OptionSelectionActivity.class);
            startActivity(mainIntent);
        }

        Button mDirectLogin = findViewById(R.id.btn_direct_login);
        mDirectLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getApplicationContext(), OptionSelectionActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
