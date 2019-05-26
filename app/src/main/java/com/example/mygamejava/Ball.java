package com.example.mygamejava;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Pair;

public class Ball {

    private float x, y, radius, xVelocity = 10, yVelocity = 5;
    private Paint paint;
    private int screenBegin;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private float DEFAULT_RADIUS = 20;

    public Ball(int wallWidth){
        screenBegin = wallWidth;
        x = screenWidth/2f; y = screenHeight/2f; radius = DEFAULT_RADIUS;
        screenWidth -= wallWidth; screenHeight-=wallWidth;
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(x, y, radius, paint);
    }

    public void setVelocities(float xVelocity, float yVelocity){
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public PlayStatus update(final Food food){
        paint.setColor(paint.getColor()+2);
        Pair<Float,Float> foodCoords = food.getCoords();

        if(x - radius<screenBegin || y - radius <screenBegin || x + radius>screenWidth || y+radius>screenHeight){
            x = screenWidth/2;
            y = screenHeight/2;
            radius = DEFAULT_RADIUS;
            return PlayStatus.LOSE;
        } else if( Math.pow( foodCoords.first - x, 2) + Math.pow(foodCoords.second-y,2) <= radius*radius) {
            radius+=1;
            return PlayStatus.SCORE;
        } else {
            x += xVelocity;
            y += yVelocity;
        }
        return PlayStatus.CONTINUE;
    }

}
