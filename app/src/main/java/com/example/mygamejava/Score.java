package com.example.mygamejava;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Score {
    private float x=Resources.getSystem().getDisplayMetrics().widthPixels/2f, y;
    private Paint paint = new Paint();
    private int score = 0;

    public Score(float wallWidth){
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        y = wallWidth-1;
    }

    public void increasePoints(){
        score++;
    }

    public void erasePoints(){  //to do: save hi score
        score=0;
    }

    public void draw(Canvas canvas){
        canvas.drawText(Integer.toString(score), x, y, paint);
    }
}
