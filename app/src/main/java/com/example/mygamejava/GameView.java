package com.example.mygamejava;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private int wallWidth = 50;
    private Ball ball;
    private Background background;
    private Score score;
    private Food food;

    public GameView(Context context){
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        background = new Background(wallWidth);
        ball = new Ball(wallWidth);
        score = new Score(wallWidth);
        food = new Food(wallWidth);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void changeBallVelocity(float xVel, float yVel){
        ball.setVelocities(xVel, yVel);
    }

    public void update(){
        PlayStatus result = ball.update(food);
        switch (result){
            case SCORE: {
                score.increasePoints();
                food.randomize();
                break;
            }
            case LOSE : {
                score.erasePoints(); break; }
        }
    }

    public void pauseGame(){
        try {
            ball.setVelocities(0,0);
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas!=null){
            background.draw(canvas);
            ball.draw(canvas);
            score.draw(canvas);
            food.draw(canvas);
        }
    }
}
