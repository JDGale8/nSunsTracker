package dallasapps.nsunstracker;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MondayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);


        final Vibrator vibr = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        
    }
}
