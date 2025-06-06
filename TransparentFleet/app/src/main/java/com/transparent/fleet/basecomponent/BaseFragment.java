package com.transparent.fleet.basecomponent;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.transparent.fleet.callbackClick.OpenFragment;


public class BaseFragment extends Fragment {

    public OpenFragment openFrag;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OpenFragment) {
            openFrag = (OpenFragment) context;
        } else {
            throw new RuntimeException(context.toString()+ " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        openFrag = null;
    }



}