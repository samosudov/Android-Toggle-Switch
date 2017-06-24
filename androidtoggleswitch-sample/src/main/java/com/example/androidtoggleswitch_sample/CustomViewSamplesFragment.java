package com.example.androidtoggleswitch_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidtoggleswitch_sample.databinding.CustomViewSamplesFragmentBinding;

/**
 * Created by rigatol on 23/06/2017.
 */

public class CustomViewSamplesFragment extends BaseSamplesFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CustomViewSamplesFragmentBinding binding = CustomViewSamplesFragmentBinding.inflate(inflater);

        return binding.getRoot();
    }
}
