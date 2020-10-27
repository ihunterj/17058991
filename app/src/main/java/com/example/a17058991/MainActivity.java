package com.example.a17058991;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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

// animation libraries
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation;

// debug
import android.util.Log;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    ImageView starOne;
    ImageView starTwo;

    ScaleAnimation scale;
    TranslateAnimation trans;
    RotateAnimation rotate;
    AlphaAnimation alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateQuestion();

        View correctView;
        correctView = (View) findViewById(R.id.correctView);
        correctView.setVisibility(View.INVISIBLE);

        ImageView starOne;
        starOne = (ImageView) findViewById(R.id.starOne);
        starOne.setVisibility(View.INVISIBLE);

        ImageView starTwo;
        starTwo = (ImageView) findViewById(R.id.starTwo);
        starTwo.setVisibility(View.INVISIBLE);

        findViewById(R.id.starTwo).setVisibility(View.INVISIBLE);

        findViewById(R.id.correctTick).setVisibility(View.INVISIBLE);

        findViewById(R.id.correctTick).setOnClickListener(this);

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

    public void correctAns() {
        Log.d("correctAns", "This will run when the answer is correct");
        View correctView;
        findViewById(R.id.correctView).setVisibility(View.VISIBLE);
        findViewById(R.id.correctTick).setVisibility(View.VISIBLE);
        findViewById(R.id.starOne).setVisibility(View.VISIBLE);
        findViewById(R.id.starTwo).setVisibility(View.VISIBLE);
        findViewById(R.id.starOne).bringToFront();
        findViewById(R.id.starTwo).bringToFront();
        findViewById(R.id.correctTick).bringToFront();

        starOne = (ImageView) findViewById(R.id.starOne);
        scale = new ScaleAnimation(0, 2, 0, 2);
        scale.setDuration(10000);
        scale.setRepeatCount(Animation.INFINITE);
        starOne.startAnimation(scale);

        starTwo = (ImageView) findViewById(R.id.starTwo);
        rotate = new RotateAnimation(0, 300);
        rotate.setDuration(10000);
        scale.setRepeatCount(Animation.INFINITE);
        starTwo.startAnimation(rotate);

        TextView questionView = (TextView)findViewById(R.id.questionBox);
        questionView.setText(GlobalC.rand1 + " + " + GlobalC.rand2 + " = " + GlobalC.ans);
        questionView.setTextColor(Color.parseColor("#00FF00"));
        findViewById(R.id.questionBox).bringToFront();
    }

    public void incorrectAns() {
        Log.d("incorrectAns", "This will run when the answer is incorrect");
    }

    public void generateQuestion() {
        int min = 0;
        int max = 9; // used for the random function.
        Random r1 =  new Random();
        Random r2 =  new Random();
        GlobalC.rand1 = r1.nextInt(max - min + 1) + min;
        GlobalC.rand2 = r2.nextInt(max - min + 1) + min;
        GlobalC.ans = GlobalC.rand1 + GlobalC.rand2;
        if(GlobalC.ans > 9) {
            generateQuestion();
            return;
        }

        Log.d("generateQ", "RANDOM NO1 IS " + GlobalC.rand1 + " AND RANDOM NO2 IS " + GlobalC.rand2 + " RESULT: (" +  GlobalC.ans + ")");

        TextView questionView = (TextView)findViewById(R.id.questionBox);
        questionView.setText("What is " + GlobalC.rand1 + " + " + GlobalC.rand2 + "?");
    }

    public void resetView() {
        starOne.clearAnimation();
        starTwo.clearAnimation();
        View correctView;
        findViewById(R.id.correctView).setVisibility(View.INVISIBLE);
        findViewById(R.id.correctTick).setVisibility(View.INVISIBLE);
        findViewById(R.id.starOne).setVisibility(View.INVISIBLE);
        findViewById(R.id.starTwo).setVisibility(View.INVISIBLE);
        TextView questionView = (TextView)findViewById(R.id.questionBox);
        questionView.setTextColor(Color.parseColor("#7d7d7d"));
        generateQuestion();
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
            case(R.id.correctTick):
                resetView();
                break;
            case (R.id.numberOne):
                if(GlobalC.ans == 1) {
                    correctAns();
                } else {
                    incorrectAns();
                }
                break;
            case (R.id.numberTwo):
                if(GlobalC.ans == 2) {
                    correctAns();
                } else {
                    incorrectAns();
                }
                break;
            case (R.id.numberThree):
                if(GlobalC.ans == 3) {
                    correctAns();
                } else {
                    incorrectAns();
                }
                break;
            case (R.id.numberFour):
                if(GlobalC.ans == 4) {
                    correctAns();
                } else {
                    incorrectAns();
                }
                break;
            case (R.id.numberFive):
                if(GlobalC.ans == 5) {
                    correctAns();
                } else {
                    incorrectAns();
                }
                break;
            case (R.id.numberSix):
                if(GlobalC.ans == 6) {
                    correctAns();
                } else {
                    incorrectAns();
                }
                break;
            case (R.id.numberSeven):
                if(GlobalC.ans == 7) {
                    correctAns();
                } else {
                    incorrectAns();
                }
                break;
            case (R.id.numberEight):
                if(GlobalC.ans == 8) {
                    correctAns();
                } else {
                    incorrectAns();
                }
                break;
            case (R.id.numberNine):
                if(GlobalC.ans == 9) {
                    correctAns();
                } else {
                    incorrectAns();
                }
                break;
            default:
                if(GlobalC.ans == 0) {
                    correctAns();
                } else {
                    incorrectAns();
                }
                break;
            }
        }
}
