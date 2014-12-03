package edu.swarthmore.cs.lab3.eracecancer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.String;
import java.lang.StringBuilder;

import org.w3c.dom.Text;

/**
 * Created by agewirt1 on 11/17/14.
 */
public class datequestions extends Activity {

    private static final String TAG = "dateQuestion";
    private Button pdateButton;
    private Button munsureButton;
    private Button mneverButton;
    private Button mNextButton;
    public static final String EXTRA_QNUM = "edu.swarthmore.cs.lab3.eracecancer.date_qnum";
    private int mQnum;

    private int pYear;
    private int pMonth;
    private int pDay;

    TextView mDateTextView;

    static final int DATE_DIALOG_ID = 0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_date);
        mQnum = getIntent().getIntExtra(EXTRA_QNUM, 100);
        mDateTextView = (TextView) findViewById(R.id.date_question_tv);
        if (mQnum == 5){
            mDateTextView.setText(R.string.mammogramQ);
        } else if (mQnum ==6){
            mDateTextView.setText(R.string.breastQ);
        } else if (mQnum == 7){
            mDateTextView.setText(R.string.papQ);
        } else if (mQnum == 8){
            mDateTextView.setText(R.string.colorectalQ);
        }

        pdateButton = (Button) findViewById(R.id.dateButton);
        munsureButton = (Button)findViewById(R.id.unsureButton);
        mneverButton = (Button) findViewById(R.id.neverButton);
        int ans = SurveyStore.get(getApplicationContext()).getAnswer(mQnum);
        if((ans != 0) && (ans != 1) && (ans != 2)){
            String d = String.valueOf(ans);
            pdateButton.setText(d);
        }
        pdateButton.setText("MM/DD/YYYY");
        mNextButton = (Button) findViewById(R.id.nextbutton);
        pdateButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               showDialog(DATE_DIALOG_ID);
                                               pdateButton.setText(new StringBuilder().append(pMonth + 1).append("/").append(pDay).append("/").append(pYear).append(" "));
                                           }
                                       });

        munsureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "about to set answer to" + String.valueOf(1));
                SurveyStore.get(getApplicationContext()).setSurveyAnswers(mQnum, 1);
            }
        });

        mneverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "about to set answer to" + String.valueOf(2));
                SurveyStore.get(getApplicationContext()).setSurveyAnswers(mQnum, 2);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(SurveyStore.get(getApplicationContext()).getAnswer(mQnum) == 0) {
                    Toast.makeText(getApplicationContext(), R.string.answer_q_toast, Toast.LENGTH_SHORT);
                }
                else {
                    setResult(mQnum);
                    finish();
                }
            }

        });
    }

    private DatePickerDialog.OnDateSetListener pDateSetListener=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            pYear = year;
            pMonth = monthOfYear;
            pDay = dayOfMonth;
            String Date = (new StringBuilder().append(pMonth+1).append(pDay).append(pYear)).toString();
            pdateButton.setText(Date);
            int iDate = Integer.parseInt(Date);
            Log.d(TAG, "about to set answer to" + String.valueOf(iDate));
            SurveyStore.get(getApplicationContext()).setSurveyAnswers(mQnum, iDate);
        }
    };

    @Override
    protected Dialog onCreateDialog(int id){
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, pDateSetListener, pYear, pMonth, pDay);
        }
        return null;
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
