package com.example.mygamejava.GameElements;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Score {
    private Paint scorePaint = new Paint(), hiscorePaint=new Paint();
    private int score = 0;
    public int hiscore;
    private int textSize = 64;
    private int trigger = 7;

    public Score(int hiscore){
        this.hiscore=hiscore;
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(textSize);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        hiscorePaint.setColor(Color.CYAN);
        hiscorePaint.setTextSize(textSize);
        hiscorePaint.setTextAlign(Paint.Align.RIGHT);
    }

    public boolean increasePoints(){
        score++;
        if (score%trigger==0) return  true;
        return false;
    }

    public void erasePoints(){
        if(score>hiscore) hiscore = score;
        score=0;
    }

    public void draw(Canvas canvas){
        canvas.drawText(Integer.toString(score), 0, textSize, scorePaint);
        int x = Resources.getSystem().getDisplayMetrics().widthPixels;
        canvas.drawText("HiScore: "+Integer.toString(hiscore), x, textSize, hiscorePaint);
    }
}
