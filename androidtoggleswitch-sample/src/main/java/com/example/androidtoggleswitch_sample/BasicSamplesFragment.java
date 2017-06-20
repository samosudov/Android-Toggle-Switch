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

public class BasicSamplesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basic_samples_fragment, container, false);

        final ToggleSwitch twoItemsToggleSwitch = (ToggleSwitch) view.findViewById(R.id.twoItemsToggleSwitch);
        final ToggleSwitch threeItemsToggleSwitch = (ToggleSwitch) view.findViewById(R.id.threeItemsToggleSwitch);
        final ToggleSwitch nItemsToggleSwitch = (ToggleSwitch) view.findViewById(R.id.nItemsToggleSwitch);
        final MultipleToggleSwitch multipleToggleSwitch = (MultipleToggleSwitch) view.findViewById(R.id.multipleToggleSwitch);

        twoItemsToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int position) {
                showClickedToast(twoItemsToggleSwitch, position);
            }
        });

        threeItemsToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int position) {
                showClickedToast(threeItemsToggleSwitch, position);
            }
        });

        nItemsToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int position) {
                showClickedToast(nItemsToggleSwitch, position);
            }
        });


        multipleToggleSwitch.setOnChangeListener(new MultipleToggleSwitch.OnChangeListener() {
            @Override
            public void onMultipleToggleSwitchChanged(int position, boolean checked) {
                String label = multipleToggleSwitch.getLabels().get(position);
                Toast.makeText(getActivity(),
                        label + "[" + position + "] => " +  checked,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void showClickedToast(ToggleSwitch toggleSwitch, int position) {
        Toast.makeText(getActivity(),
                "Checked: " + toggleSwitch.getLabels().get(position),
                Toast.LENGTH_SHORT).show();
    }
}
