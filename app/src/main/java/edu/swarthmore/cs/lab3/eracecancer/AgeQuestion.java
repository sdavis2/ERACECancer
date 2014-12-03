package edu.swarthmore.cs.lab3.eracecancer;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by agewirt1 on 11/17/14.
 */
public class AgeQuestion extends Activity {



    private EditText mEdit;
    private static final String TAG = "AgeQuestion";
    public static final String EXTRA_QNUM = "edu.swarthmore.cs.lab3.eracecancer.age_qnum";
    private int mQnum;
    private SurveyAnswer sa;
    TextView mQuestion;
    private Button mNext;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.question_age);

        mQnum = getIntent().getIntExtra(EXTRA_QNUM, 1);
        Log.d(TAG, String.valueOf(mQnum));
        sa = SurveyStore.get(getApplicationContext()).getSurveyAnswer(mQnum);

        mQuestion = (TextView) findViewById(R.id.ageQ_text_view);
        mEdit = (EditText) findViewById(R.id.userAge);
        mNext = (Button) findViewById(R.id.next_button);

        if (sa.getAnswer() != 0) {
            mEdit.setText(String.valueOf(sa.getAnswer()));
        }
        if (mQnum == 1){
            mQuestion.setText(R.string.ageQ);
            mEdit.setHint(R.string.agehint);
        } else if (mQnum == 9){
            mQuestion.setText(R.string.zipcodeQ);
            mEdit.setHint(R.string.zipcodehint);
        }
        mNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(SurveyStore.get(getApplicationContext()).getAnswer(mQnum) == 0) {
                    Toast.makeText(getApplicationContext(), R.string.answer_q_toast, Toast.LENGTH_SHORT);
                }
                else {
                    String input = mEdit.getText().toString();
                    int mtxt = Integer.parseInt(input);
                    Log.d(TAG, "about to set answer to"+String.valueOf(mtxt));
                    SurveyStore.get(getApplicationContext()).setSurveyAnswers(mQnum, mtxt);
                    setResult(mQnum);
                    finish();
                }
            }

        });
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
