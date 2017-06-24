package com.example.androidtoggleswitch_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lorenzorigato on 16/06/2017.
 */

public class BasicSamplesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basic_samples_fragment, container, false);

//        final ToggleSwitch twoItemsToggleSwitch = (ToggleSwitch) view.findViewById(R.id.twoItemsToggleSwitch);
//        final ToggleSwitch threeItemsToggleSwitch = (ToggleSwitch) view.findViewById(R.id.threeItemsToggleSwitch);
//        final ToggleSwitch nItemsToggleSwitch = (ToggleSwitch) view.findViewById(R.id.nItemsToggleSwitch);
//        final MultipleToggleSwitch multipleToggleSwitch = (MultipleToggleSwitch) view.findViewById(R.id.multipleToggleSwitch);
//
//        twoItemsToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
//            @Override
//            public void onToggleSwitchChanged(int pos) {
//                showClickedToast(twoItemsToggleSwitch, pos);
//            }
//        });
//
//        threeItemsToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
//            @Override
//            public void onToggleSwitchChanged(int pos) {
//                showClickedToast(threeItemsToggleSwitch, pos);
//            }
//        });
//
//        nItemsToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
//            @Override
//            public void onToggleSwitchChanged(int pos) {
//                showClickedToast(nItemsToggleSwitch, pos);
//            }
//        });
//
//
//        multipleToggleSwitch.setOnChangeListener(new MultipleToggleSwitch.OnChangeListener() {
//            @Override
//            public void onMultipleToggleSwitchChanged(int pos, boolean checked) {
//                String label = multipleToggleSwitch.getLabels().get(pos);
//                Toast.makeText(getActivity(),
//                        label + "[" + pos + "] => " +  checked,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }

//    private void showClickedToast(ToggleSwitch toggleSwitch, int pos) {
//        Toast.makeText(getActivity(),
//                "Checked: " + toggleSwitch.getLabels().get(pos),
//                Toast.LENGTH_SHORT).show();
//    }
}
