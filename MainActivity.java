package com.example.asg_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SoundPool soundPool;

    private AudioManager audioManager;
    private Button buttonDestroy;
    private Button buttonGun,Orange,Blue,Black,Yellow;
    private TextView tv1;

    // Maximumn sound stream.
    private static final int MAX_STREAMS = 5;

    // Stream type.
    private static final int streamType = AudioManager.STREAM_MUSIC;

    private boolean loaded;

    private int soundIdDestroy;
    private int soundIdGun;
    private int blue;
    private int yellow;
    private int orange;
    private int black;
    private float volume;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.buttonDestroy = (Button) this.findViewById(R.id.RED);
        this.buttonGun = (Button) this.findViewById(R.id.Green);
        this.Yellow = (Button) this.findViewById(R.id.Yellow);
        this.Blue = (Button) this.findViewById(R.id.Blue);
        this.Black = (Button) this.findViewById(R.id.Black);
        this.Orange = (Button) this.findViewById(R.id.Orange);
        tv1=(TextView) findViewById(R.id.tv1);
        this.buttonDestroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSoundDestroy();
                tv1.setText("RED");
            }
        });

        this.buttonGun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSoundGun();
                tv1.setText("GREEN");

            }
        });

        this.Orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playOrange();
                tv1.setText("ORANGE");
            }
        });

        this.Black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playBlack();
                tv1.setText("BLACK");
            }
        });

        this.Blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playBlue();
                tv1.setText("BLUE");
            }
        });

        this.Yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playYellow();
                tv1.setText("YELLOW");
            }
        });

        // AudioManager audio settings for adjusting the volume
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Current volumn Index of particular stream type.
        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);

        // Get the maximum volume index for a particular stream type.
        float maxVolumeIndex  = (float) audioManager.getStreamMaxVolume(streamType);

        // Volumn (0 --> 1)
        this.volume = currentVolumeIndex / maxVolumeIndex;

        // Suggests an audio stream whose volume should be changed by
        // the hardware volume controls.
        this.setVolumeControlStream(streamType);

        // For Android SDK >= 21
        if (Build.VERSION.SDK_INT >= 21 ) {
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        }
        // for Android SDK < 21
        else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        // When Sound Pool load complete.
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        // Load sound file (destroy.wav) into SoundPool.
        this.soundIdDestroy = this.soundPool.load(this, R.raw.red,1);

        // Load sound file (gun.wav) into SoundPool.
        this.soundIdGun = this.soundPool.load(this, R.raw.green,1);
        this.blue = this.soundPool.load(this, R.raw.blue,1);
        this.black = this.soundPool.load(this, R.raw.black,1);
        this.yellow = this.soundPool.load(this, R.raw.yellow,1);
        this.orange = this.soundPool.load(this, R.raw.orange,1);


    }



    // When users click on the button "Gun"
    public void playSoundGun( )  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;
            // Play sound of gunfire. Returns the ID of the new stream.
            int streamId = this.soundPool.play(this.soundIdGun,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    // When users click on the button "Destroy"
    public void playSoundDestroy( )  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;

            // Play sound objects destroyed. Returns the ID of the new stream.
            int streamId = this.soundPool.play(this.soundIdDestroy,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
    public void playOrange( )  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;

            // Play sound objects destroyed. Returns the ID of the new stream.
            int streamId = this.soundPool.play(this.orange,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
    public void playBlack( )  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;

            // Play sound objects destroyed. Returns the ID of the new stream.
            int streamId = this.soundPool.play(this.black,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
    public void playBlue( )  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;

            // Play sound objects destroyed. Returns the ID of the new stream.
            int streamId = this.soundPool.play(this.blue,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
    public void playYellow( )  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;

            // Play sound objects destroyed. Returns the ID of the new stream.
            int streamId = this.soundPool.play(this.yellow,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

}
