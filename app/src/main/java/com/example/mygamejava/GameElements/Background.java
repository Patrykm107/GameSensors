package com.example.mygamejava.GameElements;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background {

    private Bitmap background;
    private Rect bounds = new Rect(0, 0, Resources.getSystem().getDisplayMetrics().widthPixels,
            Resources.getSystem().getDisplayMetrics().heightPixels);

    public Background(Bitmap bitmap) {
        this.background = bitmap;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(background, null, bounds, null);
    }
}
