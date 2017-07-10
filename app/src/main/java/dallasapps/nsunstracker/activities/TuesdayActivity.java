package dallasapps.nsunstracker.activities;

import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import dallasapps.nsunstracker.R;
import dallasapps.nsunstracker.util.AssistanceAdder;
import dallasapps.nsunstracker.util.WeightCalculator;

import static dallasapps.nsunstracker.util.Converter.convertDpToPixels;
import static dallasapps.nsunstracker.util.StringFormatter.formatWeight;

/**
 * Controller class for Monday nSuns 4
 * The user will press each button in order for however many reps they can get
 * The main buttons will count down from however many reps they start with
 * The 1+ buttons will count up from 0
 *
 * When the user clicks all of them, remove all buttons and display a congratulations post
 * tell the user whether they should increase their 1RM, or keep it the same
 *
 * The user can add accessory excercises (from a searchable list) and select the weight, or body weight, rep, and set count
 */
public class TuesdayActivity extends AppCompatActivity {

    private double squatOneRepMax = 0;
    private double deadliftOneRepMax = 0;
    private boolean isKg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuesday);

        final Vibrator vibr = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // INITIATE BUTTONS
        final Button addAccessoryBtn = (Button) findViewById(R.id.addAccessoryBtn);

        final EditText squat1RMEditText = (EditText) findViewById(R.id.squat1RMEditNumber);
        final EditText deadlift1RMEditText = (EditText) findViewById(R.id.deadlift1RMEditNumber);

        getWeightUnitFromSharedPrefs();

        final WeightCalculator weightCalc = new WeightCalculator(isKg);

        set1RmsFromSharedPrefs();
        setSquatRepWeights(squatOneRepMax, weightCalc);
        setDeadliftRepWeights(deadliftOneRepMax, weightCalc);

        squat1RMEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // DO NOTHING
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // DO NOTHING
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().equals("")) {
                    setSquatRepWeights(Double.parseDouble(editable.toString()), weightCalc);
                    setSquatRepMaxPref(editable.toString());
                }
            }
        });

        deadlift1RMEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // DO NOTHING
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // DO NOTHING
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().equals("")) {
                    setDeadliftRepWeights(Double.parseDouble(editable.toString()), weightCalc);
                    setDeadliftRepMaxPref(editable.toString());
                }
            }
        });

    }


    public void addLayout(View view){
        LinearLayout mainVerticalLayout = (LinearLayout) findViewById(R.id.mainVerticalLayout);
        final Button addAccessoryBtn = (Button) findViewById(R.id.addAccessoryBtn);

        // Remove the button to appear later on the other side of the new card
        mainVerticalLayout.removeView(addAccessoryBtn);
        AssistanceAdder adder = new AssistanceAdder();
        CardView assistanceCardView = adder.createAssistanceLayout(this);

        mainVerticalLayout.addView(assistanceCardView);
        mainVerticalLayout.addView(addAccessoryBtn);
    }



    private void set1RmsFromSharedPrefs() {
        final EditText squat1RMEditText = (EditText) findViewById(R.id.squat1RMEditNumber);
        final EditText deadlift1RMEditText = (EditText) findViewById(R.id.deadlift1RMEditNumber);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.oneRepMaxPrefKey), 0);
        if (sharedPref == null) {
            return;
        }
        String squat1RM = sharedPref.getString(getString(R.string.squat1RMStr), null);
        String deadlift1RM = sharedPref.getString(getString(R.string.deadlift1RMStr), null);

        if (squat1RM != null) {
            if (squat1RM.equals("0.0")) {
                squat1RMEditText.setText("");
            }
            else {
                squat1RMEditText.setText(formatWeight(squat1RM, isKg));
                this.squatOneRepMax = Double.parseDouble(squat1RM);
            }
        }
        if (deadlift1RM != null) {
            if (deadlift1RM.equals("0.0")) {
                deadlift1RMEditText.setText("");
            }
            else {
                deadlift1RMEditText.setText(formatWeight(deadlift1RM, isKg));
                this.deadliftOneRepMax = Double.parseDouble(deadlift1RM);
            }
        }
    }

    private void setSquatRepWeights(double oneRepMax, WeightCalculator weightCalc) {

        final Button squat1Btn = (Button) findViewById(R.id.squat1Btn);
        final Button squat2Btn = (Button) findViewById(R.id.squat2Btn);
        final Button squat3Btn = (Button) findViewById(R.id.squat3Btn);
        final Button squat4Btn = (Button) findViewById(R.id.squat4Btn);
        final Button squat5Btn = (Button) findViewById(R.id.squat5Btn);
        final Button squat6Btn = (Button) findViewById(R.id.squat6Btn);
        final Button squat7Btn = (Button) findViewById(R.id.squat7Btn);
        final Button squat8Btn = (Button) findViewById(R.id.squat8Btn);
        final Button squat9Btn = (Button) findViewById(R.id.squat9Btn);
        if (oneRepMax > 0) {
            squat1Btn.setText(String.format("%s\nx5",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.75), isKg)));
            squat2Btn.setText(String.format("%s\nx3",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.85), isKg)));
            squat3Btn.setText(String.format("%s\nx1+", formatWeight(weightCalc.calculateWeight(oneRepMax, 0.95), isKg)));
            squat4Btn.setText(String.format("%s\nx3",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.90), isKg)));
            squat5Btn.setText(String.format("%s\nx3",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.85), isKg)));
            squat6Btn.setText(String.format("%s\nx3",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.80), isKg)));
            squat7Btn.setText(String.format("%s\nx5",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.75), isKg)));
            squat8Btn.setText(String.format("%s\nx5",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.70), isKg)));
            squat9Btn.setText(String.format("%s\nx5+", formatWeight(weightCalc.calculateWeight(oneRepMax, 0.65), isKg)));
        }

    }

    private void setDeadliftRepWeights(double oneRepMax, WeightCalculator weightCalc) {

        final Button deadlift1Btn = (Button) findViewById(R.id.deadlift1Btn);
        final Button deadlift2Btn = (Button) findViewById(R.id.deadlift2Btn);
        final Button deadlift3Btn = (Button) findViewById(R.id.deadlift3Btn);
        final Button deadlift4Btn = (Button) findViewById(R.id.deadlift4Btn);
        final Button deadlift5Btn = (Button) findViewById(R.id.deadlift5Btn);
        final Button deadlift6Btn = (Button) findViewById(R.id.deadlift6Btn);
        final Button deadlift7Btn = (Button) findViewById(R.id.deadlift7Btn);
        final Button deadlift8Btn = (Button) findViewById(R.id.deadlift8Btn);

        if (oneRepMax > 0) {
            deadlift1Btn.setText(String.format("%s\nx5",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.50), isKg)));
            deadlift2Btn.setText(String.format("%s\nx5",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.60), isKg)));
            deadlift3Btn.setText(String.format("%s\nx3",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.70), isKg)));
            deadlift4Btn.setText(String.format("%s\nx5",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.70), isKg)));
            deadlift5Btn.setText(String.format("%s\nx7",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.70), isKg)));
            deadlift6Btn.setText(String.format("%s\nx4",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.70), isKg)));
            deadlift7Btn.setText(String.format("%s\nx6",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.70), isKg)));
            deadlift8Btn.setText(String.format("%s\nx8",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.70), isKg)));
        }
    }

    public void setSquatRepMaxPref(String squatRepMax) {
        SharedPreferences.Editor sharedPrefEditor = getSharedPreferences(getString(R.string.oneRepMaxPrefKey), 0).edit();
        sharedPrefEditor.putString(getString(R.string.squat1RMStr), squatRepMax);
        sharedPrefEditor.apply();
        sharedPrefEditor.commit();
    }
    public void setDeadliftRepMaxPref(String deadliftRepMax) {
        SharedPreferences.Editor sharedPrefEditor = getSharedPreferences(getString(R.string.oneRepMaxPrefKey), 0).edit();
        sharedPrefEditor.putString(getString(R.string.deadlift1RMStr), deadliftRepMax);
        sharedPrefEditor.apply();
        sharedPrefEditor.commit();
    }

    public void getWeightUnitFromSharedPrefs() {
        SharedPreferences sharedPrefs = getSharedPreferences(getString(R.string.generalPrefKey), 0);
        if (sharedPrefs != null) {
            this.isKg = sharedPrefs.getBoolean(getString(R.string.isKGStr), false);
        }
    }
}
