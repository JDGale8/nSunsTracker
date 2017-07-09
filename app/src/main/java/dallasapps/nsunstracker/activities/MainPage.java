package dallasapps.nsunstracker.activities;

import android.content.Intent;
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

    private EditText benchORMEditText;
    private EditText ohpORMEditText;
    private EditText squatORMEditText;
    private EditText deadliftORMEditText;

    private Vibrator vibr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

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

        benchORMEditText = (EditText) findViewById(R.id.bench1RMAmount);
        ohpORMEditText = (EditText) findViewById(R.id.ohp1RMAmount);
        squatORMEditText = (EditText) findViewById(R.id.squat1RMAmount);
        deadliftORMEditText = (EditText) findViewById(R.id.deadLift1RMAmount);

        vibr = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        getSharedPrefs();
        setORMAmounts();

        startWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mondayActivityIntent = new Intent(MainPage.this, MondayActivity.class);
                startActivity(mondayActivityIntent);
                vibr.vibrate(28);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSharedPrefs();
        setORMAmounts();
    }


    public void getSharedPrefs() {

        SharedPreferences sharedORMPrefs = getSharedPreferences(getString(R.string.oneRepMaxPrefKey), 0);
        SharedPreferences sharedGeneralPrefs = getSharedPreferences(getString(R.string.generalPrefKey), 0);
        if (sharedORMPrefs != null) {
            this.benchORM = sharedORMPrefs.getString(getString(R.string.bench1RMStr), null);
            this.ohpORM = sharedORMPrefs.getString(getString(R.string.ohp1RMStr), null);
            this.squatORM = sharedORMPrefs.getString(getString(R.string.squat1RMStr), null);
            this.deadliftORM = sharedORMPrefs.getString(getString(R.string.deadlift1RMStr), null);
        }
        if (sharedGeneralPrefs != null) {
            this.isKg = sharedGeneralPrefs.getBoolean(getString(R.string.isKGStr), false);
            if(isKg) {
                this.incAmount = 2.5;
            }
            else {
                this.incAmount = 5;
            }
        }
    }


    private void setORMAmounts() {

        if (benchORM != null) {
            if (benchORM.equals("0.0")) {
                benchORMEditText.setText("");
            }
            else{
                benchORMEditText.setText(benchORM);
            }
        }
        if (ohpORM != null) {
            if (ohpORM.equals("0.0")) {
                ohpORMEditText.setText("");
            }
            else{
                ohpORMEditText.setText(ohpORM);
            }
        }
        if (squatORM != null) {
            if (squatORM.equals("0.0")) {
                squatORMEditText.setText("");
            }
            else{
                squatORMEditText.setText(squatORM);
            }
        }
        if (deadliftORM != null) {
            if (deadliftORM.equals("0.0")) {
                deadliftORMEditText.setText("");
            }
            else{
                deadliftORMEditText.setText(deadliftORM);
            }
        }
    }

    public void increaseBench(View view) {
        if (benchORMEditText.getText().toString().equals("")) {
            benchORMEditText.setText("10");
            setBenchORMInPrefs(10);
        }
        else {
            double benchORMDouble = Double.parseDouble(benchORMEditText.getText().toString());
            benchORMEditText.setText(String.format("%s",benchORMDouble + incAmount));
            setBenchORMInPrefs(benchORMDouble + incAmount);
        }
    }

    public void decreaseBench(View view) {
        if (benchORMEditText.getText().toString().equals("")) {
            vibr.vibrate(30);
        }
        else if (Double.parseDouble(benchORMEditText.getText().toString()) <= incAmount) {
            benchORMEditText.setText("");
            setBenchORMInPrefs(0);
        }
        else {
            double ohpORMDouble = Double.parseDouble(benchORMEditText.getText().toString());
            benchORMEditText.setText(String.format("%s",ohpORMDouble - incAmount));
            setBenchORMInPrefs(ohpORMDouble - incAmount);
        }
    }


    public void increaseOhp(View view) {
        if (ohpORMEditText.getText().toString().equals("")) {
            ohpORMEditText.setText("10");
            setOhpORMInPrefs(10);
        }
        else {
            double ohpORMDouble = Double.parseDouble(ohpORMEditText.getText().toString());
            ohpORMEditText.setText(String.format("%s",ohpORMDouble + incAmount));
            setOhpORMInPrefs(ohpORMDouble + incAmount);
        }
    }

    public void decreaseOhp(View view) {
        if (ohpORMEditText.getText().toString().equals("")) {
            vibr.vibrate(30);
        }
        else if (Double.parseDouble(ohpORMEditText.getText().toString()) <= incAmount) {
            ohpORMEditText.setText("");
            setOhpORMInPrefs(0);
        }
        else {
            double ohpORMDouble = Double.parseDouble(ohpORMEditText.getText().toString());
            ohpORMEditText.setText(String.format("%s",ohpORMDouble - incAmount));
            setOhpORMInPrefs(ohpORMDouble - incAmount);
        }
    }



    public void increaseSquat(View view) {
        if (squatORMEditText.getText().toString().equals("")) {
            squatORMEditText.setText("10");
            setSquatORMInPrefs(10);
        }
        else {
            double squatORMDouble = Double.parseDouble(squatORMEditText.getText().toString());
            squatORMEditText.setText(String.format("%s",squatORMDouble + incAmount));
            setOhpORMInPrefs(squatORMDouble + incAmount);
        }
    }

    public void decreaseSquat(View view) {
        if (squatORMEditText.getText().toString().equals("")) {
            vibr.vibrate(30);
        }
        else if (Double.parseDouble(squatORMEditText.getText().toString()) <= incAmount) {
            squatORMEditText.setText("");
            setSquatORMInPrefs(0);
        }
        else {
            double squatORMDouble = Double.parseDouble(squatORMEditText.getText().toString());
            squatORMEditText.setText(String.format("%s",squatORMDouble - incAmount));
            setOhpORMInPrefs(squatORMDouble - incAmount);
        }
    }


    public void increaseDeadlift(View view) {
        if (deadliftORMEditText.getText().toString().equals("")) {
            deadliftORMEditText.setText("10");
            setDeadliftORMInPrefs(10);
        }
        else {
            double deadliftORMDouble = Double.parseDouble(deadliftORMEditText.getText().toString());
            deadliftORMEditText.setText(String.format("%s",deadliftORMDouble + incAmount));
            setDeadliftORMInPrefs(deadliftORMDouble + incAmount);
        }
    }

    public void decreaseDeadlift(View view) {
        if (deadliftORMEditText.getText().toString().equals("")) {
            vibr.vibrate(30);
        }
        else if (Double.parseDouble(deadliftORMEditText.getText().toString()) <= incAmount) {
            deadliftORMEditText.setText("");
            setDeadliftORMInPrefs(0);
        }
        else {
            double deadliftORMDouble = Double.parseDouble(deadliftORMEditText.getText().toString());
            deadliftORMEditText.setText(String.format("%s",deadliftORMDouble - incAmount));
            setDeadliftORMInPrefs(deadliftORMDouble - incAmount);
        }
    }

    public void setBenchORMInPrefs(double benchORM) {
        SharedPreferences.Editor sharedPrefEditor = getSharedPreferences(getString(R.string.oneRepMaxPrefKey), 0).edit();
        sharedPrefEditor.putString(getString(R.string.bench1RMStr), Double.toString(benchORM));
        sharedPrefEditor.apply();
        sharedPrefEditor.commit();
    }

    public void setOhpORMInPrefs(double ohpORM) {
        SharedPreferences.Editor sharedPrefEditor = getSharedPreferences(getString(R.string.oneRepMaxPrefKey), 0).edit();
        sharedPrefEditor.putString(getString(R.string.ohp1RMStr), Double.toString(ohpORM));
        sharedPrefEditor.apply();
        sharedPrefEditor.commit();
    }

    public void setSquatORMInPrefs(double squatORM) {
        SharedPreferences.Editor sharedPrefEditor = getSharedPreferences(getString(R.string.oneRepMaxPrefKey), 0).edit();
        sharedPrefEditor.putString(getString(R.string.squat1RMStr), Double.toString(squatORM));
        sharedPrefEditor.apply();
        sharedPrefEditor.commit();
    }

    public void setDeadliftORMInPrefs(double deadliftORM) {
        SharedPreferences.Editor sharedPrefEditor = getSharedPreferences(getString(R.string.oneRepMaxPrefKey), 0).edit();
        sharedPrefEditor.putString(getString(R.string.deadlift1RMStr), Double.toString(deadliftORM));
        sharedPrefEditor.apply();
        sharedPrefEditor.commit();
    }
}