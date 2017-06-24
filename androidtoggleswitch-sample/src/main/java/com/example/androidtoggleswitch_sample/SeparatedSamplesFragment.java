package com.example.androidtoggleswitch_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidtoggleswitch_sample.databinding.SeparatedSamplesFragmentBinding;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

/**
 * Created by lorenzorigato on 22/06/2017.
 */

public class SeparatedSamplesFragment extends BaseSamplesFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SeparatedSamplesFragmentBinding binding = SeparatedSamplesFragmentBinding.inflate(inflater);

        binding.operatorsSeparatedToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int position) {
                String[] operators = getResources().getStringArray(R.array.operators);
                showToggleChangeToast(operators, position);
            }
        });

        return binding.getRoot();
    }
}
