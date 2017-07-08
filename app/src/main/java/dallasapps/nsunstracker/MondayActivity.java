package dallasapps.nsunstracker;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);


        final Vibrator vibr = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        // INITIATE BUTTONS

        final Button bench1Btn = (Button) findViewById(R.id.bench1Btn);
        final Button bench2Btn = (Button) findViewById(R.id.bench2Btn);
        final Button bench3Btn = (Button) findViewById(R.id.bench3Btn);
        final Button bench4Btn = (Button) findViewById(R.id.bench4Btn);
        final Button bench5Btn = (Button) findViewById(R.id.bench5Btn);
        final Button bench6Btn = (Button) findViewById(R.id.bench6Btn);
        final Button bench7Btn = (Button) findViewById(R.id.bench7Btn);
        final Button bench8Btn = (Button) findViewById(R.id.bench8Btn);
        final Button bench9Btn = (Button) findViewById(R.id.bench9Btn);

        final Button ohp1Btn = (Button) findViewById(R.id.ohp1Btn);
        final Button ohp2Btn = (Button) findViewById(R.id.ohp2Btn);
        final Button ohp3Btn = (Button) findViewById(R.id.ohp3Btn);
        final Button ohp4Btn = (Button) findViewById(R.id.ohp4Btn);
        final Button ohp5Btn = (Button) findViewById(R.id.ohp5Btn);
        final Button ohp6Btn = (Button) findViewById(R.id.ohp6Btn);
        final Button ohp7Btn = (Button) findViewById(R.id.ohp7Btn);
        final Button ohp8Btn = (Button) findViewById(R.id.ohp8Btn);

        final Button addAccessoryBtn = (Button) findViewById(R.id.addAccessoryBtn);

        final EditText bench1RM = (EditText) findViewById(R.id.bench1RMEditNumber);
        final EditText ohp1RM = (EditText) findViewById(R.id.ohp1RMEditNumber);




    }
}
