package com.example.androidtoggleswitch_sample;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by lorenzorigato on 24/06/2017.
 */

public class BaseSamplesFragment extends Fragment {

    protected void showClickedToast(String[] array, int pos) {
        Toast.makeText(getActivity(), "Checked: " + array[pos], Toast.LENGTH_SHORT).show();
    }

}
