package com.example.androidtoggleswitch_sample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtoggleswitch_sample.databinding.CustomViewSamplesFragmentBinding;

import org.jetbrains.annotations.NotNull;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import belka.us.androidtoggleswitch.widgets.ToggleSwitchButton;

/**
 * Created by rigatol on 23/06/2017.
 */

public class CustomViewSamplesFragment extends BaseSamplesFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CustomViewSamplesFragmentBinding binding = CustomViewSamplesFragmentBinding.inflate(inflater);

        binding.imageTextToggleSwitch.setView(R.layout.image_text_toggle_button, 2,
                new ToggleSwitchButton.ViewDecorator() {
                    @Override
                    public void decorate(@NotNull View view, int position) {
                        TextView textView   = (TextView) view.findViewById(R.id.text_view);
                        textView.setText(getLabelText(position));

                        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
                        imageView.setImageDrawable(getDrawable(position));
                    }
                },
        new ToggleSwitchButton.ViewDecorator() {
            @Override
            public void decorate(@NotNull View view, int position) {
                TextView textView   = (TextView) view.findViewById(R.id.text_view);
                textView.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.white));

                ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
                imageView.setColorFilter(ContextCompat.getColor(getActivity(), android.R.color.white));
            }
        }, new ToggleSwitchButton.ViewDecorator() {
            @Override
            public void decorate(@NotNull View view, int position) {

                TextView textView   = (TextView) view.findViewById(R.id.text_view);
                textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray));

                ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
                imageView.setColorFilter(ContextCompat.getColor(getActivity(), R.color.gray));
            }
        });

        binding.imageTextToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int position) {
                String[] labels = {getLabelText(0), getLabelText(1)};
                showToggleChangeToast(labels, position);
            }
        });

        return binding.getRoot();
    }

    private String getLabelText(int position) {
        switch (position) {
            case 0: return getString(R.string.camera);
            case 1: return getString(R.string.gallery);
            default: throw new RuntimeException("Unknown position");
        }
    }

    private Drawable getDrawable(int position) {
        switch (position) {
            case 0: return ContextCompat.getDrawable(getActivity(), R.drawable.ic_camera_alt_black_24dp);
            case 1: return ContextCompat.getDrawable(getActivity(), R.drawable.ic_camera_roll_black_24dp);
            default: throw new RuntimeException("Unknown position");
        }
    }
}
