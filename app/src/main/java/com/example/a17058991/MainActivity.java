package com.example.a17058991;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer; // used for music
import android.os.Bundle;

// The imports below are relevant for moving the apples on the screen.
import android.view.View;
import android.widget.ImageView;
import android.view.MotionEvent;
import android.view.View.OnClickListener;

// Question related stuff
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
        correctView = (View) findViewById(R.id.ansView);
        correctView.setVisibility(View.INVISIBLE);

        ImageView starOne;
        starOne = (ImageView) findViewById(R.id.starOne);
        starOne.setVisibility(View.INVISIBLE);

        ImageView starTwo;
        starTwo = (ImageView) findViewById(R.id.starTwo);
        starTwo.setVisibility(View.INVISIBLE);

        findViewById(R.id.starTwo).setVisibility(View.INVISIBLE);

        findViewById(R.id.replay).setVisibility(View.INVISIBLE);

        findViewById(R.id.replay).setOnClickListener(this);

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
        View ansView;
        findViewById(R.id.ansView).setVisibility(View.VISIBLE);
        findViewById(R.id.replay).setVisibility(View.VISIBLE);
        findViewById(R.id.starOne).setVisibility(View.VISIBLE);
        findViewById(R.id.starTwo).setVisibility(View.VISIBLE);
        findViewById(R.id.starOne).bringToFront();
        findViewById(R.id.starTwo).bringToFront();
        findViewById(R.id.replay).bringToFront();

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

        // code for score
        GlobalC.score++; // +1 to score
        GlobalC.questionProg++; // +1 to prog

        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.correct);
        mPlayer.start();

        GlobalC.res = 1;
    }

    public void incorrectAns() {
        Log.d("incorrectAns", "This will run when the answer is incorrect"); // debug, ensuring that the function is called appropriately (prior to the rest of the function being made)
        View ansView; // defines a View
        findViewById(R.id.ansView).setVisibility(View.VISIBLE); // sets the answer view to visible
        findViewById(R.id.replay).setVisibility(View.VISIBLE); // sets replay button to visible
        findViewById(R.id.replay).bringToFront(); // brings the button to the front so it can be seen by the user
        TextView questionView = (TextView)findViewById(R.id.questionBox);  // defines the questionView
        questionView.setText(GlobalC.rand1 + " + " + GlobalC.rand2 + " = " + GlobalC.ans); // This changes the questionView text to reflect on the generated question and the correct answer, so the user can see the correct answer in any instance.
        questionView.setTextColor(Color.parseColor("#FF0000")); //  Sets the colour to red so the user can see it is wrong
        findViewById(R.id.questionBox).bringToFront(); // Ensures the question box is visible to the user by bringing it in front of other layers.

        // code for score
        GlobalC.questionProg++; //+1 to prog

        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.incorrect); // Defines the mediaplayer so we can reference the 'incorrect.mp3' file
        mPlayer.start(); // plays the music file reference in the line above
    }

    public void generateQuestion() {
        int min = 1; // used for the random function.
        int max = 9; // used for the random function.
        Random r1 =  new Random();
        Random r2 =  new Random();
        GlobalC.rand1 = r1.nextInt(max - min + 1) + min; // r1 is provided with a random value between the max and min numbers defined above
        GlobalC.rand2 = r2.nextInt(max - min + 1) + min; // r2 is provided with a random value between the max and min numbers defined above
        GlobalC.ans = GlobalC.rand1 + GlobalC.rand2; // the answer is stored in GlobalC.ans based off the result of GlobalC.rand1 + GlobalC.rand2
        if(GlobalC.ans > 9) { // If the answer is greater than 9..
            generateQuestion(); // Re-run the function, essentially looping until the point the answer works out to be equal to or less than 9.
            return;
        }

        Log.d("generateQ", "RANDOM NO1 IS " + GlobalC.rand1 + " AND RANDOM NO2 IS " + GlobalC.rand2 + " RESULT: (" +  GlobalC.ans + ")"); // debug, used in early stages of development to ensure the random generation function is working

        TextView questionView = (TextView)findViewById(R.id.questionBox); // defines questionView
        questionView.setText("What is " + GlobalC.rand1 + " + " + GlobalC.rand2 + "?"); // once the question is generated it changes the text contained in questionView to reflect on the question that has just been generated.
    }

    public void resetView() {
        View ansView; // defines the view
        findViewById(R.id.ansView).setVisibility(View.INVISIBLE); // Makes the ansView invisible
        findViewById(R.id.replay).setVisibility(View.INVISIBLE);  // Makes the replay button invisible
        findViewById(R.id.starOne).setVisibility(View.INVISIBLE); // Makes the starOne invisible
        findViewById(R.id.starTwo).setVisibility(View.INVISIBLE); // Makes the starTwo invisible
        if(GlobalC.res == 1) { // If the variable GlobalC.res is equal to 1 then..
            starOne.clearAnimation(); // Clears the running animation on StarOne
            starTwo.clearAnimation(); // Clears the running animation on StarTwo
        }
        GlobalC.res = 0; // Sets the value of GlobalC.res to 0.
        TextView questionView = (TextView)findViewById(R.id.questionBox);
        questionView.setTextColor(Color.parseColor("#7d7d7d")); // This sets the text colour to its default colour using hex value.
        TextView scoreBox = (TextView)findViewById(R.id.scoreBox);  // defines the scoreBox
        scoreBox.setText("Score: " + GlobalC.score); // updates the score on screen
        generateQuestion(); // Runs the generateQuestion(); function
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
        switch(v.getId()) // We can open a switch case to efficiently work through the button clicks.
        {
            case(R.id.replay):
                resetView(); // This calls the resetView(); function given the replay button is clicked.
                break;
            case (R.id.numberOne):
                if(GlobalC.ans == 1) { // If the value of GlobalC.ans is equal to 1..
                    correctAns(); // This calls the correctAns(); function given the button numbered 1 is clicked
                } else { // If GlobalC.ans is not equal to the given number above then..
                    incorrectAns(); // This calls the incorrectAns(); function given the button numbered 1 is clicked
                }
                break; // Breaks (ends) the case loop
            case (R.id.numberTwo):
                if(GlobalC.ans == 2) { // If the value of GlobalC.ans is equal to 2..
                    correctAns(); // This calls the correctAns(); function given the button numbered 2 is clicked
                } else { // If GlobalC.ans is not equal to the given number above then..
                    incorrectAns(); // This calls the incorrectAns(); function given the button numbered 2 is clicked
                }
                break; // Breaks (ends) the case loop
            case (R.id.numberThree):
                if(GlobalC.ans == 3) { // If the value of GlobalC.ans is equal to 3..
                    correctAns(); // This calls the correctAns(); function given the button numbered 3 is clicked
                } else { // If GlobalC.ans is not equal to the given number above then..
                    incorrectAns(); // This calls the incorrectAns(); function given the button numbered 3 is clicked
                }
                break; // Breaks (ends) the case loop
            case (R.id.numberFour):
                if(GlobalC.ans == 4) { // If the value of GlobalC.ans is equal to 4..
                    correctAns(); // This calls the correctAns(); function given the button numbered 4 is clicked
                } else { // If GlobalC.ans is not equal to the given number above then..
                    incorrectAns(); // This calls the incorrectAns(); function given the button numbered 4 is clicked
                }
                break; // Breaks (ends) the case loop
            case (R.id.numberFive):
                if(GlobalC.ans == 5) { // If the value of GlobalC.ans is equal to 5..
                    correctAns(); // This calls the correctAns(); function given the button numbered 5 is clicked
                } else { // If GlobalC.ans is not equal to the given number above then..
                    incorrectAns(); // This calls the incorrectAns(); function given the button numbered 5 is clicked
                }
                break; // Breaks (ends) the case loop
            case (R.id.numberSix):
                if(GlobalC.ans == 6) {  // If the value of GlobalC.ans is equal to 6..
                    correctAns(); // This calls the correctAns(); function given the button numbered 6 is clicked
                } else { // If GlobalC.ans is not equal to the given number above then..
                    incorrectAns(); // This calls the incorrectAns(); function given the button numbered 6 is clicked
                }
                break; // Breaks (ends) the case loop
            case (R.id.numberSeven):
                if(GlobalC.ans == 7) { // If the value of GlobalC.ans is equal to 7..
                    correctAns(); // This calls the correctAns(); function given the button numbered 7 is clicked
                } else { // If GlobalC.ans is not equal to the given number above then..
                    incorrectAns(); // This calls the incorrectAns(); function given the button numbered 7 is clicked
                }
                break; // Breaks (ends) the case loop
            case (R.id.numberEight):
                if(GlobalC.ans == 8) { // If the value of GlobalC.ans is equal to 8..
                    correctAns(); // This calls the correctAns(); function given the button numbered 8 is clicked
                } else { // If GlobalC.ans is not equal to the given number above then..
                    incorrectAns(); // This calls the incorrectAns(); function given the button numbered 8 is clicked
                }
                break; // Breaks (ends) the case loop
            case (R.id.numberNine):
                if(GlobalC.ans == 9) { // If the value of GlobalC.ans is equal to 9..
                    correctAns(); // This calls the correctAns(); function given the button numbered 9 is clicked
                } else { // If GlobalC.ans is not equal to the given number above then..
                    incorrectAns();
                }
                break; // Breaks (ends) the case loop
            default: // This is the DEFAULT case which means say a button is on screen yet not defined, it will run this if statement. This will never happen because every button is accounted for. The only button technically "undefined" here is the button number '0', so it runs this loop. Every switch requires a default case, so I have used it in this fashion.
                if(GlobalC.ans == 0) { // If the value of GlobalC.ans is equal to 0..
                    correctAns(); // This calls the correctAns(); function given the button numbered 0 is clicked
                } else { // If GlobalC.ans is not equal to the given number above then..
                    incorrectAns(); // This calls the incorrectAns(); function given the button numbered 0 is clicked
                }
                break; // Breaks (ends) the case loop
            }
        }
}
