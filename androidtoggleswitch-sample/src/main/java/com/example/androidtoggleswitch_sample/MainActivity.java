package com.example.androidtoggleswitch_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import belka.us.androidtoggleswitch.widgets.MultipleToggleSwitch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_main);

        final MultipleToggleSwitch toggleSwitch = (MultipleToggleSwitch) findViewById(R.id.toggleSwitch);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSwitch.setSeparatorVisible(true);
                toggleSwitch.reDraw();
            }
        });
//        ToggleSwitch toggleSwitch = (ToggleSwitch) findViewById(R.id.n_items_toggle_switch);
//        ArrayList<String> labels = new ArrayList<>();
//        labels.add("A");
//        labels.add("B");
//        labels.add("C");
//        labels.add("D");
//        toggleSwitch.setLabels(labels);
    }
}
