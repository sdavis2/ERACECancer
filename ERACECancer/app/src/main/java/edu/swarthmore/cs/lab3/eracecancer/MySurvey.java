package edu.swarthmore.cs.lab3.eracecancer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MySurvey extends Activity {

    private boolean mIsFemale;
    private ArrayList <SurveyAnswer> mSurveyAnswers;
    private static final String TAG = "MySurvey";
    private Button mViewList;
    private Button mViewSurvey;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        mSurveyAnswers = SurveyStore.get(getApplicationContext()).getSurveyAnswers();
        int Size = mSurveyAnswers.size();
        String s = String.valueOf(Size);
        Log.d(TAG, "size of mSurveyAnswers: "+s);
        if (mSurveyAnswers.get(0).getAnswer() == 0) {
            Log.d(TAG, "about to call Q0");
            Intent i = new Intent(MySurvey.this, sexquestion.class);
            i.putExtra(sexquestion.EXTRA_QNUM, 0);
            startActivityForResult(i, 0);
        } else if (mSurveyAnswers.get(1).getAnswer() == 0) {
            Log.d(TAG, "about to call Q1 - if");
            Intent i = new Intent(MySurvey.this, AgeQuestion.class);
            i.putExtra(AgeQuestion.EXTRA_QNUM, 1);
            startActivityForResult(i, 1);
        } else if (mSurveyAnswers.get(2).getAnswer() == 0) {
            Log.d(TAG, "about to call Q2 - if");
            Intent i = new Intent(MySurvey.this, ethnicityquestion.class);
            startActivityForResult(i, 2);
        } else if (mSurveyAnswers.get(3).getAnswer() == 0) {
            Log.d(TAG, "about to call Q3 - if");
            Intent i = new Intent(MySurvey.this, fivequestions.class);
            i.putExtra(fivequestions.EXTRA_QNUM, 3);
            startActivityForResult(i, 3);
        } else if (mSurveyAnswers.get(4).getAnswer() == 0) {
            Log.d(TAG, "about to call Q4 - if");
            Intent i = new Intent(MySurvey.this, sexquestion.class);
            i.putExtra(sexquestion.EXTRA_QNUM, 4);
            startActivityForResult(i, 4);
        } else if (mSurveyAnswers.get(5).getAnswer() == 0) {
            Log.d(TAG, "about to call Q5 - if");
            Intent i = new Intent(MySurvey.this, datequestions.class);
            i.putExtra(datequestions.EXTRA_QNUM, 5);
            startActivityForResult(i, 5);
        } else if (mSurveyAnswers.get(6).getAnswer() == 0) {
            Log.d(TAG, "about to call Q6 - if");
            Intent i = new Intent(MySurvey.this, datequestions.class);
            i.putExtra(datequestions.EXTRA_QNUM, 6);
            startActivityForResult(i, 6);
        } else if (mSurveyAnswers.get(7).getAnswer() == 0) {
            Log.d(TAG, "about to call Q7 - if");
            Intent i = new Intent(MySurvey.this, datequestions.class);
            i.putExtra(datequestions.EXTRA_QNUM, 7);
            startActivityForResult(i, 7);
        } else if (mSurveyAnswers.get(8).getAnswer() == 0) {
            Log.d(TAG, "about to call Q8 - if");
            Intent i = new Intent(MySurvey.this, datequestions.class);
            i.putExtra(datequestions.EXTRA_QNUM, 8);
            startActivityForResult(i, 8);
        } else if (mSurveyAnswers.get(9).getAnswer() == 0) {
            Log.d(TAG, "about to call Q9 - if");
            Intent i = new Intent(MySurvey.this, AgeQuestion.class);
            i.putExtra(AgeQuestion.EXTRA_QNUM, 9);
            startActivityForResult(i, 9);
        } else if (mSurveyAnswers.get(10).getAnswer() == 0) {
            Log.d(TAG, "about to call Q10 - if");
            Intent i = new Intent(MySurvey.this, fivequestions.class);
            i.putExtra(fivequestions.EXTRA_QNUM, 10);
            startActivityForResult(i, 10);
        } else if (mSurveyAnswers.get(11).getAnswer() == 0) {
            Log.d(TAG, "about to call Q11 - if");
            Intent i = new Intent(MySurvey.this, fivequestions.class);
            i.putExtra(fivequestions.EXTRA_QNUM, 11);
            startActivityForResult(i, 11);
        }
        setContentView(R.layout.activity_my_survey);
        mViewList = (Button) findViewById(R.id.list);
        mViewSurvey = (Button) findViewById(R.id.survey);

        mViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(MySurvey.this, SuggestionListActivity.class);
                startActivity(j);
            }
        });
        mViewSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MySurvey.this, sexquestion.class);
                i.putExtra(sexquestion.EXTRA_QNUM, 0);
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, String.valueOf(requestCode));
        switch (requestCode){
            case 0:
                Log.d(TAG, "case 0");
                int gender = data.getIntExtra("GENDER", 0);
                if (gender == 1){
                    mIsFemale = false;
                }
                Intent i1 = new Intent(MySurvey.this, AgeQuestion.class);
                i1.putExtra(AgeQuestion.EXTRA_QNUM, 1);
                startActivityForResult(i1, 1);
                break;
            case 1:
                Log.d(TAG, "case 1");
                Intent i2 = new Intent(MySurvey.this, ethnicityquestion.class);
                startActivityForResult(i2, 2);
                break;
            case 2:
                Log.d(TAG, "case 2");
                Intent i3 = new Intent(MySurvey.this, fivequestions.class);
                i3.putExtra(fivequestions.EXTRA_QNUM, 3);
                startActivityForResult(i3, 3);
                break;
            case 3:
                Log.d(TAG, "case 3");
                Intent i4 = new Intent(MySurvey.this, sexquestion.class);
                i4.putExtra(sexquestion.EXTRA_QNUM, 4);
                startActivityForResult(i4, 4);
                break;
            case 4:
                if(mIsFemale) {
                    Log.d(TAG, "case 4");
                    Intent i5 = new Intent(MySurvey.this, datequestions.class);
                    i5.putExtra(datequestions.EXTRA_QNUM, 5);
                    startActivityForResult(i5, 5);
                }
                else {
                    Log.d(TAG, "case 4");
                    Intent i = new Intent(MySurvey.this, datequestions.class);
                    i.putExtra(datequestions.EXTRA_QNUM, 8);
                    startActivityForResult(i, 8);
                }
                break;
            case 5:
                Log.d(TAG, "case 5");
                Intent i6 = new Intent(MySurvey.this, datequestions.class);
                i6.putExtra(datequestions.EXTRA_QNUM, 6);
                startActivityForResult(i6, 6);
                break;
            case 6:
                Log.d(TAG, "case 6");
                Intent i7 = new Intent(MySurvey.this, datequestions.class);
                i7.putExtra(datequestions.EXTRA_QNUM, 7);
                startActivityForResult(i7, 7);
                break;
            case 7:
                Log.d(TAG, "case 7");
                Intent i8 = new Intent(MySurvey.this, datequestions.class);
                i8.putExtra(datequestions.EXTRA_QNUM, 8);
                startActivityForResult(i8, 8);
                break;
            case 8:
                Log.d(TAG, "case 8");
                Intent i9 = new Intent(MySurvey.this, AgeQuestion.class);
                i9.putExtra(AgeQuestion.EXTRA_QNUM, 9);
                startActivityForResult(i9, 9);
                break;
            case 9:
                Log.d(TAG, "case 9");
                Intent i0 = new Intent(MySurvey.this, fivequestions.class);
                i0.putExtra(fivequestions.EXTRA_QNUM, 10);
                startActivityForResult(i0, 10);
                break;
            case 10:
                Log.d(TAG, "case 10");
                Intent i = new Intent(MySurvey.this, fivequestions.class);
                i.putExtra(fivequestions.EXTRA_QNUM, 11);
                startActivityForResult(i, 11);
                break;
            case 11:
                Log.d(TAG, "case 11");
                Intent j = new Intent(MySurvey.this, SuggestionListActivity.class);
                startActivity(j);
                break;
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_survey, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
