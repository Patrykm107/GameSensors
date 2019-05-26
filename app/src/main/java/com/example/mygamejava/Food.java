package com.example.mygamejava;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Pair;

import java.util.Random;

public class Food {
    private float x, y, radius =10;
    private float screenBegin, screenWidth, screenHeight;
    private Random random = new Random();
    private Paint paint = new Paint();

    public Food(float wallWidth){
        screenBegin = 2*wallWidth;    //screen for food is smaller than moving screen
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels - screenBegin;
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels - screenBegin;

        x = random.nextFloat()*(screenWidth-screenBegin)+screenBegin;
        y = random.nextFloat()*(screenHeight-screenBegin)+screenBegin;
        paint.setColor(Color.GREEN);
    }

    public void randomize(){
        x = random.nextFloat()*(screenWidth-screenBegin)+screenBegin;
        y = random.nextFloat()*(screenHeight-screenBegin)+screenBegin;
    }

    public Pair<Float, Float> getCoords(){
        return new Pair<>(x,y);
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(x,y,radius,paint);
    }
}
