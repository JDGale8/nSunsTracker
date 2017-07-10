package dallasapps.nsunstracker.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dallasapps.nsunstracker.R;

import static dallasapps.nsunstracker.util.StringFormatter.formatWeight;

public class MainPage extends AppCompatActivity {

    private double incAmount;

    private String benchORM;
    private String ohpORM;
    private String squatORM;
    private String deadliftORM;

    private boolean isKg = false;
    private String weightUnit;

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
        final Button plusOhpBtn = (Button) findViewById(R.id.plusOhp);
        final Button minusOhpBtn = (Button) findViewById(R.id.minusOhp);
        final Button plusSquatBtn = (Button) findViewById(R.id.plusSquat);
        final Button minusSquatBtn = (Button) findViewById(R.id.minusSquat);
        final Button plusDeadliftBtn = (Button) findViewById(R.id.plusDeadlift);
        final Button minusDeadliftBtn = (Button) findViewById(R.id.minusDeadlift);

        benchORMEditText = (EditText) findViewById(R.id.bench1RMAmount);
        ohpORMEditText = (EditText) findViewById(R.id.ohp1RMAmount);
        squatORMEditText = (EditText) findViewById(R.id.squat1RMAmount);
        deadliftORMEditText = (EditText) findViewById(R.id.deadlift1RMAmount);

        vibr = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        getSharedPrefs();
        setORMAmounts();
        setWeightUnits();


        startWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mondayActivityIntent = new Intent(MainPage.this, TuesdayActivity.class);
                startActivity(mondayActivityIntent);
                vibr.vibrate(28);
            }
        });


        benchORMEditText.addTextChangedListener(new TextWatcher() {
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
                    setBenchORMInPrefs(Double.parseDouble(editable.toString()));
                }
            }
        });


        ohpORMEditText.addTextChangedListener(new TextWatcher() {
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
                    setOhpORMInPrefs(Double.parseDouble(editable.toString()));
                }
            }
        });

        squatORMEditText.addTextChangedListener(new TextWatcher() {
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
                    setSquatORMInPrefs(Double.parseDouble(editable.toString()));
                }
            }
        });

        deadliftORMEditText.addTextChangedListener(new TextWatcher() {
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
                    setDeadliftORMInPrefs(Double.parseDouble(editable.toString()));
                }
            }
        });

    }
    

    private void setWeightUnits() {
        TextView benchUnit = (TextView) findViewById(R.id.benchUnit);
        TextView ohpUnit = (TextView) findViewById(R.id.ohpUnit);
        TextView squatUnit = (TextView) findViewById(R.id.squatUnit);
        TextView deadliftUnit = (TextView) findViewById(R.id.deadliftUnit);

        benchUnit.setText(weightUnit);
        ohpUnit.setText(weightUnit);
        squatUnit.setText(weightUnit);
        deadliftUnit.setText(weightUnit);
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
            if (isKg) {
                this.incAmount = 2.5;
                this.weightUnit = "kg";
            } else {
                this.incAmount = 5;
                this.weightUnit = "lbs";
            }
        }
    }


    private void setORMAmounts() {

        if (benchORM != null) {
            if (benchORM.equals("0.0")) {
                benchORMEditText.setText("");
            } else {
                benchORMEditText.setText(formatWeight(benchORM, isKg));
            }
        }
        if (ohpORM != null) {
            if (ohpORM.equals("0.0")) {
                ohpORMEditText.setText("");
            } else {
                ohpORMEditText.setText(formatWeight(ohpORM, isKg));
            }
        }
        if (squatORM != null) {
            if (squatORM.equals("0.0")) {
                squatORMEditText.setText("");
            } else {
                squatORMEditText.setText(formatWeight(squatORM, isKg));
            }
        }
        if (deadliftORM != null) {
            if (deadliftORM.equals("0.0")) {
                deadliftORMEditText.setText("");
            } else {
                deadliftORMEditText.setText(formatWeight(deadliftORM, isKg));
            }
        }
    }

    public void increaseBench(View view) {
        if (benchORMEditText.getText().toString().equals("")) {
            benchORMEditText.setText("10");
            setBenchORMInPrefs(10);
        } else {
            double benchORMDouble = Double.parseDouble(benchORMEditText.getText().toString());
            benchORMEditText.setText(formatWeight(benchORMDouble + incAmount, isKg));
            setBenchORMInPrefs(benchORMDouble + incAmount);
        }
    }

    public void decreaseBench(View view) {
        if (benchORMEditText.getText().toString().equals("")) {
            vibr.vibrate(30);
        } else if (Double.parseDouble(benchORMEditText.getText().toString()) <= incAmount) {
            benchORMEditText.setText("");
            setBenchORMInPrefs(0);
        } else {
            double benchORMDouble = Double.parseDouble(benchORMEditText.getText().toString());
            benchORMEditText.setText(formatWeight(benchORMDouble - incAmount, isKg));
            setBenchORMInPrefs(benchORMDouble - incAmount);
        }
    }


    public void increaseOhp(View view) {
        if (ohpORMEditText.getText().toString().equals("")) {
            ohpORMEditText.setText("10");
            setOhpORMInPrefs(10);
        } else {
            double ohpORMDouble = Double.parseDouble(ohpORMEditText.getText().toString());
            ohpORMEditText.setText(formatWeight(ohpORMDouble + incAmount, isKg));
            setOhpORMInPrefs(ohpORMDouble + incAmount);
        }
    }

    public void decreaseOhp(View view) {
        if (ohpORMEditText.getText().toString().equals("")) {
            vibr.vibrate(30);
        } else if (Double.parseDouble(ohpORMEditText.getText().toString()) <= incAmount) {
            ohpORMEditText.setText("");
            setOhpORMInPrefs(0);
        } else {
            double ohpORMDouble = Double.parseDouble(ohpORMEditText.getText().toString());
            ohpORMEditText.setText(formatWeight(ohpORMDouble - incAmount, isKg));
            setOhpORMInPrefs(ohpORMDouble - incAmount);
        }
    }


    public void increaseSquat(View view) {
        if (squatORMEditText.getText().toString().equals("")) {
            squatORMEditText.setText("10");
            setSquatORMInPrefs(10);
        } else {
            double squatORMDouble = Double.parseDouble(squatORMEditText.getText().toString());
            squatORMEditText.setText(formatWeight(squatORMDouble + incAmount, isKg));
            setOhpORMInPrefs(squatORMDouble + incAmount);
        }
    }

    public void decreaseSquat(View view) {
        if (squatORMEditText.getText().toString().equals("")) {
            vibr.vibrate(30);
        } else if (Double.parseDouble(squatORMEditText.getText().toString()) <= incAmount) {
            squatORMEditText.setText("");
            setSquatORMInPrefs(0);
        } else {
            double squatORMDouble = Double.parseDouble(squatORMEditText.getText().toString());
            squatORMEditText.setText(formatWeight(squatORMDouble - incAmount, isKg));
            setOhpORMInPrefs(squatORMDouble - incAmount);
        }
    }


    public void increaseDeadlift(View view) {
        if (deadliftORMEditText.getText().toString().equals("")) {
            deadliftORMEditText.setText("10");
            setDeadliftORMInPrefs(10);
        } else {
            double deadliftORMDouble = Double.parseDouble(deadliftORMEditText.getText().toString());
            deadliftORMEditText.setText(formatWeight(deadliftORMDouble + incAmount, isKg));
            setDeadliftORMInPrefs(deadliftORMDouble + incAmount);
        }
    }

    public void decreaseDeadlift(View view) {
        if (deadliftORMEditText.getText().toString().equals("")) {
            vibr.vibrate(30);
        } else if (Double.parseDouble(deadliftORMEditText.getText().toString()) <= incAmount) {
            deadliftORMEditText.setText("");
            setDeadliftORMInPrefs(0);
        } else {
            double deadliftORMDouble = Double.parseDouble(deadliftORMEditText.getText().toString());
            deadliftORMEditText.setText(formatWeight(deadliftORMDouble - incAmount, isKg));
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