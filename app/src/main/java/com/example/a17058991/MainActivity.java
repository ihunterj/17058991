package com.example.a17058991;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// The imports below are relevant for moving the apples on the screen.
import android.view.View;
import android.widget.ImageView;
import android.view.MotionEvent;

// Question related stuff
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

// debug
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generateQuestion();
        setContentView(R.layout.activity_main);

        ImageView appleOne = (ImageView) findViewById(R.id.appleOne);
        appleOne.setOnTouchListener(handleTouch);

        ImageView appleTwo = (ImageView) findViewById(R.id.appleTwo);
        appleTwo.setOnTouchListener(handleTouch);

        ImageView appleThree = (ImageView) findViewById(R.id.appleThree);
        appleThree.setOnTouchListener(handleTouch);

        ImageView appleFour = (ImageView) findViewById(R.id.appleFour);
        appleFour.setOnTouchListener(handleTouch);

        ImageView appleFive = (ImageView) findViewById(R.id.appleFive);
        appleFive.setOnTouchListener(handleTouch);

        ImageView appleSix = (ImageView) findViewById(R.id.appleSix);
        appleSix.setOnTouchListener(handleTouch);

        ImageView appleSeven = (ImageView) findViewById(R.id.appleSeven);
        appleSeven.setOnTouchListener(handleTouch);

        ImageView appleEight = (ImageView) findViewById(R.id.appleEight);
        appleEight.setOnTouchListener(handleTouch);

        ImageView appleNine = (ImageView) findViewById(R.id.appleNine);
        appleNine.setOnTouchListener(handleTouch);

        ImageView appleTen = (ImageView) findViewById(R.id.appleTen);
        appleTen.setOnTouchListener(handleTouch);
    }

    public void generateQuestion() {
        int min = 0;
        int max = 9; // used for the random function.
        Random r =  new Random();
        int result = r.nextInt(max - min + 1) + min;
        Log.d("generateQ", "RANDOM NUMBER IS " + result);
        EditText questionBox;
        questionBox =  (EditText)findViewById(R.id.questionBox);
    }

    public void selectedAnswer (View answerView) {

    }

    private View.OnTouchListener handleTouch = new View.OnTouchListener() {
        float dX, dY;
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    dX = view.getX() - event.getRawX();
                    dY = view.getY() - event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    view.animate()
                            .x(event.getRawX() + dX)
                            .y(event.getRawY() + dY)
                            .setDuration(0)
                            .start();
                    break;
                default:
                    return false;
            }
            return true;
        }
    };
}
