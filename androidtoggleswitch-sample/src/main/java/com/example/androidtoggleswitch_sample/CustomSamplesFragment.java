package com.example.androidtoggleswitch_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import belka.us.androidtoggleswitch.widgets.MultipleToggleSwitch;
import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

/**
 * Created by lorenzorigato on 16/06/2017.
 */

public class CustomSamplesFragment extends BaseSamplesFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_samples_fragment, container, false);

        final ToggleSwitch matchParentWidthToggleSwitch = (ToggleSwitch) view.findViewById(R.id.matchParentWidthToggleSwitch);
        final ToggleSwitch customColorsToggleSwitch = (ToggleSwitch) view.findViewById(R.id.customColorsToggleSwitch);
        final ToggleSwitch customSizesToggleSwitch = (ToggleSwitch) view.findViewById(R.id.customSizesToggleSwitch);
        final MultipleToggleSwitch customBordersToggleSwitch = (MultipleToggleSwitch) view.findViewById(R.id.customBordersToggleSwitch);
        final ToggleSwitch noSeparatorToggleSwitch = (ToggleSwitch) view.findViewById(R.id.noSeparatorToggleSwitch);


        matchParentWidthToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int position) {
                String[] operators = getResources().getStringArray(R.array.operators);
                showClickedToast(operators, position);
            }
        });

        customColorsToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int pos) {
                showClickedToast(customColorsToggleSwitch, pos);
            }
        });

        customSizesToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int pos) {
                showClickedToast(customSizesToggleSwitch, pos);
            }
        });

        noSeparatorToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int pos) {
                showClickedToast(noSeparatorToggleSwitch, pos);
            }
        });

        customBordersToggleSwitch.setOnChangeListener(new MultipleToggleSwitch.OnChangeListener() {
            @Override
            public void onMultipleToggleSwitchChanged(int position, boolean checked) {
                String[] planets = getResources().getStringArray(R.array.planets);
                String label = planets[position];
                Toast.makeText(getActivity(),
                        label + "[" + position + "] => " +  checked,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
