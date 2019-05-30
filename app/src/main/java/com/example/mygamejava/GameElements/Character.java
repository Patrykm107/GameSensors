package com.example.mygamejava.GameElements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Character extends HitboxElement {

    private int xVelocity = 1, yVelocity = 1;
    private int acceleration = 0, accelerationAmp = 1;
    static final private int DEFAULT_ACCELERATION = 0;
    static final private int DEFAULT_HEIGHT = 110, DEFAULT_WIDTH = 110;
    final private Rect DEFAULT_HITBOX = new Rect(bounds.right / 2 - DEFAULT_WIDTH / 2, bounds.bottom / 2 - DEFAULT_HEIGHT / 2,
            bounds.right / 2 + DEFAULT_WIDTH / 2, bounds.bottom / 2 + DEFAULT_HEIGHT / 2);

    public Character(Bitmap bitmap, Bitmap bitmap2) {
        sprite = bitmap;
        sprite2 = bitmap2;
        hitbox = new Rect(DEFAULT_HITBOX);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprite, null, hitbox, null);
    }

    public void setVelocities(int xVelocity, int yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public boolean update() {
        if (!bounds.contains(hitbox)) {
            reset();
            return false;
        } else {
            move(Integer.signum(xVelocity) * (Math.abs(xVelocity) + acceleration),
                    Integer.signum(yVelocity) * (Math.abs(yVelocity) + acceleration));
        }
        return true;
    }

    public void reset() {
        hitbox = new Rect(DEFAULT_HITBOX);
        acceleration = DEFAULT_ACCELERATION;
    }

    public void increaseAcceleration() {
        acceleration += accelerationAmp;
    }


}
