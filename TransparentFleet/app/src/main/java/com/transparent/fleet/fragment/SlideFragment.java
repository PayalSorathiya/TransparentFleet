package com.transparent.fleet.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.transparent.fleet.BaseAppClass;
import com.transparent.fleet.R;
import com.transparent.fleet.model.SliderViewModel;

public class SlideFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static  String[] PAGE_TITLES = new String[]{};
    private static final int[] PAGE_IMAGE =
            new int[]{
                    R.drawable.info, R.drawable.info_red
            };
    private static final int[] PAGE_COLOR =
            new int[]{
                    R.color.darkOrange, R.color.darkRed
            };
    private SliderViewModel sliderViewModel;

    public static SlideFragment newInstance(int index) {

        SlideFragment fragment = new SlideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sliderViewModel = new SliderViewModel();
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        sliderViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.slide_fragment, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        final ImageView imageView = root.findViewById(R.id.imageView);
        sliderViewModel.getText().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer index) {
                textView.setText(PAGE_TITLES[index]);
//                textView.setTextColor(PAGE_COLOR[index]);
//                imageView.setImageResource(PAGE_IMAGE[index]);
            }
        });
        return root;
    }
}