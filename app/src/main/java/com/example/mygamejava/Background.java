package com.example.mygamejava;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Background {

    private float width;
    private Paint background, border;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public Background(float wallWidth){
        this.width=wallWidth;
        background = new Paint();
        background.setStyle(Paint.Style.FILL);
        background.setColor(Color.BLACK);

        border=new Paint();
        border.setStyle(Paint.Style.STROKE);
        border.setColor(Color.RED);
    }

    public void draw(Canvas canvas){
        canvas.drawRect(width,width,screenWidth-width,screenHeight-width,background);
        canvas.drawRect(width,width,screenWidth-width,screenHeight-width,border);
    }
}
