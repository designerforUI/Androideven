package com.example.plenkuing.androidevent;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PlenKuing on 2018/5/5.
 */

public class CallbackFrag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.callback_fg,container,false);
        return view;
    }
}
