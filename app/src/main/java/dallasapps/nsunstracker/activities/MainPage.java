package dallasapps.nsunstracker.activities;

import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dallasapps.nsunstracker.R;

public class MainPage extends AppCompatActivity {

    private static final String ONE_REP_MAX_PREFS = "";
    private double incAmount;

    private String benchORM;
    private String ohpORM;
    private String squatORM;
    private String deadliftORM;

    private boolean isKg;


    final Button changeDayProgramBtn = (Button) findViewById(R.id.changeDaysBtn);
    final Button startWorkoutBtn = (Button) findViewById(R.id.startWorkoutBtn);

    final Button plusBenchBtn = (Button) findViewById(R.id.plusBench);
    final Button minusBenchBtn = (Button) findViewById(R.id.minusBench);
    final Button plusOhpBtn = (Button) findViewById(R.id.plusOHP);
    final Button minusOhpBtn = (Button) findViewById(R.id.minusOHP);
    final Button plusSquatBtn = (Button) findViewById(R.id.plusSquat);
    final Button minusSquatBtn = (Button) findViewById(R.id.minusSquat);
    final Button plusDeadliftBtn = (Button) findViewById(R.id.plusDeadlift);
    final Button minusDeadliftBtn = (Button) findViewById(R.id.minusDeadlift);

    final EditText benchORMEditText = (EditText) findViewById(R.id.bench1RMAmount);
    final EditText ohpORMEditText = (EditText) findViewById(R.id.ohp1RMAmount);
    final EditText squatORMEditText = (EditText) findViewById(R.id.squat1RMAmount);
    final EditText deadliftORMEditText = (EditText) findViewById(R.id.deadLift1RMAmount);

    final Vibrator vibr = (Vibrator) getSystemService(VIBRATOR_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        getSharedPrefs();
    }


    public void getSharedPrefs() {

        SharedPreferences sharedORMPrefs = getSharedPreferences(getString(R.string.oneRepMaxPrefKey), MODE_PRIVATE);
        SharedPreferences sharedGeneralPrefs = getSharedPreferences(getString(R.string.generalPrefKey), MODE_PRIVATE);
        if (sharedORMPrefs != null) {
            this.benchORM = sharedORMPrefs.getString(getString(R.string.bench1RMStr), null);
            this.ohpORM = sharedORMPrefs.getString(getString(R.string.ohp1RMStr), null);
            this.squatORM = sharedORMPrefs.getString(getString(R.string.squat1RMStr), null);
            this.deadliftORM = sharedORMPrefs.getString(getString(R.string.deadlift1RMStr), null);
        }
        if (sharedGeneralPrefs != null) {
            this.isKg = sharedGeneralPrefs.getBoolean(getString(R.string.isKGStr), false);
        }
    }


    private void setORMAmounts() {

        if (benchORM != null) {
            benchORMEditText.setText(benchORM);
        }
        if (ohpORM != null) {
            ohpORMEditText.setText(ohpORM);
        }
        if (squatORM != null) {
            squatORMEditText.setText(squatORM);
        }
        if (deadliftORM != null) {
            deadliftORMEditText.setText(deadliftORM);
        }
    }

    public void increaseBench(View view) {
        if (benchORMEditText.toString().equals("")) {
            benchORMEditText.setText("10");
        }
        else {
            double benchORMDouble = Double.parseDouble(benchORMEditText.toString());
            benchORMEditText.setText(String.format("%s",benchORMDouble + incAmount));
        }
    }

    public void decreaseBench(View view) {
        if (benchORMEditText.toString().equals("")) {
            vibr.vibrate(30);
        }
        else if (Double.parseDouble(ohpORMEditText.toString()) < incAmount) {
            ohpORMEditText.setText("");
        }
        else {
            double benchORMDouble = Double.parseDouble(benchORMEditText.toString());
            benchORMEditText.setText(String.format("%s",benchORMDouble - incAmount));
        }
    }


    public void increaseOhp(View view) {
        if (ohpORMEditText.toString().equals("")) {
            ohpORMEditText.setText("10");
        }
        else {
            double ohpORMDouble = Double.parseDouble(ohpORMEditText.toString());
            ohpORMEditText.setText(String.format("%s",ohpORMDouble + incAmount));
        }
    }

    public void decreaseOhp(View view) {
        if (ohpORMEditText.toString().equals("")) {
            vibr.vibrate(30);
        }
        else if (Double.parseDouble(ohpORMEditText.toString()) < incAmount) {
            ohpORMEditText.setText("");
        }
        else {
            double ohpORMDouble = Double.parseDouble(ohpORMEditText.toString());
            ohpORMEditText.setText(String.format("%s",ohpORMDouble - incAmount));
        }
    }

}