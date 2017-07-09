package dallasapps.nsunstracker.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import dallasapps.nsunstracker.R;
import dallasapps.nsunstracker.util.WeightCalculator;

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
public class MondayActivity extends AppCompatActivity {

    private double benchOneRepMax = 0;
    private double ohpOneRepMax = 0;
    private boolean isKg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);

        final Vibrator vibr = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        // INITIATE BUTTONS
        final Button addAccessoryBtn = (Button) findViewById(R.id.addAccessoryBtn);

        final EditText bench1RMEditText = (EditText) findViewById(R.id.bench1RMEditNumber);
        final EditText ohp1RMEditText = (EditText) findViewById(R.id.ohp1RMEditNumber);

        final WeightCalculator weightCalc = new WeightCalculator(isKg);

        set1RmsFromSharedPrefs();

        setBenchRepWeights(benchOneRepMax, weightCalc);
        setOhpRepWeights(ohpOneRepMax, weightCalc);

        bench1RMEditText.addTextChangedListener(new TextWatcher() {
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
                    setBenchRepWeights(Double.parseDouble(editable.toString()), weightCalc);
                    setBenchRepMaxPref(editable.toString());
                }
            }
        });

        ohp1RMEditText.addTextChangedListener(new TextWatcher() {
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
                    setOhpRepWeights(Double.parseDouble(editable.toString()), weightCalc);
                    setOhpRepMaxPref(editable.toString());
                }
            }
        });

    }



    private void set1RmsFromSharedPrefs() {
        final EditText bench1RMEditText = (EditText) findViewById(R.id.bench1RMEditNumber);
        final EditText ohp1RMEditText = (EditText) findViewById(R.id.ohp1RMEditNumber);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.oneRepMaxPrefKey), 0);
        if (sharedPref == null) {
            return;
        }
        String bench1RM = sharedPref.getString(getString(R.string.bench1RMStr), null);
        String ohp1RM = sharedPref.getString(getString(R.string.ohp1RMStr), null);

        if (bench1RM != null) {
            if (bench1RM.equals("0.0")) {
                bench1RMEditText.setText(bench1RM);
            }
            else {
                bench1RMEditText.setText(bench1RM);
            }
        }
        if (ohp1RM != null) {
            if (ohp1RM.equals("0.0")) {
                ohp1RMEditText.setText("");
            }
            else {
                ohp1RMEditText.setText(ohp1RM);
            }
        }
    }

    private void setBenchRepWeights(double oneRepMax, WeightCalculator weightCalc) {

        final Button bench1Btn = (Button) findViewById(R.id.bench1Btn);
        final Button bench2Btn = (Button) findViewById(R.id.bench2Btn);
        final Button bench3Btn = (Button) findViewById(R.id.bench3Btn);
        final Button bench4Btn = (Button) findViewById(R.id.bench4Btn);
        final Button bench5Btn = (Button) findViewById(R.id.bench5Btn);
        final Button bench6Btn = (Button) findViewById(R.id.bench6Btn);
        final Button bench7Btn = (Button) findViewById(R.id.bench7Btn);
        final Button bench8Btn = (Button) findViewById(R.id.bench8Btn);
        final Button bench9Btn = (Button) findViewById(R.id.bench9Btn);
        if (oneRepMax > 0) {
            if(!isKg) {
                bench1Btn.setText(String.format("%s\nx8", (int) weightCalc.calculateBench1(oneRepMax)));
                bench2Btn.setText(String.format("%s\nx6", (int) weightCalc.calculateBench2(oneRepMax)));
                bench3Btn.setText(String.format("%s\nx4", (int) weightCalc.calculateBench3(oneRepMax)));
                bench4Btn.setText(String.format("%s\nx4", (int) weightCalc.calculateBench4(oneRepMax)));
                bench5Btn.setText(String.format("%s\nx4", (int) weightCalc.calculateBench5(oneRepMax)));
                bench6Btn.setText(String.format("%s\nx5", (int) weightCalc.calculateBench6(oneRepMax)));
                bench7Btn.setText(String.format("%s\nx6", (int) weightCalc.calculateBench7(oneRepMax)));
                bench8Btn.setText(String.format("%s\nx7", (int) weightCalc.calculateBench8(oneRepMax)));
                bench9Btn.setText(String.format("%s\nx8+", (int) weightCalc.calculateBench9(oneRepMax)));
            }
            else {
                bench1Btn.setText(String.format("%s\nx8",  weightCalc.calculateBench1(oneRepMax)));
                bench2Btn.setText(String.format("%s\nx6",  weightCalc.calculateBench2(oneRepMax)));
                bench3Btn.setText(String.format("%s\nx4",  weightCalc.calculateBench3(oneRepMax)));
                bench4Btn.setText(String.format("%s\nx4",  weightCalc.calculateBench4(oneRepMax)));
                bench5Btn.setText(String.format("%s\nx4",  weightCalc.calculateBench5(oneRepMax)));
                bench6Btn.setText(String.format("%s\nx5",  weightCalc.calculateBench6(oneRepMax)));
                bench7Btn.setText(String.format("%s\nx6",  weightCalc.calculateBench7(oneRepMax)));
                bench8Btn.setText(String.format("%s\nx7",  weightCalc.calculateBench8(oneRepMax)));
                bench9Btn.setText(String.format("%s\nx8+", weightCalc.calculateBench9(oneRepMax)));
            }
        }

    }

    private void setOhpRepWeights(double oneRepMax, WeightCalculator weightCalc) {

        final Button ohp1Btn = (Button) findViewById(R.id.ohp1Btn);
        final Button ohp2Btn = (Button) findViewById(R.id.ohp2Btn);
        final Button ohp3Btn = (Button) findViewById(R.id.ohp3Btn);
        final Button ohp4Btn = (Button) findViewById(R.id.ohp4Btn);
        final Button ohp5Btn = (Button) findViewById(R.id.ohp5Btn);
        final Button ohp6Btn = (Button) findViewById(R.id.ohp6Btn);
        final Button ohp7Btn = (Button) findViewById(R.id.ohp7Btn);
        final Button ohp8Btn = (Button) findViewById(R.id.ohp8Btn);

        if (oneRepMax > 0) {
            if(!isKg) {
                ohp1Btn.setText(String.format("%s\nx6", (int) weightCalc.calculateOhp1(oneRepMax)));
                ohp2Btn.setText(String.format("%s\nx5", (int) weightCalc.calculateOhp2(oneRepMax)));
                ohp3Btn.setText(String.format("%s\nx3", (int) weightCalc.calculateOhp3(oneRepMax)));
                ohp4Btn.setText(String.format("%s\nx5", (int) weightCalc.calculateOhp4(oneRepMax)));
                ohp5Btn.setText(String.format("%s\nx7", (int) weightCalc.calculateOhp5(oneRepMax)));
                ohp6Btn.setText(String.format("%s\nx4", (int) weightCalc.calculateOhp6(oneRepMax)));
                ohp7Btn.setText(String.format("%s\nx6", (int) weightCalc.calculateOhp7(oneRepMax)));
                ohp8Btn.setText(String.format("%s\nx8", (int) weightCalc.calculateOhp8(oneRepMax)));
            }
            else {

                ohp1Btn.setText(String.format("%s\nx6", weightCalc.calculateOhp1(oneRepMax)));
                ohp2Btn.setText(String.format("%s\nx5", weightCalc.calculateOhp2(oneRepMax)));
                ohp3Btn.setText(String.format("%s\nx3", weightCalc.calculateOhp3(oneRepMax)));
                ohp4Btn.setText(String.format("%s\nx5", weightCalc.calculateOhp4(oneRepMax)));
                ohp5Btn.setText(String.format("%s\nx7", weightCalc.calculateOhp5(oneRepMax)));
                ohp6Btn.setText(String.format("%s\nx4", weightCalc.calculateOhp6(oneRepMax)));
                ohp7Btn.setText(String.format("%s\nx6", weightCalc.calculateOhp7(oneRepMax)));
                ohp8Btn.setText(String.format("%s\nx8", weightCalc.calculateOhp8(oneRepMax)));
            }

        }
    }

    public void setBenchRepMaxPref(String benchRepMax) {
        SharedPreferences.Editor sharedPrefEditor = getSharedPreferences(getString(R.string.oneRepMaxPrefKey), 0).edit();
        sharedPrefEditor.putString(getString(R.string.bench1RMStr), benchRepMax);
        sharedPrefEditor.apply();
        sharedPrefEditor.commit();
    }
    public void setOhpRepMaxPref(String ohpRepMax) {
        SharedPreferences.Editor sharedPrefEditor = getSharedPreferences(getString(R.string.oneRepMaxPrefKey), 0).edit();
        sharedPrefEditor.putString(getString(R.string.ohp1RMStr), ohpRepMax);
        sharedPrefEditor.apply();
        sharedPrefEditor.commit();
    }
}
