package org.demo.android;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.demo.android.utils.AppConstants;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = AppConstants.DEVELOPER_LOGGER_TAG.getStrValue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: started...");
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);

        TextView text = new TextView(this);
        text.setText("Gulam Sabir Shahbaz Khan");
        text.setHeight(50);
        setContentView(text);

        Log.i(TAG, "onCreate: finished...");
    }
}
