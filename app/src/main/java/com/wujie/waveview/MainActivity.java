package com.wujie.waveview;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.wujie.waveview.view.WaveView;
import com.wujie.waveview.view.WhewView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private WaveView waveView;
    private ImageView img;

    private WhewView whewView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        waveView = (WaveView) findViewById(R.id.wave_view);

        img = (ImageView) findViewById(R.id.image_view) ;
        img.setImageResource(R.drawable.speech_annimal);
         AnimationDrawable anim = (AnimationDrawable)img.getDrawable();
        if(anim != null) {
           // anim.setOneShot(true);
            anim.start();
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waveView.setVisibility(View.VISIBLE);
                waveView.startAnimation();
            }
        });

        findViewById(R.id.wave).setVisibility(View.VISIBLE);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                waveView.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        whewView = (WhewView) findViewById(R.id.whewView);
        whewView.start();
    }


}
