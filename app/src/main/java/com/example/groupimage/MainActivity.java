package com.example.groupimage;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.groupimage.ui.SpritesView;

/**
 * Created by hongwei on 3/21/16.
 */
public class MainActivity extends AppCompatActivity {
    private String[] spriteUrls = new String[] {
            "https://edencore.herokuapp.com/assets/sprites/males/1.png",
            "https://edencore.herokuapp.com/assets/sprites/males/2.png",
            "https://edencore.herokuapp.com/assets/sprites/males/3.png",
            "https://edencore.herokuapp.com/assets/sprites/females/13.png",
            "https://edencore.herokuapp.com/assets/sprites/males/5.png",
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();

        SpritesView spritesView = (SpritesView) findViewById(R.id.spritesView);
        spritesView.loadSpriteUrls(this, spriteUrls);


        //mSpriteViewContainer.addView(spritesView);
    }
}
