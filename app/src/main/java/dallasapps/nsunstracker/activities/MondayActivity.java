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
public class MondayActivity extends AppCompatActivity {

    private double benchOneRepMax = 0;
    private double ohpOneRepMax = 0;
    private boolean isKg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);

        final Vibrator vibr = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // INITIATE BUTTONS
        final Button addAccessoryBtn = (Button) findViewById(R.id.addAccessoryBtn);

        final EditText bench1RMEditText = (EditText) findViewById(R.id.bench1RMEditNumber);
        final EditText ohp1RMEditText = (EditText) findViewById(R.id.ohp1RMEditNumber);

        getWeightUnitFromSharedPrefs();

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
                bench1RMEditText.setText("");
            }
            else {
                bench1RMEditText.setText(formatWeight(bench1RM, isKg));
                this.benchOneRepMax = Double.parseDouble(bench1RM);
            }
        }
        if (ohp1RM != null) {
            if (ohp1RM.equals("0.0")) {
                ohp1RMEditText.setText("");
            }
            else {
                ohp1RMEditText.setText(formatWeight(ohp1RM, isKg));
                this.ohpOneRepMax = Double.parseDouble(ohp1RM);
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
            bench1Btn.setText(String.format("%s\nx8",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.65), isKg)));
            bench2Btn.setText(String.format("%s\nx6",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.75), isKg)));
            bench3Btn.setText(String.format("%s\nx4",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.85), isKg)));
            bench4Btn.setText(String.format("%s\nx4",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.85), isKg)));
            bench5Btn.setText(String.format("%s\nx4",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.85), isKg)));
            bench6Btn.setText(String.format("%s\nx5",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.80), isKg)));
            bench7Btn.setText(String.format("%s\nx6",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.75), isKg)));
            bench8Btn.setText(String.format("%s\nx7",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.70), isKg)));
            bench9Btn.setText(String.format("%s\nx8+", formatWeight(weightCalc.calculateWeight(oneRepMax, 0.65), isKg)));
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
            ohp1Btn.setText(String.format("%s\nx6",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.50), isKg)));
            ohp2Btn.setText(String.format("%s\nx5",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.60), isKg)));
            ohp3Btn.setText(String.format("%s\nx4",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.70), isKg)));
            ohp4Btn.setText(String.format("%s\nx5",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.70), isKg)));
            ohp5Btn.setText(String.format("%s\nx7",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.70), isKg)));
            ohp6Btn.setText(String.format("%s\nx4",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.70), isKg)));
            ohp7Btn.setText(String.format("%s\nx6",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.70), isKg)));
            ohp8Btn.setText(String.format("%s\nx8",  formatWeight(weightCalc.calculateWeight(oneRepMax, 0.70), isKg)));
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

    public void getWeightUnitFromSharedPrefs() {
        SharedPreferences sharedPrefs = getSharedPreferences(getString(R.string.generalPrefKey), 0);
        if (sharedPrefs != null) {
            this.isKg = sharedPrefs.getBoolean(getString(R.string.isKGStr), false);
        }
    }
}
