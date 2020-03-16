package com.example.hw711datasynchronization;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnSynchronization = findViewById(R.id.btnSynchronization);
        btnSynchronization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SYNC);

                GregorianCalendar now = new GregorianCalendar();
                int curYear = now.get(Calendar.YEAR);
                int curMonth = now.get(Calendar.MONTH);
                int curDay = now.get(Calendar.DATE);

                GregorianCalendar morningStart = new GregorianCalendar();
                morningStart.set(curYear, curMonth, curDay, 6, 0);

                GregorianCalendar morningEnd = new GregorianCalendar();
                morningEnd.set(curYear, curMonth, curDay, 14, 0);

                GregorianCalendar afternoonEnd = new GregorianCalendar();
                afternoonEnd.set(curYear, curMonth, curDay, 15, 0);

                if (now.compareTo(morningStart) >= 0 && now.compareTo(morningEnd) < 0) {
                    intent.setData(Uri.parse("http://morning"));
                } else if (now.compareTo(morningEnd) >= 0 && now.compareTo(afternoonEnd) < 0) {
                    intent.setData(Uri.parse("http://afternoon"));
                } else {
                    intent.setData(Uri.parse("http://evening"));
                }

                startActivity(intent);

            }
        });
    }
}
