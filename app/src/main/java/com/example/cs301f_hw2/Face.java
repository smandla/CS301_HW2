package com.example.cs301f_hw2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.Random;

/**
 * @author Kavya Mandla
 * @version September 2019
 */
public class Face extends SurfaceView {

    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairLength;


    protected int redHair, greenHair, blueHair;
    protected int redSkin, greenSkin, blueSkin;
    protected int redEye, greenEye, blueEye;

    //constructor
    public Face(Context context, AttributeSet attrs){
        super(context,attrs);
        setWillNotDraw(false);
            randomize();
    }


    private void randomize() {
        Random random = new Random();

        //randomize reds
        redHair = random.nextInt(256);
        redEye = random.nextInt(256);
        redSkin = random.nextInt(256);

        //randomize greens
        greenHair = random.nextInt(256);
        greenEye = random.nextInt(256);
        greenSkin = random.nextInt(256);

        //randomize blues
        blueHair = random.nextInt(256);
        blueEye = random.nextInt(256);
        blueSkin = random.nextInt(256);

        setSkin(redSkin, greenSkin, blueSkin);
        setEye(redEye, greenEye, blueEye);
        setHair(redHair, greenHair,blueHair);
        hairLength = random.nextInt(4)+1;
    }

    public void setRandom(){
        randomize();
    }

    @Override
    public void onDraw(Canvas canvas){
        drawFace(skinColor, canvas);
        drawHairLength(hairColor,canvas);
        drawEyes(eyeColor,canvas);
    }

    /**
     * /**
     * External Citation
     *   Date:     September 28, 2019
     *   Problem:  Didn't know how to manipulate bits to get the rgb values
     *   Resource:
     *     https://www.tutorialspoint.com/Bitwise-right-shift-operator-in-Java
     *   Solution: This site helped me understand how to use them in order to get the right
     *   amount of bits per color value.
     * */

    public void setSkin(int red, int green, int blue){
        red = (red << 16) & 0x00FF0000;
        green = (green << 8) & 0x0000FF00;
        blue = (blue) & 0x000000FF;
        skinColor = 0xFF000000 | red | green | blue;
        this.invalidate();
    }

    public void setHair(int red, int green, int blue){
        red = (red << 16) & 0x00FF0000;
        green = (green << 8) & 0x0000FF00;
        blue = (blue) & 0x000000FF;
        hairColor = 0xFF000000 | red | green | blue;
        this.invalidate();
    }

    public void setEye(int red, int green, int blue){
        red = (red << 16) & 0x00FF0000;
        green = (green << 8) & 0x0000FF00;
        blue = (blue) & 0x000000FF;
        eyeColor = 0xFF000000 | red | green | blue;
        this.invalidate();
    }

    public void setHairLength(String hairLengths){
        if(hairLengths.equals("Rectangle Cut")){
            hairLength = 1;
        }
        else if(hairLengths.equals("Short Square Cut")){
            hairLength = 2;
        }
        else{
            hairLength = 3;
        }
    }


    public void drawFace(int color, Canvas canvas){
        Paint faceColor = new Paint();
        faceColor.setColor(color);
        canvas.drawCircle(900,500,200,faceColor);


    }

    public void drawHairLength(int color, Canvas canvas){
        if(hairLength == 1){
            //rectangle cut
            Paint hairColor = new Paint();
            hairColor.setColor(color);
            canvas.drawRect(1030,300,750,400,hairColor);

        }
        else if(hairLength == 2){
            //Short Square Cut
            Paint hairColor = new Paint();
            hairColor.setColor(color);
            canvas.drawRect(1030,300,750,400,hairColor);
            canvas.drawRect(1130,300,1030,600,hairColor);
            canvas.drawRect(760,300,640,600,hairColor);


        }
        else{
            //Long Square Cut
            Paint hairColor = new Paint();
            hairColor.setColor(color);
            canvas.drawRect(1030,300,750,400,hairColor);
            canvas.drawRect(1130,300,1030,700,hairColor);
            canvas.drawRect(760,300,640,700,hairColor);


        }
        this.invalidate();
    }

    public void drawEyes(int color, Canvas canvas){
        //white part of eye
        Paint whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        canvas.drawCircle(990,425, 40, whitePaint);
        canvas.drawCircle(800, 425, 40, whitePaint);

        //eye that changes color
        Paint facePaint = new Paint();
        facePaint.setColor(color);
        canvas.drawCircle(990, 425, 20, facePaint);
        canvas.drawCircle(800, 425, 20, facePaint);


    }

}

