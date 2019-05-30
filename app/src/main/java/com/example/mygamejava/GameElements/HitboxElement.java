package com.example.mygamejava.GameElements;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class HitboxElement {
    protected Rect hitbox;
    protected Rect bounds = new Rect(0, 0, Resources.getSystem().getDisplayMetrics().widthPixels,
            Resources.getSystem().getDisplayMetrics().heightPixels);
    protected Bitmap sprite;
    protected Bitmap sprite2;

    public boolean isOverlapping(HitboxElement other) {
        return hitbox.left < other.hitbox.right && other.hitbox.left < hitbox.right &&
                hitbox.top < other.hitbox.bottom && other.hitbox.top < hitbox.bottom;
    }

    public void move(int xChange, int yChange) {
        hitbox.left += xChange;
        hitbox.right += xChange;
        hitbox.top += yChange;
        hitbox.bottom += yChange;
    }

    public void changeSprite() {
        Bitmap helper = sprite;
        sprite = sprite2;
        sprite2 = helper;
    }
}
