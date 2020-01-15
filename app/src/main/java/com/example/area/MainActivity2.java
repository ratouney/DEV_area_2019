package com.example.area;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ResponseTypeValues;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button bb = (Button) findViewById(R.id.button2);
        bb.setOnClickListener(new AuthorizeListenerDiscord(this));
    }

    public static class AuthorizeListenerImgur implements Button.OnClickListener {

        private final MainActivity2 mMainActivity;
        String MY_CLIENT_ID = "e59ba362671594e";
        Uri MY_REDIRECT_URI = Uri.parse("com.example.area");


        public AuthorizeListenerImgur(@NonNull MainActivity2 mainActivity) {
            mMainActivity = mainActivity;
        }

        @Override
        public void onClick(View view) {

            AuthorizationServiceConfiguration serviceConfig =
                    new AuthorizationServiceConfiguration(
                            Uri.parse("https://api.imgur.com/oauth2/authorize"),
                            Uri.parse("https://api.imgur.com/oauth2/token"));


            AuthorizationRequest.Builder authRequestBuilder =
                    new AuthorizationRequest.Builder(
                            serviceConfig,
                            MY_CLIENT_ID,
                            ResponseTypeValues.CODE,
                            MY_REDIRECT_URI);

            AuthorizationRequest request = authRequestBuilder.build();
            AuthorizationService authorizationService = new AuthorizationService(view.getContext());

            String action = "com.example.area.HANDLE_AUTHORIZATION_RESPONSE";
            Intent postAuthorizationIntent = new Intent(view.getContext(), After.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(view.getContext(), request.hashCode(), postAuthorizationIntent, 0);
            authorizationService.performAuthorizationRequest(request, pendingIntent);

        }
    }

    public static class AuthorizeListenerDiscord implements Button.OnClickListener {

        private final MainActivity2 mMainActivity;
        String MY_CLIENT_ID = "664402435900702741";
        Uri MY_REDIRECT_URI = Uri.parse("http://com.example.area");


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

            String action = "com.example.area.HANDLE_AUTHORIZATION_RESPONSE";
            Intent postAuthorizationIntent = new Intent(view.getContext(), After.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(view.getContext(), request.hashCode(), postAuthorizationIntent, 0);
            authorizationService.performAuthorizationRequest(request, pendingIntent);

        }
    }
}


