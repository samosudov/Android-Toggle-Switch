package com.llollox.androidprojects.androidtoggleswitch_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llollox.androidprojects.androidtoggleswitch_sample.databinding.FragmentBasicSamplesBinding;

import com.llollox.androidprojects.androidtoggleswitch.widgets.MultipleToggleSwitch;
import com.llollox.androidprojects.androidtoggleswitch.widgets.ToggleSwitch;

/**
 * Created by lorenzorigato on 16/06/2017.
 */

public class BasicSamplesFragment extends BaseSamplesFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBasicSamplesBinding binding = FragmentBasicSamplesBinding.inflate(inflater);

        binding.twoItemsToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int position) {
                String[] array = {getString(R.string.apple), getString(R.string.lemon)};
                showToggleChangeToast(array, position);
            }
        });

        binding.threeItemsToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int position) {
                String[] array = {getString(R.string.apple), getString(R.string.orange), getString(R.string.lemon)};
                showToggleChangeToast(array, position);
            }
        });

        binding.nItemsToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int position) {
                String[] planets = getResources().getStringArray(R.array.planets);
                showToggleChangeToast(planets, position);
            }
        });


        binding.multipleToggleSwitch.setOnChangeListener(new MultipleToggleSwitch.OnChangeListener() {
            @Override
            public void onMultipleToggleSwitchChanged(int position, boolean checked) {
                String[] planets = getResources().getStringArray(R.array.planets);
                showMultipleToggleChangeToast(planets, position, checked);
            }
        });

        return binding.getRoot();
    }
}
