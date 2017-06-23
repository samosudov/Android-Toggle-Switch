package com.example.androidtoggleswitch_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

/**
 * Created by lorenzorigato on 20/06/2017.
 */

public class MatchParentWidthHeightFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_parent_width_height_fragment, container, false);

        final ToggleSwitch toggleSwitch = (ToggleSwitch) view.findViewById(R.id.matchParentWidthToggleSwitch);
        toggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int position) {
                Toast.makeText(getActivity(),
                        "Checked: " + toggleSwitch.getLabels().get(position),
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
