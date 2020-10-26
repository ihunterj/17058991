package com.example.a17058991;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// The imports below are relevant for moving the apples on the screen.
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.MotionEvent;
import android.view.View.OnClickListener;

// Question related stuff
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

// debug
import android.util.Log;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateQuestion();

        findViewById(R.id.numberZero).setOnClickListener(this);
        findViewById(R.id.numberOne).setOnClickListener(this);
        findViewById(R.id.numberTwo).setOnClickListener(this);
        findViewById(R.id.numberThree).setOnClickListener(this);
        findViewById(R.id.numberFour).setOnClickListener(this);
        findViewById(R.id.numberFive).setOnClickListener(this);
        findViewById(R.id.numberSix).setOnClickListener(this);
        findViewById(R.id.numberSeven).setOnClickListener(this);
        findViewById(R.id.numberEight).setOnClickListener(this);
        findViewById(R.id.numberNine).setOnClickListener(this);

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
        Random r1 =  new Random();
        Random r2 =  new Random();
        int r1result = r1.nextInt(max - min + 1) + min;
        int r2result = r2.nextInt(max - min + 1) + min;
        GlobalC.ans = r1result + r2result;
        if(GlobalC.ans > 10) {
            generateQuestion();
            return;
        }

        Log.d("generateQ", "RANDOM NO1 IS " + r1result + " AND RANDOM NO2 IS " + r2result + " RESULT: (" +  GlobalC.ans + ")");

        TextView questionView = (TextView)findViewById(R.id.questionBox);
        questionView.setText("What is " + r1result + " + " + r2result + "?");
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

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case (R.id.numberOne):
                if(GlobalC.ans == 1) {
                    Log.d("BTNCLICK", "(1) CORRECT!");
                } else {
                    Log.d("BTNCLICK", "(1) INCORRECT!");
                }
                break;
            case (R.id.numberTwo):
                if(GlobalC.ans == 2) {
                    Log.d("BTNCLICK", "(2) CORRECT!");
                } else {
                    Log.d("BTNCLICK", "(2) INCORRECT!");
                }
                break;
            case (R.id.numberThree):
                if(GlobalC.ans == 3) {
                    Log.d("BTNCLICK", "(3) CORRECT!");
                } else {
                    Log.d("BTNCLICK", "(3) INCORRECT!");
                }
                break;
            case (R.id.numberFour):
                if(GlobalC.ans == 4) {
                    Log.d("BTNCLICK", "(4) CORRECT!");
                } else {
                    Log.d("BTNCLICK", "(4) INCORRECT!");
                }
                break;
            case (R.id.numberFive):
                if(GlobalC.ans == 5) {
                    Log.d("BTNCLICK", "(5) CORRECT!");
                } else {
                    Log.d("BTNCLICK", "(5) INCORRECT!");
                }
                break;
            case (R.id.numberSix):
                if(GlobalC.ans == 6) {
                    Log.d("BTNCLICK", "(6) CORRECT!");
                } else {
                    Log.d("BTNCLICK", "(6) INCORRECT!");
                }
                break;
            case (R.id.numberSeven):
                if(GlobalC.ans == 7) {
                    Log.d("BTNCLICK", "(7) CORRECT!");
                } else {
                    Log.d("BTNCLICK", "(7) INCORRECT!");
                }
                break;
            case (R.id.numberEight):
                if(GlobalC.ans == 8) {
                    Log.d("BTNCLICK", "(8) CORRECT!");
                } else {
                    Log.d("BTNCLICK", "(8) INCORRECT!");
                }
                break;
            case (R.id.numberNine):
                if(GlobalC.ans == 9) {
                    Log.d("BTNCLICK", "(9) CORRECT!");
                } else {
                    Log.d("BTNCLICK", "(9) INCORRECT!");
                }
                break;
            default:
                if(GlobalC.ans == 0) {
                    Log.d("BTNCLICK", "(0) CORRECT!");
                } else {
                Log.d("BTNCLICK", "(0) INCORRECT!");
            }
                break;
        }
    }
}
