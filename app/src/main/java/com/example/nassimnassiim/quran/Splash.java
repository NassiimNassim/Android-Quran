package com.example.nassimnassiim.quran;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread procc = new Thread(){
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent activ = new Intent(Splash.this,Lecteur.class);
                    startActivity(activ);
                    finish();
                }
            }
        };
        procc.start();
    }
}
