package com.example.mygamejava.GameElements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Enemy extends HitboxElement {

    private int xVelocity = 1, yVelocity = 1;
    private int acceleration = 0, accelerationAmp = 2;
    final private int height=150, width=150;
    final private Rect DEFAULT_HITBOX = new Rect(0,0,width,height);
    final private int DEFAULT_ACCELERATION = 0;
    final private int GROW_SIZE = 10;

    public Enemy(Bitmap bitmap, Bitmap bitmap2){
        sprite=bitmap;
        sprite2=bitmap2;
        hitbox= new Rect(0,0,width,height);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(sprite, null, hitbox, null);
    }

    public void update(){

        move(Integer.signum(xVelocity)*(Math.abs(xVelocity)+acceleration),
                Integer.signum(yVelocity)*(Math.abs(yVelocity)+acceleration));
        if(hitbox.left < bounds.left || hitbox.right>bounds.right) xVelocity*=-1;
        if(hitbox.top < bounds.top || hitbox.bottom>bounds.bottom) yVelocity*=-1;
    }

    public void makeStronger(){
        acceleration += accelerationAmp;
        hitbox.bottom-=GROW_SIZE;
        hitbox.top-=GROW_SIZE;
        hitbox.right+=GROW_SIZE;
        hitbox.bottom+=GROW_SIZE;
    }

    public void reset(){
        hitbox = new Rect(DEFAULT_HITBOX);
        acceleration = DEFAULT_ACCELERATION;
    }
}
