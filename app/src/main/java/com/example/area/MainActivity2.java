package com.example.area;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ResponseTypeValues;
import net.openid.appauth.TokenResponse;

public class MainActivity2 extends AppCompatActivity {

    private static final String USED_INTENT = "USED_INTENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button b = (Button) findViewById(R.id.button);
        Button bb = (Button) findViewById(R.id.button2);
        Button bbb = (Button) findViewById(R.id.button3);
        Button bbbb = (Button) findViewById(R.id.button4);
    /*
        b.setOnClickListener(new AuthorizeListenerSpotify(this));
        bb.setOnClickListener(new AuthorizeListenerImgur(this));
        bbb.setOnClickListener(new AuthorizeListenerDiscord(this));
        bbbb.setOnClickListener(new AuthorizeListenerGoogle(this));
        checkIntent();
     */
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("yo", "ahahah");
        setIntent(intent);
        checkIntent();
    }

    private void checkIntent() {
        Log.i("yo", "hello");
        Intent intent = getIntent();
        if (intent != null) {
            String action = intent.getAction();
            switch (action) {
                case "com.example.area.appauth.HANDLE_AUTHORIZATION_RESPONSE":
                    if (!intent.hasExtra(USED_INTENT)) {
                        handleAuthorizationResponse(intent);
                        intent.putExtra(USED_INTENT, true);
                    }
                    break;
                default:
                    // do nothing
            }
        }
    }


    private void handleAuthorizationResponse(@NonNull Intent intent) {
        AuthorizationResponse response = AuthorizationResponse.fromIntent(intent);
        AuthorizationException error = AuthorizationException.fromIntent(intent);
        final AuthState authState = new AuthState(response, error);
        if (response != null) {
            AuthorizationService service = new AuthorizationService(this);
            service.performTokenRequest(response.createTokenExchangeRequest(), new AuthorizationService.TokenResponseCallback() {
                @Override
                public void onTokenRequestCompleted(@Nullable TokenResponse tokenResponse, @Nullable AuthorizationException exception) {
                    if (exception != null) {
                        Log.w("Error", "Token Exchange failed", exception);
                    } else {
                        if (tokenResponse != null) {
                            Log.i("Succes", String.format("Token Response [ Access Token: %s, ID Token: %s ]", tokenResponse.accessToken, tokenResponse.idToken));
                        }
                    }
                }
            });
        }
    }

    public static class AuthorizeListenerDiscord implements Button.OnClickListener {

        private final MainActivity2 mMainActivity;
        String MY_CLIENT_ID = "664402435900702741";
        Uri MY_REDIRECT_URI = Uri.parse("com.example.area://area");


        public AuthorizeListenerDiscord(@NonNull MainActivity2 mainActivity) {
            mMainActivity = mainActivity;
        }

        @Override
        public void onClick(View view) {

            AuthorizationServiceConfiguration serviceConfig =
                    new AuthorizationServiceConfiguration(
                            Uri.parse("https://discordapp.com/api/oauth2/authorize"),
                            Uri.parse("https://discordapp.com/api/oauth2/token"));


            AuthorizationRequest.Builder authRequestBuilder =
                    new AuthorizationRequest.Builder(
                            serviceConfig,
                            MY_CLIENT_ID,
                            ResponseTypeValues.CODE,
                            MY_REDIRECT_URI);

            AuthorizationRequest request = authRequestBuilder.build();
            AuthorizationService authorizationService = new AuthorizationService(view.getContext());

            Intent postAuthorizationIntent = new Intent(view.getContext(), After.class);
            //Intent postAuthorizationIntent = new Intent("Discord");

            PendingIntent pendingIntent = PendingIntent.getActivity(view.getContext(), request.hashCode(), postAuthorizationIntent, 0);
            authorizationService.performAuthorizationRequest(request, pendingIntent);

        }
    }

    public static class AuthorizeListenerGoogle implements Button.OnClickListener {

        private final MainActivity2 mMainActivity;
        String MY_CLIENT_ID = "602804385318-5iqdu70f4ppmbrbfbvol4q2rb8pqfmec.apps.googleusercontent.com";
        Uri MY_REDIRECT_URI = Uri.parse("com.example.area://area");


        public AuthorizeListenerGoogle(@NonNull MainActivity2 mainActivity) {
            mMainActivity = mainActivity;
        }

        @Override
        public void onClick(View view) {

            AuthorizationServiceConfiguration serviceConfig =
                    new AuthorizationServiceConfiguration(
                            Uri.parse("https://accounts.google.com/o/oauth2/v2/auth"),
                            Uri.parse("https://www.googleapis.com/oauth2/v4/token"));


            AuthorizationRequest.Builder authRequestBuilder =
                    new AuthorizationRequest.Builder(
                            serviceConfig,
                            MY_CLIENT_ID,
                            ResponseTypeValues.CODE,
                            MY_REDIRECT_URI);
            authRequestBuilder.setScopes("profile");
            AuthorizationRequest request = authRequestBuilder.build();
            AuthorizationService authorizationService = new AuthorizationService(view.getContext());

            Intent postAuthorizationIntent = new Intent(view.getContext(), After.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(view.getContext(), request.hashCode(), postAuthorizationIntent, 0);
            authorizationService.performAuthorizationRequest(request, pendingIntent);

        }
    }

}

