package com.example.mygamejava;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.example.mygamejava.GameElements.Background;
import com.example.mygamejava.GameElements.Character;
import com.example.mygamejava.GameElements.Enemy;
import com.example.mygamejava.GameElements.Food;
import com.example.mygamejava.GameElements.Score;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Character character;
    private Background background;
    private Score score;
    private Food food;
    private Enemy enemy;

    private static final String HISCORE_SHARED_PREF = "HISCORE_pref_keY";
    private static final String HISCORE_KEY = "hiSCORE";

    public GameView(Context context){
        super(context);
        getHolder().addCallback(this);

        setFocusable(true);

        SharedPreferences sharedPreferences = context.getSharedPreferences(HISCORE_SHARED_PREF,Context.MODE_PRIVATE);
        int hiscore = sharedPreferences.getInt(HISCORE_KEY, 0);
        background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background));
        character = new Character(BitmapFactory.decodeResource(getResources(),R.drawable.head),
                BitmapFactory.decodeResource(getResources(),R.drawable.mario));
        score = new Score(hiscore);
        food = new Food(BitmapFactory.decodeResource(getResources(),R.drawable.access),
                BitmapFactory.decodeResource(getResources(),R.drawable.coin));
        enemy = new Enemy(BitmapFactory.decodeResource(getResources(),R.drawable.enemy),
                BitmapFactory.decodeResource(getResources(),R.drawable.bowser));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

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

    public void changeCharacterVelocity(int xVel, int yVel){
        character.setVelocities(xVel, yVel);
    }

    public void update(){
        enemy.update();
        if(character.update() && !character.isOverlapping(enemy)){
            if(character.isOverlapping(food)) {
                if (score.increasePoints()) {
                    character.increaseAcceleration();
                    enemy.makeStronger();
                }
                food.randomize();
                while (food.isOverlapping(character)) food.randomize();
            }
        }else{
            resetGame();
        }
    }

    public void resetGame(){
        score.erasePoints();
        character.reset();
        food.randomize();
        enemy.reset();
    }

    public void pauseGame(){
        thread.setRunning(false);
    }

    public void resumeGame(){
        thread = new MainThread(getHolder(),this);
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        character.changeSprite();
        enemy.changeSprite();
        food.changeSprite();

        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas!=null){
            background.draw(canvas);
            character.draw(canvas);
            food.draw(canvas);
            enemy.draw(canvas);
            score.draw(canvas);
        }
    }

    public void saveHiScore(){
        Context context = getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(HISCORE_SHARED_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(HISCORE_KEY, score.hiscore);
        editor.apply();
    }
}
