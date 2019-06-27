package org.demo.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.demo.geoquiz.model.Question;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mPrevButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    private static final Question[] mQuestionBank = new Question[]
            {
                    new Question("Capital of india is New Delhi.", true),
                    new Question("Capital of USA is Washington DC.", true),
                    new Question("Capital of Bangladesh is Jakarta.", false),
                    new Question("The Pacific Ocean is larger than the Atlantic Ocean", false),
                    new Question("The Suez Canal connects the Red Sea and the Indian Ocean", true),
                    new Question("The source of the Nile River is in Egypt", true),
            };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle): called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswer(true);
                        }
                });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswer(false);
                    }
                });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex +1 ) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPrevButton = (Button) findViewById(R.id.previous_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex != 0){
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                    updateQuestion();
                }
            }
        });

    }

    private void updateQuestion() {
        String questionText = mQuestionBank[mCurrentIndex].getQuestionText();
        mQuestionTextView.setText(questionText);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        //correctToast.setGravity(Gravity.TOP,0,0);      // To set position to appear on top
        toast.show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState: called");
        savedInstanceState.putInt(KEY_INDEX , mCurrentIndex);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart(): called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume(): called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause(): called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop(): called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy(): called");
    }

}