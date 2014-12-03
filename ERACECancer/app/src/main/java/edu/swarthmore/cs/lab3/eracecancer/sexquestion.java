package edu.swarthmore.cs.lab3.eracecancer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by agewirt1 on 11/9/14.
 */
public class sexquestion extends Activity {

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    TextView mQText;
    RadioButton mQ1;
    RadioButton mQ2;
    public static final String EXTRA_QNUM = "edu.swarthmore.cs.lab3.eracecancer.two_qnum";
    private int mQnum;
    private static final String TAG = "sexQuestion";
    private Button mNext;
    boolean clicked = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.questions_two);
        mQText = (TextView) findViewById(R.id.two_question_tv);
        mQ1 = (RadioButton) findViewById(R.id.male_button);
        mQ2 = (RadioButton) findViewById(R.id.female_button);
        mNext = (Button) findViewById(R.id.tNext);
        mQnum = getIntent().getIntExtra(EXTRA_QNUM, 100);
        if (mQnum == 0) {
            mQText.setText(R.string.sexQ);
            mQ1.setText(R.string.male);
            mQ2.setText(R.string.female);
        }
        if (mQnum == 4) {
            mQText.setText(R.string.medinsuranceQ);
            mQ1.setText(R.string.yes);
            mQ2.setText(R.string.no);
        }
        int ans = SurveyStore.get(getApplicationContext()).getAnswer(mQnum);
        if (ans != 0) {
            if (ans == 1) {
                mQ1.setChecked(true);
            } else if (ans == 2) {
                mQ2.setChecked(true);
            }
        }

        mNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(SurveyStore.get(getApplicationContext()).getAnswer(mQnum) == 0) {
                    Toast.makeText(getApplicationContext(), R.string.answer_q_toast, Toast.LENGTH_SHORT);
                }
                else {
                    if (clicked == false){
                        if (mQnum == 0){
                            int gender = (SurveyStore.get(getApplicationContext()).getAnswer(mQnum));
                            Intent i = new Intent();
                            i.putExtra("GENDER", gender);
                            setResult(0,i);
                        }
                        else{
                            setResult(mQnum);
                        }
                    }
                    finish();
                }
            }
        });
    }

    public void onRadioButtonClicked(View view){

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.male_button:
                if (checked) {
                    if (mQnum == 0) {
                        Log.d(TAG, "about to set answer to" + String.valueOf(1));
                        SurveyStore.get(getApplicationContext()).setSurveyAnswers(0, 1);
                        //set answers for female-only questions so they are not asked
                        SurveyStore.get(getApplicationContext()).setSurveyAnswers(5, 5);
                        SurveyStore.get(getApplicationContext()).setSurveyAnswers(6, 5);
                        SurveyStore.get(getApplicationContext()).setSurveyAnswers(7, 5);
                        Intent i = new Intent();
                        i.putExtra("GENDER", 1);
                        setResult(0, i);
                        clicked = true;
                    } else {
                        Log.d(TAG, "about to set answer to" + String.valueOf(1));
                        SurveyStore.get(getApplicationContext()).setSurveyAnswers(4, 1);
                        setResult(4);
                        clicked = true;
                    }
                }
                break;
            case R.id.female_button:
                if (checked) {
                    if (mQnum == 0) {
                        Log.d(TAG, "about to set answer to" + String.valueOf(2));
                        SurveyStore.get(getApplicationContext()).setSurveyAnswers(0, 2);
                        Intent i = new Intent();
                        i.putExtra("GENDER", 2);
                        setResult(0, i);
                        clicked = true;
                    } else {
                        Log.d(TAG, "about to set answer to" + String.valueOf(2));
                        SurveyStore.get(getApplicationContext()).setSurveyAnswers(4, 2);
                        setResult(4);
                        clicked = true;
                    }
                }
                break;
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        SurveyStore.get(getApplicationContext()).saveSurveyAnswers();
    }

    @Override
    public void onStop() {
        super.onStop();
        SurveyStore.get(getApplicationContext()).saveSurveyAnswers();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        SurveyStore.get(getApplicationContext()).saveSurveyAnswers();
    }
}