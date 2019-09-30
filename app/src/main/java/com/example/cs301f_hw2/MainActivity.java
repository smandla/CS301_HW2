

package com.example.cs301f_hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Kavya Mandla
 * @version September 2019
 */
public class MainActivity extends AppCompatActivity {


    protected Face face;


    int r, g, b;

    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;

    private TextView redValue, greenValue, blueValue;

    ArrayAdapter<CharSequence> adapter;

    Spinner hairLengthSpinner;

    private int redHairProgress, greenHairProgress, blueHairProgress;
    private int redSkinProgress, greenSkinProgress, blueSkinProgress;
    private int redEyeProgress, greenEyeProgress, blueEyeProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        face = (Face) findViewById(R.id.faceSurfaceView);


        redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        redSeekBar.setMax(255);

        blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);
        blueSeekBar.setMax(255);

        greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        greenSeekBar.setMax(255);

        redValue = (TextView) findViewById(R.id.redValue);
        blueValue = (TextView) findViewById(R.id.blueValue);
        greenValue = (TextView) findViewById(R.id.greenValue);



        /**
         * /**
         * External Citation
         *   Date:     September 28, 2019
         *   Problem:  Did not know how to add Titles to spinner drop down
         *   Resource:
         *      https://www.tutorialspoint.com/android/android_spinner_control.htm
         *   Solution: Used the example code to help me create the drop down titles
         * */

        List<String> hairLengthDDSpinner = new ArrayList<>();
        hairLengthDDSpinner.add("Rectangle Cut ");
        hairLengthDDSpinner.add("Short Square Cut");
        hairLengthDDSpinner.add("Shoulder Square Cut");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, hairLengthDDSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //drop down spinner for hair length
        hairLengthSpinner = (Spinner) findViewById(R.id.hairSpinner);
        hairLengthSpinner.setAdapter(adapter);


        //radio group for eyes, hair, and skin
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupListener);


        //eyes, hair, skin radio buttons
        RadioButton hairRadioButton = findViewById(R.id.hairRadioButton);
        RadioButton eyesRadioButton = findViewById(R.id.eyesRadioButton);
        RadioButton skinRadioButton = findViewById(R.id.skinRadioButton);

        //random button
        Button randomButton = (Button) findViewById(R.id.randomButton);
        randomButton.setOnClickListener(randomButtonListener);


    }


    //on click listener for random button
    private Button.OnClickListener randomButtonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            face.setRandom();

            redHairProgress = face.redHair;
            redEyeProgress = face.redEye;
            redSkinProgress = face.redSkin;

            greenHairProgress = face.greenHair;
            greenEyeProgress = face.greenEye;
            greenSkinProgress = face.greenSkin;

            blueHairProgress = face.blueHair;
            blueEyeProgress = face.blueEye;
            blueSkinProgress = face.blueSkin;

        }
    };

    // on check change listener for radio group : eyes, hair, and skin radio buttons
    private RadioGroup.OnCheckedChangeListener radioGroupListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.skinRadioButton) {
                redSeekBar.setOnSeekBarChangeListener(skinSeekBarListener);
                greenSeekBar.setOnSeekBarChangeListener(skinSeekBarListener);
                blueSeekBar.setOnSeekBarChangeListener(skinSeekBarListener);

                redSeekBar.setProgress(redSkinProgress);
                greenSeekBar.setProgress(greenSkinProgress);
                blueSeekBar.setProgress(blueSkinProgress);
            }
            if (i == R.id.hairRadioButton) {

                redSeekBar.setOnSeekBarChangeListener(hairSeekBarListener);
                greenSeekBar.setOnSeekBarChangeListener(hairSeekBarListener);
                blueSeekBar.setOnSeekBarChangeListener(hairSeekBarListener);

                redSeekBar.setProgress(redHairProgress);
                greenSeekBar.setProgress(greenHairProgress);
                blueSeekBar.setProgress(blueHairProgress);
            }
            if (i == R.id.eyesRadioButton) {
                redSeekBar.setOnSeekBarChangeListener(eyeSeekBarListener);
                greenSeekBar.setOnSeekBarChangeListener(eyeSeekBarListener);
                blueSeekBar.setOnSeekBarChangeListener(eyeSeekBarListener);

                redSeekBar.setProgress(redEyeProgress);
                greenSeekBar.setProgress(greenEyeProgress);
                blueSeekBar.setProgress(blueEyeProgress);
            }
        }
    };

        //to update skin everytime state is changed
        public void updateSkin() {
            r = redSeekBar.getProgress();
            g = greenSeekBar.getProgress();
            b = blueSeekBar.getProgress();
            face.setSkin(r, g, b);
        }

        //seek bar change listener for red, green, and blue seek bars for SKIN
        private SeekBar.OnSeekBarChangeListener skinSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (seekBar.getId() == R.id.redSeekBar) {
                    redSkinProgress = i;
                }
                if (seekBar.getId() == R.id.blueSeekBar) {
                    blueSkinProgress = i;
                }
                if (seekBar.getId() == R.id.greenSeekBar) {
                    greenSkinProgress = i;
                }

                updateSkin();
                redValue.setText("" + redSeekBar.getProgress());
                blueValue.setText("" + blueSeekBar.getProgress());
                greenValue.setText("" + greenSeekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        //to update hair everytime state is changed
        public void updateHair() {
            r = redSeekBar.getProgress();
            g = greenSeekBar.getProgress();
            b = blueSeekBar.getProgress();
            face.setHair(r, g, b);
        }

        //seek bar change listener for red, green, and blue seek bars for HAIR
        private SeekBar.OnSeekBarChangeListener hairSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (seekBar.getId() == R.id.redSeekBar) {
                    redHairProgress = i;
                }
                if (seekBar.getId() == R.id.blueSeekBar) {
                    blueHairProgress = i;
                }
                if (seekBar.getId() == R.id.greenSeekBar) {
                    greenHairProgress = i;
                }

                updateHair();
                redValue.setText("" + redSeekBar.getProgress());
                blueValue.setText("" + blueSeekBar.getProgress());
                greenValue.setText("" + greenSeekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        //to update eye everytime state is changed
        public void updateEye() {
            r = redSeekBar.getProgress();
            g = greenSeekBar.getProgress();
            b = blueSeekBar.getProgress();
            face.setEye(r, g, b);
        }

        //seek bar change listener for red, green, and blue seek bars for EYES
        private SeekBar.OnSeekBarChangeListener eyeSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (seekBar.getId() == R.id.redSeekBar) {
                    redEyeProgress = i;
                }
                if (seekBar.getId() == R.id.blueSeekBar) {
                    blueEyeProgress = i;
                }
                if (seekBar.getId() == R.id.greenSeekBar) {
                    greenEyeProgress = i;
                }

                updateEye();
                redValue.setText("" + redSeekBar.getProgress());
                blueValue.setText("" + blueSeekBar.getProgress());
                greenValue.setText("" + greenSeekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
}