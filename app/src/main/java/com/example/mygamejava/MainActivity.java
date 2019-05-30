package com.example.mygamejava;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    private SensorManager sensorManager;
    private Sensor gyroSensor, proximitySensor;
    private SensorEventListener gyroListener, proximityListener;
    private GameView gameView;
    private final static int CHECKING_FREQ = 2 * 1000 * 1000; //every 2 seconds
    private boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        gameView= new GameView(this);
        setContentView(gameView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroListener = getGyroListener(gameView);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        proximityListener = getProximitySensorListener(gameView, proximitySensor);

        sensorManager.registerListener(gyroListener, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(proximityListener, proximitySensor, CHECKING_FREQ);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(gyroListener);
        sensorManager.unregisterListener(proximityListener);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        gameView.saveHiScore();
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(gyroListener, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(proximityListener, proximitySensor, CHECKING_FREQ);
        super.onResume();
    }

    public SensorEventListener getGyroListener(final GameView gameView){
        return new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent != null) gameView.changeCharacterVelocity((int)-sensorEvent.values[0],(int) sensorEvent.values[1]);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
    }

    public SensorEventListener getProximitySensorListener(final GameView gameView, final Sensor proximitySensor) {
          return new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[0] < proximitySensor.getMaximumRange()) {
                    if(isRunning){
                        gameView.pauseGame();
                        isRunning=false;
                    }
                    else {
                        gameView.resumeGame();
                        isRunning=true;
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
    }
}