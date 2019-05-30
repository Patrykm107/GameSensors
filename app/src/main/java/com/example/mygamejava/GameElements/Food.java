package com.example.mygamejava.GameElements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;

import java.util.Random;

public class Food extends HitboxElement{

    private Random random = new Random();
    private Paint paint = new Paint();
    private final static int COLORS[] = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA};
    private final static int HEIGHT = 70, WIDTH = 70;

    public Food(Bitmap bitmap, Bitmap bitmap2){
        sprite = bitmap;
        sprite2 = bitmap2;
        randomize();
    }

    public void randomize(){
        paint.setColorFilter(new PorterDuffColorFilter(COLORS[random.nextInt(COLORS.length)], PorterDuff.Mode.MULTIPLY));
        int x = random.nextInt(bounds.right-bounds.left-2*WIDTH)+WIDTH;
        int y = random.nextInt(bounds.bottom-bounds.top-2*HEIGHT)+HEIGHT;
        hitbox = new Rect(x-WIDTH/2, y-HEIGHT/2, x+WIDTH/2, y+HEIGHT/2);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(sprite,null,hitbox,paint);
    }
}
