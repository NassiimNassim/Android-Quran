package com.example.nassimnassiim.quran;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Lecteur extends Activity {

         private MediaPlayer mp;
         private String URL_Base = "http://www.01quran.com/mp3/qurra/sad-al-ghamidi/";
         private TextView titleid,txtDuration;
         private ImageButton liste;
         private String curentTitle,curenturl;
         private ImageButton play,upsound,downSound,butprec,butsuiv,repeat,aleatoire;
         private AudioManager audio;
         private SeekBar progresbar;
         private Boolean stop_paly=true,repeat_type = false,aleatoir_type = false;
         int MaxV,curV=0,currentPosition=0,timetotal=0;

    @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecteur);
        titleid = (TextView) findViewById(R.id.titleid);
        txtDuration = (TextView) findViewById(R.id.txtDuration);
        liste = (ImageButton) findViewById(R.id.listebut);
        play = (ImageButton) findViewById(R.id.play);
        upsound = (ImageButton) findViewById(R.id.upsound);
        downSound = (ImageButton) findViewById(R.id.downSound);
        butprec = (ImageButton) findViewById(R.id.butprec);
        butsuiv = (ImageButton) findViewById(R.id.butsuiv);
        repeat = (ImageButton) findViewById(R.id.repeat);
        aleatoire = (ImageButton) findViewById(R.id.aleatoire);
        progresbar = (SeekBar) findViewById(R.id.seekBar);
        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        MaxV = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        curentTitle = "";
        curenturl = "001";
        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        repeat.setImageResource(R.drawable.reapt);
        aleatoire.setImageResource(R.drawable.aleat);
        playurl(curenturl);
        mp.pause();
        play.setImageResource(R.drawable.play);

        liste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent procc = new Intent(Lecteur.this, MainActivity.class);
                startActivityForResult(procc, 0);
            }
        });

         play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mp.isPlaying()){
                    if (mp != null){
                        mp.start();
                        play.setImageResource(R.drawable.pause);
                        stop_paly = false ;
                        startThread();
                    }
                }else {
                    if (mp!=null) {
                        play.setImageResource(R.drawable.play);
                        mp.pause();
                        stop_paly = true;
                    }
                }
            }
        });

          butprec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop_paly = true;
                int i = Integer.parseInt(curenturl);
                if (aleatoir_type) {
                    i = (int) ((Math.random() * 114) + 1);
                }else if (!repeat_type){
                    i--;
                }
                if (i == 0) {
                    playurl("001");
                }else {
                    if (String.valueOf(i).length() == 1) {
                       playurl("00" + String.valueOf(i));
                    } else if (String.valueOf(i).length() == 2) {
                        playurl("0" + String.valueOf(i));
                    } else {
                        playurl(String.valueOf(i));
                    }
                }

            }
        });

        butsuiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop_paly = true;
                int i = Integer.parseInt(curenturl);
                if (aleatoir_type) {
                    i = (int) ((Math.random() * 114) + 1);
                }else if (!repeat_type) {
                    i++;
                }
                if (i == DataListe.getnbrSourate()+1) {
                    playurl(String.valueOf(DataListe.getnbrSourate()));
                }else {
                    if (String.valueOf(i).length() == 1) {
                        playurl("00" + String.valueOf(i));
                    } else if (String.valueOf(i).length() == 2) {
                        playurl("0" + String.valueOf(i));
                    } else {
                        playurl(String.valueOf(i));
                    }
                }
            }
        });

        upsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curV+2 < MaxV) {
                    curV += 2;
                }else {
                    curV = MaxV;
                }
                audio.setStreamVolume(AudioManager.STREAM_MUSIC,curV, 0);
            }
        });

        downSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curV-2 > 0){
                    curV -= 2;

                }else {
                    curV = 0;
                }
                audio.setStreamVolume(AudioManager.STREAM_MUSIC,curV, 0);
            }
        });

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (repeat_type){
                        repeat_type = false;
                        repeat.setImageResource(R.drawable.reapt);
                    }else {
                        repeat_type = true;
                        repeat.setImageResource(R.drawable.reapt_on);
                    }
            }
        });

        aleatoire.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (aleatoir_type) {
                    aleatoir_type = false;
                    aleatoire.setImageResource(R.drawable.aleat);
                } else {
                    aleatoir_type = true;
                    aleatoire.setImageResource(R.drawable.aleat_on);
                }
            }
        });

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int i;
                if (repeat_type) {
                    playurl(curenturl);
                    return;
                } else if (aleatoir_type) {
                    i = (int) ((Math.random() * 114) + 1);
                } else {
                    i = Integer.valueOf(curenturl) + 1;
                }
                if (String.valueOf(i).length() == 1) {
                    playurl("00" + String.valueOf(i));
                } else if (String.valueOf(i).length() == 2) {
                    playurl("0" + String.valueOf(i));
                } else {
                    playurl(String.valueOf(i));
                }
            }
        });

        progresbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mp.seekTo(progress);
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,  resultCode,  data);
        if (resultCode == RESULT_OK){
            Bundle info = data.getExtras();
            String title = info.getString("decrip");
            String url = info.getString("url");
            System.out.println(url);
            titleid.setText(title);
            if (!this.curentTitle.equals(title)) {
                if (mp.isPlaying()){
                    mp.stop();
                }
                this.playurl(url);
            }
        }
    }


    public void playurl(String url){
        stop_paly = false;
        titleid.setText(DataListe.getSourateAtIndex(Integer.parseInt(url)));
        curentTitle = (String) titleid.getText();
        curenturl = url;
         mp.reset();
        try {
            mp.setDataSource(Lecteur.this, Uri.parse(URL_Base+url+".mp3"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        timetotal= mp.getDuration();
        currentPosition = 0;
        txtDuration.setText(convertTimeToString(timetotal));
        mp.start();
        play.setImageResource(R.drawable.pause);
        progresbar.setMax(timetotal);
        startThread();

    }

    public String progress_sound(int i){
        String prog = "";
        for (int t = 0 ; t<i  ; t++){
            prog += "|";
        }
    return prog;
    }

    public void startThread(){
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp != null && currentPosition < timetotal && stop_paly == false) {
                    try {
                        Thread.sleep(1000);
                        currentPosition = mp.getCurrentPosition();
                    } catch (InterruptedException e) {
                        return;
                    } catch (Exception e) {
                        return;
                    }
                    progresbar.setProgress(currentPosition);
                }
            }
        });
        th.start();
    }

    public String convertTimeToString(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm:ss");
        return sdf.format(time);
    }
}

