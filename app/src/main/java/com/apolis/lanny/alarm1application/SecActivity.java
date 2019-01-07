package com.apolis.lanny.alarm1application;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class SecActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    MediaPlayer mp;
    Button stop;
    TextView tv;
    TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        tv = findViewById(R.id.textViewRe);
        textToSpeech = new TextToSpeech(SecActivity.this, this);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String str1 = "Attention! It's time! Time to take medicine! ";

                textToSpeech.speak(str1, TextToSpeech.QUEUE_FLUSH, null);
            }
        }, 100);



//        mp = MediaPlayer.create(this, R.raw.jinglebell);
//        mp.start();
//
//        stop = findViewById(R.id.button2);
//
//        stop.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mp.release();
//            }
//        });

    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            int result = textToSpeech.setLanguage(Locale.US);

            if(result == TextToSpeech.LANG_MISSING_DATA|| result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("ttS", "Language not supported");
            }
        }
    }
}
