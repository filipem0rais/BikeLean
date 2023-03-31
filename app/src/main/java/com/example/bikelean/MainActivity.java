package com.example.bikelean;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CurvedProgressBar curvedProgressBar = findViewById(R.id.curvedProgressBar);
        curvedProgressBar.setProgress(0);

        TextView currentLeanText = findViewById(R.id.currentLeanText);
        TextView maxRightLeanText = findViewById(R.id.rightLeanText);
        TextView maxLeftLeanText = findViewById(R.id.leftLeanText);

        // get edittextnumbersigned
        EditText editTextNumberSigned = findViewById(R.id.testInput);
        editTextNumberSigned.setOnEditorActionListener((v, actionId, event) -> {
            curvedProgressBar.setProgress(Float.parseFloat(v.getText().toString()));
            // set currentLeanText to current lean
            currentLeanText.setText(String.valueOf(curvedProgressBar.getProgress()));
            // set maxRightLeanText to max right lean
            maxRightLeanText.setText(String.valueOf(curvedProgressBar.getMaxRightLean()));
            // set maxLeftLeanText to max left lean
            maxLeftLeanText.setText(String.valueOf(curvedProgressBar.getMaxLeftLean()));
            return false;
        });

        // get reset button
        findViewById(R.id.button).setOnClickListener(v -> {
            curvedProgressBar.resetLean();
        });
    }
}