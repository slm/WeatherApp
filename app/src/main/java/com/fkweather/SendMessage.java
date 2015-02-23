package com.fkweather;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.pwittchen.weathericonview.library.WeatherIconView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SendMessage extends Activity {

    @InjectView(R.id.name_edittext)
    BootstrapEditText nameedittext;

    @InjectView(R.id.msgedit)
    BootstrapEditText message;

    @InjectView(R.id.url_edittext)
    BootstrapEditText url;

    @InjectView(R.id.send)
    BootstrapButton button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        ButterKnife.inject(this);

        final int tempget = getIntent().getIntExtra("temp",-9999999);

        RestAdapter restAdapter2 = new RestAdapter.Builder()
                .setEndpoint("http://692f1f7a.ngrok.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        final MessageClient mservice = restAdapter2.create(MessageClient.class);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mservice.addMessage(message.getText().toString(),url.getText().toString(),nameedittext.getText().toString(),tempget,new Callback<String>() {
                    @Override
                    public void success(String s, Response response) {

                        Toast.makeText(SendMessage.this,"İletiniz gönderildi teşekkür ederiz",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        error.printStackTrace();
                        Toast.makeText(SendMessage.this,"İletiniz gönderilemedi!",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });


            }
        });

    }
}
